package com.azaxxc.effintrakj.effinTrak.Budget.controller;

import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.service.BudgetService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final GlobalResponseService globalResponseService;

    public BudgetController(BudgetService budgetService, GlobalResponseService globalResponseService) {
        this.budgetService = budgetService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createBudget(@RequestBody BudgetRequestDTO dto) {
        BudgetResponseDTO savedBudget = budgetService.saveBudget(dto);
        return globalResponseService.success(savedBudget, "Budget created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getBudgetsByUserId(@PathVariable Long userId) {
        List<BudgetResponseDTO> budgets = budgetService.getBudgetsByUserId(userId);
        return globalResponseService.success(budgets, "Fetched budgets for user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
