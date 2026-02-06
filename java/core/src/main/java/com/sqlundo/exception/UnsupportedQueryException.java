package com.sqlundo.exception;

public class UnsupportedQueryException extends RuntimeException {

    public UnsupportedQueryException(String message) {
        super(message);
    }
}
