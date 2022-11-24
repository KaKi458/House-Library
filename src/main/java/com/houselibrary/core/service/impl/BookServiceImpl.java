package com.houselibrary.core.service.impl;

import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.api.model.request.PriorityChangeRequest;
import com.houselibrary.core.model.*;
import com.houselibrary.core.repository.BookRepository;
import com.houselibrary.api.model.request.BookRequest;
import com.houselibrary.core.service.AuthorService;
import com.houselibrary.core.service.CategoryService;
import com.houselibrary.core.service.SubcategoryService;
import com.houselibrary.core.service.BookService;
import com.houselibrary.core.template.Request;
import com.houselibrary.core.template.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<Book> implements BookService {

    private CategoryService categoryService;
    private SubcategoryService subcategoryService;
    private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setSubcategoryService(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public List<Book> findByTitle(String title) {

        BookRepository bookRepository = (BookRepository) repository;
        return bookRepository.findByTitle(title);
    }

    @Override
    public void changePriority(int book_id, PriorityChangeRequest request) {

        Book book = get(book_id);
        try {
            Priority priority = Priority.fromValue(request.getPriorityValue());
            book.setPriority(priority);
        } catch (IllegalArgumentException ex) {
            throw new HouseLibraryException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        repository.save(book);
    }

    @Override
    protected Book create(Request<Book> request) {
        BookRequest bookRequest = (BookRequest) request;
        Category category = categoryService.findByName(bookRequest.getCategoryName());
        Subcategory subcategory = subcategoryService.findByName(bookRequest.getSubcategoryName());
        List<Author> authors =
                bookRequest.getAuthors() != null ?
                        authorService.getAuthors(bookRequest.getAuthors()) : Collections.emptyList();
        String priorityValue = bookRequest.getPriorityValue();
        Priority priority;
        try {
            priority = priorityValue != null ? Priority.fromValue(priorityValue) : Priority.MEDIUM;
        } catch (IllegalArgumentException ex) {
            throw new HouseLibraryException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        if (!category.getSubcategories().contains(subcategory)) {
            throw new HouseLibraryException(HttpStatus.BAD_REQUEST,
                    "Category with name: " + category.getName()
                            + " does not contain subcategory with name: " + subcategory.getName());
        }
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .subcategory(subcategory)
                .priority(priority)
                .authors(authors)
                .build();
        authors.forEach(author -> author.addBook(book));
        return book;
    }

    @Override
    protected Book update(Request<Book> request) {
        return null;
    }

    @Override
    protected void clean(Book book) {
        List<Author> authors = book.getAuthors();
        authors.forEach(author -> author.removeBook(book));
        System.out.println("Book clean");

    }


    @Override
    protected String getClassName() {
        return "book";
    }

}
