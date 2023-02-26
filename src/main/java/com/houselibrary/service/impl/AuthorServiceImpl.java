package com.houselibrary.service.impl;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.repository.AuthorRepository;
import com.houselibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Author addAuthor(AuthorRequest authorRequest) {
    Author author = new Author(authorRequest.getFirstName(), authorRequest.getLastName());
    authorRepository.save(author);
    return author;
  }

  @Override
  public Author getAuthor(Long authorId) {
    Optional<Author> authorOptional = authorRepository.findById(authorId);
    if (authorOptional.isEmpty()) {
      throw new HouseLibraryException(HttpStatus.NOT_FOUND, "Author with given ID does not exist");
    }
    return authorOptional.get();
  }

  @Override
  public Author updateAuthor(Long authorId, AuthorRequest authorRequest) {
    Author author = getAuthor(authorId);
    author.setFirstName(authorRequest.getFirstName());
    author.setLastName(authorRequest.getLastName());
    authorRepository.save(author);
    return author;
  }

  @Override
  public void deleteAuthor(Long authorId) {
    Author author = getAuthor(authorId);
    authorRepository.delete(author);
  }

  @Override
  public List<Author> getAuthorsByName(String authorName) {
    return null;
  }

  @Override
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public List<Book> getAuthorBooks(Long authorId) {
    Author author = getAuthor(authorId);
    return author.getBooks();
  }
}
