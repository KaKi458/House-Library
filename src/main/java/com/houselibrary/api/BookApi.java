package com.houselibrary.api;

import com.houselibrary.api.model.request.BookRequest;
import com.houselibrary.api.model.request.PriorityChangeRequest;
import com.houselibrary.api.model.response.BookResponse;
import com.houselibrary.core.mapper.ModelMapper;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Priority;
import com.houselibrary.core.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/house-library/books")
public interface BookApi {

    @PostMapping()
    ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request);

    @GetMapping()
    ResponseEntity<List<BookResponse>> getAllBooks();

    @GetMapping("/{book_id}")
    ResponseEntity<BookResponse> getBook(@PathVariable int book_id);

    @DeleteMapping("/{book_id}")
    ResponseEntity<Void> deleteBook(@PathVariable int book_id);

    @GetMapping("/count")
    ResponseEntity<Integer> countAllBooks();

    @PatchMapping("/{book_id}/priority")
    ResponseEntity<BookResponse> changeBookPriority(@PathVariable int book_id, @RequestBody PriorityChangeRequest request);
}
