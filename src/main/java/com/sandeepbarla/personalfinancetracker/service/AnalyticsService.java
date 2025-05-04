package com.sandeepbarla.personalfinancetracker.service;

import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.model.analytics.CategorySummaryModel;
import com.sandeepbarla.personalfinancetracker.model.analytics.MonthlySummaryModel;

import java.util.List;

public interface AnalyticsService {
    List<CategorySummaryModel> getCategorySummary(User user, String type);
    List<MonthlySummaryModel> getMonthlySummary(User user);
}