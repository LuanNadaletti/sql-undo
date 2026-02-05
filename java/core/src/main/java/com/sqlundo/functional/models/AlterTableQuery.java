package com.sqlundo.functional.models;

import java.util.Locale;
import java.util.Map;

import com.sqlundo.functional.enums.AlterType;

/**
 * Represents an ALTER TABLE query, which modifies the structure of a database
 * table. This class extends the {@link Query} class and provides additional
 * properties and functionality specific to ALTER TABLE queries.
 * <p>
 * An ALTER TABLE query consists of an operator (e.g., ADD, DROP), a column
 * name, and optionally a data type for the column.
 * <p>
 * The class provides methods to retrieve the reverse operation for the given
 * operator and to access the query's properties.
 * <p>
 * The reverse operation is determined based on the operator using a predefined
 * mapping in the {@link #operatorToReverseOperation} map.
 * <p>
 * Instances of this class are immutable once created.
 *
 * @author Luan Nadaletti
 */
public class AlterTableQuery extends Query {

    private final String operator;
    private final String target;
    private final String dataType;
    private final AlterType alterType;

    private final Map<String, String> operatorToReverseOperation = Map.ofEntries(Map.entry("ADD", "DROP"),
            Map.entry("DROP", "ADD"));

    /**
     * Constructs an AlterTableQuery object with the provided statement, table name,
     * operator, column name, and data type.
     *
     * @param statement      The original statement of the ALTER TABLE query.
     * @param table          The name of the table being altered.
     * @param operator       The operator of the ALTER TABLE query (e.g., ADD,
     *                       DROP).
     * @param target         The name of the target on table being modified.
     * @param dataType       The data type of the column (optional).
     * @param columnType     A flag indicating if the target is a column (true) or
     *                       not (false).
     * @param constraintType A flag indicating if the target is a constraint (true)
     *                       or not (false).
     */
    public AlterTableQuery(String statement, String table, String operator, String target, String dataType,
            AlterType alterType) {
        super(statement, table);
        this.operator = operator;
        this.target = target;
        this.dataType = dataType;
        this.alterType = alterType;
    }

    /**
     * Retrieves the reverse operation for the given operator. The reverse operation
     * is determined based on the operator using a predefined mapping.
     *
     * @return The reverse operation for the operator.
     */
    public String getReverseOperation() {
        return operatorToReverseOperation.get(operator.toUpperCase(Locale.ROOT));
    }

    /**
     * Gets the column definition clause based on the column and constraint types.
     *
     * @return The column definition clause ("COLUMN" or "CONSTRAINT") based on the
     *         types. Returns null if neither columnType nor constraintType is true.
     */
    public String getColumnDefinitionClause() {
        return switch (alterType) {
        case COLUMN -> "COLUMN";
        case CONSTRAINT -> "CONSTRAINT";
        case NONE -> "";
        };
    }

    public String getOperator() {
        return operator;
    }

    public String getTarget() {
        return target;
    }

    public String getDataType() {
        return dataType;
    }

    public AlterType getAlterType() {
        return alterType;
    }

}
