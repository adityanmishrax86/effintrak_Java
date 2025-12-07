package com.azaxxc.effintrakj.effinTrak.Notification.controller;

import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.service.NotificationService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;


    @MockBean  // <-- for JwtAuthFilter
    private com.azaxxc.effintrakj.effinTrak.users.service.UserService userService;

    @MockBean  // <-- for JwtAuthFilter (if used)
    private com.azaxxc.effintrakj.effinTrak.users.service.RefreshTokenService refreshTokenService;

    @Test
    @WithMockUser
    void getNotificationsByUserId_ShouldReturnNotifications() throws Exception {
        // Given
        Long userId = 1L;
        NotificationResponseDTO notification = new NotificationResponseDTO();

        when(notificationService.getNotificationsByUserId(userId)).thenReturn(List.of(notification));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/notifications/user/{userId}", userId))
                .andExpect(status().isOk());
    }
}

