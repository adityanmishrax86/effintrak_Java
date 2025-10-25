package com.azaxxc.effintrakj.effinTrak.Income.controller;

import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.dtos.PageableResponse;
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
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            Pageable pageable) {
        Page<IncomeResponse> incomes;
        if (null != start && null != end) {
            incomes = incomeService.getIncomeByUserIdBetweenDatePeriods(userId, start, end, pageable);
        } else {
            incomes = incomeService.getIncomeByUserId(userId, pageable);
        }
        PageableResponse<IncomeResponse> response  = new PageableResponse<>(incomes.getContent(), incomes);

        return globalResponseService.success(response, "Fetched incomes for user");
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
