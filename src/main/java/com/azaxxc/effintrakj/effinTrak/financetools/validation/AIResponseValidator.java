package com.azaxxc.effintrakj.effinTrak.financetools.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Comprehensive validation for AI-extracted parameters
 * Ensures data quality and prevents malicious or erroneous data from entering the system
 */
@Component
public class AIResponseValidator {

    private static final Logger logger = LoggerFactory.getLogger(AIResponseValidator.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    // Validation constraints
    private static final double MIN_AMOUNT = 0.01;
    private static final double MAX_AMOUNT = 999999.99;
    private static final int MIN_DESCRIPTION_LENGTH = 2;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MIN_CATEGORY_ID = 1;
    private static final int MAX_CATEGORY_ID = 100;
    private static final int MIN_ACCOUNT_ID = 1;
    private static final int MAX_ACCOUNT_ID = 100;

    /**
     * Validate extracted amount parameter
     */
    public ValidationResult validateAmount(double amount) {
        logger.debug("Validating amount: {}", amount);

        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            return ValidationResult.error("Amount must be a valid number");
        }

        if (amount < MIN_AMOUNT) {
            return ValidationResult.error(String.format("Amount must be at least %.2f", MIN_AMOUNT));
        }

        if (amount > MAX_AMOUNT) {
            return ValidationResult.error(String.format("Amount cannot exceed %.2f", MAX_AMOUNT));
        }

        return ValidationResult.success();
    }

    /**
     * Validate extracted category ID
     */
    public ValidationResult validateCategoryId(long categoryId) {
        logger.debug("Validating category ID: {}", categoryId);

        if (categoryId < MIN_CATEGORY_ID) {
            return ValidationResult.error(String.format("Category ID must be at least %d", MIN_CATEGORY_ID));
        }

        if (categoryId > MAX_CATEGORY_ID) {
            return ValidationResult.error(String.format("Category ID cannot exceed %d", MAX_CATEGORY_ID));
        }

        return ValidationResult.success();
    }

    /**
     * Validate extracted bank account ID
     */
    public ValidationResult validateBankAccountId(long accountId) {
        logger.debug("Validating bank account ID: {}", accountId);

        if (accountId < MIN_ACCOUNT_ID) {
            return ValidationResult.error(String.format("Bank Account ID must be at least %d", MIN_ACCOUNT_ID));
        }

        if (accountId > MAX_ACCOUNT_ID) {
            return ValidationResult.error(String.format("Bank Account ID cannot exceed %d", MAX_ACCOUNT_ID));
        }

        return ValidationResult.success();
    }

    /**
     * Validate extracted date parameter
     */
    public ValidationResult validateDate(String dateStr) {
        logger.debug("Validating date: {}", dateStr);

        if (dateStr == null || dateStr.trim().isEmpty()) {
            return ValidationResult.error("Date cannot be empty");
        }

        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);

            // Check if date is not in the future
            if (date.isAfter(LocalDate.now().plusDays(1))) {
                return ValidationResult.warning("Date is in the future. Using date as provided.");
            }

            // Check if date is not too far in the past (more than 10 years)
            if (date.isBefore(LocalDate.now().minusYears(10))) {
                return ValidationResult.warning("Date is more than 10 years old. Please verify this is correct.");
            }

            return ValidationResult.success();
        } catch (Exception e) {
            logger.warn("Invalid date format: {}", dateStr);
            return ValidationResult.error(String.format("Invalid date format. Expected yyyy-MM-dd, got: %s", dateStr));
        }
    }

    /**
     * Validate extracted description parameter
     */
    public ValidationResult validateDescription(String description) {
        logger.debug("Validating description: {}", description);

        if (description == null || description.trim().isEmpty()) {
            return ValidationResult.error("Description cannot be empty");
        }

        String trimmed = description.trim();

        if (trimmed.length() < MIN_DESCRIPTION_LENGTH) {
            return ValidationResult.error(String.format("Description must be at least %d characters", MIN_DESCRIPTION_LENGTH));
        }

        if (trimmed.length() > MAX_DESCRIPTION_LENGTH) {
            return ValidationResult.error(String.format("Description cannot exceed %d characters", MAX_DESCRIPTION_LENGTH));
        }

        // Check for SQL injection patterns
        if (containsSqlInjectionPattern(trimmed)) {
            return ValidationResult.error("Description contains invalid characters or patterns");
        }

        return ValidationResult.success();
    }

    /**
     * Validate expense ID for update/delete operations
     */
    public ValidationResult validateExpenseId(long expenseId) {
        logger.debug("Validating expense ID: {}", expenseId);

        if (expenseId <= 0) {
            return ValidationResult.error("Expense ID must be a positive number");
        }

        if (expenseId > Long.MAX_VALUE / 2) {
            return ValidationResult.error("Expense ID is invalid");
        }

        return ValidationResult.success();
    }

    /**
     * Validate user ID
     */
    public ValidationResult validateUserId(long userId) {
        logger.debug("Validating user ID: {}", userId);

        if (userId <= 0) {
            return ValidationResult.error("User ID must be a positive number");
        }

        return ValidationResult.success();
    }

    /**
     * Validate extracted intent command
     */
    public ValidationResult validateIntent(String intent) {
        logger.debug("Validating intent: {}", intent);

        if (intent == null || intent.trim().isEmpty()) {
            return ValidationResult.error("Intent cannot be empty");
        }

        String upperIntent = intent.toUpperCase().trim();

        // Check against allowed intents
        List<String> allowedIntents = List.of(
            "ADD_EXPENSE", "ADD_INCOME", "UPDATE_EXPENSE", "DELETE_EXPENSE",
            "GET_MONTHLY_SPENDING", "GET_MONTHLY_INCOME", "GET_FINANCIAL_SUMMARY",
            "GET_SPENDING_BY_CATEGORY"
        );

        if (!allowedIntents.contains(upperIntent)) {
            return ValidationResult.error(String.format("Unknown operation: %s", intent));
        }

        return ValidationResult.success();
    }

    /**
     * Validate AI response format for parameter extraction
     */
    public ValidationResult validateParameterExtractionFormat(String response) {
        logger.debug("Validating parameter extraction format");

        if (response == null || response.trim().isEmpty()) {
            return ValidationResult.error("AI response is empty");
        }

        // Check minimum response length
        if (response.trim().length() < 10) {
            return ValidationResult.error("AI response is too short - extraction may have failed");
        }

        // Check for error indicators
        if (response.toLowerCase().contains("error") ||
            response.toLowerCase().contains("invalid") ||
            response.toLowerCase().contains("cannot")) {
            return ValidationResult.warning("AI response contains error indicators");
        }

        return ValidationResult.success();
    }

    /**
     * Validate AI response is not suspicious or malicious
     */
    public ValidationResult validateAIResponseSafety(String response) {
        logger.debug("Validating AI response safety");

        if (response == null || response.isEmpty()) {
            return ValidationResult.error("Response cannot be empty");
        }

        // Check for suspiciously long responses (potential injection attempt)
        if (response.length() > 10000) {
            return ValidationResult.warning("Response is unusually long");
        }

        // Check for dangerous patterns
        if (containsDangerousPatterns(response)) {
            return ValidationResult.error("Response contains suspicious patterns");
        }

        return ValidationResult.success();
    }

    /**
     * Check for SQL injection patterns
     */
    private boolean containsSqlInjectionPattern(String text) {
        Pattern sqlPattern = Pattern.compile(
            "(?i)(union|select|insert|update|delete|drop|exec|execute|script|javascript|eval)",
            Pattern.CASE_INSENSITIVE
        );
        return sqlPattern.matcher(text).find();
    }

    /**
     * Check for dangerous patterns in responses
     */
    private boolean containsDangerousPatterns(String text) {
        String[] dangerousPatterns = {
            "SELECT.*FROM", "INSERT.*INTO", "UPDATE.*SET", "DELETE.*FROM",
            "DROP TABLE", "EXEC", "<script", "javascript:", "eval\\(",
            "System.exit", "Runtime.getRuntime"
        };

        for (String pattern : dangerousPatterns) {
            try {
                if (Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(text).find()) {
                    logger.warn("Detected dangerous pattern: {}", pattern);
                    return true;
                }
            } catch (Exception e) {
                logger.warn("Error compiling pattern: {}, error: {}", pattern, e.getMessage());
            }
        }

        return false;
    }

    /**
     * Validate complete expense addition request
     */
    public ValidationResult validateExpenseAdditionRequest(double amount, long categoryId,
                                                          long bankAccountId, String date,
                                                          String description) {
        logger.debug("Validating complete expense addition request");

        List<String> errors = new ArrayList<>();

        // Validate each parameter
        ValidationResult amountValidation = validateAmount(amount);
        if (!amountValidation.isValid()) {
            errors.add(amountValidation.getErrorMessage());
        }

        ValidationResult categoryValidation = validateCategoryId(categoryId);
        if (!categoryValidation.isValid()) {
            errors.add(categoryValidation.getErrorMessage());
        }

        ValidationResult accountValidation = validateBankAccountId(bankAccountId);
        if (!accountValidation.isValid()) {
            errors.add(accountValidation.getErrorMessage());
        }

        ValidationResult dateValidation = validateDate(date);
        if (!dateValidation.isValid()) {
            errors.add(dateValidation.getErrorMessage());
        }

        ValidationResult descriptionValidation = validateDescription(description);
        if (!descriptionValidation.isValid()) {
            errors.add(descriptionValidation.getErrorMessage());
        }

        if (!errors.isEmpty()) {
            return ValidationResult.error(String.join("; ", errors));
        }

        return ValidationResult.success();
    }

    /**
     * Validate complete income addition request
     */
    public ValidationResult validateIncomeAdditionRequest(double amount, String date, String description) {
        logger.debug("Validating complete income addition request");

        List<String> errors = new ArrayList<>();

        ValidationResult amountValidation = validateAmount(amount);
        if (!amountValidation.isValid()) {
            errors.add(amountValidation.getErrorMessage());
        }

        ValidationResult dateValidation = validateDate(date);
        if (!dateValidation.isValid()) {
            errors.add(dateValidation.getErrorMessage());
        }

        ValidationResult descriptionValidation = validateDescription(description);
        if (!descriptionValidation.isValid()) {
            errors.add(descriptionValidation.getErrorMessage());
        }

        if (!errors.isEmpty()) {
            return ValidationResult.error(String.join("; ", errors));
        }

        return ValidationResult.success();
    }
}
