package com.sqlundo.functional.reversers;

import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.models.Query;

/**
 * Reverses an InsertQuery by generating the reversed SQL statement. This class
 * extends the {@link QueryReverser} class and provides the implementation to
 * reverse an InsertQuery.
 * <p>
 * The class provides the {@link #reverse(Query)} method, which takes an
 * InsertQuery as input and generates the reversed SQL statement.
 * <p>
 * Instances of this class are stateless and can be reused for multiple queries.
 *
 * @author Luan Nadaletti
 */
public class InsertQueryReverser extends QueryReverser {

    /**
     * Reverses an InsertQuery by generating the reversed SQL statement.
     *
     * @param query The InsertQuery to be reversed.
     * @return The reversed SQL statement.
     * @throws IllegalArgumentException If the provided query is not an instance
     *                                  of InsertQuery.
     */
    @Override
    public String reverse(Query query) {
        InsertQuery insertQuery = (InsertQuery) query;

        StringBuilder where = new StringBuilder();

        String operator;
        for (int i = 0; i < insertQuery.getValues().size(); i++) {
            operator = insertQuery.getValues().get(i).equalsIgnoreCase("NULL")
                    ? " IS "
                    : " = ";

            where.append(insertQuery.getFields().get(i)).append(operator)
                    .append(insertQuery.getValues().get(i));

            if (i != insertQuery.getValues().size() - 1) {
                where.append(" AND ");
            }
        }

        return String.format("DELETE FROM %s WHERE %s;",
                insertQuery.getTable(), where);
    }

}
