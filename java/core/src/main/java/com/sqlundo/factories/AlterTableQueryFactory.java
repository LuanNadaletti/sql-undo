package com.sqlundo.functional.factories;

import java.util.regex.Matcher;

import com.sqlundo.functional.enums.AlterType;
import com.sqlundo.functional.exception.MalformattedQueryException;
import com.sqlundo.functional.models.AlterTableQuery;
import com.sqlundo.functional.models.Query;

/**
 * Factory class responsible for creating an {@link AlterTableQuery} object
 * based on a provided statement. The statement is parsed using a regular
 * expression pattern, and the extracted values are used to create the query
 * object.
 * <p>
 * The expected format of the statement is: ALTER TABLE
 * <table>
 * <operator> <column> <dataType>
 * <p>
 * The factory uses regular expressions to extract the relevant parts from the
 * statement and creates an {@link AlterTableQuery} object with the extracted
 * values.
 * <p>
 * This factory only supports parsing ALTER TABLE statements with the specified
 * format.
 *
 * @author Luan Nadaletti
 */
public class AlterTableQueryFactory extends BaseQueryFactory {

    /**
     * Creates an {@link AlterTableQuery} object based on the provided statement.
     *
     * @param statement The ALTER TABLE statement to create the query from.
     * @return An {@link AlterTableQuery} object representing the parsed query.
     * @throws MalformattedQueryException If the statement is invalid and cannot be
     *                                    parsed.
     */
    @Override
    public Query createQuery(String statement) {
        Matcher matcher = match(statement, "ALTER TABLE (\\w+)\\s+(ADD|DROP)\\s+(COLUMN\\s+)?(CONSTRAINT\\s+)?(\\w+)?(?:\\s+(\\w+))?");

        String table = matcher.group(1);
        String operator = matcher.group(2);
        String column = matcher.group(5);
        String dataType = matcher.group(6);
        AlterType alterType = AlterType.NONE;

        if (matcher.group(3) != null) {
            alterType = AlterType.COLUMN;
        } else if (matcher.group(4) != null) {
            alterType = AlterType.CONSTRAINT;
        }

        return new AlterTableQuery(statement, table, operator, column, dataType, alterType);
    }

}
