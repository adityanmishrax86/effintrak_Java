package com.azaxxc.effintrakj.effinTrak.financetools.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AIResponseValidatorTest {

    private final AIResponseValidator validator = new AIResponseValidator();

    @Test
    void validateAmount_WithValidAmount_ShouldPass() {
        ValidationResult result = validator.validateAmount(120.50);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    void validateAmount_WithZeroAmount_ShouldFail() {
        ValidationResult result = validator.validateAmount(0);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).contains("at least");
    }

    @Test
    void validateDate_WithFutureDate_ShouldReturnWarning() {
        String futureDate = LocalDate.now().plusDays(2).toString();

        ValidationResult result = validator.validateDate(futureDate);

        assertThat(result.isValid()).isTrue();
        assertThat(result.isWarning()).isTrue();
    }

    @Test
    void validateDate_WithInvalidFormat_ShouldFail() {
        ValidationResult result = validator.validateDate("14-02-2026");

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).contains("Invalid date format");
    }

    @Test
    void validateDescription_WithSqlLikeInput_ShouldFail() {
        ValidationResult result = validator.validateDescription("select * from users");

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).contains("invalid characters");
    }

    @Test
    void validateIntent_WithUnknownIntent_ShouldFail() {
        ValidationResult result = validator.validateIntent("TRANSFER_FUNDS");

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).contains("Unknown operation");
    }

    @Test
    void validateIntent_WithQueryFinancialData_ShouldPass() {
        ValidationResult result = validator.validateIntent("QUERY_FINANCIAL_DATA");

        assertThat(result.isValid()).isTrue();
    }
}
