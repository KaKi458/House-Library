package com.homelibrary.api.response;

import com.homelibrary.model.Author;
import com.homelibrary.model.Book;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorResponse {

  private final Integer authorId;
  private final String firstName;
  private final String lastName;
  private final List<BookResponse> books;

  public AuthorResponse(Author author) {
    authorId = author.getId();
    firstName = author.getFirstName();
    lastName = author.getLastName();
    books = new ArrayList<>();
    getBooks(author);
  }

  private void getBooks(Author author) {
    List<Book> bookModelList = author.getBooks();
    bookModelList.forEach(b -> books.add(new BookResponse(b)));
  }
}
