package com.houselibrary.service;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public interface AuthorService {

  AuthorDto addAuthor(@NotNull AuthorRequest authorRequest);

  AuthorDto getAuthor(@NotNull Long authorId);

  AuthorDto updateAuthor(@NotNull Long authorId, AuthorRequest authorRequest);

  void deleteAuthor(@NotNull Long authorId);

  List<AuthorDto> getAuthorsByName(@NotNull String authorName);

  List<AuthorDto> getAllAuthors(int pageNo, int pageSize, String sortParam, String sortDir);

  List<BookDto> getAuthorBooks(@NotNull Long authorId);
}
