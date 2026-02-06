package com.sqlundo.enums;

import com.sqlundo.models.AlterTableQuery;
import com.sqlundo.models.CreateQuery;
import com.sqlundo.models.InsertQuery;
import com.sqlundo.models.Query;
import com.sqlundo.reversers.AlterTableQueryReverser;
import com.sqlundo.reversers.CreateQueryReverser;
import com.sqlundo.reversers.InsertQueryReverser;
import com.sqlundo.reversers.QueryReverser;

import java.util.function.Supplier;

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

    /**
     * Determines the {@code QueryReverserType} based on the given {@link Query}
     * object.
     *
     * @param query The query object.
     * @return The query reverser type associated with the query, or {@code null} if
     * no match is found.
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

    public QueryReverser getReverserInstance() {
        return reverserSupplier.get();
    }

}
