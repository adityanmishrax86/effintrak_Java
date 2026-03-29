package com.azaxxc.effintrakj.effinTrak.financetools.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chat_messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private ChatConversation conversation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userMessage;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String aiResponse;

    @Column(nullable = false)
    private String messageType; // "QUERY", "EXPENSE", "INCOME", "SUMMARY", etc.

    @Column(length = 120)
    private String operation;

    @Column(length = 120)
    private String model;

    @Column(length = 60)
    private String promptProfile;

    @Column(length = 60)
    private String promptVersion;

    @Column(length = 120)
    private String errorCode;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Builder.Default
    private boolean success = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
