package com.houselibrary.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Priority;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
    @JsonProperty
    private final Priority priority;
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AuthorResponse> authors;

    public void setAuthors(List<AuthorResponse> authors) {
        this.authors = authors;
    }
}
