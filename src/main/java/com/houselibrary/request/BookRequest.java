package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.model.Book;
import com.houselibrary.model.Category;

public class BookRequest extends Request<Book> {
    @JsonProperty
    private String title;
    @JsonProperty
    private String categoryName;
    @JsonProperty
    private String subcategoryName;

    public BookRequest() {}

    public BookRequest(String title, String categoryName, String subcategoryName) {
        this.title = title;
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }
}
