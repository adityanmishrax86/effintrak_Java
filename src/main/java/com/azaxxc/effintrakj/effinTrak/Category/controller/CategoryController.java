package com.azaxxc.effintrakj.effinTrak.Category.controller;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.dtos.UpdateCategoryRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public CategoryController(CategoryService categoryService, GlobalResponseService globalResponseService ) {
        this.categoryService = categoryService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return globalResponseService.success(
                categoryService.getAllCategories(),
                "Categories retrieved successfully"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequestDTO dto) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(id, dto.getName());
        return globalResponseService.success(updatedCategory, "Category updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

