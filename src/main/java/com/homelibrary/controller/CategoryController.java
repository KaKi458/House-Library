package com.homelibrary.controller;

import com.homelibrary.api.response.CategoryResponse;
import com.homelibrary.api.response.CategoryResponseWithoutBooks;
import com.homelibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
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
}
