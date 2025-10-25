package com.azaxxc.effintrakj.effinTrak.accounts.service;

import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountResponseDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.BankAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper mapper;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountMapper mapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.mapper = mapper;
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }

    public List<BankAccountResponseDTO> findByUserId(Long userId) {

        return bankAccountRepository.findByuser_id(userId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

