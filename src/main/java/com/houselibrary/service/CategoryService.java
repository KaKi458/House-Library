package com.houselibrary.service;

import com.houselibrary.model.Category;
import com.houselibrary.request.CategoryRequest;
import com.houselibrary.response.CategoryResponse;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(@NotNull CategoryRequest request);

    void deleteCategory(@NotNull int category_id);

    CategoryResponse getCategory(@NotNull int category_id);

    List<CategoryResponse> getAllCategories();

    int countAllCategories();

    CategoryResponse findByName(@NotNull String name);
}
