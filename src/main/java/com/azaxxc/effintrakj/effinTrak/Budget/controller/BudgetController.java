package com.azaxxc.effintrakj.effinTrak.Budget.controller;

import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.UpdateBudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.service.BudgetService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public BudgetController(BudgetService budgetService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.budgetService = budgetService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @PostMapping
    public ResponseEntity<Object> createBudget(@RequestBody BudgetRequestDTO dto, Authentication authentication) {
        Long userId = authenticatedUserResolver.resolveRequestedUserId(authentication, dto.getUserId());
        dto.setUserId(userId);
        BudgetResponseDTO savedBudget = budgetService.saveBudget(dto);
        return globalResponseService.success(savedBudget, "Budget created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getBudgetsByUserId(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<BudgetResponseDTO> budgets = budgetService.getBudgetsByUserId(effectiveUserId);
        return globalResponseService.success(budgets, "Fetched budgets for user");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBudget(@PathVariable Long id, @RequestBody UpdateBudgetRequestDTO dto,
            Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        BudgetResponseDTO updatedBudget = authenticatedUserId == null
                ? budgetService.updateBudget(id, dto)
                : budgetService.updateBudgetForUser(authenticatedUserId, id, dto);
        return globalResponseService.success(updatedBudget, "Budget updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBudget(@PathVariable Long id, Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (authenticatedUserId == null) {
            budgetService.deleteBudget(id);
        } else {
            budgetService.deleteBudgetForUser(authenticatedUserId, id);
        }
        return globalResponseService.success("Budget deleted successfully");
    }
}
