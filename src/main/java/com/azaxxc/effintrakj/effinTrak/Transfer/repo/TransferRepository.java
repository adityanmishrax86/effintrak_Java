package com.azaxxc.effintrakj.effinTrak.Transfer.repo;

import com.azaxxc.effintrakj.effinTrak.Transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByUserId(Long userId);
}

