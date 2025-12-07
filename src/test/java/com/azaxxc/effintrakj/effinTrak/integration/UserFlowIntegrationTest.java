package com.azaxxc.effintrakj.effinTrak.integration;

import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.util.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class UserFlowIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john.doe@example.com");
        registerRequest.setPhoneNumber("1234567890");
        registerRequest.setPassword("password123");
        registerRequest.setRole("USER");
    }

    @Test
    void completeUserRegistrationAndLoginFlow_ShouldSucceed() {
        // Given - User registration
        userService.registerUser(registerRequest);

        // When - Verify user was saved
        Optional<User> savedUser = userRepository.findByEmail(registerRequest.getEmail());

        // Then
        assertThat(savedUser).isPresent();
        assertThat(savedUser.get().getEmail()).isEqualTo(registerRequest.getEmail());
        assertThat(savedUser.get().getFirstName()).isEqualTo(registerRequest.getFirstName());
        assertThat(savedUser.get().getLastName()).isEqualTo(registerRequest.getLastName());

        // When - Attempt login
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail(registerRequest.getEmail());
        loginRequest.setPassword(registerRequest.getPassword());

        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest);

        // Then
        assertThat(authenticatedUser).isPresent();
        assertThat(authenticatedUser.get().getEmail()).isEqualTo(registerRequest.getEmail());
    }

    @Test
    void registerUser_WithDuplicateEmail_ShouldThrowException() {
        // Given - Register first user
        userService.registerUser(registerRequest);

        // When & Then - Try to register with same email
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            userService.registerUser(registerRequest);
        });
    }

    @Test
    void login_WithInvalidPassword_ShouldFail() {
        // Given - Register user
        userService.registerUser(registerRequest);

        // When - Attempt login with wrong password
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail(registerRequest.getEmail());
        loginRequest.setPassword("wrongpassword");

        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest);

        // Then
        assertThat(authenticatedUser).isEmpty();
    }

    @Test
    void fetchUserDetails_AfterRegistration_ShouldReturnUserDetails() {
        // Given - Register user
        userService.registerUser(registerRequest);

        // When
        Optional<com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO> userDetails =
                userService.fetchUsersDetails(registerRequest.getEmail());

        // Then
        assertThat(userDetails).isPresent();
        assertThat(userDetails.get().getEmail()).isEqualTo(registerRequest.getEmail());
        assertThat(userDetails.get().getFirstName()).isEqualTo(registerRequest.getFirstName());
    }
}

