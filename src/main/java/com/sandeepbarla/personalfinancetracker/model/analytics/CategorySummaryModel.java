package com.sandeepbarla.personalfinancetracker.model.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategorySummaryModel {
    private String categoryName;
    private double totalAmount;
}