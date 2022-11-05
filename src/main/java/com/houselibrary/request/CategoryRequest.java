package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.model.Category;
import org.springframework.boot.jackson.JsonComponent;

public class CategoryRequest extends Request<Category> {

    @JsonProperty
    private String name;

    public CategoryRequest() {}

    public CategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

