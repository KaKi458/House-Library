package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.BookRequest;
import com.houselibrary.response.BookResponse;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {

    BookResponse addBook(@NotNull BookRequest request);

    void deleteBook(@NotNull int book_id);

    BookResponse getBook(@NotNull int book_id);

    List<BookResponse> getAllBooks();

    int countAllBooks();

    List<BookResponse> findByTitle(@NotNull String titlePhrase);


}
