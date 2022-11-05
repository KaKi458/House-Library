package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.CategoryRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CategoryService extends Service<Category> {

    List<Book> getBooks(@NotNull int category_id);

    List<Subcategory> getSubcategories(@NotNull int category_id);
}
