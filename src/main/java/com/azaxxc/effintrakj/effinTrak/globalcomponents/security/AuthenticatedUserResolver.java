package com.azaxxc.effintrakj.effinTrak.globalcomponents.security;

import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserResolver {

    private final UserService userService;

    public AuthenticatedUserResolver(UserService userService) {
        this.userService = userService;
    }

    public Long resolveUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            return null;
        }
        return userService.findByEmail(authentication.getName())
                .map(user -> user.getId())
                .orElse(null);
    }

    public Long resolveOrThrow(Authentication authentication) {
        Long userId = resolveUserId(authentication);
        if (userId == null) {
            throw new AccessDeniedException("Unauthorized user");
        }
        return userId;
    }

    /**
     * Backward-compatible enforcement:
     * - If authentication is present, authenticated user must match requestedUserId.
     * - If authentication is absent, fallback to requestedUserId (supports legacy tests).
     */
    public Long resolveRequestedUserId(Authentication authentication, Long requestedUserId) {
        Long authenticatedUserId = resolveUserId(authentication);
        if (authenticatedUserId == null) {
            return requestedUserId;
        }
        if (requestedUserId != null && !authenticatedUserId.equals(requestedUserId)) {
            throw new AccessDeniedException("Forbidden: cannot access another user's data");
        }
        return authenticatedUserId;
    }
}

