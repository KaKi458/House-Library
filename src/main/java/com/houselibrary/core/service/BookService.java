package com.houselibrary.core.service;

import com.houselibrary.api.model.request.PriorityChangeRequest;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.template.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService extends Service<Book> {

    List<Book> findByTitle(@NotNull String title);

    void changePriority(int book_id, PriorityChangeRequest request);
}
