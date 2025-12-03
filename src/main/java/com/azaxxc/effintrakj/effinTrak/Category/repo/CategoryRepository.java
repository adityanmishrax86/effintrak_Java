package com.azaxxc.effintrakj.effinTrak.Category.repo;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIgnoreCase(String name);
}

