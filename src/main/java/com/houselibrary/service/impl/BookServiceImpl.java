package com.houselibrary.service.impl;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.dto.response.BookDto;
import com.houselibrary.exception.HouseLibraryException;
import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Author;
import com.houselibrary.model.Book;
import com.houselibrary.model.Priority;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.AuthorRepository;
import com.houselibrary.repository.BookRepository;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final SubcategoryRepository subcategoryRepository;
  private final ModelMapper mapper;

  @Override
  public BookDto addBook(BookRequest bookRequest) {
    Subcategory subcategory = findSubcategory(bookRequest.getSubcategoryId());
    Book book = Book.builder()
            .title(bookRequest.getTitle())
            .subcategory(subcategory)
            .priority(Priority.fromValue(bookRequest.getPriority()))
            .build();
    List<Author> authors = getAuthorsFromRequest(bookRequest);
    addAuthors(book, authors);
    bookRepository.save(book);
    return mapper.mapToBookDto(book);
  }

  @Override
  public BookDto getBook(Long bookId) {
    Book book = findBook(bookId);
    return mapper.mapToBookDto(book);
  }

  @Override
  public BookDto updateBook(Long bookId, BookRequest bookRequest) {
    Book book = findBook(bookId);
    Subcategory subcategory = findSubcategory(bookRequest.getSubcategoryId());
    book.setSubcategory(subcategory);
    removeAuthors(book);
    List<Author> authors = getAuthorsFromRequest(bookRequest);
    addAuthors(book, authors);
    book.setTitle(bookRequest.getTitle());
    book.setPriority(Priority.fromValue(bookRequest.getPriority()));
    bookRepository.save(book);
    return mapper.mapToBookDto(book);
  }

  @Override
  public void deleteBook(Long bookId) {
    Book book = findBook(bookId);
    removeAuthors(book);
    bookRepository.delete(book);
  }

  @Override
  public List<BookDto> getBooksByTitle(String bookName) {
    return null;
  }

  @Override
  public List<BookDto> getAllBooks() {
    List<Book> books = bookRepository.findAll();
    return mapper.mapToBookDtoList(books);
  }

  private List<Author> getAuthorsFromRequest(BookRequest bookRequest) {
    List<Author> authors = new ArrayList<>();
    for (Long authorId : bookRequest.getAuthors()) {
      Author author = findAuthor(authorId);
      authors.add(author);
    }
    return authors;
  }

  private void addAuthors(Book book, List<Author> authors) {
    for (Author author : authors) {
      author.addBook(book);
    }
    book.setAuthors(authors);
  }

  private void removeAuthors(Book book) {
    for (Author author : book.getAuthors()) {
      author.removeBook(book);
    }
  }

  private Subcategory findSubcategory(Long subcategoryId) {
    return subcategoryRepository.findById(subcategoryId)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Subcategory with given ID does not exist"));
  }

  private Author findAuthor(Long authorID) {
    return authorRepository.findById(authorID)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Author with given ID does not exist"));
  }

  private Book findBook(Long bookId) {
    return bookRepository.findById(bookId)
            .orElseThrow(() -> new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "Book with given ID does not exis t"));
  }
}
