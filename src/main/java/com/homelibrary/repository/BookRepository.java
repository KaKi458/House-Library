package com.homelibrary.repository;

import com.homelibrary.model.Book;
import com.homelibrary.model.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByPriority(Priority priority, Pageable pageable);
}
