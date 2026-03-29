package com.azaxxc.effintrakj.effinTrak.globalcomponents;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JWTUtil {

    private final Key key;
    private final long expirationTime;
    private final long refreshExpirationTime;

    public JWTUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms:3600000}") long expirationTime,
            @Value("${security.jwt.refresh-expiration-ms:36000000}") long refreshExpirationTime
    ) {
        this.key = Keys.hmacShaKeyFor(toSigningKey(secret));
        this.expirationTime = expirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    private byte[] toSigningKey(String secret) {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("security.jwt.secret must be configured");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(secret.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }


    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationTime))
                .signWith(key)
                .compact();
    }


    public String extractUser(String token) {
        return Jwts.parserBuilder().
                setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException je) {
            return false;
        }
    }

}
