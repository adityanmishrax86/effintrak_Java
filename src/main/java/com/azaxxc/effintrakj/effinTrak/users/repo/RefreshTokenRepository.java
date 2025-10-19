package com.azaxxc.effintrakj.effinTrak.users.repo;

import com.azaxxc.effintrakj.effinTrak.users.models.RefreshTokens;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokens,Long> {

    Optional<RefreshTokens> findByToken(String token);

    void deleteByUser(User user);
}
