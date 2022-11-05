package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.BookRepository;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.repository.Repository;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.request.CategoryRequest;
import com.houselibrary.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceImpl<Category> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Override
    public Category add(Request<Category> request) {
        CategoryRequest categoryRequest = (CategoryRequest) request;
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();
        repository.save(category);
        return category;
    }

    @Override
    public Category findByName(String name) {
        CategoryRepository categoryRepository = (CategoryRepository) repository;
        Category category;
        Optional<Category> optional = categoryRepository.findByName(name);
        if (optional.isPresent()) {
            category = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The category with name: " + name + " does not exist.");
        }
        return category;
    }

    @Override
    public List<Book> getBooks(int category_id) {
        Category category = get(category_id);
        return category.getBooks();
    }

    @Override
    public List<Subcategory> getSubcategories(int category_id) {
        Category category = get(category_id);
        return category.getSubcategories();
    }
}
