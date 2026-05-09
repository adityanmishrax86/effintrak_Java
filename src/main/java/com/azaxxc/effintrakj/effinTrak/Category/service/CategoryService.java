package com.azaxxc.effintrakj.effinTrak.Category.service;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.repo.CategoryRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.CategoryMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final List<String> DEFAULT_CATEGORIES = List.of(
            "Food",
            "Transport",
            "Utilities",
            "Entertainment",
            "Healthcare",
            "Education",
            "Shopping",
            "Travel",
            "Savings",
            "Other"
    );

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostConstruct
    @Transactional
    public void seedDefaultCategories() {
        Set<String> existingCategories = getCategories().stream()
                .map(Category::getName)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<Category> missingCategories = DEFAULT_CATEGORIES.stream()
                .filter(categoryName -> !existingCategories.contains(categoryName.toLowerCase()))
                .map(categoryName -> {
                    Category category = new Category();
                    category.setName(categoryName);
                    return category;
                })
                .toList();

        if (!missingCategories.isEmpty()) {
            categoryRepository.saveAll(missingCategories);
        }
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryResponseDTO updateCategory(Long id, String name) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        if (name != null && !name.trim().isEmpty()) {
            // Check if name already exists for another category
            Optional<Category> existingCategory = categoryRepository.findByNameIgnoreCase(name.trim());
            if (existingCategory.isPresent() && !existingCategory.get().getId().equals(id)) {
                throw new IllegalArgumentException("Category with name '" + name + "' already exists");
            }
            category.setName(name.trim());
        }

        Category updatedCategory = categoryRepository.save(category);
        return mapper.toResponseDTO(updatedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
