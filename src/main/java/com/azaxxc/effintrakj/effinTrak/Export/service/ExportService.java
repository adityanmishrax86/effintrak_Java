package com.azaxxc.effintrakj.effinTrak.Export.service;

import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ExportService {

    private final TransactionService transactionService;
    private final ReportService reportService;

    public ExportService(TransactionService transactionService,
                        ReportService reportService) {
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    public byte[] exportTransactionsToCSV(Long userId, String startDate, String endDate) throws IOException {
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsBetweenDates(userId, startDate, endDate);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        
        // CSV Header
        writer.println("ID,Type,Description,Amount,Date,Category,Source/Paid To");
        
        // CSV Data
        for (TransactionResponseDTO transaction : transactions) {
            writer.printf("%d,%s,%s,%.2f,%s,%s,%s%n",
                    transaction.getId(),
                    transaction.getType(),
                    escapeCSV(transaction.getDescription()),
                    transaction.getAmount(),
                    transaction.getDate(),
                    escapeCSV(transaction.getCategoryName()),
                    escapeCSV(transaction.getSourceOrPaidTo() != null ? transaction.getSourceOrPaidTo() : "")
            );
        }
        
        writer.flush();
        writer.close();
        return outputStream.toByteArray();
    }

    public byte[] exportReportToCSV(Long userId, String startDate, String endDate) throws IOException {
        ReportResponseDTO report = reportService.generateReport(userId, startDate, endDate);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        
        // CSV Header
        writer.println("Report Type,Category,Amount");
        
        // Summary
        writer.printf("Summary,Total Income,%.2f%n", report.getTotalIncome());
        writer.printf("Summary,Total Expense,%.2f%n", report.getTotalExpense());
        writer.printf("Summary,Balance,%.2f%n", report.getBalance());
        
        writer.println();
        writer.println("Income by Category");
        report.getIncomeByCategory().forEach((category, amount) ->
                writer.printf("Income,%s,%.2f%n", escapeCSV(category), amount)
        );
        
        writer.println();
        writer.println("Expense by Category");
        report.getExpenseByCategory().forEach((category, amount) ->
                writer.printf("Expense,%s,%.2f%n", escapeCSV(category), amount)
        );
        
        writer.flush();
        writer.close();
        return outputStream.toByteArray();
    }

    public String exportReportToText(Long userId, String startDate, String endDate) {
        ReportResponseDTO report = reportService.generateReport(userId, startDate, endDate);
        
        StringBuilder text = new StringBuilder();
        text.append("Financial Report\n");
        text.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n\n");
        text.append("Summary:\n");
        text.append("  Total Income: $").append(String.format("%.2f", report.getTotalIncome())).append("\n");
        text.append("  Total Expense: $").append(String.format("%.2f", report.getTotalExpense())).append("\n");
        text.append("  Balance: $").append(String.format("%.2f", report.getBalance())).append("\n\n");
        
        text.append("Income by Category:\n");
        report.getIncomeByCategory().forEach((category, amount) ->
                text.append("  ").append(category).append(": $").append(String.format("%.2f", amount)).append("\n")
        );
        
        text.append("\nExpense by Category:\n");
        report.getExpenseByCategory().forEach((category, amount) ->
                text.append("  ").append(category).append(": $").append(String.format("%.2f", amount)).append("\n")
        );
        
        return text.toString();
    }

    private String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}

