package com.houselibrary.core.service;

import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.template.Service;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AuthorService extends Service<Author> {

    List<Book> getBooks(@NotNull int author_id);

    Author findByName(@NotNull AuthorRequest request);

    List<Author> getAuthors(@NotNull List<AuthorRequest> requests);

}
