package com.houselibrary.service;

import com.houselibrary.api.response.AuthorResponse;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Author;
import com.houselibrary.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorResponse getAuthor(Integer authorId) {
    Author author = findAuthor(authorId);
    return new AuthorResponse(author);
  }

  public List<AuthorResponse> getAllAuthors(int pageNo, int pageSize, String sortParam, String sortDir) {
    Sort sort = Sort.by(sortParam);
    sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? sort.ascending() : sort.descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Author> page = authorRepository.findAll(pageable);
    List<Author> authors = page.getContent();
    List<AuthorResponse> responses = new ArrayList<>();
    authors.forEach(a -> responses.add(new AuthorResponse(a)));
    return responses;
  }

  private Author findAuthor(Integer authorId) {
    return authorRepository.findById(authorId).orElseThrow(
            () -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Author with given ID does not exist"));
  }
}
