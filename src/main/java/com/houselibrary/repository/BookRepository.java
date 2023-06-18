package com.houselibrary.repository;

import com.houselibrary.model.Book;
import com.houselibrary.model.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findBySubcategoryId(Long subcategoryId, Pageable pageable);
    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Book> findByPriority(Priority priority, Pageable pageable);
    Page<Book> findBySubcategoryIdAndPriority(Long subcategoryId, Priority priority, Pageable pageable);
    Page<Book> findByCategoryIdAndPriority(Long categoryId, Priority priority, Pageable pageable);
}
