package com.houselibrary.exception;

import org.springframework.http.HttpStatus;

public class HouseLibraryException extends RuntimeException {

  private final HttpStatus httpStatus;

  public HouseLibraryException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
