package com.sandeepbarla.personalfinancetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlySummaryResponse {
    private String yearMonth;        // ✅ New field
    private int year;
    private int month;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
}