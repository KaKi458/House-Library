package com.houselibrary.controller;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorRequest authorRequest) {
    Author author = authorService.addAuthor(authorRequest);
    AuthorDto authorDto = modelMapper.mapToAuthorDto(author);
    return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAllAuthors() {
    List<Author> authors = authorService.getAllAuthors();
    List<AuthorDto> authorsDto = modelMapper.mapToAuthorDtoList(authors);
    return ResponseEntity.ok().body(authorsDto);
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
    Author author = authorService.getAuthor(authorId);
    AuthorDto authorDto = modelMapper.mapToAuthorDto(author);
    return ResponseEntity.ok().body(authorDto);
  }

  @PutMapping("/{authorId}")
  public ResponseEntity<AuthorDto> updateAuthor(
      @PathVariable Long authorId, @RequestBody AuthorRequest authorRequest) {
    Author author = authorService.updateAuthor(authorId, authorRequest);
    AuthorDto authorDto = modelMapper.mapToAuthorDto(author);
    return ResponseEntity.ok().body(authorDto);
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) {
    authorService.deleteAuthor(authorId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{authorId}/books")
  public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable Long authorId) {
    List<Book> books = authorService.getAuthorBooks(authorId);
    List<BookDto> booksDto = modelMapper.mapToBookDtoList(books);
    return ResponseEntity.ok().body(booksDto);
  }
}
