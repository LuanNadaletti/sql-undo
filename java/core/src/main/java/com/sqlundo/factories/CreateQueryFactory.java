package com.sqlundo.functional.factories;

import java.util.regex.Matcher;

import com.sqlundo.functional.enums.CreateQueryType;
import com.sqlundo.functional.exception.MalformattedQueryException;
import com.sqlundo.functional.models.CreateQuery;
import com.sqlundo.functional.models.Query;

/**
 * Factory class responsible for creating a {@link CreateQuery} object based on
 * a provided statement. The statement is parsed using a regular expression
 * pattern, and the extracted values are used to create the query object.
 * <p>
 * The expected format of the statement is: CREATE <type>
 * <table>
 * <p>
 * The factory uses regular expressions to extract the type and table name from
 * the statement and creates a {@link CreateQuery} object with the extracted
 * values.
 * <p>
 * This factory only supports parsing CREATE statements with the specified
 * format.
 * <p>
 * The type is matched to the {@link CreateQueryType} enumeration using the
 * {@link CreateQueryType#fromType(String)} method.
 *
 * @author Luan Nadaletti
 */
public class CreateQueryFactory extends BaseQueryFactory {

    /**
     * Creates a {@link CreateQuery} object based on the provided statement.
     *
     * @param statement The CREATE statement to create the query from.
     * @return A {@link CreateQuery} object representing the parsed query.
     * @throws MalformattedQueryException If the statement is invalid and cannot be
     *                                    parsed.
     */
    @Override
    public Query createQuery(String statement) {
        Matcher matcher = match(statement, "\\s*CREATE\\s*(TABLE|SEQUENCE|EXCEPTION)\\s*(\\w+);\\s*");

        return new CreateQuery(statement, matcher.group(2), CreateQueryType.fromType(matcher.group(1)));
    }

}
