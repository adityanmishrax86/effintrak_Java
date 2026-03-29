package com.azaxxc.effintrakj.effinTrak.usersettings.controller;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsRequest;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.service.UserSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-settings")
@Tag(name = "User Settings", description = "Per-user preferences used by the application and AI context")
@SecurityRequirement(name = "bearerAuth")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public UserSettingsController(UserSettingsService userSettingsService,
                                  AuthenticatedUserResolver authenticatedUserResolver) {
        this.userSettingsService = userSettingsService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user settings")
    public ResponseEntity<UserSettingsResponse> getCurrentUserSettings(Authentication authentication) {
        Long userId = authenticatedUserResolver.resolveOrThrow(authentication);
        return ResponseEntity.ok(userSettingsService.getEffectiveSettings(userId));
    }

    @PutMapping("/me")
    @Operation(summary = "Create or update current user settings")
    public ResponseEntity<UserSettingsResponse> updateCurrentUserSettings(
            @Valid @RequestBody UserSettingsRequest request,
            Authentication authentication) {
        Long userId = authenticatedUserResolver.resolveOrThrow(authentication);
        return ResponseEntity.ok(userSettingsService.updateForUser(userId, request));
    }
}
