package com.houselibrary.service;

import com.houselibrary.api.response.CategoryResponse;
import com.houselibrary.api.response.CategoryResponseWithoutBooks;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryResponse getCategory(Integer categoryId) {
    Category category = findCategory(categoryId);
    return new CategoryResponse(category);
  }

  public List<CategoryResponseWithoutBooks> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    List<CategoryResponseWithoutBooks> responses = new ArrayList<>();
    categories.forEach(category -> responses.add(new CategoryResponseWithoutBooks(category)));
    return responses;
  }

  public CategoryResponse updateCategoryName(Integer categoryId, String newCategoryName) {
    Category category = findCategory(categoryId);
    category.setName(newCategoryName);
    category = categoryRepository.save(category);
    return new CategoryResponse(category);
  }

  private Category findCategory(Integer categoryId) {
    return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Category with given ID does not exist"));
  }
}
