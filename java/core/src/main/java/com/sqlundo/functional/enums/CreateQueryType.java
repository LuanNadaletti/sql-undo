package com.sqlundo.functional.enums;

/**
 * Enumeration representing the types of create queries. Currently supports
 * TABLE, SEQUENCE and EXCEPTION types.
 */
public enum CreateQueryType {

    TABLE("TABLE"),
    SEQUENCE("SEQUENCE"),
    EXCEPTION("EXCEPTION");

    private final String keyWord;

    CreateQueryType(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    /**
     * Returns the query type based on a type string.
     *
     * @param type The type string to be converted to a query type.
     * @return The query type corresponding to the type string, or null if not
     * found.
     */
    public static CreateQueryType fromType(String type) {
        if ("TABLE".equalsIgnoreCase(type)) {
            return TABLE;
        }
        if ("SEQUENCE".equalsIgnoreCase(type)) {
            return SEQUENCE;
        }
        if ("EXCEPTION".equalsIgnoreCase(type)) {
            return EXCEPTION;
        }
        return null;
    }

}
