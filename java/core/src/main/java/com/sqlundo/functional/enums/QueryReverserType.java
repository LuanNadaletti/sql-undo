package com.sqlundo.functional.enums;

import java.util.function.Supplier;

import com.sqlundo.functional.models.AlterTableQuery;
import com.sqlundo.functional.models.CreateQuery;
import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.models.Query;
import com.sqlundo.functional.reversers.AlterTableQueryReverser;
import com.sqlundo.functional.reversers.CreateQueryReverser;
import com.sqlundo.functional.reversers.InsertQueryReverser;
import com.sqlundo.functional.reversers.QueryReverser;

/**
 * The {@code QueryReverserType} enum represents different types of query
 * reversers. Each query reverser type is associated with a corresponding
 * {@link QueryReverser} implementation that can reverse a specific type of SQL
 * query.
 *
 * <p>
 * This enum provides methods to retrieve the appropriate {@link QueryReverser}
 * for each query reverser type, and to determine the query reverser type based
 * on a given {@link Query} object.
 * </p>
 *
 * <p>
 * Example usage:
 *
 * <pre>{@code
 * Query query = new InsertQuery("INSERT INTO table (column) VALUES ('value')");
 * QueryReverserType reverserType = QueryReverserType.fromQuery(query);
 * QueryReverser reverser = reverserType.getQueryReverser();
 * }</pre>
 * </p>
 *
 * @author Luan Nadaletti
 * @see QueryReverser
 */
public enum QueryReverserType {

    INSERT_REVERSER(InsertQueryReverser::new),
    CREATE_REVERSER(CreateQueryReverser::new),
    ALTER_TABLE_REVERSER(AlterTableQueryReverser::new);

    private final Supplier<QueryReverser> reverserSupplier;

    QueryReverserType(Supplier<QueryReverser> reverserSupplier) {
        this.reverserSupplier = reverserSupplier;
    }

    public QueryReverser getReverserInstance() {
        return reverserSupplier.get();
    }

    /**
     * Determines the {@code QueryReverserType} based on the given {@link Query}
     * object.
     *
     * @param query The query object.
     * @return The query reverser type associated with the query, or {@code null} if
     *         no match is found.
     */
    public static QueryReverserType fromQuery(Query query) {
        if (query instanceof InsertQuery) {
            return INSERT_REVERSER;
        }
        if (query instanceof CreateQuery) {
            return CREATE_REVERSER;
        }
        if (query instanceof AlterTableQuery) {
            return ALTER_TABLE_REVERSER;
        }

        return null;
    }

}
