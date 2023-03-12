package com.houselibrary.controller;

import com.houselibrary.dto.request.AuthorRequest;
import com.houselibrary.dto.response.AuthorDto;
import com.houselibrary.dto.response.BookDto;
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

  @PostMapping
  public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorRequest authorRequest) {
    AuthorDto authorDto = authorService.addAuthor(authorRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAllAuthors(
          @RequestParam(required = false, defaultValue = "0") int pageNo,
          @RequestParam(required = false, defaultValue = "10") int pageSize,
          @RequestParam(required = false, defaultValue = "lastName") String sortParam,
          @RequestParam(required = false, defaultValue = "asc") String sortDir
  ) {
    List<AuthorDto> authorsDto = authorService.getAllAuthors(pageNo, pageSize, sortParam, sortDir);
    return ResponseEntity.ok().body(authorsDto);
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
    AuthorDto authorDto = authorService.getAuthor(authorId);
    return ResponseEntity.ok().body(authorDto);
  }

  @PutMapping("/{authorId}")
  public ResponseEntity<AuthorDto> updateAuthor(
      @PathVariable Long authorId, @RequestBody AuthorRequest authorRequest) {
    AuthorDto authorDto = authorService.updateAuthor(authorId, authorRequest);
    return ResponseEntity.ok().body(authorDto);
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) {
    authorService.deleteAuthor(authorId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{authorId}/books")
  public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable Long authorId) {
    List<BookDto> booksDto = authorService.getAuthorBooks(authorId);
    return ResponseEntity.ok().body(booksDto);
  }
}
