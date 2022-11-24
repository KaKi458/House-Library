package com.houselibrary.core.service.impl;

import com.houselibrary.core.model.*;
import com.houselibrary.core.repository.SubcategoryRepository;
import com.houselibrary.core.service.BookService;
import com.houselibrary.core.service.SubcategoryService;
import com.houselibrary.core.service.CategoryService;
import com.houselibrary.core.template.Request;
import com.houselibrary.api.model.request.SubcategoryRequest;
import com.houselibrary.core.template.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl extends ServiceImpl<Subcategory> implements SubcategoryService {

    private CategoryService categoryService;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.repository = subcategoryRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> getBooks(int subcategory_id) {
        Subcategory subcategory = get(subcategory_id);
        return subcategory.getBooks();
    }

    @Override
    protected Subcategory create(Request<Subcategory> request) {
        SubcategoryRequest subcategoryRequest = (SubcategoryRequest) request;
        return Subcategory.builder()
                .name(subcategoryRequest.getName())
                .category(categoryService.findByName(subcategoryRequest.getCategoryName()))
                .build();
    }

    @Override
    protected Subcategory update(Request<Subcategory> request) {
        return null;
    }

    @Override
    protected void clean(Subcategory subcategory) {
        List<Book> books = subcategory.getBooks();
        for (Book book : books) {
            List<Author> authors = book.getAuthors();
            authors.forEach(author -> author.removeBook(book));
        }
        System.out.println("Subcategory clean");
    }

    @Override
    protected String getClassName() {
        return "subcategory";
    }
}
