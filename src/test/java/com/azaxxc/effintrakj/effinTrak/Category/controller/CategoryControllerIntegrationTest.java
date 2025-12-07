package com.azaxxc.effintrakj.effinTrak.Category.controller;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.dtos.UpdateCategoryRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createCategory_WithValidData_ShouldReturnCategory() throws Exception {
        // Given
        Category category = new Category();
        category.setName("Test Category");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        // When & Then
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getAllCategories_ShouldReturnCategories() throws Exception {
        // Given
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test Category");

        when(categoryService.getAllCategories()).thenReturn(List.of(categoryDTO));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getCategoryById_WhenExists_ShouldReturnCategory() throws Exception {
        // Given
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.of(category));

        // When & Then
        mockMvc.perform(get("/api/categories/{id}", categoryId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getCategoryById_WhenNotExists_ShouldReturnNotFound() throws Exception {
        // Given
        Long categoryId = 999L;

        when(categoryService.getCategoryById(categoryId)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/categories/{id}", categoryId))
                .andExpect(status().isNotFound());
    }
}

