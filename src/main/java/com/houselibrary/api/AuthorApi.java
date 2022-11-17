package com.houselibrary.api;

import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.api.model.response.AuthorResponse;
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
}
