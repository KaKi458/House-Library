package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Author;
import com.houselibrary.core.template.Request;
import lombok.Getter;

@Getter
public class AuthorRequest extends Request<Author> {

    @JsonProperty(value = "first name", required = true)
    private String firstName;
    @JsonProperty(value = "last name", required = true)
    private String lastName;
}
