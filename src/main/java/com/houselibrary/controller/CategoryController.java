package com.houselibrary.controller;

import com.houselibrary.api.response.CategoryResponse;
import com.houselibrary.api.response.CategoryResponseWithoutBooks;
import com.houselibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryResponseWithoutBooks>> getAllCategories() {
    List<CategoryResponseWithoutBooks> categoriesDto = categoryService.getAllCategories();
    return ResponseEntity.ok().body(categoriesDto);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer categoryId) {
    CategoryResponse categoryResponse = categoryService.getCategory(categoryId);
    return ResponseEntity.ok().body(categoryResponse);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> updateCategoryName(
      @PathVariable Integer categoryId, @RequestBody String newCategoryName) {
    CategoryResponse categoryResponse = categoryService.updateCategoryName(categoryId, newCategoryName);
    return ResponseEntity.ok().body(categoryResponse);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.noContent().build();
  }
}
