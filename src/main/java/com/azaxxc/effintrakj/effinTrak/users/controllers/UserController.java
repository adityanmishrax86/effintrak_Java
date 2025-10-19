package com.azaxxc.effintrakj.effinTrak.users.controllers;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil;
import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.RefreshTokenService;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    public UserService userService;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public UserController(UserService userService, JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest userRegisterRequest) {

        userService.registerUser(userRegisterRequest);

        return ResponseEntity.ok("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<User> user = userService.authenticateUser(loginRequestDTO);
                if(user.isEmpty()) {
                    return ResponseEntity.status(401).body("Authentication failed");
                }
            String token = jwtUtil.generateToken(loginRequestDTO.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(loginRequestDTO.getEmail());

            refreshTokenService.createRefreshToken(user.get(), refreshToken, 36000000);
            Map<String, String> tokens = Map.of(
                    "token", token,
                    "refreshToken",refreshToken
            );

            return ResponseEntity.ok(tokens);

    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshRequest = request.get("refreshToken");

        if(refreshTokenService.validateRefreshToken(refreshRequest)) {
            String userEmail = jwtUtil.extractUser(refreshRequest);
            String newToken = jwtUtil.generateToken(userEmail);

            return ResponseEntity.ok(Collections.singletonMap("token", newToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }



    @GetMapping("/profile")
    public ResponseEntity<Map<String,String>> getUserProfiile(Authentication authentication) {
        String email = authentication.getName();
        Optional<UserResponseDTO> userResponseDTO = userService.fetchUsersDetails(email);
        if(userResponseDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message","User details not found"));
        }
        UserResponseDTO user = userResponseDTO.get();
        Map<String, String> userProfile = Map.of(
                "firstName", user.getFirstName(),
                "lastName", user.getLastName(),
                "email", user.getEmail(),
                "phoneNumber", user.getPhoneNumber(),
                "role", null == user.getRole() ? "BASIC" : user.getRole(),
                "isActive", String.valueOf(user.isActive())
        );
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        userService.logout();
        return ResponseEntity.ok("Logged out Successful");
    }
}
