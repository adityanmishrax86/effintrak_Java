package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.config.ChatSystemConfig;
import com.azaxxc.effintrakj.effinTrak.financetools.config.PromptRegistry;
import com.azaxxc.effintrakj.effinTrak.financetools.exceptions.*;
import com.azaxxc.effintrakj.effinTrak.financetools.guardrails.AIGuardrails;
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

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatClient chatClient;
    private final FinanceTools financeTools;
    private final AIContextService aiContextService;
    private final ConversationService conversationService;
    private final AIResponseValidator responseValidator;
    private final AIGuardrails guardrails;

    public ChatService(ChatModel chatModel, FinanceTools financeTools, AIContextService aiContextService,
                      ConversationService conversationService, AIResponseValidator responseValidator,
                      AIGuardrails guardrails) {
        this.financeTools = financeTools;
        this.aiContextService = aiContextService;
        this.conversationService = conversationService;
        this.responseValidator = responseValidator;
        this.guardrails = guardrails;
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
        logger.info("Processing prompt for userId: {}, conversationId: {}", userId, conversationId);
        logger.debug("Prompt content: {}", prompt);

        try {
            // Validate user input
            ValidationResult userInputValidation = validateUserInput(prompt, userId);
            if (!userInputValidation.isValid()) {
                logger.warn("User input validation failed: {}", userInputValidation.getMessage());
                return "Invalid input: " + userInputValidation.getMessage();
            }

            // Check rate limits
            AIGuardrails.RateLimitResult rateLimitResult = guardrails.checkRateLimit(userId);
            if (!rateLimitResult.isAllowed()) {
                logger.warn("Rate limit exceeded for user: {}", userId);
                throw new RateLimitExceededException(rateLimitResult.getMessage());
            }

            // Step 0: Load AI context
            logger.info("Step 0: Loading AI context with user's categories and bank accounts...");
            ChatConversation conversation = conversationService.getOrCreateConversation(userId, conversationId);
            String userContext = aiContextService.buildUserContext(userId);
            if (ChatSystemConfig.LOG_AI_CONTEXT) {
                logger.debug("User context loaded: {}", userContext);
            }

            // Step 1: Analyze intent
            logger.info("Step 1: Analyzing user intent...");
            String intentAnalysis = analyzeIntent(prompt, userContext);
            logger.info("Intent analysis result: {}", intentAnalysis);

            // Validate intent
            ValidationResult intentValidation = responseValidator.validateIntent(intentAnalysis);
            if (!intentValidation.isValid()) {
                logger.warn("Intent validation failed: {}", intentValidation.getMessage());
                return "Unable to determine a valid operation from your request. " + intentValidation.getMessage();
            }

            // Verify intent consistency
            if (!guardrails.isIntentReasonable(prompt, intentAnalysis)) {
                logger.warn("Detected unusual intent pattern for user {}: {}", userId, intentAnalysis);
                return "Your request seems unusual. Could you please clarify what you want to do?";
            }

            // Step 2: Execute tool based on intent
            logger.info("Step 2: Executing tool based on intent...");
            String result = executeToolBasedOnIntent(intentAnalysis, prompt, userId, userContext);
            logger.info("Tool execution result: {}", result);

            // Step 3: Format response
            logger.info("Step 3: Formatting response...");
            String formattedResponse = formatResponse(prompt, result);
            logger.info("Final response: {}", formattedResponse);

            // Step 4: Persist conversation
            logger.info("Step 4: Persisting conversation...");
            conversationService.saveMessage(
                conversation.getId(),
                prompt,
                formattedResponse,
                intentAnalysis.toUpperCase().trim()
            );
            logger.info("Conversation persisted successfully");

            return formattedResponse;

        } catch (RateLimitExceededException e) {
            logger.warn("Rate limit exceeded: {}", e.getMessage());
            return "Rate limit exceeded: " + e.getMessage();
        } catch (ParameterValidationException e) {
            logger.error("Parameter validation failed: {}", e.getMessage());
            return "Parameter validation error: " + e.getMessage();
        } catch (InvalidAIResponseException e) {
            logger.error("AI response validation failed: {}", e.getMessage());
            return "Could not properly process your request. " + e.getMessage();
        } catch (HallucinationDetectedException e) {
            logger.error("Hallucination detected: {}", e.getMessage());
            return "Detected unusual response pattern. Please try again or rephrase your request.";
        } catch (Exception e) {
            logger.error("Error processing prompt", e);
            logger.error("Error type: {}, message: {}", e.getClass().getName(), e.getMessage());

            String errorMsg = String.format(
                "Error: %s - %s. Check server logs for details.",
                e.getClass().getSimpleName(),
                e.getMessage() != null ? e.getMessage() : "Unknown error"
            );
            return errorMsg;
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

        if (prompt.length() > 2000) {
            return ValidationResult.error("Prompt is too long (maximum 2000 characters)");
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

        String intentPrompt = PromptRegistry.getIntentAnalysisPrompt(userContext, prompt);

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

        try {
            String paramExtraction = PromptRegistry.getExpenseParameterExtractionPrompt(userContext, prompt);

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

            logger.info("Parsed parameters - Amount: {}, Category: {}, Account: {}, Date: {}, Description: {}",
                amount, categoryId, bankAccountId, date, description);

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

            String result = financeTools.addExpenseTool(amount, categoryId, bankAccountId, date, description, null, null, userId);
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

        try {
            String paramExtraction = PromptRegistry.getIncomeParameterExtractionPrompt(userContext, prompt);

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

            logger.info("Parsed income parameters - Amount: {}, Date: {}, Description: {}, Category: {}, Account: {}",
                amount, date, description, categoryId, bankAccountId);

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

            String result = financeTools.addIncomeTool(amount, description, null, null, bankAccountId, date, categoryId, userId);
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
            String paramExtraction = PromptRegistry.getUpdateExpenseParameterExtractionPrompt(userContext, prompt);

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
            String paramExtraction = PromptRegistry.getDeleteExpenseParameterExtractionPrompt(userContext, prompt);

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

            String formatPrompt = PromptRegistry.getResponseFormattingPrompt(originalPrompt, result);

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
        logger.debug("{} not found, using default: {}", key, ChatSystemConfig.TODAY_DATE);
        return ChatSystemConfig.TODAY_DATE;
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
}
