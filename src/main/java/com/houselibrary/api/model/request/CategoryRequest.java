package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Category;
import com.houselibrary.core.template.Request;
import lombok.Getter;

@Getter
public class CategoryRequest extends Request<Category> {

    @JsonProperty
    private String name;

}

