package com.houselibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubcategoryResponse {

    @JsonProperty
    private final int id;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final int categoryId;
    @JsonProperty
    private final String categoryName;
    @JsonProperty
    private final int numberOfBooks;
}
