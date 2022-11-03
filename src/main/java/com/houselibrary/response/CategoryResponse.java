package com.houselibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryResponse {

    @JsonProperty
    private final int id;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final int numberOfSubcategories;
    @JsonProperty
    private final int numberOfBooks;
}
