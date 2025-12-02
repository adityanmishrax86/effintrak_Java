package com.azaxxc.effintrakj.effinTrak.Category.service;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.repo.CategoryRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.CategoryMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
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

    @PostConstruct
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

