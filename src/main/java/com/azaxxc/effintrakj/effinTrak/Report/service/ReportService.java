package com.azaxxc.effintrakj.effinTrak.Report.service;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.CategoryTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ComparisonDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.MonthlyTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    public List<MonthlyTrendDTO> getMonthlyTrend(Long userId, Integer year) {
        if (year == null) {
            year = LocalDate.now().getYear();
        }

        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        List<Income> incomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);

        Map<String, Double> monthlyIncome = incomes.stream()
                .collect(Collectors.groupingBy(
                        income -> income.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.summingDouble(Income::getAmount)));

        Map<String, Double> monthlyExpense = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.summingDouble(Expense::getAmount)));

        List<MonthlyTrendDTO> trends = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%d-%02d", year, month);
            Double income = monthlyIncome.getOrDefault(monthKey, 0.0);
            Double expense = monthlyExpense.getOrDefault(monthKey, 0.0);
            trends.add(new MonthlyTrendDTO(monthKey, income, expense, income - expense));
        }

        return trends;
    }

    public List<CategoryTrendDTO> getCategoryTrend(Long userId, Long categoryId, String period) {
        LocalDate start;
        LocalDate end = LocalDate.now();

        if ("yearly".equalsIgnoreCase(period)) {
            start = end.minusYears(1);
        } else {
            start = end.minusMonths(12); // Default to monthly for 12 months
        }

        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);
        
        if (categoryId != null) {
            expenses = expenses.stream()
                    .filter(e -> e.getCategory() != null && e.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        }

        Map<String, Map<String, Double>> categoryMonthlyData = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getCategory() != null ? expense.getCategory().getName() : "Uncategorized",
                        Collectors.groupingBy(
                                expense -> expense.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                                Collectors.summingDouble(Expense::getAmount))));

        return categoryMonthlyData.entrySet().stream()
                .map(entry -> new CategoryTrendDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public ComparisonDTO getComparisonReport(Long userId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Income> incomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start, end);

        Double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        Double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        Double balance = totalIncome - totalExpense;
        Double total = totalIncome + totalExpense;

        Double incomePercentage = total > 0 ? (totalIncome / total) * 100 : 0.0;
        Double expensePercentage = total > 0 ? (totalExpense / total) * 100 : 0.0;

        Map<String, Double> incomeByCategory = incomes.stream()
                .collect(Collectors.groupingBy(
                        income -> income.getCategory().getName(),
                        Collectors.summingDouble(Income::getAmount)));

        Map<String, Double> expenseByCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getCategory().getName(),
                        Collectors.summingDouble(Expense::getAmount)));

        // Get top 5 categories
        Map<String, Double> topIncomeCategories = incomeByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<String, Double> topExpenseCategories = expenseByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return new ComparisonDTO(totalIncome, totalExpense, balance, incomePercentage, expensePercentage,
                topIncomeCategories, topExpenseCategories);
    }
}
