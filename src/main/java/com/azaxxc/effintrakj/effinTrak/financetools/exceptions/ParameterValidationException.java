package com.azaxxc.effintrakj.effinTrak.financetools.exceptions;

/**
 * Thrown when validation of extracted parameters fails
 */
public class ParameterValidationException extends AIProcessingException {

    public ParameterValidationException(String message) {
        super(message, "VALIDATION_ERROR", false);
    }
}

