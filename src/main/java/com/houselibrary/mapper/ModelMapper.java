package com.houselibrary.mapper;

import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.dto.response.CategoryDto;
import com.houselibrary.dto.response.SubcategoryDto;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;

import java.util.List;

public interface ModelMapper {

  CategoryDto mapToCategoryDto(Category category);

  SubcategoryDto mapToSubcategoryDto(Subcategory subcategory);

  AuthorDto mapToAuthorDto(Author author);

  BookDto mapToBookDto(Book book);

  List<AuthorDto> mapToAuthorDtoList(List<Author> authors);

  List<CategoryDto> mapToCategoryDtoList(List<Category> categories);

  List<SubcategoryDto> mapToSubcategoryDtoList(List<Subcategory> subcategories);

  List<BookDto> mapToBookDtoList(List<Book> books);
}
