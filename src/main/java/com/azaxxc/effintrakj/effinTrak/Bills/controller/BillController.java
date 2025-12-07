package com.azaxxc.effintrakj.effinTrak.Bills.controller;

import com.azaxxc.effintrakj.effinTrak.Bills.dtos.BillDTO;
import com.azaxxc.effintrakj.effinTrak.Bills.service.BillService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;
    private final GlobalResponseService globalResponseService;

    public BillController(BillService billService, GlobalResponseService globalResponseService) {
        this.billService = billService;
        this.globalResponseService = globalResponseService;
    }

    @GetMapping("/user/{userId}/overdue")
    public ResponseEntity<Object> getOverdueBills(@PathVariable Long userId) {
        List<BillDTO> bills = billService.getOverdueBills(userId);
        return globalResponseService.success(bills, "Fetched overdue bills");
    }
}

