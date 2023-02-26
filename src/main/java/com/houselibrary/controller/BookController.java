package com.houselibrary.controller;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
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
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<BookDto> addBook(@RequestBody BookRequest bookRequest) {
    Book book = bookService.addBook(bookRequest);
    BookDto bookDto = modelMapper.mapToBookDto(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    List<BookDto> booksDto = modelMapper.mapToBookDtoList(books);
    return ResponseEntity.ok().body(booksDto);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
    Book book = bookService.getBook(bookId);
    BookDto bookDto = modelMapper.mapToBookDto(book);
    return ResponseEntity.ok().body(bookDto);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<BookDto> updateBook(
      @PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
    Book book = bookService.updateBook(bookId, bookRequest);
    BookDto bookDto = modelMapper.mapToBookDto(book);
    return ResponseEntity.ok().body(bookDto);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
    bookService.deleteBook(bookId);
    return ResponseEntity.noContent().build();
  }
}
