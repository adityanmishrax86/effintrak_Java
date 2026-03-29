package com.azaxxc.effintrakj.effinTrak.financetools.guardrails;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AIGuardrailsTest {

    private final AIGuardrails guardrails = new AIGuardrails();

    @Test
    void checkRateLimit_WhenUnderThreshold_ShouldAllow() {
        AIGuardrails.RateLimitResult result = guardrails.checkRateLimit(1L);

        assertThat(result.isAllowed()).isTrue();
    }

    @Test
    void checkRateLimit_WhenExceededPerMinute_ShouldLimit() {
        for (int i = 0; i < 30; i++) {
            guardrails.checkRateLimit(2L);
        }

        AIGuardrails.RateLimitResult result = guardrails.checkRateLimit(2L);

        assertThat(result.isAllowed()).isFalse();
        assertThat(result.getMessage()).contains("Per-minute");
    }

    @Test
    void detectAnomalies_WithLargeExpense_ShouldDetect() {
        AIGuardrails.AnomalyResult result = guardrails.detectAnomalies(3L, 12000.0, "ADD_EXPENSE");

        assertThat(result.isAnomalyDetected()).isTrue();
        assertThat(result.getAnomalySummary()).contains("Large transaction");
    }

    @Test
    void verifyResponseConsistency_WithRepetitiveResponse_ShouldFail() {
        boolean consistent = guardrails.verifyResponseConsistency(
                "add an expense",
                "done done done done done",
                "ADD_EXPENSE"
        );

        assertThat(consistent).isFalse();
    }

    @Test
    void detectHallucination_WithContradictoryResponse_ShouldDetect() {
        boolean hallucination = guardrails.detectHallucination(
                "The transaction was successfully created but failed to save.",
                "add expense"
        );

        assertThat(hallucination).isTrue();
    }

    @Test
    void validateFinancialDataSanity_WithInvalidCategory_ShouldFail() {
        boolean valid = guardrails.validateFinancialDataSanity(100.0, 0L, 1L);

        assertThat(valid).isFalse();
    }
}
