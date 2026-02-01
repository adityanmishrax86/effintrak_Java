package com.azaxxc.effintrakj.effinTrak.financetools.config;

/**
 * Configuration class for AI chat system settings.
 * Centralizes all tunable parameters.
 */
public class ChatSystemConfig {

    // ==================== AI BEHAVIOR SETTINGS ====================
    public static final String TODAY_DATE = "2026-02-02";
    public static final String DEFAULT_CONVERSATION_PREFIX = "conv";

    // ==================== DEFAULT VALUES FOR EXTRACTION ====================
    public static final long DEFAULT_CATEGORY_ID = 1L;
    public static final long DEFAULT_BANK_ACCOUNT_ID = 1L;
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // ==================== OPERATION TYPES ====================
    public static final String OP_ADD_EXPENSE = "ADD_EXPENSE";
    public static final String OP_ADD_INCOME = "ADD_INCOME";
    public static final String OP_UPDATE_EXPENSE = "UPDATE_EXPENSE";
    public static final String OP_DELETE_EXPENSE = "DELETE_EXPENSE";
    public static final String OP_GET_MONTHLY_SPENDING = "GET_MONTHLY_SPENDING";
    public static final String OP_GET_MONTHLY_INCOME = "GET_MONTHLY_INCOME";
    public static final String OP_GET_FINANCIAL_SUMMARY = "GET_FINANCIAL_SUMMARY";
    public static final String OP_GET_SPENDING_BY_CATEGORY = "GET_SPENDING_BY_CATEGORY";

    // ==================== FEATURE FLAGS ====================
    public static final boolean ENABLE_UPDATE_EXPENSE = true;
    public static final boolean ENABLE_DELETE_EXPENSE = true;
    public static final boolean REQUIRE_EXPENSE_ID_FOR_UPDATES = true;
    public static final boolean REQUIRE_EXPENSE_ID_FOR_DELETE = true;

    // ==================== LOGGING ====================
    public static final boolean LOG_AI_CONTEXT = true;
    public static final boolean LOG_PARAMETER_EXTRACTION = true;
    public static final boolean LOG_INTENT_ANALYSIS = true;

    // ==================== ERROR MESSAGES ====================
    public static final String ERROR_MISSING_EXPENSE_ID = "Expense ID is required for this operation. Please provide the expense ID.";
    public static final String ERROR_MISSING_AMOUNT = "Amount is required. Please specify how much.";
    public static final String ERROR_INVALID_AMOUNT = "Amount must be a valid number.";
    public static final String ERROR_EXPENSE_NOT_FOUND = "Expense not found with the provided ID.";
    public static final String ERROR_USER_NOT_FOUND = "User not found.";

    // ==================== SUCCESS MESSAGES ====================
    public static final String SUCCESS_EXPENSE_ADDED = "Expense recorded successfully!";
    public static final String SUCCESS_INCOME_ADDED = "Income recorded successfully!";
    public static final String SUCCESS_EXPENSE_UPDATED = "Expense updated successfully!";
    public static final String SUCCESS_EXPENSE_DELETED = "Expense deleted successfully!";

    // ==================== PAGINATION ====================
    public static final int DEFAULT_PAGE_SIZE = 1000;
    public static final int CONVERSATIONS_PAGE_SIZE = 10;
}

