package com.houselibrary.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryRequest(@JsonProperty String name) {}
