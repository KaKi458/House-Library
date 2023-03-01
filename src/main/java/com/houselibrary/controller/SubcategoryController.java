package com.houselibrary.controller;

import com.houselibrary.dto.request.SubcategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

  private final SubcategoryService subcategoryService;

  @PostMapping
  public ResponseEntity<SubcategoryDto> addSubcategory(
      @RequestBody SubcategoryRequest subcategoryRequest) {
    SubcategoryDto subcategoryDto = subcategoryService.addSubcategory(subcategoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryDto);
  }

  @GetMapping
  public ResponseEntity<List<SubcategoryDto>> getAllSubcategories() {
    List<SubcategoryDto> subcategoriesDto = subcategoryService.getAllSubcategories();
    return ResponseEntity.ok(subcategoriesDto);
  }

  @GetMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryDto> getSubcategory(@PathVariable Long subcategoryId) {
    SubcategoryDto subcategoryDto = subcategoryService.getSubcategory(subcategoryId);
    return ResponseEntity.ok(subcategoryDto);
  }

  @PutMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryDto> updateCategory(
      @PathVariable Long subcategoryId, @RequestBody SubcategoryRequest subcategoryRequest) {
    SubcategoryDto subcategoryDto =
            subcategoryService.updateSubcategory(subcategoryId, subcategoryRequest);
    return ResponseEntity.ok().body(subcategoryDto);
  }

  @DeleteMapping("/{subcategoryId}")
  public ResponseEntity<Void> deleteSubcategory(@PathVariable Long subcategoryId) {
    subcategoryService.deleteSubcategory(subcategoryId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{subcategoryId}/books")
  public ResponseEntity<List<BookDto>> getSubcategoryBooks(@PathVariable Long subcategoryId) {
    List<BookDto> booksDto = subcategoryService.getSubcategoryBooks(subcategoryId);
    return ResponseEntity.ok(booksDto);
  }

  @GetMapping("/{subcategoryId}/books/{priority}")
  public ResponseEntity<List<BookDto>> getSubcategoryBooksByPriority(
      @PathVariable Long subcategoryId, @PathVariable int priority) {
    List<BookDto> booksDto = subcategoryService.getSubcategoryBooksByPriority(subcategoryId, priority);
    return ResponseEntity.ok(booksDto);
  }
}
