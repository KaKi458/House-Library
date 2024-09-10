package com.houselibrary.api.response;

import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SubcategoryResponse {

  private final Integer subcategoryId;
  private final String subcategoryName;
  private final Integer categoryId;
  private final String categoryName;
  private final List<BookRecord> books;

  public SubcategoryResponse(Subcategory subcategory) {
    subcategoryId = subcategory.getId();
    subcategoryName = subcategory.getName();
    categoryId = subcategory.getCategory().getId();
    categoryName = subcategory.getCategory().getName();
    books = new ArrayList<>();
    getBooks(subcategory);
  }

  private void getBooks(Subcategory subcategory) {
    List<Book> bookModelList = subcategory.getBooks();
    bookModelList.forEach(b -> books.add(new BookRecord(b.getId(), b.getTitle(),
            AuthorRecord.getAuthors(b.getAuthors()), b.getPriority().getValue())));
  }

  private record BookRecord(Integer bookId, String title, List<AuthorRecord> authors, Integer priority){}
}
