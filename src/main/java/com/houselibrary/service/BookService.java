package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.request.BookRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService extends Service<Book> {

    List<Book> findByTitle(@NotNull String title);
}
