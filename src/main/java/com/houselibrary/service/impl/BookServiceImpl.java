package com.houselibrary.service.impl;

import com.houselibrary.dto.request.BookRequest;
import com.houselibrary.dto.request.PriorityRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    Priority priority;
    try {
       priority = bookRequest.getPriority() != null
              ? Priority.fromValue(bookRequest.getPriority())
              : Priority.defaultPriority;
    } catch (IllegalArgumentException ex) {
      throw new HouseLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
    }
    Book book = Book.builder()
            .title(bookRequest.getTitle())
            .category(subcategory.getCategory())
            .subcategory(subcategory)
            .priority(priority)
            .build();
    List<Author> authors = getAuthorsFromRequest(bookRequest);
    addAuthors(book, authors);
    Book savedBook = bookRepository.save(book);
    return mapper.mapToBookDto(savedBook);
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
    try {
      Priority priority = bookRequest.getPriority() != null
              ? Priority.fromValue(bookRequest.getPriority())
              : book.getPriority();
      book.setPriority(priority);
    } catch (IllegalArgumentException ex) {
      throw new HouseLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
    }
    book.setSubcategory(subcategory);
    book.setCategory(subcategory.getCategory());
    removeAuthors(book);
    List<Author> authors = getAuthorsFromRequest(bookRequest);
    addAuthors(book, authors);
    book.setTitle(bookRequest.getTitle());
    Book updatedBook = bookRepository.save(book);
    return mapper.mapToBookDto(updatedBook);
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
  public List<BookDto> getAllBooks(
          int pageNo, int pageSize, String sortParam, String sortDir, Integer priorityValue) {
    Sort sort = Sort.by(sortParam);
    sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? sort.ascending() : sort.descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Book> bookPage;
    if (priorityValue != null) {
      try {
        Priority priority = Priority.fromValue(priorityValue);
        bookPage = bookRepository.findByPriority(priority, pageable);
      } catch (IllegalArgumentException ex) {
        throw new HouseLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
      }
    } else {
       bookPage = bookRepository.findAll(pageable);
    }
    List<Book> books = bookPage.getContent();

    return mapper.mapToBookDtoList(books);
  }

  @Override
  public BookDto changeBookPriority(Long bookId, PriorityRequest priorityRequest) {
    Book book = findBook(bookId);
    try {
      Priority priority = Priority.fromValue(priorityRequest.getPriority());
      book.setPriority(priority);
    } catch (IllegalArgumentException ex) {
      throw new HouseLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
    }
    Book updatedBook = bookRepository.save(book);
    return mapper.mapToBookDto(updatedBook);
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
                    HttpStatus.NOT_FOUND, "Book with given ID does not exist"));
  }
}
