package com.sqlundo.functional.models;

/**
 * Abstract class representing a database query.
 *
 * @author Luan Nadaletti
 */
public abstract class Query {

    protected final String statement;
    protected final String table;

    protected Query(String statement, String table) {
        this.statement = statement;
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    @Override
    public String toString() {
        return statement;
    }
}
