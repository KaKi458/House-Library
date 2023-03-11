package com.houselibrary.service.impl;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.repository.AuthorRepository;
import com.houselibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final ModelMapper mapper;

  @Override
  public AuthorDto addAuthor(AuthorRequest authorRequest) {
    Author author = new Author(authorRequest.getFirstName(), authorRequest.getLastName());
    authorRepository.save(author);
    return mapper.mapToAuthorDto(author);
  }

  @Override
  public AuthorDto getAuthor(Long authorId) {
    Author author = findAuthor(authorId);
    return mapper.mapToAuthorDto(author);
  }

  @Override
  public AuthorDto updateAuthor(Long authorId, AuthorRequest authorRequest) {
    Author author = findAuthor(authorId);
    author.setFirstName(authorRequest.getFirstName());
    author.setLastName(authorRequest.getLastName());
    authorRepository.save(author);
    return mapper.mapToAuthorDto(author);
  }

  @Override
  public void deleteAuthor(Long authorId) {
    Author author = findAuthor(authorId);
    authorRepository.delete(author);
  }

  @Override
  public List<AuthorDto> getAuthorsByName(String authorName) {
    return null;
  }

  @Override
  public List<AuthorDto> getAllAuthors(int pageNo, int pageSize, String sortParam, String sortDir) {
    Sort sort = Sort.by(sortParam);
    sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? sort.ascending() : sort.descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Author> page = authorRepository.findAll(pageable);
    List<Author> authors = page.getContent();
    return mapper.mapToAuthorDtoList(authors);
  }

  @Override
  public List<BookDto> getAuthorBooks(Long authorId) {
    Author author = findAuthor(authorId);
    List<Book> books = author.getBooks();
    return mapper.mapToBookDtoList(books);
  }

  private Author findAuthor(Long authorId) {
    return authorRepository.findById(authorId).orElseThrow(
            () -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Author with given ID does not exist"));
  }
}
