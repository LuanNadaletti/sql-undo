package com.sqlundo.api.config;

import com.sqlundo.functional.exception.UnsupportedQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnsupportedQueryException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedQueryException() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(), "Unsupported query conversion");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
    }
}
