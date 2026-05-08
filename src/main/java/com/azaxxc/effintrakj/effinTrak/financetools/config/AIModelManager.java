package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.springframework.stereotype.Component;

@Component
public class AIModelManager {

    private final AIChatProperties properties;

    public AIModelManager(AIChatProperties properties) {
        this.properties = properties;
    }

    public ModelSelection resolveModel(String requestedModel) {
        String defaultModel = properties.getDefaultModel();
        if (requestedModel == null || requestedModel.isBlank()) {
            return ModelSelection.selected(defaultModel);
        }

        if (requestedModel.equals(defaultModel)) {
            return ModelSelection.selected(defaultModel);
        }

        if (!properties.isAllowModelOverride()) {
            return ModelSelection.withWarning(
                    defaultModel,
                    "Model override is disabled. Using default model."
            );
        }

        if (!properties.getSupportedModels().contains(requestedModel)) {
            return ModelSelection.withWarning(
                    defaultModel,
                    "Requested model is not supported. Using default model."
            );
        }

        // Runtime currently uses the globally configured ChatModel bean.
        // Keep API behavior explicit: accept the request but continue with default model.
        return ModelSelection.withWarning(
                defaultModel,
                "Per-request model override is accepted but not yet applied at runtime. Using default model."
        );
    }

    public record ModelSelection(String model, String warning) {
        public static ModelSelection selected(String model) {
            return new ModelSelection(model, null);
        }

        public static ModelSelection withWarning(String model, String warning) {
            return new ModelSelection(model, warning);
        }
    }
}
