package com.azaxxc.effintrakj.effinTrak.Savings.controller;

import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import com.azaxxc.effintrakj.effinTrak.Savings.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {
    private final SavingsService savingsService;

    @Autowired
    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    @PostMapping
    public ResponseEntity<Savings> createSavings(@RequestBody Savings savings) {
        return ResponseEntity.ok(savingsService.saveSavings(savings));
    }

    @GetMapping
    public ResponseEntity<List<Savings>> getAllSavings() {
        return ResponseEntity.ok(savingsService.getAllSavings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Savings> getSavingsById(@PathVariable Long id) {
        return savingsService.getSavingsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavings(@PathVariable Long id) {
        savingsService.deleteSavings(id);
        return ResponseEntity.noContent().build();
    }
}

