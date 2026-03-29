package com.azaxxc.effintrakj.effinTrak.financetools.dtos;

public class NaturalPromptRequest {
    private String prompt;
    private long userId;
    private String conversationId;
    private String model;

    public NaturalPromptRequest() {}

    public NaturalPromptRequest(String prompt, long userId) {
        this.prompt = prompt;
        this.userId = userId;
        this.conversationId = "conv-" + System.currentTimeMillis();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
