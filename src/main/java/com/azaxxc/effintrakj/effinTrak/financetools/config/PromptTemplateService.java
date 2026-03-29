package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

@Service
public class PromptTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(PromptTemplateService.class);
    private final AIChatProperties properties;
    private final Properties templates = new Properties();

    public PromptTemplateService(AIChatProperties properties) {
        this.properties = properties;
        loadTemplates();
    }

    public String getPromptVersion() {
        return properties.getPromptVersion();
    }

    public String getPromptProfile() {
        return properties.getPromptProfile();
    }

    public String intentAnalysis(String userContext, String userRequest) {
        return format("intent.analysis", userContext, userRequest);
    }

    public String expenseParams(String userContext, String userRequest, String todayDate) {
        return format("params.expense", userContext, userRequest, todayDate);
    }

    public String expenseBatchParams(String userContext, String userRequest, String todayDate) {
        return format("params.expense.batch", userContext, userRequest, todayDate);
    }

    public String incomeParams(String userContext, String userRequest, String todayDate) {
        return format("params.income", userContext, userRequest, todayDate);
    }

    public String incomeBatchParams(String userContext, String userRequest, String todayDate) {
        return format("params.income.batch", userContext, userRequest, todayDate);
    }

    public String updateExpenseParams(String userContext, String userRequest, String todayDate) {
        return format("params.expense.update", userContext, userRequest, todayDate);
    }

    public String deleteExpenseParams(String userContext, String userRequest, String todayDate) {
        return format("params.expense.delete", userContext, userRequest, todayDate);
    }

    public String createSavingsParams(String userContext, String userRequest, String todayDate) {
        return format("params.savings.create", userContext, userRequest, todayDate);
    }

    public String addToSavingsParams(String userContext, String userRequest) {
        return format("params.savings.deposit", userContext, userRequest);
    }

    public String withdrawFromSavingsParams(String userContext, String userRequest) {
        return format("params.savings.withdraw", userContext, userRequest);
    }

    public String updateBudgetParams(String userContext, String userRequest) {
        return format("params.budget.update", userContext, userRequest);
    }

    public String addSubscriptionParams(String userContext, String userRequest, String todayDate) {
        return format("params.subscription.add", userContext, userRequest, todayDate);
    }

    public String cancelSubscriptionParams(String userContext, String userRequest, String todayDate) {
        return format("params.subscription.cancel", userContext, userRequest, todayDate);
    }

    public String addCreditParams(String userContext, String userRequest, String todayDate) {
        return format("params.credit.add", userContext, userRequest, todayDate);
    }

    public String creditPaymentParams(String userContext, String userRequest, String todayDate) {
        return format("params.credit.payment", userContext, userRequest, todayDate);
    }

    public String transferParams(String userContext, String userRequest, String todayDate) {
        return format("params.transfer.create", userContext, userRequest, todayDate);
    }

    public String createRecurringParams(String userContext, String userRequest, String todayDate) {
        return format("params.recurring.create", userContext, userRequest, todayDate);
    }

    public String pauseRecurringParams(String userContext, String userRequest) {
        return format("params.recurring.pause", userContext, userRequest);
    }

    public String deleteRecurringParams(String userContext, String userRequest) {
        return format("params.recurring.delete", userContext, userRequest);
    }

    public String financialQueryParams(String userContext, String userRequest, String todayDate) {
        return format("params.financial.query", userContext, userRequest, todayDate);
    }

    public String responseFormatter(String originalRequest, String operationResult) {
        return format("response.format", originalRequest, operationResult);
    }

    private String format(String key, Object... args) {
        String template = templates.getProperty(key);
        if (template == null || template.isBlank()) {
            throw new IllegalStateException("Missing prompt template for key: " + key);
        }
        return MessageFormat.format(template, args);
    }

    private void loadTemplates() {
        String resourcePath = "ai-prompts/" + properties.getPromptProfile() + ".properties";
        ClassPathResource resource = new ClassPathResource(resourcePath);
        if (!resource.exists()) {
            throw new IllegalStateException("Prompt profile not found: " + resourcePath);
        }
        try (InputStream inputStream = resource.getInputStream()) {
            templates.load(inputStream);
            logger.info("Loaded AI prompt profile '{}' with version '{}'", properties.getPromptProfile(), properties.getPromptVersion());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load prompt templates from " + resourcePath, e);
        }
    }
}
