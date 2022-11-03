package com.houselibrary.mapper;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.response.BookResponse;
import com.houselibrary.response.CategoryResponse;
import com.houselibrary.response.SubcategoryResponse;
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
                .build();
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

    default List<BookResponse> mapBooks(List<Book> books) {
        if (books == null)
            return null;
        List<BookResponse> response = new ArrayList<>(books.size());
        for (Book book : books)
            response.add(map(book));
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
}
