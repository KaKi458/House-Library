package com.houselibrary.service.impl;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.BookRepository;
import com.houselibrary.service.AuthorService;
import com.houselibrary.service.BookService;
import com.houselibrary.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorService authorService;
  private final SubcategoryService subcategoryService;

  @Override
  public Book addBook(BookRequest bookRequest) {
    Subcategory subcategory = subcategoryService.getSubcategory(bookRequest.getSubcategoryId());
    Book book = Book.builder().title(bookRequest.getTitle()).subcategory(subcategory).build();

    List<Author> authors = new ArrayList<>();
    for (Long authorId : bookRequest.getAuthors()) {
      Author author = authorService.getAuthor(authorId);
      author.addBook(book);
      authors.add(author);
    }
    book.setAuthors(authors);
    bookRepository.save(book);
    return book;
  }

  @Override
  public Book getBook(Long bookId) {
    Optional<Book> bookOptional = bookRepository.findById(bookId);
    if (bookOptional.isEmpty()) {
      throw new HouseLibraryException(HttpStatus.NOT_FOUND, "Book with given ID does not exist");
    }
    return bookOptional.get();
  }

  @Override
  public Book updateBook(Long bookId, BookRequest bookRequest) {
    Book book = getBook(bookId);
    Subcategory subcategory = subcategoryService.getSubcategory(bookRequest.getSubcategoryId());
    List<Author> authors = new ArrayList<>();
    for (Long authorId : bookRequest.getAuthors()) {
      Author author = authorService.getAuthor(authorId);
      if (!author.getBooks().contains(book)) {
        author.addBook(book);
      }
      authors.add(author);
    }
    book.setTitle(bookRequest.getTitle());
    book.setSubcategory(subcategory);
    book.setAuthors(authors);
    bookRepository.save(book);
    return book;
  }

  @Override
  public void deleteBook(Long bookId) {
    Book book = getBook(bookId);
    bookRepository.delete(book);
  }

  @Override
  public List<Book> getBooksByTitle(String bookName) {
    return null;
  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }
}
