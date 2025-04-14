package com.sandeepbarla.personalfinancetracker.repository;

import com.sandeepbarla.personalfinancetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // ✅ MVP: Fetch all transactions for the logged-in user
    List<Transaction> findAllByUserId(UUID userId);
}