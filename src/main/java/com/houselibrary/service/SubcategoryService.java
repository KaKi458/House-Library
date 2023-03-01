package com.houselibrary.service;

import com.houselibrary.dto.request.SubcategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.SubcategoryDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface SubcategoryService {

  SubcategoryDto addSubcategory(@NotNull SubcategoryRequest subcategoryRequest);

  SubcategoryDto getSubcategory(@NotNull Long subcategoryId);

  SubcategoryDto updateSubcategory(@NotNull Long subcategoryId, SubcategoryRequest subcategoryRequest);

  void deleteSubcategory(@NotNull Long subcategoryId);

  List<SubcategoryDto> getAllSubcategories();

  List<BookDto> getSubcategoryBooks(@NotNull Long subcategoryId);

  List<BookDto> getSubcategoryBooksByPriority(@NotNull Long subcategoryId, @NotNull int priority);
}
