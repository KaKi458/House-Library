package com.houselibrary.model;

import org.springframework.http.HttpStatus;

public class HouseLibraryException extends RuntimeException {

    private HttpStatus httpStatus;

    public HouseLibraryException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
