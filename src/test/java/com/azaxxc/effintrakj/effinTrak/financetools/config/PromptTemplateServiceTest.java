package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromptTemplateServiceTest {

    @Test
    void loadsProfileAndFormatsIntentPrompt() {
        AIChatProperties properties = new AIChatProperties();
        properties.setPromptProfile("dev");
        properties.setPromptVersion("test-v1");

        PromptTemplateService service = new PromptTemplateService(properties);
        String prompt = service.intentAnalysis("CTX", "Add grocery expense");

        assertEquals("dev", service.getPromptProfile());
        assertEquals("test-v1", service.getPromptVersion());
        assertTrue(prompt.contains("CTX"));
        assertTrue(prompt.contains("Add grocery expense"));
    }

    @Test
    void formatsBatchAndQueryPrompts() {
        AIChatProperties properties = new AIChatProperties();
        properties.setPromptProfile("dev");

        PromptTemplateService service = new PromptTemplateService(properties);

        String batchExpensePrompt = service.expenseBatchParams("CTX", "add coffee and lunch", "2026-02-14");
        String batchIncomePrompt = service.incomeBatchParams("CTX", "add salary and bonus", "2026-02-14");
        String queryPrompt = service.financialQueryParams("CTX", "show top categories", "2026-02-14");

        assertTrue(batchExpensePrompt.contains("ITEM:"));
        assertTrue(batchIncomePrompt.contains("ITEM:"));
        assertTrue(queryPrompt.contains("QUERY_TYPE"));
    }
}
