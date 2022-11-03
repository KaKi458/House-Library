package com.houselibrary.repository;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    List<Book> findAll();

    List<Book> findByTitle(String title);

    long countByCategory(Category category);

    long countBySubcategory(Subcategory subcategory);
}
