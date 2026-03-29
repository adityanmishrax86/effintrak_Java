package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PromptRegistryTest {

    @Test
    void getExpenseParameterExtractionPrompt_ShouldIncludeCurrentDateAndRequest() {
        String context = "Sample context";
        String request = "I spent 25 on groceries";

        String prompt = PromptRegistry.getExpenseParameterExtractionPrompt(context, request);

        assertThat(prompt).contains(request);
        assertThat(prompt).contains(ChatSystemConfig.getTodayDate());
    }

    @Test
    void buildAIInstructionsFooter_ShouldIncludeCurrentDate() {
        String footer = PromptRegistry.buildAIInstructionsFooter();

        assertThat(footer).contains(ChatSystemConfig.getTodayDate());
    }

    @Test
    void getIntentAnalysisPrompt_ShouldContainKnownOperations() {
        String prompt = PromptRegistry.getIntentAnalysisPrompt("ctx", "add expense");

        assertThat(prompt).contains("ADD_EXPENSE");
        assertThat(prompt).contains("GET_FINANCIAL_SUMMARY");
    }

    @Test
    void getSystemMessage_WithUnknownOperation_ShouldReturnDefault() {
        String message = PromptRegistry.getSystemMessage("UNKNOWN_OP");

        assertThat(message).isEqualTo("Processing financial request...");
    }
}
