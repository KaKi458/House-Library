package com.houselibrary.api;

import com.houselibrary.api.model.request.CategoryRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.api.model.response.CategoryResponse;
import com.houselibrary.api.model.response.SubcategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/house-library/categories")
public interface CategoryApi {

    @PostMapping()
    ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request);

    @GetMapping()
    ResponseEntity<List<CategoryResponse>> getAllCategories();

    @GetMapping("/{category_id}")
    ResponseEntity<CategoryResponse> getCategory(@PathVariable int category_id);

    @DeleteMapping("/{category_id}")
    ResponseEntity<Void> deleteCategory(@PathVariable int category_id);

    @GetMapping("/count")
    ResponseEntity<Integer> countAllCategories();

    @GetMapping("/{category_id}/books")
    ResponseEntity<List<BookResponse>> getCategoryBooks(@PathVariable int category_id);

    @GetMapping("/{category_id}/subcategories")
    ResponseEntity<List<SubcategoryResponse>> getCategorySubcategories(@PathVariable int category_id);
}
