package com.azaxxc.effintrakj.effinTrak.financetools.services;

import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import com.azaxxc.effintrakj.effinTrak.financetools.repos.ChatConversationRepository;
import com.azaxxc.effintrakj.effinTrak.financetools.repos.ChatMessageRepository;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversationServiceTest {

    @Mock
    private ChatConversationRepository conversationRepository;

    @Mock
    private ChatMessageRepository messageRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConversationService conversationService;

    @Test
    void getOrCreateConversation_WhenMissing_PreservesRequestedConversationId() {
        long userId = 42L;
        String requestedConversationId = "conv-42-client-seeded";

        User user = new User();
        user.setId(userId);

        when(conversationRepository.findByConversationId(requestedConversationId)).thenReturn(Optional.empty());
        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(conversationRepository.save(any(ChatConversation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ChatConversation created = conversationService.getOrCreateConversation(userId, requestedConversationId);

        ArgumentCaptor<ChatConversation> captor = ArgumentCaptor.forClass(ChatConversation.class);
        verify(conversationRepository).save(captor.capture());
        assertThat(captor.getValue().getConversationId()).isEqualTo(requestedConversationId);
        assertThat(created.getConversationId()).isEqualTo(requestedConversationId);
    }
}
