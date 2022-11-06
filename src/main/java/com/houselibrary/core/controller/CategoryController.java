package com.houselibrary.core.controller;

import com.houselibrary.api.CategoryApi;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.api.model.request.CategoryRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.api.model.response.CategoryResponse;
import com.houselibrary.api.model.response.SubcategoryResponse;
import com.houselibrary.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        Category category = categoryService.add(request);
        CategoryResponse response = modelMapper.map(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        List<CategoryResponse> response = modelMapper.mapCategories(categories);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable int category_id) {
        Category category = categoryService.get(category_id);
        CategoryResponse response = modelMapper.map(category);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable int category_id) {
        categoryService.delete(category_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Integer> countAllCategories() {
        int numberOfCategories = categoryService.countAll();
        return ResponseEntity.ok().body(numberOfCategories);
    }

    @Override
    public ResponseEntity<List<BookResponse>> getCategoryBooks(@PathVariable int category_id) {
        List<Book> books = categoryService.getBooks(category_id);
        List<BookResponse> response = modelMapper.mapBooks(books);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<SubcategoryResponse>> getCategorySubcategories(@PathVariable int category_id) {
        List<Subcategory> subcategories = categoryService.getSubcategories(category_id);
        List<SubcategoryResponse> response = modelMapper.mapSubcategories(subcategories);
        return ResponseEntity.ok(response);
    }
}
