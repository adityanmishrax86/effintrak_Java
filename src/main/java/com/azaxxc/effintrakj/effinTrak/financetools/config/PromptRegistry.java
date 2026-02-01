package com.azaxxc.effintrakj.effinTrak.financetools.config;

/**
 * Centralized registry for all AI system prompts and contexts.
 * This allows easy monitoring and modification of AI behavior.
 *
 * All prompts follow a professional finance assistant tone with clear,
 * structured, and business-appropriate language.
 */
public class PromptRegistry {

    // ==================== INTENT ANALYSIS PROMPTS ====================

    public static String getIntentAnalysisPrompt(String userContext, String userRequest) {
        return String.format("""
            %s
            
            TASK: Analyze the following financial request and classify it into the appropriate operation category.
            
            USER REQUEST: "%s"
            
            CLASSIFICATION OPTIONS:
            - ADD_EXPENSE: User intends to record a new expense transaction
            - ADD_INCOME: User intends to record incoming revenue or earnings
            - UPDATE_EXPENSE: User intends to modify an existing expense record
            - DELETE_EXPENSE: User intends to remove an expense record
            - GET_MONTHLY_SPENDING: User requests total monthly expenditure analysis
            - GET_MONTHLY_INCOME: User requests total monthly income analysis
            - GET_FINANCIAL_SUMMARY: User requests comprehensive financial overview or account balance
            - GET_SPENDING_BY_CATEGORY: User requests expense breakdown by category
            
            RESPONSE INSTRUCTION: Respond with ONLY the operation command name, nothing else.""",
            userContext, userRequest);
    }

    // ==================== PARAMETER EXTRACTION PROMPTS ====================

    public static String getExpenseParameterExtractionPrompt(String userContext, String userRequest) {
        return String.format("""
            %s
            
            TASK: Extract and structure expense transaction parameters from the user request.
            
            USER REQUEST: "%s"
            
            REQUIRED OUTPUT FORMAT (one parameter per line, exact spacing):
            AMOUNT: [numeric value, exclude currency symbols]
            CATEGORY_ID: [integer ID from available categories listed above]
            BANK_ACCOUNT_ID: [integer ID from available accounts listed above]
            DATE: [ISO 8601 format: yyyy-MM-dd, default to current date if not specified]
            DESCRIPTION: [brief transaction description or merchant name]
            
            EXTRACTION GUIDELINES:
            - If category name is mentioned, match to the corresponding ID from the categories context above
            - If payment method/account is specified, identify the corresponding account ID
            - Ensure all numerical values are properly formatted
            - If no specific date is provided, use the current date: 2026-02-02
            - Maintain professional description formatting""",
            userContext, userRequest);
    }

    public static String getIncomeParameterExtractionPrompt(String userContext, String userRequest) {
        return String.format("""
            %s
            
            TASK: Extract and structure income transaction parameters from the user request.
            
            USER REQUEST: "%s"
            
            REQUIRED OUTPUT FORMAT (one parameter per line, exact spacing):
            AMOUNT: [numeric value, exclude currency symbols]
            DATE: [ISO 8601 format: yyyy-MM-dd, default to current date if not specified]
            DESCRIPTION: [source of income or transaction description]
            
            EXTRACTION GUIDELINES:
            - Numeric values should be precise and properly formatted
            - If no specific date is provided, use the current date: 2026-02-02
            - Income description should clearly identify the source (e.g., salary, freelance, bonus)
            - Maintain professional formatting throughout""",
            userContext, userRequest);
    }

    public static String getUpdateExpenseParameterExtractionPrompt(String userContext, String userRequest) {
        return String.format("""
            %s
            
            TASK: Extract modification parameters for an existing expense record update.
            
            USER REQUEST: "%s"
            
            REQUIRED OUTPUT FORMAT (one parameter per line, exact spacing):
            EXPENSE_ID: [integer ID of the expense to be updated]
            AMOUNT: [new numeric value, or "NONE" if not being modified]
            CATEGORY_ID: [new category ID from available categories, or "NONE" if not changing]
            BANK_ACCOUNT_ID: [new account ID from available accounts, or "NONE" if not changing]
            DATE: [new date in yyyy-MM-dd format, or "NONE" if not changing]
            DESCRIPTION: [new description, or "NONE" if not changing]
            
            EXTRACTION GUIDELINES:
            - EXPENSE_ID is mandatory; if not provided, respond with: EXPENSE_ID: REQUIRED
            - Mark unchanged fields as "NONE"
            - Use current date reference: 2026-02-02
            - Validate all numeric values before extraction""",
            userContext, userRequest);
    }

    public static String getDeleteExpenseParameterExtractionPrompt(String userContext, String userRequest) {
        return String.format("""
            %s
            
            TASK: Extract deletion parameters and confirm user authorization for permanent record removal.
            
            USER REQUEST: "%s"
            
            REQUIRED OUTPUT FORMAT (one parameter per line, exact spacing):
            EXPENSE_ID: [integer ID of the expense to be deleted]
            CONFIRMATION: [yes/no - explicit user confirmation of deletion]
            
            EXTRACTION GUIDELINES:
            - EXPENSE_ID is mandatory; if not provided, respond with: EXPENSE_ID: REQUIRED
            - CONFIRMATION must be explicitly affirmed by the user for the operation to proceed
            - Deletion is a permanent action and requires clear user intent
            - Current date reference: 2026-02-02""",
            userContext, userRequest);
    }

    // ==================== RESPONSE FORMATTING PROMPTS ====================

    public static String getResponseFormattingPrompt(String userRequest, String operationResult) {
        return String.format("""
            TASK: Compose a professional response to the user's financial request.
            
            ORIGINAL REQUEST: "%s"
            OPERATION RESULT: %s
            
            RESPONSE GUIDELINES:
            - Maintain a professional and courteous tone
            - Provide clear confirmation of the completed action
            - Include relevant transaction details (amount, category, date, account)
            - Use proper financial terminology
            - Keep response concise yet informative
            - Avoid unnecessary casual language""",
            userRequest, operationResult);
    }

    // ==================== USER CONTEXT PROMPTS ====================

    public static String buildFinancialContextHeader() {
        return "\n╔════════════════════════════════════════════════════════════════╗\n" +
               "║              USER FINANCIAL ACCOUNT CONTEXT                    ║\n" +
               "╚════════════════════════════════════════════════════════════════╝\n\n";
    }

    public static String buildCategoryContextSection(String categoriesInfo) {
        return "EXPENSE CATEGORIES:\n" +
               "─────────────────────────────────────────────────────────────\n" +
               categoriesInfo +
               "\nINSTRUCTION: Match user-mentioned categories to the corresponding ID above.\n" +
               "             Apply intelligent matching for synonyms and variations.\n\n";
    }

    public static String buildBankAccountContextSection(String accountsInfo) {
        return "BANK ACCOUNTS & PAYMENT METHODS:\n" +
               "─────────────────────────────────────────────────────────────\n" +
               accountsInfo +
               "\nINSTRUCTION: Identify user-specified accounts/payment methods and map to IDs.\n" +
               "             Consider account nicknames and payment method types.\n\n";
    }

    public static String buildAIInstructionsFooter() {
        return "╔════════════════════════════════════════════════════════════════╗\n" +
               "║                   PROCESSING GUIDELINES                        ║\n" +
               "╚════════════════════════════════════════════════════════════════╝\n\n" +
               "1. CATEGORY MATCHING: Cross-reference user input with available categories.\n" +
               "   - Apply fuzzy matching for category names\n" +
               "   - If no exact match, select the most semantically similar category\n" +
               "   - Default to General/Miscellaneous (ID: 1) only if absolutely unclear\n\n" +
               "2. ACCOUNT SELECTION: Identify the appropriate payment method or account.\n" +
               "   - Match payment method descriptions to account records\n" +
               "   - If not specified, use the user's primary/default account\n" +
               "   - Default to Primary Account (ID: 1) only if unclear\n\n" +
               "3. DATE HANDLING: Ensure proper date formatting and interpretation.\n" +
               "   - Use ISO 8601 format (yyyy-MM-dd)\n" +
               "   - Interpret relative dates (yesterday, last week, etc.) accurately\n" +
               "   - Current reference date: 2026-02-02\n\n" +
               "4. AMOUNT VALIDATION: Verify numerical accuracy.\n" +
               "   - Accept values with or without currency symbols\n" +
               "   - Ensure two decimal place precision for currency\n\n" +
               "5. MODIFICATION OPERATIONS: Require explicit user authorization.\n" +
               "   - For UPDATE: Confirm specific changes before execution\n" +
               "   - For DELETE: Obtain unambiguous user confirmation\n" +
               "   - Display transaction details for verification\n\n";
    }

    // ==================== WARNING/INFO PROMPTS ====================

    public static String getUpdateExpenseWarningPrompt() {
        return """
            ────────────────────────────────────────────────────────────────
            EXPENSE MODIFICATION REQUEST
            ────────────────────────────────────────────────────────────────
            
            To proceed with updating an expense record, please provide the following information:
            
            1. EXPENSE IDENTIFIER
               • The ID of the expense to be modified (request a transaction list if needed)
            
            2. MODIFICATION DETAILS
               • Specify which field(s) require updating:
                 - Amount (transaction value)
                 - Category (expense classification)
                 - Account (payment method)
                 - Date (transaction date)
                 - Description (transaction notes)
            
            Please provide the expense ID and the desired modifications, and I will process
            the update accordingly.""";
    }

    public static String getDeleteExpenseWarningPrompt() {
        return """
            ────────────────────────────────────────────────────────────────
            EXPENSE DELETION REQUEST - PERMANENT ACTION
            ────────────────────────────────────────────────────────────────
            
            To proceed with deleting an expense record, the following steps are required:
            
            1. EXPENSE IDENTIFICATION
               • Provide the ID of the expense to be deleted
               • Request a recent transaction list if you need to locate the expense
            
            2. USER AUTHORIZATION
               • Confirm explicit authorization for permanent record deletion
               • This action cannot be reversed or recovered
            
            Please provide the expense ID and explicit confirmation to proceed.""";
    }

    // ==================== CONFIG MESSAGES ====================

    public static String getSystemMessage(String operation) {
        return switch(operation) {
            case "ADD_EXPENSE" -> "Processing expense transaction...";
            case "ADD_INCOME" -> "Processing income transaction...";
            case "UPDATE_EXPENSE" -> "Preparing to update expense record...";
            case "DELETE_EXPENSE" -> "Preparing to delete expense record...";
            case "GET_MONTHLY_SPENDING" -> "Analyzing monthly expenditure data...";
            case "GET_MONTHLY_INCOME" -> "Analyzing monthly income data...";
            case "GET_FINANCIAL_SUMMARY" -> "Compiling comprehensive financial summary...";
            case "GET_SPENDING_BY_CATEGORY" -> "Generating category-based expense analysis...";
            default -> "Processing financial request...";
        };
    }
}

