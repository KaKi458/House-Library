package com.houselibrary.core.service;

import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.template.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CategoryService extends Service<Category> {

    List<Book> getBooks(@NotNull int category_id);

    List<Subcategory> getSubcategories(@NotNull int category_id);
}
