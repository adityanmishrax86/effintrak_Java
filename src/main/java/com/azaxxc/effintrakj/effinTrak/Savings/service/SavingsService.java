package com.azaxxc.effintrakj.effinTrak.Savings.service;

import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import com.azaxxc.effintrakj.effinTrak.Savings.repo.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsService {
    private final SavingsRepository savingsRepository;

    @Autowired
    public SavingsService(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    public Savings saveSavings(Savings savings) {
        return savingsRepository.save(savings);
    }

    public List<Savings> getAllSavings() {
        return savingsRepository.findAll();
    }

    public Optional<Savings> getSavingsById(Long id) {
        return savingsRepository.findById(id);
    }

    public void deleteSavings(Long id) {
        savingsRepository.deleteById(id);
    }
}

