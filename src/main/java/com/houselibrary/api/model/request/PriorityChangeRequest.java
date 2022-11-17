package com.houselibrary.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.houselibrary.core.model.Priority;
import lombok.Getter;

@Getter
public class PriorityChangeRequest {

    @JsonProperty("priority")
    private String priorityValue;
}
