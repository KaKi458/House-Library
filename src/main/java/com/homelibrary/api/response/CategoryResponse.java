package com.homelibrary.api.response;

import com.homelibrary.model.Book;
import com.homelibrary.model.Category;
import com.homelibrary.model.Subcategory;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryResponse {

  private final Integer categoryId;
  private final String categoryName;
  private final List<SubcategoryRecord> subcategories;
  private final List<BookRecord> books;

  public CategoryResponse(Category category) {
    categoryId = category.getId();
    categoryName = category.getName();
    subcategories = new ArrayList<>();
    books = new ArrayList<>();
    getSubcategories(category);
    getBooks(category);
  }

  private void getSubcategories(Category category) {
    List<Subcategory> subcategoryModelList = category.getSubcategories();
    subcategoryModelList.forEach(s -> subcategories.add(new SubcategoryRecord(s.getId(), s.getName())));
  }

  private void getBooks(Category category) {
    List<Book> bookModelList = category.getBooks();
    bookModelList.forEach(b -> books.add(new BookRecord(b.getId(), b.getTitle(), b.getSubcategory().getId(),
            b.getSubcategory().getName(), AuthorRecord.getAuthors(b.getAuthors()), b.getPriority().getValue())));
  }

  private record BookRecord(Integer bookId, String title, Integer subcategoryId, String subcategoryName,
                            List<AuthorRecord> authors, Integer priority){}
}
