package com.azaxxc.effintrakj.effinTrak.Credits.controller;

import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.UpdateCreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.service.CreditService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public CreditController(CreditService creditService,
                           UserService userService,
                           GlobalResponseService globalResponseService) {
        this.creditService = creditService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createCredit(@Valid @RequestBody CreditRequestDTO dto) {
        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        CreditResponseDTO savedCredit = creditService.saveCredit(dto, user);
        return globalResponseService.success(savedCredit, "Credit created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getCreditsByUserId(@PathVariable Long userId) {
        List<CreditResponseDTO> credits = creditService.getCreditsByUserId(userId);
        return globalResponseService.success(credits, "Fetched credits for user");
    }

    @GetMapping("/user/{userId}/filter")
    public ResponseEntity<Object> getCreditsByUserIdBetweenDates(
            @PathVariable Long userId,
            @RequestParam String start,
            @RequestParam String end) {
        List<CreditResponseDTO> credits = creditService.getCreditsByUserIdBetweenDates(userId, start, end);
        return globalResponseService.success(credits, "Fetched credits for user between dates");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCreditById(@PathVariable Long id) {
        try {
            CreditResponseDTO credit = creditService.getCreditResponseById(id);
            return globalResponseService.success(credit, "Fetched credit");
        } catch (RuntimeException e) {
            return globalResponseService.error("Credit not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCredit(@PathVariable Long id, @RequestBody UpdateCreditRequestDTO dto) {
        CreditResponseDTO updatedCredit = creditService.updateCredit(id, dto);
        return globalResponseService.success(updatedCredit, "Credit updated successfully");
    }

    @GetMapping("/user/{userId}/upcoming")
    public ResponseEntity<Object> getUpcomingCredits(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "30") Integer days) {
        List<CreditResponseDTO> credits = creditService.getUpcomingCredits(userId, days);
        return globalResponseService.success(credits, "Fetched upcoming credits");
    }

    @GetMapping("/user/{userId}/overdue")
    public ResponseEntity<Object> getOverdueCredits(@PathVariable Long userId) {
        List<CreditResponseDTO> credits = creditService.getOverdueCredits(userId);
        return globalResponseService.success(credits, "Fetched overdue credits");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return globalResponseService.success("Credit deleted successfully");
    }
}

