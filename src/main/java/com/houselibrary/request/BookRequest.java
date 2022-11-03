package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookRequest(@JsonProperty String title, @JsonProperty String categoryName, @JsonProperty String subcategoryName) {}
