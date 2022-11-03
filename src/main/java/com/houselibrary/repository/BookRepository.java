package com.houselibrary.repository;

import com.houselibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    List<Book> findAll();

    List<Book> findByTitle(String title);
}
