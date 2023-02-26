package com.houselibrary.mapper;

import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapperImpl implements ModelMapper {

  @Override
  public CategoryDto mapToCategoryDto(Category category) {
    return new CategoryDto(category.getId(), category.getName());
  }

  @Override
  public SubcategoryDto mapToSubcategoryDto(Subcategory subcategory) {
    return SubcategoryDto.builder()
        .subcategoryId(subcategory.getId())
        .subcategoryName(subcategory.getName())
        .categoryId(subcategory.getCategory().getId())
        .categoryName(subcategory.getCategory().getName())
        .build();
  }

  @Override
  public AuthorDto mapToAuthorDto(Author author) {
    return AuthorDto.builder()
        .authorId(author.getId())
        .firstName(author.getFirstName())
        .lastName(author.getLastName())
        .build();
  }

  @Override
  public BookDto mapToBookDto(Book book) {
    BookDto bookDto =
        BookDto.builder()
            .bookId(book.getId())
            .title(book.getTitle())
            .categoryId(book.getSubcategory().getCategory().getId())
            .categoryName(book.getSubcategory().getCategory().getName())
            .subcategoryId(book.getSubcategory().getId())
            .subcategoryName(book.getSubcategory().getName())
            .build();
    List<AuthorDto> authorsDto = new ArrayList<>();
    for (Author author : book.getAuthors()) {
      authorsDto.add(mapToAuthorDto(author));
    }
    bookDto.setAuthors(authorsDto);
    return bookDto;
  }

  @Override
  public List<AuthorDto> mapToAuthorDtoList(List<Author> authors) {
    return authors.stream().map(this::mapToAuthorDto).toList();
  }

  @Override
  public List<CategoryDto> mapToCategoryDtoList(List<Category> categories) {
    return categories.stream().map(this::mapToCategoryDto).toList();
  }

  @Override
  public List<SubcategoryDto> mapToSubcategoryDtoList(List<Subcategory> subcategories) {
    return subcategories.stream().map(this::mapToSubcategoryDto).toList();
  }

  @Override
  public List<BookDto> mapToBookDtoList(List<Book> books) {
    return books.stream().map(this::mapToBookDto).toList();
  }
}
