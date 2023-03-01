package com.houselibrary.controller;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryRequest categoryRequest) {
    CategoryDto categoryDto = categoryService.addCategory(categoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    List<CategoryDto> categoriesDto = categoryService.getAllCategories();
    return ResponseEntity.ok().body(categoriesDto);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
    CategoryDto categoryDto = categoryService.getCategory(categoryId);
    return ResponseEntity.ok().body(categoryDto);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(
      @PathVariable Long categoryId, @RequestBody CategoryRequest categoryRequest) {
    CategoryDto categoryDto = categoryService.updateCategory(categoryId, categoryRequest);
    return ResponseEntity.ok().body(categoryDto);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{categoryId}/books")
  public ResponseEntity<List<BookDto>> getCategoryBooks(@PathVariable Long categoryId) {
    List<BookDto> booksDto = categoryService.getCategoryBooks(categoryId);
    return ResponseEntity.ok(booksDto);
  }

  @GetMapping("/{categoryId}/subcategories")
  public ResponseEntity<List<SubcategoryDto>> getCategorySubcategories(
      @PathVariable Long categoryId) {
    List<SubcategoryDto> subcategoriesDto = categoryService.getCategorySubcategories(categoryId);
    return ResponseEntity.ok(subcategoriesDto);
  }
}
