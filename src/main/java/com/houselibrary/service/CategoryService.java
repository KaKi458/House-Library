package com.houselibrary.service;

import com.houselibrary.model.Category;
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
}
