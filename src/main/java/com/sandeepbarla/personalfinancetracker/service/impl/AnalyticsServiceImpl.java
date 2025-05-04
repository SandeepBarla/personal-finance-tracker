package com.sandeepbarla.personalfinancetracker.service.impl;

import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.model.analytics.CategorySummaryModel;
import com.sandeepbarla.personalfinancetracker.model.analytics.MonthlySummaryModel;
import com.sandeepbarla.personalfinancetracker.model.enums.TransactionType;
import com.sandeepbarla.personalfinancetracker.repository.TransactionRepository;
import com.sandeepbarla.personalfinancetracker.repository.projection.CategorySummaryProjection;
import com.sandeepbarla.personalfinancetracker.repository.projection.MonthlySummaryProjection;
import com.sandeepbarla.personalfinancetracker.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<CategorySummaryModel> getCategorySummary(User user, String type) {
        // Step 1: Validate the `type` input if it's not null
        TransactionType transactionType = null;
        if (type != null && !type.trim().isEmpty()) {
            try {
                transactionType = TransactionType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction type: " + type);
            }
        }
// If type is null or blank, transactionType stays null → fetch all

        // Step 2: Fetch category-wise summary from the repository
        List<CategorySummaryProjection> projections = transactionRepository
                .getCategorySummaryByUserIdAndOptionalType(user.getId(), transactionType);

        // Step 3: Convert projection results to domain models
        return projections.stream()
                .map(p -> new CategorySummaryModel(p.getCategoryName(), p.getTotalAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MonthlySummaryModel> getMonthlySummary(User user) {
        List<MonthlySummaryProjection> projections =
                transactionRepository.getMonthlySummaryByUserId(user.getId());

        return projections.stream()
                .map(proj -> {
                    int year = proj.getYear();
                    int month = proj.getMonth();
                    String yearMonth = String.format("%04d-%02d", year, month); // ✅ Format as YYYY-MM

                    return new MonthlySummaryModel(
                            year,
                            month,
                            yearMonth,
                            BigDecimal.valueOf(proj.getTotalIncome()),   // ✅ Convert safely
                            BigDecimal.valueOf(proj.getTotalExpense())
                    );
                })
                .collect(Collectors.toList());
    }
}