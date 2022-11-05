package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.model.Subcategory;

public class SubcategoryRequest extends Request<Subcategory> {
    @JsonProperty
    private String name;
    @JsonProperty
    private String categoryName;

    public SubcategoryRequest() {}

    public SubcategoryRequest(String name, String categoryName) {
        this.name = name;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}