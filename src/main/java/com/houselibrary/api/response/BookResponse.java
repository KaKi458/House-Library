package com.houselibrary.api.response;

import com.houselibrary.model.Book;
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
    categoryId = book.getCategory().getId();
    categoryName = book.getCategory().getName();
    subcategoryId = book.getSubcategory().getId();
    subcategoryName = book.getSubcategory().getName();
    authors = AuthorRecord.getAuthors(book.getAuthors());
    priority = book.getPriority().getValue();
  }
}
