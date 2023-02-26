package com.houselibrary.controller;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
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
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryRequest categoryRequest) {
    Category category = categoryService.addCategory(categoryRequest);
    CategoryDto categoryDto = modelMapper.mapToCategoryDto(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    List<CategoryDto> categoriesDto = modelMapper.mapToCategoryDtoList(categories);
    return ResponseEntity.ok().body(categoriesDto);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
    Category category = categoryService.getCategory(categoryId);
    CategoryDto categoryDto = modelMapper.mapToCategoryDto(category);
    return ResponseEntity.ok().body(categoryDto);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(
      @PathVariable Long categoryId, @RequestBody CategoryRequest categoryRequest) {
    Category category = categoryService.updateCategory(categoryId, categoryRequest);
    CategoryDto categoryDto = modelMapper.mapToCategoryDto(category);
    return ResponseEntity.ok().body(categoryDto);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{categoryId}/books")
  public ResponseEntity<List<BookDto>> getCategoryBooks(@PathVariable Long categoryId) {
    List<Book> books = categoryService.getCategoryBooks(categoryId);
    List<BookDto> booksDto = modelMapper.mapToBookDtoList(books);
    return ResponseEntity.ok(booksDto);
  }

  @GetMapping("/{categoryId}/subcategories")
  public ResponseEntity<List<SubcategoryDto>> getCategorySubcategories(
      @PathVariable Long categoryId) {
    List<Subcategory> subcategories = categoryService.getCategorySubcategories(categoryId);
    List<SubcategoryDto> subcategoriesDto = modelMapper.mapToSubcategoryDtoList(subcategories);
    return ResponseEntity.ok(subcategoriesDto);
  }
}
