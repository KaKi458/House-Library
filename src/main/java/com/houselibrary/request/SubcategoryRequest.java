package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubcategoryRequest(@JsonProperty String name, @JsonProperty String categoryName) { }
