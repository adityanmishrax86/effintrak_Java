package com.azaxxc.effintrakj.effinTrak.financetools.exceptions;

/**
 * Base exception for AI-related errors
 */
public class AIProcessingException extends RuntimeException {

    private final String errorCode;
    private final boolean isRetryable;

    public AIProcessingException(String message) {
        this(message, "AI_ERROR", false);
    }

    public AIProcessingException(String message, String errorCode, boolean isRetryable) {
        super(message);
        this.errorCode = errorCode;
        this.isRetryable = isRetryable;
    }

    public AIProcessingException(String message, Throwable cause) {
        this(message, "AI_ERROR", true);
        initCause(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public boolean isRetryable() {
        return isRetryable;
    }
}

