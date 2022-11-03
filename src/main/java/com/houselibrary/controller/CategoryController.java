package com.houselibrary.controller;

import com.houselibrary.model.Category;
import com.houselibrary.request.CategoryRequest;
import com.houselibrary.response.CategoryResponse;
import com.houselibrary.service.BookService;
import com.houselibrary.service.CategoryService;
import com.houselibrary.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/house-library/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.addCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> response = categoryService.getAllCategories();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable int category_id) {
        CategoryResponse response = categoryService.getCategory(category_id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int category_id) {
        categoryService.deleteCategory(category_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllCategories() {
        int numberOfCategories = categoryService.countAllCategories();
        return ResponseEntity.ok().body(numberOfCategories);
    }
}
