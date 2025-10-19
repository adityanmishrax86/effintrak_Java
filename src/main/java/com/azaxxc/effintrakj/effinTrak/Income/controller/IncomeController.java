package com.azaxxc.effintrakj.effinTrak.Income.controller;

import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    private final IncomeService incomeService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public IncomeController(IncomeService incomeService, UserService userService, GlobalResponseService globalResponseService) {
        this.incomeService = incomeService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createIncome(@Valid @RequestBody NewIncomeRequestDTO dto) {

        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Income savedIncome = incomeService.saveIncome(dto, user);
        return globalResponseService.success("Income added successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getIncomeByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            Pageable pageable) {
        // Default date range if not provided
        if (startDate == null) startDate = new Date(0); // epoch
        if (endDate == null) endDate = new Date(); // now
        Page<Income> incomes = incomeService.getIncomeByUserId(userId, startDate, endDate, pageable);
        return globalResponseService.success(incomes, "Fetched incomes for user");
    }

    @GetMapping
    public ResponseEntity<Object> getAllIncomesByUser() {
        List<Income> incomes = incomeService.getAllIncomes();
        return globalResponseService.success(incomes, "Fetched all incomes");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
