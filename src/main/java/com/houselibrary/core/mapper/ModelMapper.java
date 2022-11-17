package com.houselibrary.core.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.houselibrary.api.model.response.AuthorResponse;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.api.model.response.CategoryResponse;
import com.houselibrary.api.model.response.SubcategoryResponse;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    default BookResponse map(Book book) {
        if (book == null)
            return null;
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categoryId(book.getCategory().getId())
                .categoryName(book.getCategory().getName())
                .subcategoryId(book.getSubcategory().getId())
                .subcategoryName(book.getSubcategory().getName())
                .priority(book.getPriority())
                .build();
    }

    default BookResponse mapWithAuthors(Book book) {
        if(book == null)
            return null;
        BookResponse response = map(book);
        response.setAuthors(mapAuthors(book.getAuthors()));
        return response;
    }

    default CategoryResponse map(Category category) {
        if (category == null)
            return null;
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .numberOfSubcategories(category.getSubcategories() != null ? category.getSubcategories().size() : 0)
                .numberOfBooks(category.getBooks() != null ? category.getBooks().size() : 0)
                .build();
    }

    default SubcategoryResponse map(Subcategory subcategory) {
        if (subcategory == null)
            return null;
        return SubcategoryResponse.builder()
                .id(subcategory.getId())
                .name(subcategory.getName())
                .categoryId(subcategory.getCategory().getId())
                .categoryName(subcategory.getCategory().getName())
                .numberOfBooks(subcategory.getBooks() != null ? subcategory.getBooks().size() : 0)
                .build();
    }

    default AuthorResponse map(Author author) {
        if (author == null)
            return null;
        return AuthorResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    default AuthorResponse mapWithBooks(Author author) {
        if(author == null)
            return null;
        AuthorResponse response = map(author);
        response.setBooks(mapBooks(author.getBooks()));
        return response;
    }


    default List<BookResponse> mapBooks(List<Book> books) {
        if (books == null)
            return null;
        List<BookResponse> response = new ArrayList<>(books.size());
        for (Book book : books)
            response.add(map(book));
        return response;
    }

    default List<BookResponse> mapBooksWithAuthors(List<Book> books) {
        if (books == null)
            return null;
        List<BookResponse> response = new ArrayList<>(books.size());
        for (Book book : books)
            response.add(mapWithAuthors(book));
        return response;
    }

    default List<CategoryResponse> mapCategories(List<Category> categories) {
        if (categories == null)
            return null;
        List<CategoryResponse> response = new ArrayList<>(categories.size());
        for (Category category : categories)
            response.add(map(category));
        return response;
    }

    default List<SubcategoryResponse> mapSubcategories(List<Subcategory> subcategories) {
        if (subcategories == null)
            return null;
        List<SubcategoryResponse> response = new ArrayList<>(subcategories.size());
        for (Subcategory subcategory : subcategories)
            response.add(map(subcategory));
        return response;
    }

    default List<AuthorResponse> mapAuthors(List<Author> authors) {
        if (authors == null)
            return null;
        List<AuthorResponse> response = new ArrayList<>(authors.size());
        for (Author author : authors)
            response.add(map(author));
        return response;
    }
}
