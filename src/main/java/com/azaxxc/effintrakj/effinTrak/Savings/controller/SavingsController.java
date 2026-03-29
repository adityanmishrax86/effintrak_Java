package com.azaxxc.effintrakj.effinTrak.Savings.controller;

import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.UpdateSavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.service.SavingsService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {
    private final SavingsService savingsService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    @Autowired
    public SavingsController(SavingsService savingsService,
            UserService userService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.savingsService = savingsService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @PostMapping
    public ResponseEntity<Object> createSavings(@Valid @RequestBody SavingsRequestDTO dto, Authentication authentication) {
        Long userId = authenticatedUserResolver.resolveRequestedUserId(authentication, dto.getUserId());
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        SavingsResponseDTO savedSavings = savingsService.saveSavings(dto, user);
        return globalResponseService.success(savedSavings, "Savings created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getSavingsByUserId(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<SavingsResponseDTO> savings = savingsService.getSavingsByUserId(effectiveUserId);
        return globalResponseService.success(savings, "Fetched savings for user");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSavingsById(@PathVariable Long id, Authentication authentication) {
        try {
            Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
            SavingsResponseDTO savings = authenticatedUserId == null
                    ? savingsService.getSavingsResponseById(id)
                    : savingsService.getSavingsResponseByIdForUser(authenticatedUserId, id);
            return globalResponseService.success(savings, "Fetched savings");
        } catch (RuntimeException e) {
            return globalResponseService.error("Savings not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSavings(@PathVariable Long id, @RequestBody UpdateSavingsRequestDTO dto,
            Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        SavingsResponseDTO updatedSavings = authenticatedUserId == null
                ? savingsService.updateSavings(id, dto)
                : savingsService.updateSavingsForUser(authenticatedUserId, id, dto);
        return globalResponseService.success(updatedSavings, "Savings updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSavings(@PathVariable Long id, Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (authenticatedUserId == null) {
            savingsService.deleteSavings(id);
        } else {
            savingsService.deleteSavingsForUser(authenticatedUserId, id);
        }
        return globalResponseService.success("Savings deleted successfully");
    }
}
