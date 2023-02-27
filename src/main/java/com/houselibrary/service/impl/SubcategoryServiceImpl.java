package com.houselibrary.service.impl;

import com.houselibrary.dto.request.SubcategoryRequest;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.service.CategoryService;
import com.houselibrary.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

  private final SubcategoryRepository subcategoryRepository;
  private final CategoryService categoryService;

  @Override
  public Subcategory addSubcategory(SubcategoryRequest subcategoryRequest) {
    Category category = categoryService.getCategory(subcategoryRequest.getCategoryId());
    Subcategory subcategory = new Subcategory(subcategoryRequest.getSubcategoryName(), category);
    subcategoryRepository.save(subcategory);
    return subcategory;
  }

  @Override
  public Subcategory getSubcategory(Long subcategoryId) {
    Optional<Subcategory> subcategoryOptional = subcategoryRepository.findById(subcategoryId);
    if (subcategoryOptional.isEmpty()) {
      throw new HouseLibraryException(
          HttpStatus.NOT_FOUND, "Subcategory with given ID does not exist");
    }
    return subcategoryOptional.get();
  }

  @Override
  public Subcategory updateSubcategory(Long subcategoryId, SubcategoryRequest subcategoryRequest) {
    Subcategory subcategory = getSubcategory(subcategoryId);
    Category category = categoryService.getCategory(subcategoryRequest.getCategoryId());
    subcategory.setName(subcategoryRequest.getSubcategoryName());
    subcategory.setCategory(category);
    subcategoryRepository.save(subcategory);
    return subcategory;
  }

  @Override
  public void deleteSubcategory(Long subcategoryId) {
    Subcategory subcategory = getSubcategory(subcategoryId);
    removeAllBooksFromSubcategory(subcategory);
    subcategoryRepository.delete(subcategory);
  }

  @Override
  public List<Subcategory> getAllSubcategories() {
    return subcategoryRepository.findAll();
  }

  @Override
  public List<Book> getSubcategoryBooks(Long subcategoryId) {
    Subcategory subcategory = getSubcategory(subcategoryId);
    return subcategory.getBooks();
  }

  private void removeAllBooksFromSubcategory(Subcategory subcategory) {
    for (Book book : subcategory.getBooks()) {
      book.setSubcategory(null);
    }
  }
}
