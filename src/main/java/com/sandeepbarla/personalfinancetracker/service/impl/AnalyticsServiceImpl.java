package com.sandeepbarla.personalfinancetracker.service.impl;

import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.model.analytics.CategorySummaryModel;
import com.sandeepbarla.personalfinancetracker.model.analytics.MonthlySummaryModel;
import com.sandeepbarla.personalfinancetracker.repository.TransactionRepository;
import com.sandeepbarla.personalfinancetracker.repository.projection.CategorySummaryProjection;
import com.sandeepbarla.personalfinancetracker.repository.projection.MonthlySummaryProjection;
import com.sandeepbarla.personalfinancetracker.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final TransactionRepository transactionRepository;

    // ✅ Get total amount grouped by category
    @Override
    public List<CategorySummaryModel> getCategorySummary(User user) {
        List<CategorySummaryProjection> projections =
                transactionRepository.getCategorySummaryByUserId(user.getId());

        return projections.stream()
                .map(proj -> new CategorySummaryModel(
                        proj.getCategoryName(),
                        proj.getTotalAmount()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Get total income & expense grouped by month
    @Override
    public List<MonthlySummaryModel> getMonthlySummary(User user) {
        List<MonthlySummaryProjection> projections =
                transactionRepository.getMonthlySummaryByUserId(user.getId());

        return projections.stream()
                .map(proj -> new MonthlySummaryModel(
                        proj.getYear(),
                        proj.getMonth(),
                        proj.getTotalIncome(),
                        proj.getTotalExpense()
                ))
                .collect(Collectors.toList());
    }
}