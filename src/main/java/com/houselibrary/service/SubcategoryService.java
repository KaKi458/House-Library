package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.SubcategoryRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SubcategoryService extends Service<Subcategory> {

    List<Book> getBooks(@NotNull int subcategory_id);
}
