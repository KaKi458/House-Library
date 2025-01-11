package com.homelibrary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HomeLibraryException extends RuntimeException {

  private final HttpStatus httpStatus;

  public HomeLibraryException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

}
