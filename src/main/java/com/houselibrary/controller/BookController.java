package com.houselibrary.controller;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.BookRequest;
import com.houselibrary.response.BookResponse;
import com.houselibrary.service.BookService;
import com.houselibrary.service.CategoryService;
import com.houselibrary.service.SubcategoryService;
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

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        BookResponse response = bookService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> response = bookService.getAllBooks();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable int book_id) {
        BookResponse response = bookService.getBook(book_id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int book_id) {
        bookService.deleteBook(book_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countAllBooks() {
        int numberOfBooks = bookService.countAllBooks();
        return ResponseEntity.ok().body(numberOfBooks);
    }
}
