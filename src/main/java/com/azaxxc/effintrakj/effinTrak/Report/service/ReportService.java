package com.azaxxc.effintrakj.effinTrak.Report.service;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ReportService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public ReportResponseDTO generateReport(Long userId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Income> incomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);

        Double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        Double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        Double balance = totalIncome - totalExpense;

        Map<String, Double> incomeByCategory = incomes.stream()
                .collect(Collectors.groupingBy(
                        income -> income.getCategory().getName(),
                        Collectors.summingDouble(Income::getAmount)));

        Map<String, Double> expenseByCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getCategory().getName(),
                        Collectors.summingDouble(Expense::getAmount)));

        return new ReportResponseDTO(totalIncome, totalExpense, balance, incomeByCategory, expenseByCategory);
    }
}
