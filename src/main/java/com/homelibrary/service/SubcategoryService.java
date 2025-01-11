package com.homelibrary.service;

import com.homelibrary.api.response.SubcategoryResponse;
import com.homelibrary.exception.HomeLibraryException;
import com.homelibrary.model.Subcategory;
import com.homelibrary.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter
public class SubcategoryService {

  private final SubcategoryRepository subcategoryRepository;

  public SubcategoryResponse getSubcategory(Integer subcategoryId) {
    Subcategory subcategory = findSubcategory(subcategoryId);
    return new SubcategoryResponse(subcategory);
  }

  public SubcategoryResponse updateSubcategoryName(Integer subcategoryId, String newSubcategoryName) {
    Subcategory subcategory = findSubcategory(subcategoryId);
    subcategory.setName(newSubcategoryName);
    subcategory = subcategoryRepository.save(subcategory);
    return new SubcategoryResponse(subcategory);
  }

  private Subcategory findSubcategory(Integer subcategoryId) {
    return subcategoryRepository.findById(subcategoryId)
            .orElseThrow(() -> new HomeLibraryException(
                    HttpStatus.NOT_FOUND, "Subcategory with given ID does not exist"));
  }
}
