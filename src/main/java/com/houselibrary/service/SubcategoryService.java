package com.houselibrary.service;

import com.houselibrary.dto.request.SubcategoryRequest;
import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface SubcategoryService {

  Subcategory addSubcategory(@NotNull SubcategoryRequest subcategoryRequest);

  Subcategory getSubcategory(@NotNull Long subcategoryId);

  Subcategory updateSubcategory(@NotNull Long subcategoryId, SubcategoryRequest subcategoryRequest);

  void deleteSubcategory(@NotNull Long subcategoryId);

  List<Subcategory> getAllSubcategories();

  List<Book> getSubcategoryBooks(@NotNull Long subcategoryId);

  List<Book> getSubcategoryBooksByPriority(@NotNull Long subcategoryId, @NotNull int priority);
}
