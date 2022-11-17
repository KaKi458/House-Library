package com.houselibrary.core.controller;

import com.houselibrary.api.SubcategoryApi;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.api.model.request.SubcategoryRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.api.model.response.SubcategoryResponse;
import com.houselibrary.core.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubcategoryController implements SubcategoryApi {

    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService, ModelMapper modelMapper) {
        this.subcategoryService = subcategoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<SubcategoryResponse> addSubcategory(@RequestBody SubcategoryRequest request) {
        Subcategory subcategory = subcategoryService.add(request);
        SubcategoryResponse response = modelMapper.map(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<SubcategoryResponse>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryService.getAll();
        List<SubcategoryResponse> response = modelMapper.mapSubcategories(subcategories);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable int subcategory_id) {
        Subcategory subcategory = subcategoryService.get(subcategory_id);
        SubcategoryResponse response = modelMapper.map(subcategory);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int subcategory_id) {
        subcategoryService.delete(subcategory_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Integer> countAllSubcategories() {
        int numberOfSubcategories = subcategoryService.countAll();
        return ResponseEntity.ok(numberOfSubcategories);
    }

    @Override
    public ResponseEntity<List<BookResponse>> getSubcategoryBooks(@PathVariable int subcategory_id) {
        List<Book> books = subcategoryService.getBooks(subcategory_id);
        List<BookResponse> response = modelMapper.mapBooksWithAuthors(books);
        return ResponseEntity.ok(response);
    }
}
