package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.config.AIChatProperties;
import com.azaxxc.effintrakj.effinTrak.financetools.config.AIModelManager;
import com.azaxxc.effintrakj.effinTrak.financetools.config.ChatSystemConfig;
import com.azaxxc.effintrakj.effinTrak.financetools.config.PromptRegistry;
import com.azaxxc.effintrakj.effinTrak.financetools.config.PromptTemplateService;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.AIExecutionResult;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.financetools.exceptions.*;
import com.azaxxc.effintrakj.effinTrak.financetools.guardrails.AIGuardrails;
import com.azaxxc.effintrakj.effinTrak.financetools.guardrails.AIToolPolicy;
import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.services.AIContextService;
import com.azaxxc.effintrakj.effinTrak.financetools.services.ConversationService;
import com.azaxxc.effintrakj.effinTrak.financetools.validation.AIResponseValidator;
import com.azaxxc.effintrakj.effinTrak.financetools.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatClient chatClient;
    private final FinanceTools financeTools;
    private final AIContextService aiContextService;
    private final ConversationService conversationService;
    private final AIResponseValidator responseValidator;
    private final AIGuardrails guardrails;
    private final AIToolPolicy toolPolicy;
    private final AIModelManager modelManager;
    private final AIChatProperties chatProperties;
    private final PromptTemplateService promptTemplateService;

    public ChatService(ChatModel chatModel, FinanceTools financeTools, AIContextService aiContextService,
                      ConversationService conversationService, AIResponseValidator responseValidator,
                      AIGuardrails guardrails, AIToolPolicy toolPolicy,
                      AIModelManager modelManager, AIChatProperties chatProperties,
                      PromptTemplateService promptTemplateService) {
        this.financeTools = financeTools;
        this.aiContextService = aiContextService;
        this.conversationService = conversationService;
        this.responseValidator = responseValidator;
        this.guardrails = guardrails;
        this.toolPolicy = toolPolicy;
        this.modelManager = modelManager;
        this.chatProperties = chatProperties;
        this.promptTemplateService = promptTemplateService;
        this.chatClient = ChatClient.builder(chatModel).build();
        logger.info("ChatService initialized with ChatModel: {}", chatModel.getClass().getSimpleName());
    }

    /**
     * Process a natural language prompt and invoke appropriate tools
     * @param prompt Natural language instruction from the user
     * @param userId The user's ID for context
     * @param conversationId Unique conversation ID for memory
     * @return Response from the AI with tool execution results
     */
    public String processPrompt(String prompt, long userId, String conversationId) {
        AIExecutionResult result = processPromptDetailed(prompt, userId, conversationId, null);
        return result.getMessage();
    }

    public AIExecutionResult processPromptDetailed(String prompt, long userId, String conversationId, String requestedModel) {
        logger.info("Processing prompt for userId: {}, conversationId: {}", userId, conversationId);
        logger.debug("Prompt content: {}", prompt);
        AIModelManager.ModelSelection modelSelection = modelManager.resolveModel(requestedModel);
        String selectedModel = modelSelection.model();
        ChatConversation conversation = null;

        try {
            // Validate user input
            ValidationResult userInputValidation = validateUserInput(prompt, userId);
            if (!userInputValidation.isValid()) {
                logger.warn("User input validation failed: {}", userInputValidation.getMessage());
                AIExecutionResult result = AIExecutionResult.failure(
                        "Invalid input: " + userInputValidation.getMessage(),
                        "INVALID_INPUT",
                        null,
                        conversationId,
                        userId,
                        selectedModel
                );
                result.setPromptProfile(promptTemplateService.getPromptProfile());
                result.setPromptVersion(promptTemplateService.getPromptVersion());
                result.addWarning(modelSelection.warning());
                saveAuditIfConversationAvailable(conversation, prompt, result);
                return result;
            }

            // Check rate limits
            AIGuardrails.RateLimitResult rateLimitResult = guardrails.checkRateLimit(userId);
            if (!rateLimitResult.isAllowed()) {
                logger.warn("Rate limit exceeded for user: {}", userId);
                throw new RateLimitExceededException(rateLimitResult.getMessage());
            }

            // Step 0: Load AI context
            logger.info("Step 0: Loading AI context with user's categories and bank accounts...");
            conversation = conversationService.getOrCreateConversation(userId, conversationId);
            String userContext = buildPromptContext(userId, conversation);
            if (ChatSystemConfig.LOG_AI_CONTEXT) {
                logger.debug("User context loaded: {}", userContext);
            }

            if (isGenericNonFinancialPrompt(prompt)) {
                String generalReply = generalAssistanceResponse();
                AIExecutionResult response = AIExecutionResult.success(
                        generalReply,
                        "GENERAL_ASSISTANCE",
                        conversationId,
                        userId,
                        selectedModel
                );
                response.setPromptProfile(promptTemplateService.getPromptProfile());
                response.setPromptVersion(promptTemplateService.getPromptVersion());
                response.addWarning(modelSelection.warning());
                saveAuditIfConversationAvailable(conversation, prompt, response);
                return response;
            }

            // Step 1: Analyze intent
            logger.info("Step 1: Analyzing user intent...");
            String intentAnalysis = analyzeIntent(prompt, userContext);
            logger.info("Intent analysis result: {}", intentAnalysis);

            // Validate intent
            ValidationResult intentValidation = responseValidator.validateIntent(intentAnalysis);
            if (!intentValidation.isValid()) {
                logger.warn("Intent validation failed: {}", intentValidation.getMessage());
                AIExecutionResult result = AIExecutionResult.failure(
                        "Unable to determine a valid operation from your request. " + intentValidation.getMessage(),
                        "INVALID_INTENT",
                        intentAnalysis,
                        conversationId,
                        userId,
                        selectedModel
                );
                result.setPromptProfile(promptTemplateService.getPromptProfile());
                result.setPromptVersion(promptTemplateService.getPromptVersion());
                result.addWarning(modelSelection.warning());
                saveAuditIfConversationAvailable(conversation, prompt, result);
                return result;
            }

            // Verify intent consistency
            if (!guardrails.isIntentReasonable(prompt, intentAnalysis)) {
                logger.warn("Detected unusual intent pattern for user {}: {}", userId, intentAnalysis);
                AIExecutionResult result = AIExecutionResult.failure(
                        "Your request seems unusual. Could you please clarify what you want to do?",
                        "INTENT_INCONSISTENT",
                        intentAnalysis,
                        conversationId,
                        userId,
                        selectedModel
                );
                result.setPromptProfile(promptTemplateService.getPromptProfile());
                result.setPromptVersion(promptTemplateService.getPromptVersion());
                result.addWarning(modelSelection.warning());
                saveAuditIfConversationAvailable(conversation, prompt, result);
                return result;
            }

            AIToolPolicy.PolicyDecision policyDecision = toolPolicy.checkOperationAllowed(intentAnalysis.toUpperCase().trim());
            if (!policyDecision.allowed()) {
                logger.warn("Policy blocked operation {} for user {}: {}", intentAnalysis, userId, policyDecision.message());
                AIExecutionResult result = AIExecutionResult.failure(
                        policyDecision.message(),
                        policyDecision.errorCode(),
                        intentAnalysis,
                        conversationId,
                        userId,
                        selectedModel
                );
                result.setPromptProfile(promptTemplateService.getPromptProfile());
                result.setPromptVersion(promptTemplateService.getPromptVersion());
                result.addWarning(modelSelection.warning());
                saveAuditIfConversationAvailable(conversation, prompt, result);
                return result;
            }

            // Step 2: Execute tool based on intent
            logger.info("Step 2: Executing tool based on intent...");
            String result = executeToolBasedOnIntent(intentAnalysis, prompt, userId, userContext);
            logger.info("Tool execution result: {}", result);

            // Step 3: Format response
            logger.info("Step 3: Formatting response...");
            String formattedResponse = chatProperties.isEnableResponseFormatting()
                    ? formatResponse(prompt, result)
                    : result;
            logger.info("Final response: {}", formattedResponse);

            AIExecutionResult response = AIExecutionResult.success(
                    formattedResponse,
                    intentAnalysis.toUpperCase().trim(),
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;

        } catch (RateLimitExceededException e) {
            logger.warn("Rate limit exceeded: {}", e.getMessage());
            AIExecutionResult response = AIExecutionResult.failure(
                    "Rate limit exceeded: " + e.getMessage(),
                    "RATE_LIMIT_EXCEEDED",
                    null,
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;
        } catch (ParameterValidationException e) {
            logger.error("Parameter validation failed: {}", e.getMessage());
            AIExecutionResult response = AIExecutionResult.failure(
                    "Parameter validation error: " + e.getMessage(),
                    "PARAMETER_VALIDATION_FAILED",
                    null,
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;
        } catch (InvalidAIResponseException e) {
            logger.error("AI response validation failed: {}", e.getMessage());
            AIExecutionResult response = AIExecutionResult.failure(
                    "Could not properly process your request. " + e.getMessage(),
                    "INVALID_AI_RESPONSE",
                    null,
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;
        } catch (HallucinationDetectedException e) {
            logger.error("Hallucination detected: {}", e.getMessage());
            AIExecutionResult response = AIExecutionResult.failure(
                    "Detected unusual response pattern. Please try again or rephrase your request.",
                    "HALLUCINATION_DETECTED",
                    null,
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;
        } catch (Exception e) {
            logger.error("Error processing prompt", e);
            logger.error("Error type: {}, message: {}", e.getClass().getName(), e.getMessage());

            String errorMsg = String.format(
                "Error: %s - %s. Check server logs for details.",
                e.getClass().getSimpleName(),
                e.getMessage() != null ? e.getMessage() : "Unknown error"
            );
            AIExecutionResult response = AIExecutionResult.failure(
                    errorMsg,
                    "UNEXPECTED_ERROR",
                    null,
                    conversationId,
                    userId,
                    selectedModel
            );
            response.setPromptProfile(promptTemplateService.getPromptProfile());
            response.setPromptVersion(promptTemplateService.getPromptVersion());
            response.addWarning(modelSelection.warning());
            saveAuditIfConversationAvailable(conversation, prompt, response);
            return response;
        }
    }

    /**
     * Validate user input before processing
     */
    private ValidationResult validateUserInput(String prompt, long userId) {
        logger.debug("Validating user input");

        if (prompt == null || prompt.trim().isEmpty()) {
            return ValidationResult.error("Prompt cannot be empty");
        }

        if (prompt.length() > chatProperties.getMaxPromptLength()) {
            return ValidationResult.error("Prompt is too long (maximum " + chatProperties.getMaxPromptLength() + " characters)");
        }

        ValidationResult userIdValidation = responseValidator.validateUserId(userId);
        if (!userIdValidation.isValid()) {
            return userIdValidation;
        }

        return ValidationResult.success();
    }

    /**
     * Analyze user intent using AI with context (uses centralized prompt)
     */
    private String analyzeIntent(String prompt, String userContext) {
        logger.debug("Calling analyzeIntent with prompt: {}", prompt);

        String intentPrompt = promptTemplateService.intentAnalysis(userContext, prompt);

        try {
            logger.debug("Sending intent analysis request to ChatClient...");
            String response = chatClient.prompt()
                    .user(intentPrompt)
                    .call()
                    .content()
                    .trim();

            // Validate response format
            ValidationResult responseValidation = responseValidator.validateAIResponseSafety(response);
            if (!responseValidation.isValid()) {
                logger.error("AI response failed safety check: {}", responseValidation.getMessage());
                throw new InvalidAIResponseException(responseValidation.getMessage());
            }

            if (ChatSystemConfig.LOG_INTENT_ANALYSIS) {
                logger.info("Intent analysis successful: {}", response);
            }
            return response;
        } catch (InvalidAIResponseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed during intent analysis", e);
            throw new AIProcessingException("Failed to analyze request intent: " + e.getMessage(), "INTENT_ANALYSIS_FAILED", true);
        }
    }

    /**
     * Extract parameters and execute the appropriate tool
     */
    private String executeToolBasedOnIntent(String intent, String prompt, long userId, String userContext) {
        intent = intent.toUpperCase().trim();
        logger.debug("Executing tool for intent: {}", intent);

        try {
            switch (intent) {
                case ChatSystemConfig.OP_ADD_EXPENSE:
                    logger.info("Handling ADD_EXPENSE");
                    return handleAddExpense(prompt, userId, userContext);
                case ChatSystemConfig.OP_ADD_INCOME:
                    logger.info("Handling ADD_INCOME");
                    return handleAddIncome(prompt, userId, userContext);
                case ChatSystemConfig.OP_UPDATE_EXPENSE:
                    logger.info("Handling UPDATE_EXPENSE");
                    return handleUpdateExpense(prompt, userId, userContext);
                case ChatSystemConfig.OP_DELETE_EXPENSE:
                    logger.info("Handling DELETE_EXPENSE");
                    return handleDeleteExpense(prompt, userId, userContext);
                case ChatSystemConfig.OP_GET_MONTHLY_SPENDING:
                    logger.info("Handling GET_MONTHLY_SPENDING");
                    return financeTools.getMonthlySpending(userId);
                case ChatSystemConfig.OP_GET_MONTHLY_INCOME:
                    logger.info("Handling GET_MONTHLY_INCOME");
                    return financeTools.getMonthlyIncome(userId);
                case ChatSystemConfig.OP_GET_FINANCIAL_SUMMARY:
                    logger.info("Handling GET_FINANCIAL_SUMMARY");
                    return financeTools.getFinancialSummary(userId);
                case ChatSystemConfig.OP_GET_SPENDING_BY_CATEGORY:
                    logger.info("Handling GET_SPENDING_BY_CATEGORY");
                    return financeTools.getSpendingByCategory(userId);
                case ChatSystemConfig.OP_CREATE_SAVINGS_GOAL:
                    logger.info("Handling CREATE_SAVINGS_GOAL");
                    return handleCreateSavingsGoal(prompt, userId, userContext);
                case ChatSystemConfig.OP_GET_SAVINGS_PROGRESS:
                    logger.info("Handling GET_SAVINGS_PROGRESS");
                    return financeTools.getSavingsProgress(userId);
                case ChatSystemConfig.OP_ADD_TO_SAVINGS:
                    logger.info("Handling ADD_TO_SAVINGS");
                    return handleAddToSavings(prompt, userId, userContext);
                case ChatSystemConfig.OP_WITHDRAW_FROM_SAVINGS:
                    logger.info("Handling WITHDRAW_FROM_SAVINGS");
                    return handleWithdrawFromSavings(prompt, userId, userContext);
                case ChatSystemConfig.OP_UPDATE_BUDGET:
                    logger.info("Handling UPDATE_BUDGET");
                    return handleUpdateBudget(prompt, userId, userContext);
                case ChatSystemConfig.OP_ADD_SUBSCRIPTION:
                    logger.info("Handling ADD_SUBSCRIPTION");
                    return handleAddSubscription(prompt, userId, userContext);
                case ChatSystemConfig.OP_GET_ACTIVE_SUBSCRIPTIONS:
                    logger.info("Handling GET_ACTIVE_SUBSCRIPTIONS");
                    return financeTools.getActiveSubscriptions(userId);
                case ChatSystemConfig.OP_CANCEL_SUBSCRIPTION:
                    logger.info("Handling CANCEL_SUBSCRIPTION");
                    return handleCancelSubscription(prompt, userId, userContext);
                case ChatSystemConfig.OP_ADD_CREDIT:
                    logger.info("Handling ADD_CREDIT");
                    return handleAddCredit(prompt, userId, userContext);
                case ChatSystemConfig.OP_GET_ACTIVE_CREDITS:
                    logger.info("Handling GET_ACTIVE_CREDITS");
                    return financeTools.getActiveCredits(userId);
                case ChatSystemConfig.OP_MAKE_CREDIT_PAYMENT:
                    logger.info("Handling MAKE_CREDIT_PAYMENT");
                    return handleMakeCreditPayment(prompt, userId, userContext);
                case ChatSystemConfig.OP_TRANSFER_MONEY:
                    logger.info("Handling TRANSFER_MONEY");
                    return handleTransferMoney(prompt, userId, userContext);
                case ChatSystemConfig.OP_CREATE_RECURRING_TRANSACTION:
                    logger.info("Handling CREATE_RECURRING_TRANSACTION");
                    return handleCreateRecurringTransaction(prompt, userId, userContext);
                case ChatSystemConfig.OP_GET_ACTIVE_RECURRING_TRANSACTIONS:
                    logger.info("Handling GET_ACTIVE_RECURRING_TRANSACTIONS");
                    return financeTools.getActiveRecurringTransactions(userId);
                case ChatSystemConfig.OP_PAUSE_RECURRING_TRANSACTION:
                    logger.info("Handling PAUSE_RECURRING_TRANSACTION");
                    return handlePauseRecurring(prompt, userId, userContext);
                case ChatSystemConfig.OP_DELETE_RECURRING_TRANSACTION:
                    logger.info("Handling DELETE_RECURRING_TRANSACTION");
                    return handleDeleteRecurring(prompt, userId, userContext);
                case ChatSystemConfig.OP_QUERY_FINANCIAL_DATA:
                    logger.info("Handling QUERY_FINANCIAL_DATA");
                    return handleQueryFinancialData(prompt, userId, userContext);
                default:
                    logger.warn("Unknown intent: {}", intent);
                    return "I couldn't determine what you wanted to do. Please try again with more details.";
            }
        } catch (ParameterValidationException | InvalidAIResponseException | HallucinationDetectedException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error executing tool for intent: {}", intent, e);
            throw new AIProcessingException("Failed to execute operation: " + e.getMessage(), "TOOL_EXECUTION_FAILED", true);
        }
    }

    /**
     * Handle expense addition with context and validation
     */
    private String handleAddExpense(String prompt, long userId, String userContext) {
        logger.debug("Extracting expense parameters from prompt: {}", prompt);
        String todayDate = aiContextService.getUserTodayDate(userId);

        try {
            if (isBatchAddRequest(prompt)) {
                String batchResult = handleBatchAddExpense(prompt, userId, userContext, todayDate);
                if (batchResult != null) {
                    return batchResult;
                }
            }

            String paramExtraction = promptTemplateService.expenseParams(userContext, prompt, todayDate);

            logger.debug("Sending parameter extraction request...");
            String params = chatClient.prompt()
                    .user(paramExtraction)
                    .call()
                    .content();

            // Validate parameter extraction response format
            ValidationResult formatValidation = responseValidator.validateParameterExtractionFormat(params);
            if (!formatValidation.isValid()) {
                logger.error("Parameter extraction format validation failed: {}", formatValidation.getMessage());
                throw new InvalidAIResponseException(formatValidation.getMessage());
            }

            if (ChatSystemConfig.LOG_PARAMETER_EXTRACTION) {
                logger.debug("Extracted parameters: {}", params);
            }

            double amount = extractNumber(params, "AMOUNT");
            long categoryId = extractNumber(params, "CATEGORY_ID").longValue();
            long bankAccountId = extractNumber(params, "BANK_ACCOUNT_ID").longValue();
            String date = extractDate(params, "DATE");
            String description = extractText(params, "DESCRIPTION");
            String paymentMethod = normalizeOptionalField(extractText(params, "PAYMENT_METHOD"));
            String paidTo = normalizeOptionalField(extractText(params, "PAID_TO"));

            logger.info("Parsed parameters - Amount: {}, Category: {}, Account: {}, Date: {}, Description: {}, PaymentMethod: {}, PaidTo: {}",
                amount, categoryId, bankAccountId, date, description, paymentMethod, paidTo);

            // Comprehensive validation of all parameters
            ValidationResult completeValidation = responseValidator.validateExpenseAdditionRequest(
                amount, categoryId, bankAccountId, date, description
            );
            if (!completeValidation.isValid()) {
                logger.error("Expense parameter validation failed: {}", completeValidation.getMessage());
                throw new ParameterValidationException(completeValidation.getMessage());
            }

            // Sanity check on financial data
            if (!guardrails.validateFinancialDataSanity(amount, categoryId, bankAccountId)) {
                logger.error("Financial data sanity check failed for user {}", userId);
                throw new ParameterValidationException("Financial data contains invalid values");
            }

            // Detect anomalies
            AIGuardrails.AnomalyResult anomalyResult = guardrails.detectAnomalies(userId, amount, "ADD_EXPENSE");
            if (anomalyResult.isAnomalyDetected()) {
                logger.warn("Anomaly detected for user {}: {}", userId, anomalyResult.getAnomalySummary());
                // Log but don't block - just inform user
            }

            String result = financeTools.addExpenseTool(amount, categoryId, bankAccountId, date, description, paymentMethod, paidTo, userId);
            logger.info("Expense added successfully: {}", result);
            return result;

        } catch (ParameterValidationException | InvalidAIResponseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to add expense", e);
            throw new AIProcessingException("Failed to add expense: " + e.getMessage(), "ADD_EXPENSE_FAILED", true);
        }
    }

    /**
     * Handle income addition with context and validation
     */
    private String handleAddIncome(String prompt, long userId, String userContext) {
        logger.debug("Extracting income parameters from prompt: {}", prompt);
        String todayDate = aiContextService.getUserTodayDate(userId);

        try {
            if (isBatchAddRequest(prompt)) {
                String batchResult = handleBatchAddIncome(prompt, userId, userContext, todayDate);
                if (batchResult != null) {
                    return batchResult;
                }
            }

            String paramExtraction = promptTemplateService.incomeParams(userContext, prompt, todayDate);

            logger.debug("Sending income parameter extraction request...");
            String params = chatClient.prompt()
                    .user(paramExtraction)
                    .call()
                    .content();

            // Validate parameter extraction response format
            ValidationResult formatValidation = responseValidator.validateParameterExtractionFormat(params);
            if (!formatValidation.isValid()) {
                logger.error("Parameter extraction format validation failed: {}", formatValidation.getMessage());
                throw new InvalidAIResponseException(formatValidation.getMessage());
            }

            if (ChatSystemConfig.LOG_PARAMETER_EXTRACTION) {
                logger.debug("Extracted income parameters: {}", params);
            }

            double amount = extractNumber(params, "AMOUNT");
            String date = extractDate(params, "DATE");
            String description = extractText(params, "DESCRIPTION");
            long categoryId = extractNumber(params, "CATEGORY_ID").longValue();
            long bankAccountId = extractNumber(params, "BANK_ACCOUNT_ID").longValue();
            String source = normalizeOptionalField(extractText(params, "SOURCE"));
            String note = normalizeOptionalField(extractText(params, "NOTE"));

            logger.info("Parsed income parameters - Amount: {}, Date: {}, Description: {}, Category: {}, Account: {}, Source: {}, Note: {}",
                amount, date, description, categoryId, bankAccountId, source, note);

            // Validate income parameters
            ValidationResult incomeValidation = responseValidator.validateIncomeAdditionRequest(
                amount, date, description
            );
            if (!incomeValidation.isValid()) {
                logger.error("Income parameter validation failed: {}", incomeValidation.getMessage());
                throw new ParameterValidationException(incomeValidation.getMessage());
            }

            // Detect anomalies
            AIGuardrails.AnomalyResult anomalyResult = guardrails.detectAnomalies(userId, amount, "ADD_INCOME");
            if (anomalyResult.isAnomalyDetected()) {
                logger.warn("Anomaly detected for user {}: {}", userId, anomalyResult.getAnomalySummary());
            }

            String result = financeTools.addIncomeTool(amount, description, source, note, bankAccountId, date, categoryId, userId);
            logger.info("Income added successfully: {}", result);
            return result;

        } catch (ParameterValidationException | InvalidAIResponseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to add income", e);
            throw new AIProcessingException("Failed to add income: " + e.getMessage(), "ADD_INCOME_FAILED", true);
        }
    }

    /**
     * Handle expense update - requires manual entry of expense ID
     */
    private String handleUpdateExpense(String prompt, long userId, String userContext) {
        if (!ChatSystemConfig.ENABLE_UPDATE_EXPENSE) {
            return "Update expense feature is currently disabled.";
        }

        logger.debug("Attempting to extract update parameters: {}", prompt);

        try {
            String paramExtraction = promptTemplateService.updateExpenseParams(userContext, prompt, aiContextService.getUserTodayDate(userId));

            String params = chatClient.prompt()
                    .user(paramExtraction)
                    .call()
                    .content();

            // Validate parameter extraction response format
            ValidationResult formatValidation = responseValidator.validateParameterExtractionFormat(params);
            if (!formatValidation.isValid()) {
                throw new InvalidAIResponseException(formatValidation.getMessage());
            }

            if (ChatSystemConfig.LOG_PARAMETER_EXTRACTION) {
                logger.debug("Extracted update parameters: {}", params);
            }

            String expenseIdStr = extractText(params, "EXPENSE_ID");

            // If no expense ID found, ask user to provide it manually
            if (expenseIdStr.isEmpty() || expenseIdStr.equals("REQUIRED")) {
                return PromptRegistry.getUpdateExpenseWarningPrompt() +
                       "\n\nTo proceed, please reply with the expense ID you want to update.";
            }

            try {
                long expenseId = Long.parseLong(expenseIdStr);

                // Validate expense ID
                ValidationResult expenseIdValidation = responseValidator.validateExpenseId(expenseId);
                if (!expenseIdValidation.isValid()) {
                    throw new ParameterValidationException(expenseIdValidation.getMessage());
                }

                String amountStr = extractText(params, "AMOUNT");
                String categoryStr = extractText(params, "CATEGORY_ID");
                String accountStr = extractText(params, "BANK_ACCOUNT_ID");
                String dateStr = extractText(params, "DATE");
                String description = extractText(params, "DESCRIPTION");

                logger.info("Parsed update parameters - ExpenseId: {}, Amount: {}, Category: {}, Account: {}, Date: {}, Description: {}",
                    expenseId, amountStr, categoryStr, accountStr, dateStr, description);

                // Call update tool with the extracted parameters
                return financeTools.updateExpenseTool(expenseId, amountStr, categoryStr, accountStr, dateStr, description, userId);
            } catch (NumberFormatException e) {
                logger.error("Invalid expense ID format: {}", expenseIdStr);
                throw new ParameterValidationException("Invalid expense ID provided. Please provide a valid numeric expense ID.");
            }
        } catch (ParameterValidationException | InvalidAIResponseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to update expense", e);
            throw new AIProcessingException("Failed to update expense: " + e.getMessage(), "UPDATE_EXPENSE_FAILED", true);
        }
    }

    /**
     * Handle expense deletion - requires manual entry of expense ID and confirmation
     */
    private String handleDeleteExpense(String prompt, long userId, String userContext) {
        if (!ChatSystemConfig.ENABLE_DELETE_EXPENSE) {
            return "Delete expense feature is currently disabled.";
        }

        logger.debug("Attempting to extract delete parameters: {}", prompt);

        try {
            String paramExtraction = promptTemplateService.deleteExpenseParams(userContext, prompt, ChatSystemConfig.getTodayDate());

            String params = chatClient.prompt()
                    .user(paramExtraction)
                    .call()
                    .content();

            // Validate parameter extraction response format
            ValidationResult formatValidation = responseValidator.validateParameterExtractionFormat(params);
            if (!formatValidation.isValid()) {
                throw new InvalidAIResponseException(formatValidation.getMessage());
            }

            if (ChatSystemConfig.LOG_PARAMETER_EXTRACTION) {
                logger.debug("Extracted delete parameters: {}", params);
            }

            String expenseIdStr = extractText(params, "EXPENSE_ID");
            String confirmation = extractText(params, "CONFIRMATION");

            // If no expense ID found, ask user to provide it manually
            if (expenseIdStr.isEmpty() || expenseIdStr.equals("REQUIRED")) {
                return PromptRegistry.getDeleteExpenseWarningPrompt() +
                       "\n\nTo proceed, please reply with: 'Delete expense ID [number]'";
            }

            // If no confirmation, ask for it
            if (!confirmation.equalsIgnoreCase("yes")) {
                return "Please confirm deletion by saying 'Yes, delete expense ID " + expenseIdStr + "'";
            }

            try {
                long expenseId = Long.parseLong(expenseIdStr);

                // Validate expense ID
                ValidationResult expenseIdValidation = responseValidator.validateExpenseId(expenseId);
                if (!expenseIdValidation.isValid()) {
                    throw new ParameterValidationException(expenseIdValidation.getMessage());
                }

                logger.info("Parsed delete parameters - ExpenseId: {}, Confirmation: {}", expenseId, confirmation);

                return financeTools.deleteExpenseTool(expenseId, userId);
            } catch (NumberFormatException e) {
                logger.error("Invalid expense ID format: {}", expenseIdStr);
                throw new ParameterValidationException("Invalid expense ID provided. Please provide a valid numeric expense ID.");
            }
        } catch (ParameterValidationException | InvalidAIResponseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to delete expense", e);
            throw new AIProcessingException("Failed to delete expense: " + e.getMessage(), "DELETE_EXPENSE_FAILED", true);
        }
    }

    private String handleCreateSavingsGoal(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.createSavingsParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        String name = extractText(params, "NAME");
        String description = extractText(params, "DESCRIPTION");
        double targetAmount = extractNumber(params, "TARGET_AMOUNT");
        String targetDate = extractDate(params, "TARGET_DATE");
        String depositFrequency = extractText(params, "DEPOSIT_FREQUENCY");
        if (depositFrequency.isBlank()) {
            depositFrequency = "MONTHLY";
        }
        return financeTools.createSavingsGoalTool(name, description, targetAmount, targetDate, depositFrequency, userId);
    }

    private String handleAddToSavings(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.addToSavingsParams(userContext, prompt))
                .call()
                .content();
        long savingsId = extractNumber(params, "SAVINGS_ID").longValue();
        double amount = extractNumber(params, "DEPOSIT_AMOUNT");
        return financeTools.addToSavingsTool(savingsId, amount, userId);
    }

    private String handleWithdrawFromSavings(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.withdrawFromSavingsParams(userContext, prompt))
                .call()
                .content();
        long savingsId = extractNumber(params, "SAVINGS_ID").longValue();
        double amount = extractNumber(params, "WITHDRAWAL_AMOUNT");
        return financeTools.withdrawFromSavingsTool(savingsId, amount, userId);
    }

    private String handleUpdateBudget(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.updateBudgetParams(userContext, prompt))
                .call()
                .content();
        long budgetId = extractNumber(params, "BUDGET_ID").longValue();
        double amount = extractNumber(params, "AMOUNT");
        String startDate = extractDate(params, "START_DATE");
        String endDate = extractDate(params, "END_DATE");
        return financeTools.updateBudgetTool(budgetId, amount, startDate, endDate, userId);
    }

    private String handleAddSubscription(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.addSubscriptionParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        String name = extractText(params, "NAME");
        String description = extractText(params, "DESCRIPTION");
        double price = extractNumber(params, "PRICE");
        String billingCycle = extractText(params, "BILLING_CYCLE");
        if (billingCycle.isBlank()) {
            billingCycle = "monthly";
        }
        String startDate = extractDate(params, "START_DATE");
        return financeTools.addSubscriptionTool(name, description, price, billingCycle, startDate, userId);
    }

    private String handleCancelSubscription(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.cancelSubscriptionParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        long subscriptionId = extractNumber(params, "SUBSCRIPTION_ID").longValue();
        String endDate = extractDate(params, "END_DATE");
        return financeTools.cancelSubscriptionTool(subscriptionId, endDate, userId);
    }

    private String handleAddCredit(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.addCreditParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        String description = extractText(params, "DESCRIPTION");
        double amount = extractNumber(params, "AMOUNT");
        String type = extractText(params, "TYPE");
        if (type.isBlank()) {
            type = "LOAN";
        }
        String dueDate = extractDate(params, "DUE_DATE");
        double interestRate = extractNumber(params, "INTEREST_RATE");
        String paymentMethod = normalizeOptionalField(extractText(params, "PAYMENT_METHOD"));
        return financeTools.addCreditTool(description, amount, type, dueDate, interestRate, paymentMethod, userId);
    }

    private String handleMakeCreditPayment(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.creditPaymentParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        long creditId = extractNumber(params, "CREDIT_ID").longValue();
        double paymentAmount = extractNumber(params, "PAYMENT_AMOUNT");
        String paymentDate = extractDate(params, "PAYMENT_DATE");
        return financeTools.makePaymentTool(creditId, paymentAmount, paymentDate, userId);
    }

    private String handleTransferMoney(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.transferParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        long fromAccountId = extractNumber(params, "FROM_ACCOUNT_ID").longValue();
        long toAccountId = extractNumber(params, "TO_ACCOUNT_ID").longValue();
        double amount = extractNumber(params, "AMOUNT");
        String description = extractText(params, "DESCRIPTION");
        String transferDate = extractDate(params, "TRANSFER_DATE");
        return financeTools.transferMoneyTool(fromAccountId, toAccountId, amount, description, transferDate, userId);
    }

    private String handleCreateRecurringTransaction(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.createRecurringParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        String type = extractText(params, "TYPE");
        String description = extractText(params, "DESCRIPTION");
        double amount = extractNumber(params, "AMOUNT");
        long categoryId = extractNumber(params, "CATEGORY_ID").longValue();
        String frequency = extractText(params, "FREQUENCY");
        String startDate = extractDate(params, "START_DATE");
        String paymentMethod = normalizeOptionalField(extractText(params, "PAYMENT_METHOD"));
        return financeTools.createRecurringTransactionTool(type, description, amount, categoryId, frequency, startDate, null, paymentMethod, userId);
    }

    private String handlePauseRecurring(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.pauseRecurringParams(userContext, prompt))
                .call()
                .content();
        long recurringId = extractNumber(params, "RECURRING_ID").longValue();
        boolean paused = extractBoolean(params, "PAUSED", true);
        return financeTools.pauseRecurringTool(recurringId, paused, userId);
    }

    private String handleDeleteRecurring(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.deleteRecurringParams(userContext, prompt))
                .call()
                .content();
        long recurringId = extractNumber(params, "RECURRING_ID").longValue();
        return financeTools.deleteRecurringTool(recurringId, userId);
    }

    private String handleQueryFinancialData(String prompt, long userId, String userContext) {
        String params = chatClient.prompt()
                .user(promptTemplateService.financialQueryParams(userContext, prompt, aiContextService.getUserTodayDate(userId)))
                .call()
                .content();
        String queryType = extractText(params, "QUERY_TYPE");
        String startDate = extractDate(params, "START_DATE");
        String endDate = extractDate(params, "END_DATE");
        String keyword = extractText(params, "KEYWORD");
        if (queryType.isBlank()) {
            queryType = "REPORT";
        }
        return financeTools.queryFinancialDataTool(userId, queryType, startDate, endDate, keyword);
    }

    /**
     * Format the result nicely for the user (uses centralized prompt)
     */
    private String formatResponse(String originalPrompt, String result) {
        logger.debug("Formatting response for result: {}", result);

        try {
            // Detect hallucinations before formatting
            if (guardrails.detectHallucination(result, originalPrompt)) {
                logger.warn("Potential hallucination detected in result");
                throw new HallucinationDetectedException("Detected unusual response pattern");
            }

            String formatPrompt = promptTemplateService.responseFormatter(originalPrompt, result);

            logger.debug("Sending format response request...");
            String formattedResponse = chatClient.prompt()
                    .user(formatPrompt)
                    .call()
                    .content();

            // Verify response consistency
            if (!guardrails.verifyResponseConsistency(originalPrompt, formattedResponse, "FORMAT")) {
                logger.warn("Response consistency check failed");
                logger.info("Returning unformatted result due to consistency check failure");
                return result;
            }

            logger.debug("Formatted response: {}", formattedResponse);
            return formattedResponse;
        } catch (HallucinationDetectedException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to format response", e);
            logger.warn("Returning unformatted result due to formatting error");
            return result;
        }
    }

    /**
     * Simple prompt without explicit conversation ID
     */
    public String processSimplePrompt(String prompt, long userId) {
        logger.info("Processing simple prompt for userId: {}", userId);
        return processPrompt(prompt, userId, ChatSystemConfig.DEFAULT_CONVERSATION_PREFIX + "-" + System.currentTimeMillis());
    }

    /**
     * Clear conversation memory
     */
    public void clearConversationMemory(String conversationId) {
        logger.info("Clearing conversation memory for: {}", conversationId);
    }

    // ==================== HELPER METHODS ====================

    private Double extractNumber(String text, String key) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.contains(key)) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    try {
                        Double value = Double.parseDouble(parts[1].trim().replaceAll("[^0-9.]", ""));
                        logger.debug("Extracted {} = {}", key, value);
                        return value;
                    } catch (NumberFormatException e) {
                        logger.warn("Failed to parse {} from: {}", key, line);
                        return key.contains("CATEGORY") || key.contains("ACCOUNT") || key.contains("BANK") ? ChatSystemConfig.DEFAULT_CATEGORY_ID : 0.0;
                    }
                }
            }
        }
        Double defaultValue = key.contains("CATEGORY") || key.contains("ACCOUNT") || key.contains("BANK") ? ChatSystemConfig.DEFAULT_CATEGORY_ID : 0.0;
        logger.debug("{} not found, using default: {}", key, defaultValue);
        return defaultValue;
    }

    private String extractDate(String text, String key) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.contains(key)) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    String date = parts[1].trim();
                    if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        logger.debug("Extracted {} = {}", key, date);
                        return date;
                    }
                }
            }
        }
        logger.debug("{} not found, using default: {}", key, ChatSystemConfig.getTodayDate());
        return ChatSystemConfig.getTodayDate();
    }

    private String extractText(String text, String key) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.contains(key)) {
                String[] parts = line.split(":", 2);
                if (parts.length > 1) {
                    String value = parts[1].trim();
                    logger.debug("Extracted {} = {}", key, value);
                    return value;
                }
            }
        }
        logger.debug("{} not found", key);
        return "";
    }

    private boolean extractBoolean(String text, String key, boolean defaultValue) {
        String value = extractText(text, key);
        if (value.isBlank()) {
            return defaultValue;
        }
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes");
    }

    private void saveAuditIfConversationAvailable(ChatConversation conversation, String prompt, AIExecutionResult result) {
        if (conversation == null) {
            return;
        }
        conversationService.saveMessage(
                conversation.getId(),
                prompt,
                result.getMessage(),
                result.getOperation() != null ? result.getOperation() : "ERROR",
                result.getOperation(),
                result.getModel(),
                result.getPromptProfile(),
                result.getPromptVersion(),
                result.getErrorCode(),
                result.isSuccess()
        );
    }

    private String handleBatchAddExpense(String prompt, long userId, String userContext, String todayDate) {
        String batchParams = chatClient.prompt()
                .user(promptTemplateService.expenseBatchParams(userContext, prompt, todayDate))
                .call()
                .content();
        List<String> lines = parseBatchLines(batchParams);
        if (lines.size() <= 1) {
            return null;
        }

        List<NewExpenseRequestDTO> dtos = new ArrayList<>();
        for (String line : lines) {
            double amount = extractNumber(line, "AMOUNT");
            long categoryId = extractNumber(line, "CATEGORY_ID").longValue();
            long bankAccountId = extractNumber(line, "BANK_ACCOUNT_ID").longValue();
            String date = extractDate(line, "DATE");
            String description = extractText(line, "DESCRIPTION");
            String paymentMethod = normalizeOptionalField(extractText(line, "PAYMENT_METHOD"));
            String paidTo = normalizeOptionalField(extractText(line, "PAID_TO"));

            NewExpenseRequestDTO dto = new NewExpenseRequestDTO();
            dto.setAmount(amount);
            dto.setCategoryId(categoryId);
            dto.setBankAccountId(bankAccountId);
            dto.setDate(date);
            dto.setDescription(description);
            dto.setPaymentMethod(paymentMethod);
            dto.setPaidTo(paidTo);
            dto.setRecurring(false);
            dtos.add(dto);
        }
        return financeTools.addExpensesBulkTool(dtos, userId);
    }

    private String handleBatchAddIncome(String prompt, long userId, String userContext, String todayDate) {
        String batchParams = chatClient.prompt()
                .user(promptTemplateService.incomeBatchParams(userContext, prompt, todayDate))
                .call()
                .content();
        List<String> lines = parseBatchLines(batchParams);
        if (lines.size() <= 1) {
            return null;
        }

        List<NewIncomeRequestDTO> dtos = new ArrayList<>();
        for (String line : lines) {
            double amount = extractNumber(line, "AMOUNT");
            String date = extractDate(line, "DATE");
            String description = extractText(line, "DESCRIPTION");
            long categoryId = extractNumber(line, "CATEGORY_ID").longValue();
            long bankAccountId = extractNumber(line, "BANK_ACCOUNT_ID").longValue();
            String source = normalizeOptionalField(extractText(line, "SOURCE"));
            String note = normalizeOptionalField(extractText(line, "NOTE"));

            NewIncomeRequestDTO dto = new NewIncomeRequestDTO();
            dto.setAmount(amount);
            dto.setDate(date);
            dto.setDescription(description);
            dto.setCategoryId(categoryId);
            dto.setBankAccountId(bankAccountId);
            dto.setSource(source);
            dto.setNote(note);
            dtos.add(dto);
        }
        return financeTools.addIncomesBulkTool(dtos, userId);
    }

    private List<String> parseBatchLines(String batchParams) {
        List<String> lines = new ArrayList<>();
        for (String rawLine : batchParams.split("\n")) {
            String line = rawLine.trim();
            if (!line.toUpperCase(Locale.ROOT).startsWith("ITEM:")) {
                continue;
            }
            String payload = line.substring("ITEM:".length()).trim();
            String[] tokens = payload.split(";");
            StringBuilder normalized = new StringBuilder();
            for (String token : tokens) {
                String[] parts = token.split("=", 2);
                if (parts.length < 2) {
                    continue;
                }
                normalized.append(parts[0].trim().toUpperCase(Locale.ROOT))
                        .append(": ")
                        .append(parts[1].trim())
                        .append("\n");
            }
            if (!normalized.isEmpty()) {
                lines.add(normalized.toString());
            }
        }
        return lines;
    }

    private boolean isBatchAddRequest(String prompt) {
        String normalized = prompt == null ? "" : prompt.toLowerCase(Locale.ROOT);
        if (!(normalized.contains("add") || normalized.contains("record") || normalized.contains("log"))) {
            return false;
        }
        int amountTokenCount = 0;
        String[] tokens = normalized.split("\\s+");
        for (String token : tokens) {
            if (token.matches("\\$?\\d+(\\.\\d{1,2})?")) {
                amountTokenCount++;
            }
        }
        return amountTokenCount > 1 || normalized.contains(" and ") || normalized.contains(",");
    }

    private boolean isGenericNonFinancialPrompt(String prompt) {
        if (prompt == null) {
            return true;
        }
        String normalized = prompt.trim().toLowerCase(Locale.ROOT);
        if (normalized.isEmpty()) {
            return true;
        }
        List<String> financeKeywords = List.of(
                "expense", "income", "spend", "spent", "budget", "saving", "savings", "subscription",
                "credit", "loan", "transfer", "recurring", "report", "summary", "category", "categories",
                "transaction", "transactions", "bill", "bills", "monthly", "$"
        );
        for (String keyword : financeKeywords) {
            if (normalized.contains(keyword)) {
                return false;
            }
        }
        List<String> genericPhrases = List.of(
                "hi", "hello", "hey", "yo", "good morning", "good afternoon", "good evening",
                "how are you", "what can you do", "help", "thanks", "thank you", "ok", "okay"
        );
        for (String phrase : genericPhrases) {
            if (normalized.equals(phrase) || normalized.startsWith(phrase + " ")) {
                return true;
            }
        }
        return false;
    }

    private String generalAssistanceResponse() {
        return "Hi. I can help manage your finances. Try prompts like: "
                + "\"add expense 45 for groceries\", "
                + "\"show my monthly summary\", "
                + "\"add 20 coffee and 50 groceries\", "
                + "\"show top spending categories this month\".";
    }

    private String buildPromptContext(long userId, ChatConversation conversation) {
        String baseContext = aiContextService.buildUserContext(userId);
        if (conversation == null) {
            return baseContext;
        }
        List<com.azaxxc.effintrakj.effinTrak.financetools.models.ChatMessage> recentMessages =
                conversationService.getRecentMessages(conversation.getId(), 6).stream()
                        .sorted(java.util.Comparator.comparing(com.azaxxc.effintrakj.effinTrak.financetools.models.ChatMessage::getCreatedAt))
                        .collect(Collectors.toList());
        return baseContext + aiContextService.buildConversationContext(recentMessages);
    }

    private String normalizeOptionalField(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty() || "NONE".equalsIgnoreCase(trimmed) || "NULL".equalsIgnoreCase(trimmed)) {
            return null;
        }
        return trimmed;
    }
}
