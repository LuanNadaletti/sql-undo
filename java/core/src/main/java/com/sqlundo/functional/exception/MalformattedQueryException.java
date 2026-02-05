package com.sqlundo.functional.exception;

/**
 * Exception thrown when a query is recognized but cannot be reversed because it
 * does not adhere to the established standards.
 *
 * @author Luan Nadaletti
 */
public class MalformattedQueryException extends RuntimeException {

    public MalformattedQueryException(String message) {
        super(message);
    }
}
