package com.azaxxc.effintrakj.effinTrak.Notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private String type;
    private Boolean isRead;
    private LocalDateTime createdAt;
}

