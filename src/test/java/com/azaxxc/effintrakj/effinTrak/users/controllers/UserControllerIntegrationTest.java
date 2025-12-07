package com.azaxxc.effintrakj.effinTrak.users.controllers;

import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.users.service.UserService userService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.users.service.RefreshTokenService refreshTokenService;

    @Test
    void registerUser_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john.doe@example.com");
        registerRequest.setPhoneNumber("1234567890");
        registerRequest.setPassword("password123");
        registerRequest.setRole("USER");

        // When & Then
        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    void login_WithValidCredentials_ShouldReturnTokens() throws Exception {
        // Given
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        com.azaxxc.effintrakj.effinTrak.users.models.User user = new com.azaxxc.effintrakj.effinTrak.users.models.User();
        user.setEmail("test@example.com");

        when(userService.authenticateUser(any(LoginRequestDTO.class))).thenReturn(java.util.Optional.of(user));
        when(jwtUtil.generateToken(anyString())).thenReturn("test-token");
        when(jwtUtil.generateRefreshToken(anyString())).thenReturn("test-refresh-token");

        // When & Then
        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    void login_WithInvalidCredentials_ShouldReturnUnauthorized() throws Exception {
        // Given
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("wrongpassword");

        when(userService.authenticateUser(any(LoginRequestDTO.class))).thenReturn(java.util.Optional.empty());

        // When & Then
        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Authentication failed"));
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void getUserProfile_WhenAuthenticated_ShouldReturnProfile() throws Exception {
        // Given
        com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO userResponseDTO = 
                new com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO();
        userResponseDTO.setFirstName("John");
        userResponseDTO.setLastName("Doe");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setPhoneNumber("1234567890");
        userResponseDTO.setActive(true);

        when(userService.fetchUsersDetails("test@example.com"))
                .thenReturn(java.util.Optional.of(userResponseDTO));

        // When & Then
        mockMvc.perform(get("/api/v1/users/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void logout_ShouldReturnSuccess() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/v1/users/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Logged out Successful"));
    }
}

