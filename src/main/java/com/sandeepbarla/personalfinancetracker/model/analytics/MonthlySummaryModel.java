package com.sandeepbarla.personalfinancetracker.model.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySummaryModel {
    private int year;
    private int month;
    private double totalIncome;
    private double totalExpense;
}