package com.houselibrary.service;

import com.houselibrary.model.Subcategory;
import com.houselibrary.request.SubcategoryRequest;
import com.houselibrary.response.SubcategoryResponse;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SubcategoryService {

    SubcategoryResponse addSubcategory(@NotNull SubcategoryRequest request);

    void deleteSubcategory(@NotNull int category_id);

    SubcategoryResponse getSubcategory(@NotNull int category_id);

    List<SubcategoryResponse> getAllSubcategories();

    int countAllSubcategories();

    SubcategoryResponse findByName(@NotNull String name);
}
