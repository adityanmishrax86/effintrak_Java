package com.azaxxc.effintrakj.effinTrak.Transfer.service;

import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.model.Transfer;
import com.azaxxc.effintrakj.effinTrak.Transfer.repo.TransferRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.TransferMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final TransferMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TransferService(TransferRepository transferRepository,
                           BankAccountService bankAccountService,
                           UserService userService,
                           TransferMapper mapper) {
        this.transferRepository = transferRepository;
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Transactional
    public TransferResponseDTO createTransfer(TransferRequestDTO dto, User user) {
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (dto.getFromAccountId().equals(dto.getToAccountId())) {
            throw new IllegalArgumentException("From and To accounts cannot be the same.");
        }

        BankAccount fromAccount = bankAccountService.getBankAccountById(dto.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("From account not found"));
        BankAccount toAccount = bankAccountService.getBankAccountById(dto.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("To account not found"));

        // Verify both accounts belong to the user
        if (!fromAccount.getUser().getId().equals(user.getId()) || !toAccount.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Both accounts must belong to the user");
        }

        // Check sufficient balance
        if (fromAccount.getBalance() < dto.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in from account");
        }

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance() - dto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + dto.getAmount());

        bankAccountService.saveBankAccount(fromAccount);
        bankAccountService.saveBankAccount(toAccount);

        // Create transfer record
        Transfer transfer = mapper.toTransfer(dto);
        transfer.setUser(user);
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setTransferDate(LocalDate.parse(dto.getTransferDate(), formatter));

        Transfer savedTransfer = transferRepository.save(transfer);
        return mapper.toTransferResponse(savedTransfer);
    }

    public List<TransferResponseDTO> getTransfersByUserId(Long userId) {
        List<Transfer> transfers = transferRepository.findByUserId(userId);
        return transfers.stream()
                .map(mapper::toTransferResponse)
                .collect(Collectors.toList());
    }
}

