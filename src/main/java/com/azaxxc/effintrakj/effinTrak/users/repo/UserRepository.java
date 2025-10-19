package com.azaxxc.effintrakj.effinTrak.users.repo;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
