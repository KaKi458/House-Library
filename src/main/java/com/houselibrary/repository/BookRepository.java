package com.houselibrary.repository;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface BookRepository extends Repository<Book> {

    List<Book> findByTitle(String title);

    long countByCategory(Category category);

    long countBySubcategory(Subcategory subcategory);

//    @Override
//    default Optional<Book> findByName(String name) {
//        return findByTitle(name).stream().findFirst();
//    }
}
