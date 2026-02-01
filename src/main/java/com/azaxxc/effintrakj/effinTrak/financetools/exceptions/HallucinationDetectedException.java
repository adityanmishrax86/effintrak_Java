package com.azaxxc.effintrakj.effinTrak.financetools.exceptions;

/**
 * Thrown when AI hallucination or suspicious response pattern is detected
 */
public class HallucinationDetectedException extends AIProcessingException {

    public HallucinationDetectedException(String message) {
        super(message, "HALLUCINATION_DETECTED", false);
    }
}

