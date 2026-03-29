package com.azaxxc.effintrakj.effinTrak.financetools.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {
    private String response;
    private long userId;
    private String conversationId;
    private long timestamp;
    private String status;
    private String operation;
    private String errorCode;
    private String model;
    private String promptProfile;
    private String promptVersion;
    private List<String> warnings = new ArrayList<>();

    public ChatResponse() {
        this.timestamp = System.currentTimeMillis();
        this.status = "success";
    }

    public ChatResponse(String response, long userId, String conversationId) {
        this();
        this.response = response;
        this.userId = userId;
        this.conversationId = conversationId;
    }

    public static ChatResponse error(String message) {
        ChatResponse response = new ChatResponse();
        response.response = message;
        response.status = "error";
        return response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
