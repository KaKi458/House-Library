package com.houselibrary.controller;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.dto.request.PriorityRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping
  public ResponseEntity<BookDto> addBook(@RequestBody BookRequest bookRequest) {
    BookDto bookDto = bookService.addBook(bookRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getAllBooks(
          @RequestParam(required = false, defaultValue = "0") int pageNo,
          @RequestParam(required = false, defaultValue = "50") int pageSize,
          @RequestParam(required = false, defaultValue = "title") String sortParam,
          @RequestParam(required = false, defaultValue = "asc") String sortDir,
          @RequestParam(required = false) Integer priority
  ) {
    List<BookDto> booksDto = bookService.getAllBooks(
            pageNo, pageSize, sortParam, sortDir, priority);
    return ResponseEntity.ok().body(booksDto);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
    BookDto bookDto = bookService.getBook(bookId);
    return ResponseEntity.ok().body(bookDto);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<BookDto> updateBook(
      @PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
    BookDto bookDto = bookService.updateBook(bookId, bookRequest);
    return ResponseEntity.ok().body(bookDto);
  }

  @PatchMapping("/{bookId}")
  public ResponseEntity<BookDto> updateBookPriority(
          @PathVariable Long bookId, @RequestBody PriorityRequest priorityRequest) {
    BookDto bookDto = bookService.changeBookPriority(bookId, priorityRequest);
    return ResponseEntity.ok().body(bookDto);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
    bookService.deleteBook(bookId);
    return ResponseEntity.noContent().build();
  }
}
