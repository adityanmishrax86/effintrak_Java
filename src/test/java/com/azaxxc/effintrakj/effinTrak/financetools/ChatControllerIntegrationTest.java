package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.dtos.AIExecutionResult;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.ChatResponse;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.NaturalPromptRequest;
import com.azaxxc.effintrakj.effinTrak.financetools.metrics.AIMetricsRecorder;
import com.azaxxc.effintrakj.effinTrak.financetools.services.ConversationService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatControllerIntegrationTest {

    @Mock
    private ChatService chatService;

    @Mock
    private ConversationService conversationService;

    @Mock
    private UserService userService;

    @Mock
    private AIMetricsRecorder aiMetricsRecorder;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ChatController chatController;

    @Test
    void processNaturalPrompt_WhenSuccess_ShouldReturnDetailedResponse() {
        User user = new User();
        user.setId(1L);

        AIExecutionResult result = AIExecutionResult.success("Expense added", "ADD_EXPENSE", "conv-1", 1L, "llama3");
        result.setPromptProfile("prod");
        result.setPromptVersion("v1");

        when(authentication.getName()).thenReturn("user@example.com");
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(chatService.processPromptDetailed(anyString(), anyLong(), anyString(), anyString())).thenReturn(result);

        NaturalPromptRequest request = new NaturalPromptRequest();
        request.setPrompt("Add expense 20 for groceries");
        request.setModel("llama3");

        ResponseEntity<ChatResponse> response = chatController.processNaturalPrompt(request, authentication);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo("success");
        assertThat(response.getBody().getOperation()).isEqualTo("ADD_EXPENSE");
        assertThat(response.getBody().getModel()).isEqualTo("llama3");
    }

    @Test
    void processNaturalPrompt_WhenFailure_ShouldReturnBadRequest() {
        User user = new User();
        user.setId(2L);

        AIExecutionResult result = AIExecutionResult.failure(
                "Policy blocked",
                "OPERATION_BLOCKED",
                "DELETE_EXPENSE",
                "conv-2",
                2L,
                "llama3"
        );

        when(authentication.getName()).thenReturn("user@example.com");
        when(userService.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(chatService.processPromptDetailed(anyString(), anyLong(), anyString(), anyString())).thenReturn(result);

        NaturalPromptRequest request = new NaturalPromptRequest();
        request.setPrompt("Delete expense");
        request.setModel("llama3");

        ResponseEntity<ChatResponse> response = chatController.processNaturalPrompt(request, authentication);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo("error");
        assertThat(response.getBody().getErrorCode()).isEqualTo("OPERATION_BLOCKED");
    }
}
