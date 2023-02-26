package com.houselibrary.service.impl;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category addCategory(CategoryRequest categoryRequest) {
    Category category = new Category(categoryRequest.getCategoryName());
    categoryRepository.save(category);
    return category;
  }

  @Override
  public Category getCategory(Long categoryId) {
    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    if (categoryOptional.isEmpty()) {
      throw new HouseLibraryException(
          HttpStatus.NOT_FOUND, "Category with given ID does not exist");
    }
    return categoryOptional.get();
  }

  @Override
  public Category updateCategory(Long categoryId, CategoryRequest categoryRequest) {
    Category category = getCategory(categoryId);
    category.setName(categoryRequest.getCategoryName());
    categoryRepository.save(category);
    return category;
  }

  @Override
  public void deleteCategory(Long categoryId) {
    Category category = getCategory(categoryId);
    categoryRepository.delete(category);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public List<Book> getCategoryBooks(Long categoryId) {
    Category category = getCategory(categoryId);
    return category.getBooks();
  }

  @Override
  public List<Subcategory> getCategorySubcategories(Long categoryId) {
    Category category = getCategory(categoryId);
    return category.getSubcategories();
  }
}
