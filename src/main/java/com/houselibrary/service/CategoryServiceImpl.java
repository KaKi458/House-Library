package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(CategoryRequest request) {
        Category category = Category.builder().name(request.name()).build();
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(int category_id) {
        if (categoryRepository.existsById(category_id)) categoryRepository.deleteById(category_id);
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The category with id: " + category_id + " does not exist.");
    }

    @Override
    public Category getCategory(int category_id) {
        Category category;
        Optional<Category> optional = categoryRepository.findById(category_id);
        if (optional.isPresent()) {
            category = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The category with id: " + category_id + " does not exist.");
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public int countAllCategories() {
        return (int) categoryRepository.count();
    }

    @Override
    public Category findByName(String name) {
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
        Category category = getCategory(category_id);
        return category.getBooks();
    }

    @Override
    public List<Subcategory> getSubcategories(int category_id) {
        Category category = getCategory(category_id);
        return category.getSubcategories();
    }
}
