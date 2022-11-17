package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Subcategory;
import com.houselibrary.core.template.Request;
import lombok.Getter;

@Getter
public class SubcategoryRequest extends Request<Subcategory> {

    @JsonProperty
    private String name;
    @JsonProperty
    private String categoryName;
}