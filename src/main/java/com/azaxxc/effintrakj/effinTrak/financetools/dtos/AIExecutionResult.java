package com.azaxxc.effintrakj.effinTrak.financetools.dtos;

import java.util.ArrayList;
import java.util.List;

public class AIExecutionResult {

    private boolean success;
    private String message;
    private String errorCode;
    private String operation;
    private String conversationId;
    private long userId;
    private String model;
    private String promptProfile;
    private String promptVersion;
    private List<String> warnings = new ArrayList<>();

    public static AIExecutionResult success(String message, String operation, String conversationId, long userId, String model) {
        AIExecutionResult result = new AIExecutionResult();
        result.success = true;
        result.message = message;
        result.operation = operation;
        result.conversationId = conversationId;
        result.userId = userId;
        result.model = model;
        return result;
    }

    public static AIExecutionResult failure(String message, String errorCode, String operation, String conversationId, long userId, String model) {
        AIExecutionResult result = new AIExecutionResult();
        result.success = false;
        result.message = message;
        result.errorCode = errorCode;
        result.operation = operation;
        result.conversationId = conversationId;
        result.userId = userId;
        result.model = model;
        return result;
    }

    public void addWarning(String warning) {
        if (warning != null && !warning.isBlank()) {
            this.warnings.add(warning);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getOperation() {
        return operation;
    }

    public String getConversationId() {
        return conversationId;
    }

    public long getUserId() {
        return userId;
    }

    public String getModel() {
        return model;
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

    public List<String> getWarnings() {
        return warnings;
    }
}
