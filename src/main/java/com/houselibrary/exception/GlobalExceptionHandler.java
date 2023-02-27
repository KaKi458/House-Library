package com.houselibrary.exception;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(HouseLibraryException.class)
  public ResponseEntity<?> handleException(HouseLibraryException e) {
    log.error("Session API exception raised: {}", e.getMessage());
    return ResponseEntity.status(e.getHttpStatus())
            .contentType(MediaType.APPLICATION_JSON)
            .body(getJson(e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
    log.error("MethodArgumentNotValidException raised: {}", e.getMessage());
    FieldError fieldError = e.getBindingResult().getFieldError();
    String field = fieldError != null ? fieldError.getField() : "<unknown>";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(getJson("Validation failed for parameter '" + field + "'"));
  }

  @ExceptionHandler(ValueInstantiationException.class)
  public ResponseEntity<?> handleException(ValueInstantiationException e) {
    log.error("ValueInstantiationException raised: {}", e.getMessage());
    String field = e.getPath().get(0).getFieldName();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(getJson("Validation failed for parameter '" + field + "'"));
  }

  private String getJson(String message) {
    return new JSONObject().put("error", message).toString();
  }
}
