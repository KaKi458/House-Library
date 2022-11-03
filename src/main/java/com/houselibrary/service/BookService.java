package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.request.BookRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

    Book addBook(@NotNull BookRequest request);

    void deleteBook(@NotNull int book_id);

    Book getBook(@NotNull int book_id);

    List<Book> getAllBooks();

    int countAllBooks();

    List<Book> findByTitle(@NotNull String titlePhrase);


}
