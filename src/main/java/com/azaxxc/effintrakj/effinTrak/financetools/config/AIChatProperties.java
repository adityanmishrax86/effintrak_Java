package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "app.ai")
public class AIChatProperties {

    private String defaultModel = "llama-3.3-70b-versatile";
    private Set<String> supportedModels = new LinkedHashSet<>(List.of("llama-3.3-70b-versatile"));
    private boolean allowModelOverride = false;
    private boolean enableResponseFormatting = true;
    private boolean enforceToolPolicies = true;
    private int maxPromptLength = 2000;
    private String promptProfile = "prod";
    private String promptVersion = "v1";
    private Set<String> enabledOperations = new LinkedHashSet<>(List.of(
            ChatSystemConfig.OP_ADD_EXPENSE,
            ChatSystemConfig.OP_ADD_INCOME,
            ChatSystemConfig.OP_UPDATE_EXPENSE,
            ChatSystemConfig.OP_DELETE_EXPENSE,
            ChatSystemConfig.OP_GET_MONTHLY_SPENDING,
            ChatSystemConfig.OP_GET_MONTHLY_INCOME,
            ChatSystemConfig.OP_GET_FINANCIAL_SUMMARY,
            ChatSystemConfig.OP_GET_SPENDING_BY_CATEGORY,
            ChatSystemConfig.OP_CREATE_SAVINGS_GOAL,
            ChatSystemConfig.OP_GET_SAVINGS_PROGRESS,
            ChatSystemConfig.OP_ADD_TO_SAVINGS,
            ChatSystemConfig.OP_WITHDRAW_FROM_SAVINGS,
            ChatSystemConfig.OP_UPDATE_BUDGET,
            ChatSystemConfig.OP_ADD_SUBSCRIPTION,
            ChatSystemConfig.OP_GET_ACTIVE_SUBSCRIPTIONS,
            ChatSystemConfig.OP_CANCEL_SUBSCRIPTION,
            ChatSystemConfig.OP_ADD_CREDIT,
            ChatSystemConfig.OP_GET_ACTIVE_CREDITS,
            ChatSystemConfig.OP_MAKE_CREDIT_PAYMENT,
            ChatSystemConfig.OP_TRANSFER_MONEY,
            ChatSystemConfig.OP_CREATE_RECURRING_TRANSACTION,
            ChatSystemConfig.OP_GET_ACTIVE_RECURRING_TRANSACTIONS,
            ChatSystemConfig.OP_PAUSE_RECURRING_TRANSACTION,
            ChatSystemConfig.OP_DELETE_RECURRING_TRANSACTION,
            ChatSystemConfig.OP_QUERY_FINANCIAL_DATA
    ));

    public String getDefaultModel() {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel;
    }

    public Set<String> getSupportedModels() {
        return supportedModels;
    }

    public void setSupportedModels(Set<String> supportedModels) {
        this.supportedModels = supportedModels;
    }

    public boolean isAllowModelOverride() {
        return allowModelOverride;
    }

    public void setAllowModelOverride(boolean allowModelOverride) {
        this.allowModelOverride = allowModelOverride;
    }

    public boolean isEnableResponseFormatting() {
        return enableResponseFormatting;
    }

    public void setEnableResponseFormatting(boolean enableResponseFormatting) {
        this.enableResponseFormatting = enableResponseFormatting;
    }

    public boolean isEnforceToolPolicies() {
        return enforceToolPolicies;
    }

    public void setEnforceToolPolicies(boolean enforceToolPolicies) {
        this.enforceToolPolicies = enforceToolPolicies;
    }

    public int getMaxPromptLength() {
        return maxPromptLength;
    }

    public void setMaxPromptLength(int maxPromptLength) {
        this.maxPromptLength = maxPromptLength;
    }

    public String getPromptProfile() {
        return promptProfile;
    }

    public void setPromptProfile(String promptProfile) {
        this.promptProfile = promptProfile;
    }

    public String getPromptVersion() {
        return promptVersion;
    }

    public void setPromptVersion(String promptVersion) {
        this.promptVersion = promptVersion;
    }

    public Set<String> getEnabledOperations() {
        return enabledOperations;
    }

    public void setEnabledOperations(Set<String> enabledOperations) {
        this.enabledOperations = enabledOperations;
    }
}
