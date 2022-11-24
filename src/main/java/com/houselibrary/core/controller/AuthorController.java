package com.houselibrary.core.controller;

import com.houselibrary.api.AuthorApi;
import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.api.model.request.BookRequest;
import com.houselibrary.api.model.response.AuthorResponse;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.service.AuthorService;
import com.houselibrary.core.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements AuthorApi {

    private AuthorService authorService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public ResponseEntity<AuthorResponse> addAuthor(AuthorRequest request) {
        Author author = authorService.add(request);
        AuthorResponse response = modelMapper.map(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<Author> authors = authorService.getAll();
        List<AuthorResponse> response = modelMapper.mapAuthors(authors);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<AuthorResponse> getAuthor(int author_id) {
        Author author = authorService.get(author_id);
        AuthorResponse response = modelMapper.map(author);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(int author_id) {
        authorService.delete(author_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookResponse>> getAuthorBooks(int author_id) {
        List<Book> books = authorService.getBooks(author_id);
        List<BookResponse> response = modelMapper.mapBooks(books);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<BookResponse> addAuthorBook(int author_id, BookRequest bookRequest) {
//        Book book = bookService.add(bookRequest);
//        book = authorService.addBook(author_id, book);
//        BookResponse response = modelMapper.map(book);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return  null;
    }
}
