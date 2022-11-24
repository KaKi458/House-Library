package com.houselibrary.core.service.impl;

import com.houselibrary.api.model.request.AuthorRequest;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.HouseLibraryException;
import com.houselibrary.core.repository.AuthorRepository;
import com.houselibrary.core.service.AuthorService;
import com.houselibrary.core.service.BookService;
import com.houselibrary.core.template.Request;
import com.houselibrary.core.template.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl extends ServiceImpl<Author> implements AuthorService {


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.repository = authorRepository;
    }

    @Override
    public List<Book> getBooks(int author_id) {
        Author author = get(author_id);
        return author.getBooks();
    }

    @Override
    public Book addBook(int author_id, Book book) {
        Author author = get(author_id);
        author.addBook(book);
        repository.save(author);
        return book;
    }

    @Override
    public Author findByName(AuthorRequest request) {
        AuthorRepository authorRepository = (AuthorRepository) repository;
        Optional<Author> optional =
                authorRepository.findByName(
                        request.getFirstName(),
                        request.getLastName());
        if(optional.isPresent())
            return optional.get();
        else
            throw new HouseLibraryException(
                    HttpStatus.BAD_REQUEST, "Author does not exist.");
    }

    @Override
    public List<Author> getAuthors(List<AuthorRequest> requests) {
        List<Author> authors = new ArrayList<>();
        requests.forEach(
                authorRequest -> authors.add(findByName(authorRequest)));
        return authors;
    }

    @Override
    protected Author create(Request<Author> request) {
        AuthorRequest authorRequest = (AuthorRequest) request;
        return Author.builder()
                .firstName(authorRequest.getFirstName())
                .lastName(authorRequest.getLastName())
                .build();
    }

    @Override
    protected Author update(Request<Author> request) {
        return null;
    }

    @Override
    protected String getClassName() {
        return "author";
    }

    @Override
    protected void clean(Author author) {
        List<Book> books = author.getBooks();
        for(Book book : books) {
            book.removeAuthor(author);
        }
    }
}
