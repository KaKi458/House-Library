package com.houselibrary.controller;

import com.houselibrary.api.request.BookRequest;
import com.houselibrary.api.request.ChangePriorityRequest;
import com.houselibrary.api.response.BookResponse;
import com.houselibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-library/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
    BookResponse bookResponse = bookService.addBook(bookRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
  }

  @GetMapping
  public ResponseEntity<List<BookResponse>> getAllBooks(
          @RequestParam(required = false, defaultValue = "0") int pageNo,
          @RequestParam(required = false, defaultValue = "50") int pageSize,
          @RequestParam(required = false, defaultValue = "title") String sortParam,
          @RequestParam(required = false, defaultValue = "asc") String sortDir,
          @RequestParam(required = false) Integer priority) {
    List<BookResponse> booksDto = bookService.getAllBooks(pageNo, pageSize, sortParam, sortDir, priority);
    return ResponseEntity.ok().body(booksDto);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookResponse> getBook(@PathVariable Integer bookId) {
    BookResponse bookResponse = bookService.getBook(bookId);
    return ResponseEntity.ok().body(bookResponse);
  }

  @PatchMapping("/{bookId}")
  public ResponseEntity<BookResponse> changeBookPriority(
          @PathVariable Integer bookId, @RequestBody ChangePriorityRequest changePriorityRequest) {
    BookResponse bookResponse = bookService.changeBookPriority(bookId, changePriorityRequest);
    return ResponseEntity.ok().body(bookResponse);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
    bookService.deleteBook(bookId);
    return ResponseEntity.noContent().build();
  }
}
