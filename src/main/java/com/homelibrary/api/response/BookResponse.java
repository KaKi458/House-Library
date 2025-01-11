package com.homelibrary.api.response;

import com.homelibrary.model.Book;
import com.homelibrary.model.Category;
import com.homelibrary.model.Subcategory;
import lombok.Getter;

import java.util.List;

@Getter
public class BookResponse {

  private final Integer bookId;
  private final String title;
  private final Integer categoryId;
  private final String categoryName;
  private final Integer subcategoryId;
  private final String subcategoryName;
  private final List<AuthorRecord> authors;
  private final Integer priority;

  public BookResponse(Book book) {
    bookId = book.getId();
    title = book.getTitle();
    Category category = book.getCategory();
    categoryId = category != null ? category.getId() : null;
    categoryName = category != null ? category.getName() : null;
    Subcategory subcategory = book.getSubcategory();
    subcategoryId = subcategory != null ? subcategory.getId() : null;
    subcategoryName = subcategory != null ? subcategory.getName() : null;
    authors = AuthorRecord.getAuthors(book.getAuthors());
    priority = book.getPriority().getValue();
  }
}
