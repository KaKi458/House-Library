package com.houselibrary.service;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.dto.response.BookDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface BookService {

  BookDto addBook(@NotNull BookRequest bookRequest);

  BookDto getBook(@NotNull Long bookId);

  BookDto updateBook(@NotNull Long bookId, BookRequest bookRequest);

  void deleteBook(@NotNull Long bookId);

  List<BookDto> getBooksByTitle(@NotNull String bookName);

  List<BookDto> getAllBooks(int pageNo, int pageSize, String sortParam, String sortDir);
}
