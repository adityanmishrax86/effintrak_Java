package com.azaxxc.effintrakj.effinTrak.financetools.repos;

import com.azaxxc.effintrakj.effinTrak.financetools.models.ChatConversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long> {
    Optional<ChatConversation> findByConversationId(String conversationId);

    Page<ChatConversation> findByUserId(Long userId, Pageable pageable);

    List<ChatConversation> findByUserIdOrderByUpdatedAtDesc(Long userId);

    void deleteByConversationId(String conversationId);
}

