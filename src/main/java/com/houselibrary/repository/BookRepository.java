package com.houselibrary.repository;

import com.houselibrary.model.Book;
import com.houselibrary.model.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByPriority(Priority priority, Pageable pageable);
}
