package com.houselibrary.core.service.impl;

import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.model.HouseLibraryException;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.repository.CategoryRepository;
import com.houselibrary.api.model.request.CategoryRequest;
import com.houselibrary.core.service.CategoryService;
import com.houselibrary.core.template.Request;
import com.houselibrary.core.template.ServiceImpl;
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
