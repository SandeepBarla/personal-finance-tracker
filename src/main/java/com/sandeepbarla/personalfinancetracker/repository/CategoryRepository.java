package com.sandeepbarla.personalfinancetracker.repository;

import com.sandeepbarla.personalfinancetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}