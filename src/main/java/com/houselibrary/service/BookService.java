package com.houselibrary.service;

import com.houselibrary.model.Book;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface BookService {

    void addBook(@NotNull Book book);

    void deleteBook(@NotNull int bookId);

    Book getBook(@NotNull int bookId);

    List<Book> getAllBooks();

    int countAllBooks();

    List<Book> findByTitle(@NotNull String titlePhrase);
    
}
