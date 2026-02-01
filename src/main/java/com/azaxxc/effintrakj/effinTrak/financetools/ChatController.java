// new file
package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.financetools.dtos.ChatResponse;
import com.azaxxc.effintrakj.effinTrak.financetools.dtos.NaturalPromptRequest;
import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.services.ConversationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final FinanceTools financeTools;
    private final ChatService chatService;
    private final ConversationService conversationService;

    public ChatController(FinanceTools financeTools, ChatService chatService, ConversationService conversationService) {
        this.financeTools = financeTools;
        this.chatService = chatService;
        this.conversationService = conversationService;
    }

    /**
     * Process natural language prompt with AI context and tool invocation
     */
    @PostMapping("/prompt")
    public ResponseEntity<ChatResponse> processNaturalPrompt(@RequestBody NaturalPromptRequest request) {
        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ChatResponse.error("Prompt cannot be empty"));
        }

        if (request.getUserId() <= 0) {
            return ResponseEntity.badRequest()
                    .body(ChatResponse.error("Valid userId is required"));
        }

        try {
            String conversationId = request.getConversationId();
            if (conversationId == null || conversationId.isEmpty()) {
                conversationId = "conv-" + System.currentTimeMillis();
            }

            String response = chatService.processPrompt(
                    request.getPrompt(),
                    request.getUserId(),
                    conversationId
            );

            return ResponseEntity.ok(
                    new ChatResponse(response, request.getUserId(), conversationId)
            );
        } catch (Exception e) {
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
            @RequestParam long userId) {

        NaturalPromptRequest request = new NaturalPromptRequest(prompt, userId);
        return processNaturalPrompt(request);
    }

    /**
     * Get all conversations for a user
     */
    @GetMapping("/conversations")
    public ResponseEntity<List<ChatConversation>> getUserConversations(@RequestParam long userId) {
        try {
            List<ChatConversation> conversations = conversationService.getUserConversations(userId);
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
            @RequestParam long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<ChatConversation> conversations = conversationService.getUserConversationsPaginated(userId, page, size);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get a specific conversation by ID
     */
    @GetMapping("/conversations/{conversationId}")
    public ResponseEntity<ChatConversation> getConversation(@PathVariable String conversationId) {
        try {
            return conversationService.getConversation(conversationId)
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
            @RequestBody Map<String, String> updates) {
        try {
            String title = updates.get("title");
            String description = updates.get("description");
            ChatConversation updated = conversationService.updateConversation(conversationId, title, description);
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
            @RequestParam long userId) {
        try {
            conversationService.deleteConversation(conversationId, userId);
            return ResponseEntity.ok("Conversation deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting conversation");
        }
    }


}
