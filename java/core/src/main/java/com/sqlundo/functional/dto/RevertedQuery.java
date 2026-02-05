package com.sqlundo.functional.dto;

/**
 * Core output model representing one reversed SQL statement.
 * Keeps the core module independent of HTTP/API DTOs.
 */
public record RevertedQuery(String original, String reverted) {
}
