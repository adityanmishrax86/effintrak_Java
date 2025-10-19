package com.azaxxc.effintrakj.effinTrak.Credits.controller;

import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.Credits.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
        return ResponseEntity.ok(creditService.saveCredit(credit));
    }

    @GetMapping
    public ResponseEntity<List<Credit>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Long id) {
        return creditService.getCreditById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
}

