package com.houselibrary.model;

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
