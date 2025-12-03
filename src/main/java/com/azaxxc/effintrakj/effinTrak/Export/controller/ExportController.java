package com.azaxxc.effintrakj.effinTrak.Export.controller;

import com.azaxxc.effintrakj.effinTrak.Export.service.ExportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    private final ExportService exportService;
    private final GlobalResponseService globalResponseService;

    public ExportController(ExportService exportService, GlobalResponseService globalResponseService) {
        this.exportService = exportService;
        this.globalResponseService = globalResponseService;
    }

    @GetMapping("/user/{userId}/transactions")
    public ResponseEntity<?> exportTransactions(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "csv") String format) {
        
        try {
            if ("csv".equalsIgnoreCase(format)) {
                byte[] csvData = exportService.exportTransactionsToCSV(userId, startDate, endDate);
                
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);
                headers.setContentDispositionFormData("attachment", 
                        "transactions_" + startDate + "_to_" + endDate + ".csv");
                
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(csvData);
            } else {
                return globalResponseService.error("Unsupported format. Only 'csv' is supported.", 
                        org.springframework.http.HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return globalResponseService.error("Error exporting transactions: " + e.getMessage(),
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}/report")
    public ResponseEntity<?> exportReport(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "csv") String format) {
        
        try {
            if ("csv".equalsIgnoreCase(format)) {
                byte[] csvData = exportService.exportReportToCSV(userId, startDate, endDate);
                
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);
                headers.setContentDispositionFormData("attachment", 
                        "report_" + startDate + "_to_" + endDate + ".csv");
                
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(csvData);
            } else if ("text".equalsIgnoreCase(format)) {
                String textData = exportService.exportReportToText(userId, startDate, endDate);
                
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);
                headers.setContentDispositionFormData("attachment", 
                        "report_" + startDate + "_to_" + endDate + ".txt");
                
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(textData);
            } else {
                return globalResponseService.error("Unsupported format. Supported formats: 'csv', 'text'", 
                        org.springframework.http.HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return globalResponseService.error("Error exporting report: " + e.getMessage(),
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

