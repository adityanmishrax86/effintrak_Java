// new file
package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.dtos.ChatResponse;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.AIExecutionResult;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.NaturalPromptRequest;
import com.azaxxc.effintrakj.effinTrak.financetools.metrics.AIMetricsRecorder;
import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.services.ConversationService;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "AI Chat", description = "Natural language finance operations and conversation history")
@SecurityRequirement(name = "bearerAuth")
public class ChatController {

    private final ChatService chatService;
    private final ConversationService conversationService;
    private final UserService userService;
    private final AIMetricsRecorder aiMetricsRecorder;

    public ChatController(ChatService chatService, ConversationService conversationService, UserService userService,
                          AIMetricsRecorder aiMetricsRecorder) {
        this.chatService = chatService;
        this.conversationService = conversationService;
        this.userService = userService;
        this.aiMetricsRecorder = aiMetricsRecorder;
    }

    /**
     * Process natural language prompt with AI context and tool invocation
     */
    @PostMapping("/prompt")
    @Operation(summary = "Process a natural language finance request")
    public ResponseEntity<ChatResponse> processNaturalPrompt(@RequestBody NaturalPromptRequest request, Authentication authentication) {
        long start = System.nanoTime();
        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ChatResponse.error("Prompt cannot be empty"));
        }

        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).body(ChatResponse.error("Unauthorized user"));
        }

        try {
            String conversationId = request.getConversationId();
            if (conversationId == null || conversationId.isEmpty()) {
                conversationId = "conv-" + System.currentTimeMillis();
            }

            AIExecutionResult execution = chatService.processPromptDetailed(
                    request.getPrompt(),
                    authenticatedUserId,
                    conversationId,
                    request.getModel()
            );
            aiMetricsRecorder.recordExecution(
                    execution.getModel(),
                    execution.getOperation(),
                    execution.isSuccess(),
                    execution.getErrorCode(),
                    System.nanoTime() - start
            );
            ChatResponse response = toChatResponse(execution);
            if (execution.isSuccess()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            aiMetricsRecorder.recordExecution(
                    request.getModel(),
                    "UNHANDLED",
                    false,
                    "UNEXPECTED_ERROR",
                    System.nanoTime() - start
            );
            return ResponseEntity.internalServerError()
                    .body(ChatResponse.error("Error processing your request: " + e.getMessage()));
        }
    }

    /**
     * Simple query endpoint for quick requests
     */
    @PostMapping("/prompt/simple")
    public ResponseEntity<ChatResponse> processSimplePrompt(
            @RequestParam String prompt,
            Authentication authentication) {

        NaturalPromptRequest request = new NaturalPromptRequest();
        request.setPrompt(prompt);
        return processNaturalPrompt(request, authentication);
    }

    /**
     * Get all conversations for a user
     */
    @GetMapping("/conversations")
    public ResponseEntity<List<ChatConversation>> getUserConversations(Authentication authentication) {
        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).build();
        }
        try {
            List<ChatConversation> conversations = conversationService.getUserConversations(authenticatedUserId);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get paginated conversations for a user
     */
    @GetMapping("/conversations/paginated")
    public ResponseEntity<Page<ChatConversation>> getUserConversationsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).build();
        }
        try {
            Page<ChatConversation> conversations = conversationService.getUserConversationsPaginated(authenticatedUserId, page, size);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get a specific conversation by ID
     */
    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<ChatConversation> getConversation(@PathVariable String conversationId, Authentication authentication) {
        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).build();
        }
        try {
            return conversationService.getConversation(conversationId, authenticatedUserId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Update conversation title and description
     */
    @PutMapping("/conversations/{conversationId}")
    public ResponseEntity<ChatConversation> updateConversation(
            @PathVariable String conversationId,
            @RequestBody Map<String, String> updates,
            Authentication authentication) {
        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).build();
        }
        try {
            String title = updates.get("title");
            String description = updates.get("description");
            ChatConversation updated = conversationService.updateConversation(conversationId, title, description, authenticatedUserId);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Delete a conversation
     */
    @DeleteMapping("/conversations/{conversationId}")
    public ResponseEntity<String> deleteConversation(
            @PathVariable String conversationId,
            Authentication authentication) {
        Long authenticatedUserId = resolveAuthenticatedUserId(authentication);
        if (authenticatedUserId == null) {
            return ResponseEntity.status(401).body("Unauthorized user");
        }
        try {
            conversationService.deleteConversation(conversationId, authenticatedUserId);
            return ResponseEntity.ok("Conversation deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting conversation");
        }
    }

    private Long resolveAuthenticatedUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            return null;
        }
        return userService.findByEmail(authentication.getName())
                .map(user -> user.getId())
                .orElse(null);
    }

    private ChatResponse toChatResponse(AIExecutionResult execution) {
        ChatResponse response = new ChatResponse(execution.getMessage(), execution.getUserId(), execution.getConversationId());
        response.setStatus(execution.isSuccess() ? "success" : "error");
        response.setOperation(execution.getOperation());
        response.setErrorCode(execution.getErrorCode());
        response.setModel(execution.getModel());
        response.setPromptProfile(execution.getPromptProfile());
        response.setPromptVersion(execution.getPromptVersion());
        response.setWarnings(execution.getWarnings());
        return response;
    }

}
