package com.houselibrary.controller;

import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Book;
import com.houselibrary.request.BookRequest;
import com.houselibrary.response.BookResponse;
import com.houselibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/house-library/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        Book book = bookService.add(request);
        BookResponse response = modelMapper.map(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.getAll();
        List<BookResponse> response = modelMapper.mapBooks(books);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable int book_id) {
        Book book = bookService.get(book_id);
        BookResponse response = modelMapper.map(book);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int book_id) {
        bookService.delete(book_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllBooks() {
        int numberOfBooks = bookService.countAll();
        return ResponseEntity.ok().body(numberOfBooks);
    }
}
