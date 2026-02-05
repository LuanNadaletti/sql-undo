package com.sqlundo.functional.exception;

public class UnsupportedQueryException extends RuntimeException {

    public UnsupportedQueryException(String message) {
        super(message);
    }
}
