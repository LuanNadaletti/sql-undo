package com.sqlundo.functional.models;

import java.util.List;

/**
 * Represents an INSERT query, used to insert data into a database table. This
 * class extends the {@link Query} class and provides additional properties for
 * the fields and values of the insert query.
 * <p>
 * The class provides getter methods to retrieve the fields and values
 * associated with the insert query.
 * <p>
 * Instances of this class are immutable once created.
 *
 * @author Luan Nadaletti
 */
public class InsertQuery extends Query {

    private final List<String> fields;
    private final List<String> values;

    /**
     * Constructs an InsertQuery object with the provided statement, table name,
     * fields, and values.
     *
     * @param statement The original statement of the INSERT query.
     * @param table     The name of the table where data is being inserted.
     * @param fields    The fields being inserted.
     * @param values    The corresponding values for the fields.
     */
    public InsertQuery(String statement, String table, List<String> fields,
                       List<String> values) {
        super(statement, table);
        this.fields = fields;
        this.values = values;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<String> getValues() {
        return values;
    }

}
