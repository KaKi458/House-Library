package com.houselibrary.controller;

import com.houselibrary.model.Subcategory;
import com.houselibrary.request.SubcategoryRequest;
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

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping()
    public ResponseEntity<SubcategoryResponse> addSubcategory(@RequestBody SubcategoryRequest request) {
        SubcategoryResponse response = subcategoryService.addSubcategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<SubcategoryResponse>> getAllSubcategories() {
        List<SubcategoryResponse> response = subcategoryService.getAllSubcategories();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{subcategory_id}")
    public ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable int subcategory_id) {
        SubcategoryResponse response = subcategoryService.getSubcategory(subcategory_id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{subcategory_id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int subcategory_id) {
        subcategoryService.deleteSubcategory(subcategory_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllSubcategories() {
        int numberOfSubcategories = subcategoryService.countAllSubcategories();
        return ResponseEntity.ok().body(numberOfSubcategories);
    }
}
