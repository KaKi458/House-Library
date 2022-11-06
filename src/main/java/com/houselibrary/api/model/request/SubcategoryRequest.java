package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.template.Request;

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