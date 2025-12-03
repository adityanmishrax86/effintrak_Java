package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.UpdateRecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.model.RecurringTransaction;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.repo.RecurringTransactionRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.RecurringTransactionMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringTransactionService {

    private final RecurringTransactionRepository recurringTransactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final BankAccountService bankAccountService;
    private final RecurringTransactionMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RecurringTransactionService(RecurringTransactionRepository recurringTransactionRepository,
                                      UserService userService,
                                      CategoryService categoryService,
                                      BankAccountService bankAccountService,
                                      RecurringTransactionMapper mapper) {
        this.recurringTransactionRepository = recurringTransactionRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.bankAccountService = bankAccountService;
        this.mapper = mapper;
    }

    public RecurringTransactionResponseDTO saveRecurringTransaction(RecurringTransactionRequestDTO dto, User user) {
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (!dto.getType().equals("INCOME") && !dto.getType().equals("EXPENSE")) {
            throw new IllegalArgumentException("Type must be either 'INCOME' or 'EXPENSE'");
        }

        RecurringTransaction recurringTransaction = mapper.toRecurringTransaction(dto);
        recurringTransaction.setUser(user);

        if (dto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            recurringTransaction.setCategory(category);
        }

        if (dto.getBankAccountId() != null) {
            BankAccount bankAccount = bankAccountService.getBankAccountById(dto.getBankAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
            recurringTransaction.setBankAccount(bankAccount);
        }

        LocalDate startDate = LocalDate.parse(dto.getStartDate(), formatter);
        recurringTransaction.setStartDate(startDate);
        recurringTransaction.setNextDueDate(startDate);

        if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
            LocalDate endDate = LocalDate.parse(dto.getEndDate(), formatter);
            recurringTransaction.setEndDate(endDate);
        }

        RecurringTransaction saved = recurringTransactionRepository.save(recurringTransaction);
        return mapper.toRecurringTransactionResponse(saved);
    }

    public List<RecurringTransactionResponseDTO> getRecurringTransactionsByUserId(Long userId) {
        List<RecurringTransaction> transactions = recurringTransactionRepository.findByUserId(userId);
        return transactions.stream()
                .map(mapper::toRecurringTransactionResponse)
                .collect(Collectors.toList());
    }

    public RecurringTransactionResponseDTO updateRecurringTransaction(Long id, UpdateRecurringTransactionRequestDTO dto) {
        RecurringTransaction current = recurringTransactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurring transaction not found with id: " + id));

        if (dto.getDescription() != null) {
            current.setDescription(dto.getDescription());
        }
        if (dto.getAmount() != null) {
            if (dto.getAmount() <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            current.setAmount(dto.getAmount());
        }
        if (dto.getType() != null) {
            if (!dto.getType().equals("INCOME") && !dto.getType().equals("EXPENSE")) {
                throw new IllegalArgumentException("Type must be either 'INCOME' or 'EXPENSE'");
            }
            current.setType(dto.getType());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            current.setCategory(category);
        }
        if (dto.getBankAccountId() != null) {
            BankAccount bankAccount = bankAccountService.getBankAccountById(dto.getBankAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
            current.setBankAccount(bankAccount);
        }
        if (dto.getFrequency() != null) {
            current.setFrequency(dto.getFrequency());
        }
        if (dto.getStartDate() != null && !dto.getStartDate().isEmpty()) {
            LocalDate startDate = LocalDate.parse(dto.getStartDate(), formatter);
            current.setStartDate(startDate);
        }
        if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
            LocalDate endDate = LocalDate.parse(dto.getEndDate(), formatter);
            current.setEndDate(endDate);
        } else if (dto.getEndDate() != null && dto.getEndDate().isEmpty()) {
            current.setEndDate(null);
        }
        if (dto.getPaymentMethod() != null) {
            current.setPaymentMethod(dto.getPaymentMethod());
        }
        if (dto.getPaidTo() != null) {
            current.setPaidTo(dto.getPaidTo());
        }
        if (dto.getSource() != null) {
            current.setSource(dto.getSource());
        }
        if (dto.getNote() != null) {
            current.setNote(dto.getNote());
        }
        if (dto.getIsActive() != null) {
            current.setIsActive(dto.getIsActive());
        }

        // Recalculate next due date if frequency changed
        if (dto.getFrequency() != null || dto.getStartDate() != null) {
            current.setNextDueDate(calculateNextDueDate(current.getNextDueDate(), current.getFrequency()));
        }

        RecurringTransaction updated = recurringTransactionRepository.save(current);
        return mapper.toRecurringTransactionResponse(updated);
    }

    public void deleteRecurringTransaction(Long id) {
        recurringTransactionRepository.deleteById(id);
    }

    private LocalDate calculateNextDueDate(LocalDate currentDate, String frequency) {
        return switch (frequency.toUpperCase()) {
            case "DAILY" -> currentDate.plusDays(1);
            case "WEEKLY" -> currentDate.plusWeeks(1);
            case "MONTHLY" -> currentDate.plusMonths(1);
            case "YEARLY" -> currentDate.plusYears(1);
            default -> throw new IllegalArgumentException("Invalid frequency: " + frequency);
        };
    }
}

