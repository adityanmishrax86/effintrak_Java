package com.azaxxc.effintrakj.effinTrak.Credits.service;

import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.Credits.repo.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public Optional<Credit> getCreditById(Long id) {
        return creditRepository.findById(id);
    }

    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}

