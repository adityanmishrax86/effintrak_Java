package com.azaxxc.effintrakj.effinTrak.Budget.service;

import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.model.Budget;
import com.azaxxc.effintrakj.effinTrak.Budget.repo.BudgetRepository;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.BudgetMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final BudgetMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BudgetService(BudgetRepository budgetRepository,
            CategoryService categoryService,
            UserService userService,
            BudgetMapper mapper) {
        this.budgetRepository = budgetRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public BudgetResponseDTO saveBudget(BudgetRequestDTO dto) {
        User user = userService.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Budget budget = mapper.toBudget(dto);
        budget.setUser(user);

        if (dto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            budget.setCategory(category);
        }

        budget.setStartDate(LocalDate.parse(dto.getStartDate(), formatter));
        budget.setEndDate(LocalDate.parse(dto.getEndDate(), formatter));

        Budget savedBudget = budgetRepository.save(budget);
        return mapper.toBudgetResponse(savedBudget);
    }

    public List<BudgetResponseDTO> getBudgetsByUserId(Long userId) {
        return budgetRepository.findByUserId(userId).stream()
                .map(mapper::toBudgetResponse)
                .collect(Collectors.toList());
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
