package com.houselibrary.service;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CategoryService {

  CategoryDto addCategory(@NotNull CategoryRequest categoryRequest);

  CategoryDto getCategory(@NotNull Long categoryId);

  CategoryDto updateCategory(@NotNull Long categoryId, CategoryRequest categoryRequest);

  void deleteCategory(@NotNull Long categoryId);

  List<CategoryDto> getAllCategories();

  List<BookDto> getCategoryBooks(
          @NotNull Long categoryId, int pageNo, int pageSize, String sortParam, String sortDir, Integer priority);

  List<SubcategoryDto> getCategorySubcategories(@NotNull Long categoryId);

}
