package com.sandeepbarla.personalfinancetracker.repository;

import com.sandeepbarla.personalfinancetracker.model.Transaction;
import com.sandeepbarla.personalfinancetracker.repository.projection.CategorySummaryProjection;
import com.sandeepbarla.personalfinancetracker.repository.projection.MonthlySummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // ✅ MVP: Fetch all transactions for the logged-in user
    List<Transaction> findAllByUserId(UUID userId);

    // ✅ Analytics: Group by category name and calculate total amount
    @Query("SELECT c.name AS categoryName, SUM(t.amount) AS totalAmount " +
            "FROM Transaction t JOIN t.category c " +
            "WHERE t.user.id = :userId " +
            "GROUP BY c.name")
    List<CategorySummaryProjection> getCategorySummaryByUserId(@Param("userId") UUID userId);

    // ✅ Analytics: Group by year and month and calculate totals using PostgreSQL EXTRACT
    @Query("SELECT EXTRACT(YEAR FROM t.date) AS year, " +
            "       EXTRACT(MONTH FROM t.date) AS month, " +
            "       SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END) AS totalIncome, " +
            "       SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END) AS totalExpense " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "GROUP BY EXTRACT(YEAR FROM t.date), EXTRACT(MONTH FROM t.date) " +
            "ORDER BY year, month")
    List<MonthlySummaryProjection> getMonthlySummaryByUserId(@Param("userId") UUID userId);
}