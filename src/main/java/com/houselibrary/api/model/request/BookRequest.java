package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.model.Book;
import com.houselibrary.core.template.Request;
import lombok.Getter;

import java.util.List;

@Getter
public class BookRequest extends Request<Book> {

    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    private String categoryName;
    @JsonProperty(required = true)
    private String subcategoryName;
    @JsonProperty("priority")
    private String priorityValue;
    @JsonProperty("authors")
    private List<AuthorRequest> authors;
}
