package com.azaxxc.effintrakj.effinTrak.financetools.services;

import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatMessage;
import com.azaxxc.effintrakj.effinTrak.financetools.repos.ChatConversationRepository;
import com.azaxxc.effintrakj.effinTrak.financetools.repos.ChatMessageRepository;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConversationService {
    private static final Logger logger = LoggerFactory.getLogger(ConversationService.class);

    private final ChatConversationRepository conversationRepository;
    private final ChatMessageRepository messageRepository;
    private final UserService userService;

    public ConversationService(ChatConversationRepository conversationRepository,
                               ChatMessageRepository messageRepository,
                               UserService userService) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    /**
     * Create a new conversation for a user
     */
    public ChatConversation createConversation(Long userId, String title) {
        logger.info("Creating new conversation for userId: {}, title: {}", userId, title);

        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        String conversationId = "conv-" + userId + "-" + System.currentTimeMillis();

        ChatConversation conversation = ChatConversation.builder()
                .conversationId(conversationId)
                .user(user)
                .title(title)
                .description("")
                .build();

        ChatConversation saved = conversationRepository.save(conversation);
        logger.info("Conversation created with ID: {}", saved.getConversationId());
        return saved;
    }

    /**
     * Get or create a conversation by ID
     */
    public ChatConversation getOrCreateConversation(Long userId, String conversationId) {
        logger.debug("Getting or creating conversation: {}", conversationId);

        Optional<ChatConversation> existing = conversationRepository.findByConversationId(conversationId);
        if (existing.isPresent()) {
            ChatConversation conv = existing.get();
            if (!conv.getUser().getId().equals(userId)) {
                throw new IllegalArgumentException("Conversation does not belong to this user");
            }
            logger.debug("Found existing conversation: {}", conversationId);
            return conv;
        }

        // Create new one
        return createConversation(userId, "Chat");
    }

    /**
     * Save a message to a conversation
     */
    public ChatMessage saveMessage(Long conversationId, String userMessage, String aiResponse, String messageType) {
        logger.debug("Saving message to conversation: {}", conversationId);

        ChatConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found with id: " + conversationId));

        ChatMessage message = ChatMessage.builder()
                .conversation(conversation)
                .userMessage(userMessage)
                .aiResponse(aiResponse)
                .messageType(messageType)
                .build();

        ChatMessage saved = messageRepository.save(message);
        logger.info("Message saved: {}", saved.getId());
        return saved;
    }

    /**
     * Get all messages in a conversation
     */
    public List<ChatMessage> getConversationMessages(Long conversationId) {
        logger.debug("Fetching messages for conversation: {}", conversationId);
        return messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);
    }

    /**
     * Get conversation by ID
     */
    public Optional<ChatConversation> getConversation(String conversationId) {
        logger.debug("Fetching conversation: {}", conversationId);
        return conversationRepository.findByConversationId(conversationId);
    }

    /**
     * Get all conversations for a user
     */
    public List<ChatConversation> getUserConversations(Long userId) {
        logger.debug("Fetching conversations for userId: {}", userId);
        return conversationRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    /**
     * Get paginated conversations for a user
     */
    public Page<ChatConversation> getUserConversationsPaginated(Long userId, int page, int size) {
        logger.debug("Fetching paginated conversations for userId: {}, page: {}, size: {}", userId, page, size);
        Pageable pageable = PageRequest.of(page, size);
        return conversationRepository.findByUserId(userId, pageable);
    }

    /**
     * Delete a conversation and all its messages
     */
    public void deleteConversation(String conversationId, Long userId) {
        logger.info("Deleting conversation: {} for userId: {}", conversationId, userId);

        ChatConversation conversation = conversationRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found: " + conversationId));

        if (!conversation.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Conversation does not belong to this user");
        }

        conversationRepository.deleteByConversationId(conversationId);
        logger.info("Conversation deleted: {}", conversationId);
    }

    /**
     * Update conversation title and description
     */
    public ChatConversation updateConversation(String conversationId, String title, String description) {
        logger.debug("Updating conversation: {}", conversationId);

        ChatConversation conversation = conversationRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found: " + conversationId));

        if (title != null && !title.isEmpty()) {
            conversation.setTitle(title);
        }
        if (description != null) {
            conversation.setDescription(description);
        }

        return conversationRepository.save(conversation);
    }

    /**
     * Get recent messages from a conversation (useful for context)
     */
    public List<ChatMessage> getRecentMessages(Long conversationId, int limit) {
        logger.debug("Fetching recent {} messages for conversation: {}", limit, conversationId);
        Pageable pageable = PageRequest.of(0, limit);
        Page<ChatMessage> messages = messageRepository.findByConversationIdOrderByCreatedAtDesc(conversationId, pageable);
        return messages.getContent();
    }
}

