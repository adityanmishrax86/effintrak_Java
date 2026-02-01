package com.azaxxc.effintrakj.effinTrak.financetools;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {
    private String response;
    private long userId;
    private String conversationId;
    private long timestamp;
    private String status;

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
}

