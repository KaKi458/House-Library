package com.houselibrary.core.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import java.util.Objects;

public enum Priority {

    HIGH,
    MEDIUM,
    LOW;

    public static Priority fromValue(String value) {
        for (Priority priority : Priority.values()) {
            if (Objects.equals(value, priority.toString())) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
