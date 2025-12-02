package com.azaxxc.effintrakj.effinTrak.globalcomponents.filters;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;
    private UserService userService;

    public JwtAuthFilter(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(null != authHeader && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if(jwtUtil.isValidToken(token) ) {
                String userName = jwtUtil.extractUser(token);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/swagger-ui.html") ||
               path.startsWith("/swagger-ui/") ||
               path.equals("/v3/api-docs") ||
               path.startsWith("/v3/api-docs/") ||
               path.equals("/v3/api-docs.yaml") ||
               path.startsWith("/v3/api-docs.yaml/") ||
               path.startsWith("/webjars/") ||
               path.equals("/api/v1/users/register") ||
               path.equals("/api/v1/users/login");
    }

}
