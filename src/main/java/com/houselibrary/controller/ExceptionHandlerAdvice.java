package com.houselibrary.controller;

import com.houselibrary.model.HouseLibraryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    private String toJsonErrorMessage(String errMsg) {

        JSONObject json = new JSONObject().put("error", new JSONObject().put("message", errMsg));
        return json.toString();
    }

    @ExceptionHandler(HouseLibraryException.class)
    public ResponseEntity<?> handleException(HouseLibraryException e) {

        log.error("Session API exception raised: ", e);
        return ResponseEntity
                .status(e.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJsonErrorMessage(e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {

        log.error("MethodArgumentNotValidException raised: ", e);

        FieldError fe = e.getBindingResult().getFieldError();
        String field = "<unknown>";
        if (fe != null) {
            field = fe.getField();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJsonErrorMessage("Validation failed for parameter '" + field + "'"));
    }

    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<?> handleException(ValueInstantiationException e) {

        log.error("ValueInstantiationException raised: ", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        toJsonErrorMessage(
                                "Validation failed for parameter '" + e.getPath().get(0).getFieldName()));
    }
}

