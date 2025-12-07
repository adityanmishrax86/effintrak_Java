package com.azaxxc.effintrakj.effinTrak.Expense.service;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.UpdateExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.ExpenseMapper;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.IncomeMapper;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.PageResponseMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;
    private final BankAccountService bankAccountService;
    private final ExpenseMapper mapper;
    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryService categoryService,
                          BankAccountService bankAccountService,
                          UserService userService,
                          ExpenseMapper mapper) {

        this.expenseRepository = expenseRepository;
        this.categoryService = categoryService;
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public Expense saveExpense(NewExpenseRequestDTO dto, User user) {

        if(dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than zero.");
        } else if (dto.getDescription().isEmpty() || dto.getDate().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields.");
        }

        Category ctg = categoryService.getCategories().stream().filter(x -> x.getId()==dto.getCategoryId())
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No Category found;"));
        BankAccount bankAccount = bankAccountService.getBankAccountById(dto.getBankAccountId())
                .orElseThrow(() -> new IllegalArgumentException("No Bank Account found with id:" + dto.getBankAccountId()));

        Expense expense = mapper.toExpense(dto);
        expense.setUser(user);
        expense.setCategory(ctg);
        expense.setBankAccount(bankAccount);

        expense.setDate(formatter.parse(dto.getDate(), java.time.LocalDate::from));

        return expenseRepository.save(expense);
    }

    public Page<ExpenseResponse> getExpenseByUserId(Long userId, Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findByUserIdOrderByDateDesc(userId, pageable);

        if(expenses.isEmpty()) return Page.empty();

        return PageResponseMapper.mapPageable(expenses, pageable, mapper::toExpenseResponse);
    }

    public Page<ExpenseResponse> getExpenseByUserIdBetweenDatePeriods(Long userId, String startDate, String endDate, Pageable pageable) {

        LocalDate start = formatter.parse(startDate, LocalDate::from);
        LocalDate end = formatter.parse(endDate, LocalDate::from);

        Page<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start,end, pageable);
        if(expenses.isEmpty()) {
            return Page.empty();
        }

        return PageResponseMapper.mapPageable(expenses, pageable, mapper::toExpenseResponse);
    }

    public ExpenseResponse updateExpenseDetail(Long expenseId, UpdateExpenseRequestDTO dto) {
        User currentUser = userService.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("Invalid User ID Request"));
        Expense currentExpense = expenseRepository.findByUserIdAndId(currentUser.getId(), expenseId)
                .orElseThrow(() -> new RuntimeException("Invalid Expense Details"));

        currentExpense.setAmount(dto.getAmount());
        currentExpense.setDescription(dto.getDescription());
        currentExpense.setIsRecurring(dto.isRecurring());
        currentExpense.setPaidTo(dto.getPaidTo());
        currentExpense.setPaymentMethod(dto.getPaymentMethod());
        LocalDate date = formatter.parse(dto.getDate(), LocalDate::from);
        currentExpense.setDate(date);
        Category ctg = categoryService.getCategories().stream().filter(x -> Objects.equals(x.getId(), dto.getCategoryId()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(("No Category found with id:" + dto.getCategoryId())));
        currentExpense.setCategory(ctg);

        try {
            expenseRepository.save(currentExpense);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Couldn't update the Income Transaction.");
        }

        return mapper.toExpenseResponse(currentExpense);

    }

    public Page<ExpenseResponse> getExpensesWithFilters(Long userId, Long categoryId, Double minAmount, Double maxAmount,
                                                         String paymentMethod, Long bankAccountId, String startDate,
                                                         String endDate, Pageable pageable) {
        LocalDate start = startDate != null ? formatter.parse(startDate, LocalDate::from) : null;
        LocalDate end = endDate != null ? formatter.parse(endDate, LocalDate::from) : null;

        Page<Expense> expenses = expenseRepository.findExpensesWithFilters(
                userId, categoryId, minAmount, maxAmount, paymentMethod, bankAccountId, start, end, pageable);

        if (expenses.isEmpty()) {
            return Page.empty();
        }

        return PageResponseMapper.mapPageable(expenses, pageable, mapper::toExpenseResponse);
    }

    public List<ExpenseResponse> searchExpensesByDescription(Long userId, String search) {
        List<Expense> expenses = expenseRepository.findByUserIdAndDescriptionContainingIgnoreCase(userId, search);
        return expenses.stream()
                .map(mapper::toExpenseResponse)
                .toList();
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

