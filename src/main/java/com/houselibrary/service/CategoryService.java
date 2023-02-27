package com.houselibrary.service;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CategoryService {

  Category addCategory(@NotNull CategoryRequest categoryRequest);

  Category getCategory(@NotNull Long categoryId);

  Category updateCategory(@NotNull Long categoryId, CategoryRequest categoryRequest);

  void deleteCategory(@NotNull Long categoryId);

  List<Category> getAllCategories();

  List<Book> getCategoryBooks(@NotNull Long categoryId);

  List<Subcategory> getCategorySubcategories(@NotNull Long categoryId);
}
