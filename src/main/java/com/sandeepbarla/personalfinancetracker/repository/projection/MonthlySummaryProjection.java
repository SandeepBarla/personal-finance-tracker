package com.sandeepbarla.personalfinancetracker.repository.projection;

public interface MonthlySummaryProjection {
    int getYear();
    int getMonth();
    Double getTotalIncome();
    Double getTotalExpense();
}