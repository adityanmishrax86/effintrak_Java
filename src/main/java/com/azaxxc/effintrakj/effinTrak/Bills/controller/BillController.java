package com.azaxxc.effintrakj.effinTrak.Bills.controller;

import com.azaxxc.effintrakj.effinTrak.Bills.dtos.BillDTO;
import com.azaxxc.effintrakj.effinTrak.Bills.service.BillService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public BillController(BillService billService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.billService = billService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @GetMapping("/user/{userId}/overdue")
    public ResponseEntity<Object> getOverdueBills(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<BillDTO> bills = billService.getOverdueBills(effectiveUserId);
        return globalResponseService.success(bills, "Fetched overdue bills");
    }
}
