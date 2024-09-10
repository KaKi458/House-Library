package com.houselibrary.service;

import com.houselibrary.api.response.SubcategoryResponse;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.SubcategoryRepository;
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

  public void deleteSubcategory(Integer subcategoryId) {
    Subcategory subcategory = findSubcategory(subcategoryId);
    removeAllBooksFromSubcategory(subcategory);
    subcategoryRepository.delete(subcategory);
  }

  private void removeAllBooksFromSubcategory(Subcategory subcategory) {
    for (Book book : subcategory.getBooks()) {
      book.setSubcategory(null);
    }
  }

  private Subcategory findSubcategory(Integer subcategoryId) {
    return subcategoryRepository.findById(subcategoryId)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Subcategory with given ID does not exist"));
  }
}
