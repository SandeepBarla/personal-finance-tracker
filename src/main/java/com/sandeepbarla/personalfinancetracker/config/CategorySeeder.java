package com.sandeepbarla.personalfinancetracker.config;

import com.sandeepbarla.personalfinancetracker.model.Category;
import com.sandeepbarla.personalfinancetracker.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional // ✅ Ensures the seeding runs in a single transaction
    public void run(String... args) {
        try {
            if (categoryRepository.count() == 0) {
                List<String> defaultCategories = List.of(
                        "Food", "Transport", "Rent", "Utilities",
                        "Entertainment", "Health", "Shopping", "Other"
                );

                List<Category> categories = defaultCategories.stream()
                        .map(name -> Category.builder().name(name).build())
                        .toList();

                categoryRepository.saveAll(categories);
                System.out.println("✅ Seeded default categories");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Failed to seed categories: " + e.getMessage());
            // Optional: log full stack trace if needed
            // e.printStackTrace();
        }
    }
}