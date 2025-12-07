package com.azaxxc.effintrakj.effinTrak.Category.service;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.repo.CategoryRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.CategoryMapper;
import com.azaxxc.effintrakj.effinTrak.util.builders.CategoryTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService categoryService;

    private Category testCategory;
    private CategoryResponseDTO testCategoryResponseDTO;

    @BeforeEach
    void setUp() {
        testCategory = CategoryTestDataBuilder.aCategory()
                .withId(1L)
                .withName("Test Category")
                .build();

        testCategoryResponseDTO = new CategoryResponseDTO();
        testCategoryResponseDTO.setId(1L);
        testCategoryResponseDTO.setName("Test Category");
    }

    @Test
    void saveCategory_ShouldSaveAndReturnCategory() {
        // Given
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // When
        Category result = categoryService.saveCategory(testCategory);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testCategory.getId());
        verify(categoryRepository, times(1)).save(testCategory);
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategoryDTOs() {
        // Given
        when(categoryRepository.findAll()).thenReturn(List.of(testCategory));
        when(mapper.toResponseDTO(any(Category.class))).thenReturn(testCategoryResponseDTO);

        // When
        List<CategoryResponseDTO> result = categoryService.getAllCategories();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getCategoryById_WhenExists_ShouldReturnCategory() {
        // Given
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(testCategory));

        // When
        Optional<Category> result = categoryService.getCategoryById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_WhenNotExists_ShouldReturnEmpty() {
        // Given
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<Category> result = categoryService.getCategoryById(999L);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void updateCategory_WithValidId_ShouldUpdateAndReturnDTO() {
        // Given
        Long categoryId = 1L;
        String newName = "Updated Category";
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(testCategory));
        when(categoryRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);
        when(mapper.toResponseDTO(any(Category.class))).thenReturn(testCategoryResponseDTO);

        // When
        CategoryResponseDTO result = categoryService.updateCategory(categoryId, newName);

        // Then
        assertThat(result).isNotNull();
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory_WithInvalidId_ShouldThrowException() {
        // Given
        Long categoryId = 999L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> categoryService.updateCategory(categoryId, "New Name"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void updateCategory_WithDuplicateName_ShouldThrowException() {
        // Given
        Long categoryId = 1L;
        String newName = "Existing Category";
        Category existingCategory = CategoryTestDataBuilder.aCategory().withId(2L).withName(newName).build();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(testCategory));
        when(categoryRepository.findByNameIgnoreCase(newName)).thenReturn(Optional.of(existingCategory));

        // When & Then
        assertThatThrownBy(() -> categoryService.updateCategory(categoryId, newName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");
    }

    @Test
    void deleteCategory_ShouldCallRepository() {
        // Given
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);

        // When
        categoryService.deleteCategory(categoryId);

        // Then
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}

