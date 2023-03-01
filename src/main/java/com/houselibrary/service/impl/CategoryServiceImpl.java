package com.houselibrary.service.impl;

import com.houselibrary.dto.request.CategoryRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ModelMapper mapper;

  @Override
  public CategoryDto addCategory(CategoryRequest categoryRequest) {
    Category category = new Category(categoryRequest.getCategoryName());
    categoryRepository.save(category);
    return mapper.mapToCategoryDto(category);
  }

  @Override
  public CategoryDto getCategory(Long categoryId) {
    Category category = findCategory(categoryId);
    return mapper.mapToCategoryDto(category);
  }

  @Override
  public CategoryDto updateCategory(Long categoryId, CategoryRequest categoryRequest) {
    Category category = findCategory(categoryId);
    category.setName(categoryRequest.getCategoryName());
    categoryRepository.save(category);
    return mapper.mapToCategoryDto(category);
  }

  @Override
  public void deleteCategory(Long categoryId) {
    Category category = findCategory(categoryId);
    removeAllBooksFromCategory(category);
    categoryRepository.delete(category);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return mapper.mapToCategoryDtoList(categories);
  }

  @Override
  public List<BookDto> getCategoryBooks(Long categoryId) {
    Category category = findCategory(categoryId);
    List<Book> books = category.getBooks();
    return mapper.mapToBookDtoList(books);
  }

  @Override
  public List<SubcategoryDto> getCategorySubcategories(Long categoryId) {
    Category category = findCategory(categoryId);
    List<Subcategory> subcategories = category.getSubcategories();
    return mapper.mapToSubcategoryDtoList(subcategories);
  }

  private void removeAllBooksFromCategory(Category category) {
    for (Book book : category.getBooks()) {
      book.setSubcategory(null);
    }
  }

  private Category findCategory(Long categoryId) {
    return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Category with given ID does not exist"));
  }
}
