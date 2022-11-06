package com.houselibrary.core.service;

import com.houselibrary.core.model.Book;
import com.houselibrary.core.template.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService extends Service<Book> {

    List<Book> findByTitle(@NotNull String title);
}
