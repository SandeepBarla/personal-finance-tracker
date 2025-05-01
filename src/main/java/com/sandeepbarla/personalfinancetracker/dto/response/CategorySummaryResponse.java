package com.sandeepbarla.personalfinancetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategorySummaryResponse {
    private String category;
    private double amount;
}