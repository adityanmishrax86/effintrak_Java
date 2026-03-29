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

        return ModelSelection.selected(requestedModel);
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
