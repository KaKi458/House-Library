package com.houselibrary.controller;

import com.houselibrary.model.Book;
import com.houselibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/house-library")
public class HouseLibraryController {

    private final BookService bookService;

    @Autowired
    public HouseLibraryController(BookService bookService) { this.bookService = bookService ;}

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
}
