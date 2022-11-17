package com.houselibrary.core.controller;

import com.houselibrary.api.BookApi;
import com.houselibrary.api.model.request.PriorityChangeRequest;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Book;
import com.houselibrary.api.model.request.BookRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.core.model.Priority;
import com.houselibrary.core.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController implements BookApi {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        Book book = bookService.add(request);
        BookResponse response = modelMapper.mapWithAuthors(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.getAll();
        List<BookResponse> response = modelMapper.mapBooksWithAuthors(books);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<BookResponse> getBook(@PathVariable int book_id) {
        Book book = bookService.get(book_id);
        BookResponse response = modelMapper.mapWithAuthors(book);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteBook(@PathVariable int book_id) {
        bookService.delete(book_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Integer> countAllBooks() {
        int numberOfBooks = bookService.countAll();
        return ResponseEntity.ok().body(numberOfBooks);
    }

    @Override
    public ResponseEntity<BookResponse> changeBookPriority(int book_id, PriorityChangeRequest request) {
        bookService.changePriority(book_id, request);
        BookResponse response = modelMapper.mapWithAuthors(bookService.get(book_id));
        return ResponseEntity.ok().body(response);
    }
}
