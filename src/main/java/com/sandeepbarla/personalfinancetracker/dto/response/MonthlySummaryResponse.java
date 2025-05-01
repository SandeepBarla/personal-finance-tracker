package com.sandeepbarla.personalfinancetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySummaryResponse {
    private int year;
    private int month;
    private double totalIncome;
    private double totalExpense;
}