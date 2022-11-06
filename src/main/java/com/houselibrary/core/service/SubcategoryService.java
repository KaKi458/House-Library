package com.houselibrary.core.service;

import com.houselibrary.core.model.Book;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.template.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SubcategoryService extends Service<Subcategory> {

    List<Book> getBooks(@NotNull int subcategory_id);
}
