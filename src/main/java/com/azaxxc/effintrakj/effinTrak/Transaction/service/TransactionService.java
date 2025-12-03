package com.azaxxc.effintrakj.effinTrak.Transaction.service;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TransactionService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<TransactionResponseDTO> getAllTransactions(Long userId) {
        List<Income> incomes = incomeRepository.findByUserIdOrderByDateDesc(userId);
        List<Expense> expenses = expenseRepository.findByUserIdOrderByDateDesc(userId);

        List<TransactionResponseDTO> transactions = new ArrayList<>();
        transactions.addAll(incomes.stream().map(this::mapIncomeToDTO).toList());
        transactions.addAll(expenses.stream().map(this::mapExpenseToDTO).toList());

        return transactions.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> getTransactionsBetweenDates(Long userId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Income> incomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);

        List<TransactionResponseDTO> transactions = new ArrayList<>();
        transactions.addAll(incomes.stream().map(this::mapIncomeToDTO).toList());
        transactions.addAll(expenses.stream().map(this::mapExpenseToDTO).toList());

        return transactions.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO mapIncomeToDTO(Income income) {
        return new TransactionResponseDTO(
                income.getId(),
                income.getDescription(),
                income.getAmount(),
                income.getDate().toString(),
                "INCOME",
                income.getCategory().getName(),
                income.getSource());
    }

    public List<TransactionResponseDTO> searchTransactions(Long userId, String search) {
        List<Income> incomes = incomeRepository.findByUserIdAndDescriptionContainingIgnoreCase(userId, search);
        List<Expense> expenses = expenseRepository.findByUserIdAndDescriptionContainingIgnoreCase(userId, search);

        List<TransactionResponseDTO> transactions = new ArrayList<>();
        transactions.addAll(incomes.stream().map(this::mapIncomeToDTO).toList());
        transactions.addAll(expenses.stream().map(this::mapExpenseToDTO).toList());

        return transactions.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO mapExpenseToDTO(Expense expense) {
        return new TransactionResponseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate().toString(),
                "EXPENSE",
                expense.getCategory() != null ? expense.getCategory().getName() : "",
                expense.getPaidTo());
    }
}
