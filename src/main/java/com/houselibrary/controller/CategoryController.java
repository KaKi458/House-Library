package com.houselibrary.controller;

import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Category;
import com.houselibrary.request.CategoryRequest;
import com.houselibrary.response.CategoryResponse;
import com.houselibrary.service.CategoryService;
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
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        Category category = categoryService.addCategory(request);
        CategoryResponse response = modelMapper.map(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponse> response = modelMapper.mapCategories(categories);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable int category_id) {
        Category category = categoryService.getCategory(category_id);
        CategoryResponse response = modelMapper.map(category);
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
