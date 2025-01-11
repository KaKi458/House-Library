package com.homelibrary.service;

import com.homelibrary.api.request.AuthorRequest;
import com.homelibrary.api.request.BookRequest;
import com.homelibrary.api.request.ChangePriorityRequest;
import com.homelibrary.api.response.BookResponse;
import com.homelibrary.exception.HomeLibraryException;
import com.homelibrary.model.*;
import com.homelibrary.repository.AuthorRepository;
import com.homelibrary.repository.BookRepository;
import com.homelibrary.repository.CategoryRepository;
import com.homelibrary.repository.SubcategoryRepository;
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
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final CategoryRepository categoryRepository;
  private final SubcategoryRepository subcategoryRepository;

  public BookResponse addBook(BookRequest bookRequest) {
    Category category = getCategoryFromRequest(bookRequest);
    Subcategory subcategory = getSubcategoryFromRequest(bookRequest, category);
    Priority priority = getPriorityFromRequest(bookRequest);

    Book book = Book.builder()
            .title(bookRequest.getTitle())
            .category(category)
            .subcategory(subcategory)
            .priority(priority)
            .build();

    List<Author> authors = getAuthorsFromRequest(bookRequest);
    linkAuthorsAndBook(authors, book);
    book = bookRepository.save(book);
    return new BookResponse(book);
  }

  public BookResponse getBook(Integer bookId) {
    Book book = findBook(bookId);
    return new BookResponse(book);
  }

  public List<BookResponse> getAllBooks(
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
        throw new HomeLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
      }
    } else {
       bookPage = bookRepository.findAll(pageable);
    }
    List<Book> books = bookPage.getContent();

    List<BookResponse> responses = new ArrayList<>();
    books.forEach(book -> responses.add(new BookResponse(book)));
    return responses;
  }

  public void deleteBook(Integer bookId) {
    Book book = findBook(bookId);
    unlinkAuthorsFromBook(book);
    removeSubcategoryIfEmpty(book.getSubcategory());
    removeCategoryIfEmpty(book.getCategory());
    bookRepository.delete(book);
  }

  public BookResponse changeBookPriority(Integer bookId, ChangePriorityRequest changePriorityRequest) {
    Book book = findBook(bookId);
    try {
      Priority priority = Priority.fromValue(changePriorityRequest.getPriority());
      book.setPriority(priority);
    } catch (IllegalArgumentException ex) {
      throw new HomeLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
    }
    book = bookRepository.save(book);
    return new BookResponse(book);
  }

  private Category getCategoryFromRequest(BookRequest bookRequest) {
    Category category = categoryRepository.findByName(bookRequest.getCategory()).orElse(null);
    if (category == null) {
      category = new Category(bookRequest.getCategory());
      categoryRepository.save(category);
    }
    return category;
  }

  private Subcategory getSubcategoryFromRequest(BookRequest bookRequest, Category category) {
    Subcategory subcategory = subcategoryRepository.findByName(bookRequest.getSubcategory()).orElse(null);
    if (subcategory == null) {
      subcategory = new Subcategory(bookRequest.getSubcategory(), category);
      subcategoryRepository.save(subcategory);
    } else {
      if (subcategory.getCategory() != category) {
        throw new HomeLibraryException(HttpStatus.BAD_REQUEST, "Given subcategory does not belong to given category");
      }
    }
    return subcategory;
  }

  private Priority getPriorityFromRequest(BookRequest bookRequest) {
    try {
        return bookRequest.getPriority() != null
              ? Priority.fromValue(bookRequest.getPriority())
              : Priority.defaultPriority;
    } catch (IllegalArgumentException ex) {
      throw new HomeLibraryException(HttpStatus.BAD_REQUEST, "Invalid priority");
    }
  }

  private List<Author> getAuthorsFromRequest(BookRequest bookRequest) {
    List<Author> authors = new ArrayList<>();
    for (AuthorRequest authorRequest : bookRequest.getAuthors()) {
      Author author = authorRepository.findByFirstNameAndLastName(authorRequest.firstName(), authorRequest.lastName())
                      .orElse(null);
      if (author == null) {
        author = new Author(authorRequest.firstName(), authorRequest.lastName());
        authorRepository.save(author);
      }
      authors.add(author);
    }
    return authors;
  }

  private void linkAuthorsAndBook(List<Author> authors, Book book) {
    for (Author author : authors) {
      author.addBook(book);
    }
    book.setAuthors(authors);
  }

  private void unlinkAuthorsFromBook(Book book) {
    for (Author author : book.getAuthors()) {
      author.removeBook(book);
      if (author.getBooks().isEmpty()) {
        authorRepository.delete(author);
      }
    }
  }

  private void removeSubcategoryIfEmpty(Subcategory subcategory) {
    if (subcategory.getBooks().isEmpty()) {
      subcategoryRepository.delete(subcategory);
    }
  }

  private void removeCategoryIfEmpty(Category category) {
    if (category.getBooks().isEmpty()) {
      categoryRepository.delete(category);
    }
  }

  private Book findBook(Integer bookId) {
    return bookRepository.findById(bookId)
            .orElseThrow(() -> new HomeLibraryException(
                    HttpStatus.NOT_FOUND, "Book with given ID does not exist"));
  }
}
