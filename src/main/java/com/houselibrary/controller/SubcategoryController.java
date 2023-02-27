package com.houselibrary.controller;

import com.houselibrary.dto.request.SubcategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
import com.houselibrary.model.Priority;
import com.houselibrary.model.Subcategory;
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
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<SubcategoryDto> addSubcategory(
      @RequestBody SubcategoryRequest subcategoryRequest) {
    Subcategory subcategory = subcategoryService.addSubcategory(subcategoryRequest);
    SubcategoryDto subcategoryDto = modelMapper.mapToSubcategoryDto(subcategory);
    return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryDto);
  }

  @GetMapping
  public ResponseEntity<List<SubcategoryDto>> getAllSubcategories() {
    List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
    List<SubcategoryDto> subcategoriesDto = modelMapper.mapToSubcategoryDtoList(subcategories);
    return ResponseEntity.ok(subcategoriesDto);
  }

  @GetMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryDto> getSubcategory(@PathVariable Long subcategoryId) {
    Subcategory subcategory = subcategoryService.getSubcategory(subcategoryId);
    SubcategoryDto subcategoryDto = modelMapper.mapToSubcategoryDto(subcategory);
    return ResponseEntity.ok(subcategoryDto);
  }

  @PutMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryDto> updateCategory(
      @PathVariable Long subcategoryId, @RequestBody SubcategoryRequest subcategoryRequest) {
    Subcategory subcategory =
        subcategoryService.updateSubcategory(subcategoryId, subcategoryRequest);
    SubcategoryDto subcategoryDto = modelMapper.mapToSubcategoryDto(subcategory);
    return ResponseEntity.ok().body(subcategoryDto);
  }

  @DeleteMapping("/{subcategoryId}")
  public ResponseEntity<Void> deleteSubcategory(@PathVariable Long subcategoryId) {
    subcategoryService.deleteSubcategory(subcategoryId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{subcategoryId}/books")
  public ResponseEntity<List<BookDto>> getSubcategoryBooks(@PathVariable Long subcategoryId) {
    List<Book> books = subcategoryService.getSubcategoryBooks(subcategoryId);
    List<BookDto> booksDto = modelMapper.mapToBookDtoList(books);
    return ResponseEntity.ok(booksDto);
  }

  @GetMapping("/{subcategoryId}/books/{priority}")
  public ResponseEntity<List<BookDto>> getSubcategoryBooksByPriority(
          @PathVariable Long subcategoryId, @PathVariable int priority) {
    List<Book> books = subcategoryService.getSubcategoryBooksByPriority(subcategoryId, priority);
    List<BookDto> booksDto = modelMapper.mapToBookDtoList(books);
    return ResponseEntity.ok(booksDto);
  }
}
