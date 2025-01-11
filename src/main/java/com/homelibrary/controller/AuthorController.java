package com.homelibrary.controller;

import com.homelibrary.api.response.AuthorResponse;
import com.homelibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping
  public ResponseEntity<List<AuthorResponse>> getAllAuthors(
          @RequestParam(required = false, defaultValue = "0") int pageNo,
          @RequestParam(required = false, defaultValue = "10") int pageSize,
          @RequestParam(required = false, defaultValue = "lastName") String sortParam,
          @RequestParam(required = false, defaultValue = "asc") String sortDir
  ) {
    List<AuthorResponse> authorsDto = authorService.getAllAuthors(pageNo, pageSize, sortParam, sortDir);
    return ResponseEntity.ok().body(authorsDto);
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<AuthorResponse> getAuthor(@PathVariable Integer authorId) {
    AuthorResponse authorResponse = authorService.getAuthor(authorId);
    return ResponseEntity.ok().body(authorResponse);
  }
}
