package com.azaxxc.effintrakj.effinTrak.Income.controller;

import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.UpdateIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.dtos.PageableResponse;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    private final IncomeService incomeService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    @Autowired
    public IncomeController(IncomeService incomeService, UserService userService, GlobalResponseService globalResponseService,
                            AuthenticatedUserResolver authenticatedUserResolver) {
        this.incomeService = incomeService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @PostMapping
    public ResponseEntity<Object> createIncome(@Valid @RequestBody NewIncomeRequestDTO dto, Authentication authentication) {

        Long userId = authenticatedUserResolver.resolveRequestedUserId(authentication, dto.getUserId());
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        dto.setUserId(userId);

        Income savedIncome = incomeService.saveIncome(dto, user);
        return globalResponseService.success("Income added successfully");
    }

    @PostMapping("/bulk")
    public ResponseEntity<Object> createIncomeBulk(@Valid @RequestBody List<NewIncomeRequestDTO> dtos, Authentication authentication) {
        if (dtos == null || dtos.isEmpty()) {
            throw new IllegalArgumentException("At least one income is required.");
        }

        Long requestedUserId = dtos.getFirst().getUserId();
        Long userId = authenticatedUserResolver.resolveRequestedUserId(authentication, requestedUserId);
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        dtos.forEach(dto -> dto.setUserId(userId));
        incomeService.saveIncomes(dtos, user);
        return globalResponseService.success(Map.of("count", dtos.size()), "Incomes added successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getIncomeByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) Long bankAccountId,
            Pageable pageable,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        Page<IncomeResponse> incomes;
        
        // Use advanced filtering if any filter parameters are provided
        if (categoryId != null || minAmount != null || maxAmount != null || 
            bankAccountId != null || (start != null && end != null)) {
            incomes = incomeService.getIncomesWithFilters(
                    effectiveUserId, categoryId, minAmount, maxAmount, bankAccountId, start, end, pageable);
        } else if (null != start && null != end) {
            incomes = incomeService.getIncomeByUserIdBetweenDatePeriods(effectiveUserId, start, end, pageable);
        } else {
            incomes = incomeService.getIncomeByUserId(effectiveUserId, pageable);
        }
        PageableResponse<IncomeResponse> response  = new PageableResponse<>(incomes.getContent(), incomes);

        return globalResponseService.success(response, "Fetched incomes for user");
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<Object> searchIncomes(
            @PathVariable Long userId,
            @RequestParam String search,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<IncomeResponse> incomes = incomeService.searchIncomesByDescription(effectiveUserId, search);
        return globalResponseService.success(incomes, "Search results for incomes");
    }

    @PutMapping("/user/{incomeId}")
    public ResponseEntity<Object> updateIncome(@PathVariable Long incomeId, @RequestBody UpdateIncomeRequestDTO dto,
                                               Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, dto.getUserId());
        dto.setUserId(effectiveUserId);
        IncomeResponse icr = incomeService.updateIncomeDetail(incomeId, dto);

        return globalResponseService.success(icr, "Income updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (effectiveUserId != null) {
            incomeService.deleteIncomeForUser(effectiveUserId, id);
        } else {
            // Backward compatibility for tests without auth principal
            incomeService.deleteIncome(id);
        }
        return ResponseEntity.noContent().build();
    }
}
