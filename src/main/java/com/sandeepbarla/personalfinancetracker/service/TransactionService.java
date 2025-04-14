package com.sandeepbarla.personalfinancetracker.service;

import com.sandeepbarla.personalfinancetracker.dto.request.TransactionRequest;
import com.sandeepbarla.personalfinancetracker.model.Category;
import com.sandeepbarla.personalfinancetracker.model.Transaction;
import com.sandeepbarla.personalfinancetracker.model.User;
import com.sandeepbarla.personalfinancetracker.repository.CategoryRepository;
import com.sandeepbarla.personalfinancetracker.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public void createTransaction(TransactionRequest request, User currentUser) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Transaction transaction = Transaction.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .type(request.getType())
                .category(category)
                .user(currentUser)
                .build();

        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(User currentUser) {
        return transactionRepository.findAllByUserId(currentUser.getId());
    }

    public void updateTransaction(UUID id, TransactionRequest request, User currentUser) {
        Transaction transaction = transactionRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found or not yours"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(category);

        transactionRepository.save(transaction);
    }

    public void deleteTransaction(UUID id, User currentUser) {
        Transaction transaction = transactionRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found or not yours"));

        transactionRepository.delete(transaction);
    }
}