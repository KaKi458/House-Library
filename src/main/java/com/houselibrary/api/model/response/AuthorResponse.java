package com.houselibrary.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
public class AuthorResponse {

    @JsonProperty
    private final int id;
    @JsonProperty("first_name")
    private final String firstName;
    @JsonProperty("last_name")
    private final String lastName;
    @JsonProperty(value = "books")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookResponse> books;

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }
}
