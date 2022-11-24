package com.houselibrary.core.service.impl;

import com.houselibrary.core.model.*;
import com.houselibrary.core.repository.CategoryRepository;
import com.houselibrary.api.model.request.CategoryRequest;
import com.houselibrary.core.service.CategoryService;
import com.houselibrary.core.template.Request;
import com.houselibrary.core.template.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<Category> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
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

    @Override
    protected Category create(Request<Category> request) {
        CategoryRequest categoryRequest = (CategoryRequest) request;
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }

    @Override
    protected Category update(Request<Category> request) {
        return null;
    }

    @Override
    protected void clean(Category category) {
        List<Book> books = category.getBooks();
        List<Subcategory> subcategories = category.getSubcategories();
        for (Book book : books) {
            List<Author> authors = book.getAuthors();
            authors.forEach(author -> author.removeBook(book));
        }
        for (Subcategory subcategory : subcategories) {
            subcategory.setBooks(null);
        }
    }

    @Override
    protected String getClassName() {
        return "category";
    }
}
