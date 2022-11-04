package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.CategoryRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CategoryService {

    Category addCategory(@NotNull CategoryRequest request);

    void deleteCategory(@NotNull int category_id);

    Category getCategory(@NotNull int category_id);

    List<Category> getAllCategories();

    int countAllCategories();

    Category findByName(@NotNull String name);

    List<Book> getBooks(int category_id);

    List<Subcategory> getSubcategories(int category_id);
}
