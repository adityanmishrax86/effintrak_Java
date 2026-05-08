package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.config.AIChatProperties;
import com.azaxxc.effintrakj.effinTrak.financetools.config.AIModelManager;
import com.azaxxc.effintrakj.effinTrak.financetools.config.PromptTemplateService;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.AIExecutionResult;
import com.azaxxc.effintrakj.effinTrak.financetools.guardrails.AIGuardrails;
import com.azaxxc.effintrakj.effinTrak.financetools.guardrails.AIToolPolicy;
import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.services.AIContextService;
import com.azaxxc.effintrakj.effinTrak.financetools.services.ConversationService;
import com.azaxxc.effintrakj.effinTrak.financetools.validation.AIResponseValidator;
import com.azaxxc.effintrakj.effinTrak.financetools.validation.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.model.ChatModel;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private ChatModel chatModel;

    @Mock
    private FinanceTools financeTools;

    @Mock
    private AIContextService aiContextService;

    @Mock
    private ConversationService conversationService;

    @Mock
    private AIResponseValidator responseValidator;

    @Mock
    private AIGuardrails guardrails;

    @Mock
    private AIToolPolicy toolPolicy;

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        AIChatProperties properties = new AIChatProperties();
        properties.setDefaultModel("llama-3.3-70b-versatile");
        properties.setSupportedModels(new LinkedHashSet<>(List.of("llama-3.3-70b-versatile", "llama3-8b-8192")));
        properties.setPromptProfile("dev");
        properties.setPromptVersion("test-v1");

        chatService = new ChatService(
                chatModel,
                financeTools,
                aiContextService,
                conversationService,
                responseValidator,
                guardrails,
                toolPolicy,
                new AIModelManager(properties),
                properties,
                new PromptTemplateService(properties)
        );
    }

    @Test
    void processPromptDetailed_WhenPromptEmpty_ShouldReturnInvalidInput() {
        AIExecutionResult result = chatService.processPromptDetailed("  ", 1L, "conv-1", null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorCode()).isEqualTo("INVALID_INPUT");
        assertThat(result.getPromptProfile()).isEqualTo("dev");
        assertThat(result.getPromptVersion()).isEqualTo("test-v1");
    }

    @Test
    void processPromptDetailed_WhenRateLimited_ShouldReturnRateLimitError() {
        when(responseValidator.validateUserId(1L)).thenReturn(ValidationResult.success());
        when(guardrails.checkRateLimit(1L)).thenReturn(AIGuardrails.RateLimitResult.limited("too many requests"));

        AIExecutionResult result = chatService.processPromptDetailed("show summary", 1L, "conv-1", null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorCode()).isEqualTo("RATE_LIMIT_EXCEEDED");
        assertThat(result.getMessage()).contains("Rate limit exceeded");
    }

    @Test
    void processPromptDetailed_WhenGreeting_ShouldReturnGeneralAssistance() {
        ChatConversation conversation = new ChatConversation();
        conversation.setId(100L);

        when(responseValidator.validateUserId(1L)).thenReturn(ValidationResult.success());
        when(guardrails.checkRateLimit(1L)).thenReturn(AIGuardrails.RateLimitResult.allowed());
        when(conversationService.getOrCreateConversation(1L, "conv-1")).thenReturn(conversation);
        when(aiContextService.buildUserContext(1L)).thenReturn("ctx");

        AIExecutionResult result = chatService.processPromptDetailed("hi", 1L, "conv-1", null);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getOperation()).isEqualTo("GENERAL_ASSISTANCE");
        assertThat(result.getMessage()).contains("I can help manage your finances");
        verify(conversationService, times(1)).saveMessage(
                eq(100L),
                eq("hi"),
                eq(result.getMessage()),
                eq("GENERAL_ASSISTANCE"),
                eq("GENERAL_ASSISTANCE"),
                eq("llama-3.3-70b-versatile"),
                eq("dev"),
                eq("test-v1"),
                eq(null),
                eq(true)
        );
    }

    @Test
    void isBatchAddRequest_WhenPromptContainsMultipleAmounts_ShouldReturnTrue() throws Exception {
        Method method = ChatService.class.getDeclaredMethod("isBatchAddRequest", String.class);
        method.setAccessible(true);

        boolean isBatch = (boolean) method.invoke(chatService, "add expense 120 for groceries and 45 for taxi");

        assertThat(isBatch).isTrue();
    }
}
