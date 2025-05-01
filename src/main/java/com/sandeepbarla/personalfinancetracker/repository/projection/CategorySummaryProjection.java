package com.sandeepbarla.personalfinancetracker.repository.projection;

public interface CategorySummaryProjection {
    String getCategoryName();
    Double getTotalAmount();
}