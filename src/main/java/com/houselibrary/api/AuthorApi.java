package com.houselibrary.api;

import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.api.model.request.BookRequest;
import com.houselibrary.api.model.response.AuthorResponse;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.template.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/house-library/authors")
public interface AuthorApi {

    @PostMapping()
    ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request);

    @GetMapping()
    ResponseEntity<List<AuthorResponse>> getAllAuthors();

    @GetMapping("/{author_id}")
    ResponseEntity<AuthorResponse> getAuthor(@PathVariable int author_id);

    @DeleteMapping("/{author_id}")
    ResponseEntity<Void> deleteAuthor(@PathVariable int author_id);

    @GetMapping("/{author_id}/books")
    ResponseEntity<List<BookResponse>> getAuthorBooks(@PathVariable int author_id);

    @PostMapping("/{author_id}/books")
    ResponseEntity<BookResponse> addAuthorBook(@PathVariable int author_id, @RequestBody BookRequest request);
}
