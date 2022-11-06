package com.houselibrary.core.repository;

import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.template.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface BookRepository extends Repository<Book> {

    List<Book> findByTitle(String title);

    long countByCategory(Category category);

    long countBySubcategory(Subcategory subcategory);

    @Override
    default Optional<Book> findByName(String name) {
        return findByTitle(name).stream().findFirst();
    }
}
