package com.azaxxc.effintrakj.effinTrak.accounts.controller;

import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountCreateRequestDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountResponseDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, UserService userService, GlobalResponseService globalResponseService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createBankAccount(@Valid @RequestBody BankAccountCreateRequestDTO bankAccountCreateRequestDTO) {

        Long userId = bankAccountCreateRequestDTO.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<BankAccountResponseDTO> banksOfUser = bankAccountService.findByUserId(bankAccountCreateRequestDTO.getUserId());


        String newBankName = bankAccountCreateRequestDTO.getBankName().trim();
        if (banksOfUser.stream()
                .map(BankAccountResponseDTO::getName)
                .noneMatch(x -> x != null && x.trim().equalsIgnoreCase(newBankName))) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setName(newBankName);
            bankAccount.setUser(user);
            bankAccountService.saveBankAccount(bankAccount);
            return globalResponseService.success("Bank account created successfully");
        } else {
            return globalResponseService.error("User already has a bank account", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllBankAccounts(@Valid @PathVariable Long userId) {
        User user  = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<BankAccountResponseDTO> accounts = bankAccountService.findByUserId(userId);
        return globalResponseService.success(accounts, "Fetched all bank accounts");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return globalResponseService.success(null, "Bank account deleted successfully");
    }
}
