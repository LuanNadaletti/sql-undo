package com.sqlundo.functional.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import com.sqlundo.functional.exception.MalformattedQueryException;
import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.models.Query;

/**
 * Factory class responsible for creating an {@link InsertQuery} object based on
 * a provided statement. The statement is parsed using a regular expression
 * pattern, and the extracted values are used to create the query object.
 * <p>
 * The expected format of the statement is: INSERT INTO
 * <table>
 * (<fields>) VALUES (<values>)
 * <p>
 * The factory uses regular expressions to extract the table name, fields, and
 * values from the statement and creates an {@link InsertQuery} object with the
 * extracted values.
 * <p>
 * This factory only supports parsing INSERT statements with the specified
 * format.
 * <p>
 * The fields and values are extracted as comma-separated strings and converted
 * to lists using the {@link Arrays#asList(Object[])} method. The values are
 * trimmed to remove leading and trailing whitespace using the
 * {@link String#trim()} method.
 * <p>
 * The resulting {@link InsertQuery} object contains the statement, table name,
 * fields, and values.
 *
 * @author Luan Nadaletti
 */
public class InsertQueryFactory extends BaseQueryFactory {

    /**
     * Creates an {@link InsertQuery} object based on the provided statement.
     *
     * @param statement The INSERT statement to create the query from.
     * @return An {@link InsertQuery} object representing the parsed query.
     * @throws MalformattedQueryException If the statement is invalid and cannot
     *                                  be parsed.
     */
    @Override
    public Query createQuery(String statement) {
        Matcher matcher = match(statement, "\\s*INSERT\\s*INTO\\s*(\\w+)\\s*\\((.+)\\)\\s*VALUES\\s*\\((.+)\\)\\s*");

        String table = matcher.group(1);
        String fieldsGroup = matcher.group(2);
        String valuesGroup = matcher.group(3);

        List<String> fields = parseFieldsOrValues(fieldsGroup);
        List<String> values = parseFieldsOrValues(valuesGroup);
        return new InsertQuery(statement, table, fields, values);
    }

    private List<String> parseFieldsOrValues(String group) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : group.toCharArray()) {
            if (c == '"' || c == '\'') {
                insideQuotes = !insideQuotes;
            }

            if (c == ',' && !insideQuotes) {
                result.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            result.add(current.toString().trim());
        }

        return result;
    }

}
