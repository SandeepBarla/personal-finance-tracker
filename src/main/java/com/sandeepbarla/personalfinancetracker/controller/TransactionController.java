package com.sandeepbarla.personalfinancetracker.controller;

import com.sandeepbarla.personalfinancetracker.dto.request.TransactionRequest;
import com.sandeepbarla.personalfinancetracker.model.Transaction;
import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.service.TransactionService;
import com.sandeepbarla.personalfinancetracker.util.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody @Valid TransactionRequest request, @CurrentUser User user) {
        transactionService.createTransaction(request, user);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(@CurrentUser User user) {
        return transactionService.getAllTransactions(user);
    }

    @PutMapping("/{id}")
    public void updateTransaction(@PathVariable UUID id, @RequestBody @Valid TransactionRequest request, @CurrentUser User user) {
        transactionService.updateTransaction(id, request, user);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id, @CurrentUser User user) {
        transactionService.deleteTransaction(id, user);
    }
}