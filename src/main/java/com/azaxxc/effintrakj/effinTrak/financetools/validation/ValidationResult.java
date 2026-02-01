package com.azaxxc.effintrakj.effinTrak.financetools.validation;

/**
 * Represents the result of a validation operation
 * Contains status, error/warning messages, and validation outcome
 */
public class ValidationResult {

    private final boolean valid;
    private final String message;
    private final ValidationType type;

    private ValidationResult(boolean valid, String message, ValidationType type) {
        this.valid = valid;
        this.message = message;
        this.type = type;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return message;
    }

    public ValidationType getType() {
        return type;
    }

    public boolean isError() {
        return type == ValidationType.ERROR;
    }

    public boolean isWarning() {
        return type == ValidationType.WARNING;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, "Validation passed", ValidationType.SUCCESS);
    }

    public static ValidationResult success(String message) {
        return new ValidationResult(true, message, ValidationType.SUCCESS);
    }

    public static ValidationResult error(String message) {
        return new ValidationResult(false, message, ValidationType.ERROR);
    }

    public static ValidationResult warning(String message) {
        return new ValidationResult(true, message, ValidationType.WARNING);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", type, message);
    }

    public enum ValidationType {
        SUCCESS, WARNING, ERROR
    }
}

