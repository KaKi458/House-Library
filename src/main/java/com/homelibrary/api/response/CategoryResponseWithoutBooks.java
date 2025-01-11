package com.homelibrary.api.response;

import com.homelibrary.model.Category;
import com.homelibrary.model.Subcategory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryResponseWithoutBooks {

    private final Integer categoryId;
    private final String categoryName;
    private final List<SubcategoryRecord> subcategories;

    public CategoryResponseWithoutBooks(Category category) {
        categoryId = category.getId();
        categoryName = category.getName();
        subcategories = new ArrayList<>();
        getSubcategories(category);
    }

    private void getSubcategories(Category category) {
        List<Subcategory> subcategoryModelList = category.getSubcategories();
        subcategoryModelList.forEach(s -> subcategories.add(new SubcategoryRecord(s.getId(), s.getName())));
    }
}
