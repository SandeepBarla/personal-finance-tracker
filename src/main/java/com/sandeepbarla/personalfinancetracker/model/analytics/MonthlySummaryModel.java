package com.sandeepbarla.personalfinancetracker.model.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlySummaryModel {
    private int year;
    private int month;
    private String yearMonth;       // ✅ New field for "YYYY-MM"
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
}