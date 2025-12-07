package com.azaxxc.effintrakj.effinTrak.util;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Collections;

public class SecurityTestUtils {

    private static final JWTUtil jwtUtil = new JWTUtil();

    /**
     * Creates a mock JWT token for testing
     */
    public static String createTestToken(String email) {
        return jwtUtil.generateToken(email);
    }

    /**
     * Creates a mock refresh token for testing
     */
    public static String createTestRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }

    /**
     * Sets up security context with authenticated user
     */
    public static void setupSecurityContext(String email) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    /**
     * Clears the security context
     */
    public static void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Creates a RequestPostProcessor for MockMvc to authenticate requests
     */
    public static RequestPostProcessor jwtAuthentication(String email) {
        return SecurityMockMvcRequestPostProcessors.authentication(
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );
    }

    /**
     * Creates a RequestPostProcessor with a JWT token in Authorization header
     * Note: This is a simplified version - in real tests, you may need to configure
     * the JwtAuthFilter to accept these tokens
     */
    public static RequestPostProcessor jwtToken(String token) {
        return request -> {
            request.addHeader("Authorization", "Bearer " + token);
            return request;
        };
    }
}

