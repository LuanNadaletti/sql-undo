package com.sqlundo.api.model;

public class QueryDTO {

    private String original;
    private String reverted;

    public QueryDTO(String original, String reverted) {
        this.original = original;
        this.reverted = reverted;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getReverted() {
        return reverted;
    }

    public void setReverted(String reverted) {
        this.reverted = reverted;
    }
}
