package com.houselibrary.api;

import com.houselibrary.api.model.request.SubcategoryRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.api.model.response.SubcategoryResponse;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/house-library/subcategories")
public interface SubcategoryApi {

    @PostMapping()
    ResponseEntity<SubcategoryResponse> addSubcategory(@RequestBody SubcategoryRequest request);

    @GetMapping()
    ResponseEntity<List<SubcategoryResponse>> getAllSubcategories();

    @GetMapping("/{subcategory_id}")
    ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable int subcategory_id);

    @DeleteMapping("/{subcategory_id}")
    ResponseEntity<Void> deleteSubcategory(@PathVariable int subcategory_id);

    @GetMapping("/count")
    ResponseEntity<Integer> countAllSubcategories();

    @GetMapping("/{subcategory_id}/books")
    ResponseEntity<List<BookResponse>> getSubcategoryBooks(@PathVariable int subcategory_id);
}
