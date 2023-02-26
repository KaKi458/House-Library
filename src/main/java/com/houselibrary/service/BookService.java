package com.houselibrary.service;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.model.Book;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

  Book addBook(@NotNull BookRequest bookRequest);

  Book getBook(@NotNull Long bookId);

  Book updateBook(@NotNull Long bookId, BookRequest bookRequest);

  void deleteBook(@NotNull Long bookId);

  List<Book> getBooksByTitle(@NotNull String bookName);

  List<Book> getAllBooks();
}
