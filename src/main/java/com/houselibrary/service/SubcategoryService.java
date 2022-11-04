package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Subcategory;
import com.houselibrary.request.SubcategoryRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SubcategoryService {

    Subcategory addSubcategory(@NotNull SubcategoryRequest request);

    void deleteSubcategory(@NotNull int category_id);

    Subcategory getSubcategory(@NotNull int category_id);

    List<Subcategory> getAllSubcategories();

    int countAllSubcategories();

    Subcategory findByName(@NotNull String name);

    List<Book> getBooks(@NotNull int subcategory_id);

}
