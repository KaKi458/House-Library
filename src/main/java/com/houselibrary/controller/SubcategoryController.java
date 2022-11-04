package com.houselibrary.controller;

import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.SubcategoryRequest;
import com.houselibrary.response.BookResponse;
import com.houselibrary.response.SubcategoryResponse;
import com.houselibrary.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/house-library/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService, ModelMapper modelMapper) {
        this.subcategoryService = subcategoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<SubcategoryResponse> addSubcategory(@RequestBody SubcategoryRequest request) {
        Subcategory subcategory = subcategoryService.addSubcategory(request);
        SubcategoryResponse response = modelMapper.map(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<SubcategoryResponse>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        List<SubcategoryResponse> response = modelMapper.mapSubcategories(subcategories);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{subcategory_id}")
    public ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable int subcategory_id) {
        Subcategory subcategory = subcategoryService.getSubcategory(subcategory_id);
        SubcategoryResponse response = modelMapper.map(subcategory);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{subcategory_id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int subcategory_id) {
        subcategoryService.deleteSubcategory(subcategory_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllSubcategories() {
        int numberOfSubcategories = subcategoryService.countAllSubcategories();
        return ResponseEntity.ok(numberOfSubcategories);
    }

    @GetMapping("/{subcategory_id}/books")
    public ResponseEntity<List<BookResponse>> getSubcategoryBooks(@PathVariable int subcategory_id) {
        List<Book> books = subcategoryService.getBooks(subcategory_id);
        List<BookResponse> response = modelMapper.mapBooks(books);
        return ResponseEntity.ok(response);
    }
}
