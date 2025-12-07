package com.azaxxc.effintrakj.effinTrak.users.service;

import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.util.builders.UserTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private RegisterRequest registerRequest;
    private LoginRequestDTO loginRequestDTO;

    @BeforeEach
    void setUp() {
        testUser = UserTestDataBuilder.aUser()
                .withEmail("test@example.com")
                .withPassword("password123")
                .build();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john.doe@example.com");
        registerRequest.setPhoneNumber("1234567890");
        registerRequest.setPassword("password123");
        registerRequest.setRole("USER");

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("test@example.com");
        loginRequestDTO.setPassword("password123");
    }

    @Test
    void registerUser_WhenUserDoesNotExist_ShouldSaveUser() {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // When
        userService.registerUser(registerRequest);

        // Then
        verify(userRepository, times(1)).findByEmail(registerRequest.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_WhenUserAlreadyExists_ShouldThrowException() {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));

        // When & Then
        assertThatThrownBy(() -> userService.registerUser(registerRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("already exists");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void authenticateUser_WithValidCredentials_ShouldReturnUser() {
        // Given
        when(userRepository.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.of(testUser));

        // When
        Optional<User> result = userService.authenticateUser(loginRequestDTO);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo(loginRequestDTO.getEmail());
        verify(userRepository, times(1)).findByEmail(loginRequestDTO.getEmail());
    }

    @Test
    void authenticateUser_WithInvalidEmail_ShouldReturnEmpty() {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // When
        Optional<User> result = userService.authenticateUser(loginRequestDTO);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void authenticateUser_WithInvalidPassword_ShouldReturnEmpty() {
        // Given
        loginRequestDTO.setPassword("wrongpassword");
        when(userRepository.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.of(testUser));

        // When
        Optional<User> result = userService.authenticateUser(loginRequestDTO);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void fetchUsersDetails_WhenUserExists_ShouldReturnUserResponseDTO() {
        // Given
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(testUser));

        // When
        Optional<UserResponseDTO> result = userService.fetchUsersDetails(email);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo(email);
        assertThat(result.get().getFirstName()).isEqualTo(testUser.getFirstName());
        assertThat(result.get().getLastName()).isEqualTo(testUser.getLastName());
    }

    @Test
    void fetchUsersDetails_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        Optional<UserResponseDTO> result = userService.fetchUsersDetails(email);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUser() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // When
        Optional<User> result = userService.findById(userId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(testUser.getId());
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Given
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        Optional<User> result = userService.findById(userId);

        // Then
        assertThat(result).isEmpty();
    }
}

