package com.azaxxc.effintrakj.effinTrak.financetools.dtos;

import java.util.Map;

/**
 * Legacy DTO for manual tool invocation.
 * Kept for backward compatibility only.
 * Prefer using NaturalPromptRequest for new code.
 */
public class ChatRequest {
    private String tool;
    private Map<String, Object> params;

    public ChatRequest() {}

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}

