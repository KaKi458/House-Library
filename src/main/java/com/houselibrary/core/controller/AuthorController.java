package com.houselibrary.core.controller;

import com.houselibrary.api.AuthorApi;
import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.api.model.response.AuthorResponse;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements AuthorApi {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
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
}
