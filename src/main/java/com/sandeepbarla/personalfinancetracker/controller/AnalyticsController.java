package com.sandeepbarla.personalfinancetracker.controller;

import com.sandeepbarla.personalfinancetracker.dto.response.CategorySummaryResponse;
import com.sandeepbarla.personalfinancetracker.dto.response.MonthlySummaryResponse;
import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.model.analytics.CategorySummaryModel;
import com.sandeepbarla.personalfinancetracker.model.analytics.MonthlySummaryModel;
import com.sandeepbarla.personalfinancetracker.service.AnalyticsService;
import com.sandeepbarla.personalfinancetracker.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/category-summary")
    public List<CategorySummaryResponse> getCategorySummary(@CurrentUser User user) {
        List<CategorySummaryModel> models = analyticsService.getCategorySummary(user);

        return models.stream()
                .map(model -> new CategorySummaryResponse(model.getCategoryName(), model.getTotalAmount()))
                .collect(Collectors.toList());
    }

    @GetMapping("/monthly-summary")
    public List<MonthlySummaryResponse> getMonthlySummary(@CurrentUser User user) {
        List<MonthlySummaryModel> models = analyticsService.getMonthlySummary(user);

        return models.stream()
                .map(model -> new MonthlySummaryResponse(
                        model.getYear(),
                        model.getMonth(),
                        model.getTotalIncome(),
                        model.getTotalExpense()))
                .collect(Collectors.toList());
    }
}