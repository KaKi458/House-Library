package com.homelibrary.controller;

import com.homelibrary.api.response.SubcategoryResponse;
import com.homelibrary.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

  private final SubcategoryService subcategoryService;

  @GetMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable Integer subcategoryId) {
    SubcategoryResponse subcategoryResponse = subcategoryService.getSubcategory(subcategoryId);
    return ResponseEntity.ok(subcategoryResponse);
  }

  @PutMapping("/{subcategoryId}")
  public ResponseEntity<SubcategoryResponse> updateSubcategoryName(
      @PathVariable Integer subcategoryId, @RequestBody String newSubcategoryName) {
    SubcategoryResponse subcategoryResponse = subcategoryService.updateSubcategoryName(subcategoryId, newSubcategoryName);
    return ResponseEntity.ok().body(subcategoryResponse);
  }
}
