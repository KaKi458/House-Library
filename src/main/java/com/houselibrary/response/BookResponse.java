package com.houselibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookResponse {

    @JsonProperty
    private final int id;
    @JsonProperty
    private final String title;
    @JsonProperty
    private final int categoryId;
    @JsonProperty
    private final String categoryName;
    @JsonProperty
    private final int subcategoryId;
    @JsonProperty
    private final String subcategoryName;
}
