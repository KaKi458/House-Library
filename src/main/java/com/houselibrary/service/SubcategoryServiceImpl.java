package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.BookRepository;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.request.Request;
import com.houselibrary.request.SubcategoryRequest;
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
    public Subcategory add(Request<Subcategory> request) {
        SubcategoryRequest subcategoryRequest = (SubcategoryRequest) request;
        Subcategory subcategory = Subcategory.builder()
                .name(subcategoryRequest.getName())
                .category(categoryService.findByName(subcategoryRequest.getCategoryName()))
                .build();
        repository.save(subcategory);
        return subcategory;
    }

    @Override
    public Subcategory findByName(String name) {
        SubcategoryRepository subcategoryRepository = (SubcategoryRepository) repository;
        Subcategory subcategory;
        Optional<Subcategory> optional = subcategoryRepository.findByName(name);
        if (optional.isPresent()) {
            subcategory = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The category with name: " + name + " does not exist.");
        }
        return subcategory;

    }

    @Override
    public List<Book> getBooks(int subcategory_id) {
        Subcategory subcategory = get(subcategory_id);
        return subcategory.getBooks();
    }
}
