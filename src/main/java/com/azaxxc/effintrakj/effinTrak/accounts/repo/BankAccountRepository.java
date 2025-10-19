package com.azaxxc.effintrakj.effinTrak.accounts.repo;

import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByuser_id(Long userId);
}

