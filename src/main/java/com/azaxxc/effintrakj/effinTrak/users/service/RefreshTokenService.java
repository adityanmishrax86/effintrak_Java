package com.azaxxc.effintrakj.effinTrak.users.service;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.exceptions.ResourceNotFoundException;
import com.azaxxc.effintrakj.effinTrak.users.models.RefreshTokens;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.RefreshTokenRepository;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshTokens createRefreshToken(User user, String token, long expiryMs) {

        RefreshTokens refreshTokens = new RefreshTokens();
        refreshTokens.setToken(token);
        refreshTokens.setUser(user);
        refreshTokens.setExpiryDate(Instant.now().plus(expiryMs, ChronoUnit.MILLIS));

        return refreshTokenRepository.save(refreshTokens);
    }

    public boolean validateRefreshToken(String token) {
        RefreshTokens refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
        return !refreshToken.getExpiryDate().isAfter(Instant.now());
    }

    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }


}
