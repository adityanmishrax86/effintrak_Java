package com.azaxxc.effintrakj.effinTrak.users.service;

import com.azaxxc.effintrakj.effinTrak.users.models.RefreshTokens;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public void createRefreshToken(User user, String token, long expiryMs) {

        RefreshTokens refreshTokens = refreshTokenRepository.findByUserId(user.getId()).orElse(new RefreshTokens());

        refreshTokens.setToken(token);
        refreshTokens.setUser(user);
        refreshTokens.setExpiryDate(Instant.now().plus(expiryMs, ChronoUnit.MILLIS));

        refreshTokenRepository.save(refreshTokens);
    }

    public boolean validateRefreshToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        Optional<RefreshTokens> refreshToken = refreshTokenRepository.findByToken(token);
        return refreshToken
                .map(rt -> rt.getExpiryDate().isAfter(Instant.now()))
                .orElse(false);
    }

    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }


}
