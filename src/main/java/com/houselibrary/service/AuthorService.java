package com.houselibrary.service;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface AuthorService {

  Author addAuthor(@NotNull AuthorRequest authorRequest);

  Author getAuthor(@NotNull Long authorId);

  Author updateAuthor(@NotNull Long authorId, AuthorRequest authorRequest);

  void deleteAuthor(@NotNull Long authorId);

  List<Author> getAuthorsByName(@NotNull String authorName);

  List<Author> getAllAuthors();

  List<Book> getAuthorBooks(@NotNull Long authorId);
}
