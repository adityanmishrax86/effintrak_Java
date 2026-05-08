package com.azaxxc.effintrakj.effinTrak.financetools.config;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIModelManagerTest {

    @Test
    void resolveModel_WhenNoRequest_UsesDefault() {
        AIChatProperties properties = new AIChatProperties();
        properties.setDefaultModel("llama-3.3-70b-versatile");
        AIModelManager modelManager = new AIModelManager(properties);

        AIModelManager.ModelSelection selection = modelManager.resolveModel(null);

        assertEquals("llama-3.3-70b-versatile", selection.model());
        assertNull(selection.warning());
    }

    @Test
    void resolveModel_WhenOverrideDisabled_ReturnsDefaultWithWarning() {
        AIChatProperties properties = new AIChatProperties();
        properties.setDefaultModel("llama-3.3-70b-versatile");
        properties.setAllowModelOverride(false);
        AIModelManager modelManager = new AIModelManager(properties);

        AIModelManager.ModelSelection selection = modelManager.resolveModel("llama3-8b-8192");

        assertEquals("llama-3.3-70b-versatile", selection.model());
        assertNotNull(selection.warning());
    }

    @Test
    void resolveModel_WhenSupportedOverrideEnabled_ReturnsDefaultUntilRuntimeSupportsOverride() {
        AIChatProperties properties = new AIChatProperties();
        properties.setDefaultModel("llama-3.3-70b-versatile");
        properties.setAllowModelOverride(true);
        properties.setSupportedModels(new LinkedHashSet<>(List.of("llama-3.3-70b-versatile", "llama3-8b-8192")));
        AIModelManager modelManager = new AIModelManager(properties);

        AIModelManager.ModelSelection selection = modelManager.resolveModel("llama3-8b-8192");

        assertEquals("llama-3.3-70b-versatile", selection.model());
        assertNotNull(selection.warning());
        assertTrue(selection.warning().contains("not yet applied"));
    }

    @Test
    void resolveModel_WhenRequestedIsDefault_DoesNotWarn() {
        AIChatProperties properties = new AIChatProperties();
        properties.setDefaultModel("llama-3.3-70b-versatile");
        properties.setAllowModelOverride(false);
        AIModelManager modelManager = new AIModelManager(properties);

        AIModelManager.ModelSelection selection = modelManager.resolveModel("llama-3.3-70b-versatile");

        assertEquals("llama-3.3-70b-versatile", selection.model());
        assertNull(selection.warning());
    }
}
