package com.azaxxc.effintrakj.effinTrak.financetools.exceptions;

/**
 * Thrown when AI response validation fails
 */
public class InvalidAIResponseException extends AIProcessingException {

    public InvalidAIResponseException(String message) {
        super(message, "INVALID_AI_RESPONSE", false);
    }

    public InvalidAIResponseException(String message, Throwable cause) {
        super(message, "INVALID_AI_RESPONSE", false);
        initCause(cause);
    }
}

