package com.azaxxc.effintrakj.effinTrak.financetools.exceptions;

/**
 * Thrown when rate limit is exceeded
 */
public class RateLimitExceededException extends AIProcessingException {

    public RateLimitExceededException(String message) {
        super(message, "RATE_LIMIT_EXCEEDED", true);
    }
}

