package com.houselibrary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorDetails {

    private Date timeStamp;
    private HttpStatusCode httpStatusCode;
    private String message;
}
