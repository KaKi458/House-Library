package com.houselibrary.model;

import java.util.Objects;

public enum Priority {

  ZERO(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;
  Priority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Priority fromValue(int value) {
    for (Priority priority : Priority.values()) {
      if (value == priority.getValue()) {
        return priority;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
