package com.sqlundo.functional.enums;

import java.util.Locale;
import java.util.function.Supplier;

import com.sqlundo.functional.factories.AlterTableQueryFactory;
import com.sqlundo.functional.factories.BaseQueryFactory;
import com.sqlundo.functional.factories.CreateQueryFactory;
import com.sqlundo.functional.factories.InsertQueryFactory;

/**
 * The {@code QueryType} enum represents different types of SQL queries. Each
 * query type is associated with a corresponding {@link QueryFactory}
 * implementation that can create query objects of that type.
 *
 * <p>
 * This enum provides methods to retrieve the appropriate {@link QueryFactory}
 * for each query type, and to determine the query type based on a given SQL
 * statement.
 * </p>
 *
 * <p>
 * Example usage:
 *
 * <pre>{@code
 * String statement = "INSERT INTO table (column) VALUES ('value')";
 * QueryType queryType = QueryType.fromStatement(statement);
 * QueryFactory factory = queryType.getQueryFactory();
 * }</pre>
 * </p>
 *
 * <p>
 * Note that the {@code UPDATE} query type returns {@code null} for the query
 * factory. This is because the {@code UPDATE} statements are not currently
 * supported by the program, and they do not require any specific reversal or
 * object creation logic.
 * </p>
 *
 * <p>
 * It is recommended to use proper validation or error handling when using the
 * {@code UPDATE} query type, to ensure that unsupported statements are not
 * processed.
 * </p>
 *
 * @author Luan Nadaletti
 * @see QueryFactory
 */
public enum QueryType {
    CREATE(CreateQueryFactory::new),
    ALTER(AlterTableQueryFactory::new),
    INSERT(InsertQueryFactory::new),
    UPDATE(null);

    private final Supplier<BaseQueryFactory> factorySupplier;

    QueryType(Supplier<BaseQueryFactory> factorySupplier) {
        this.factorySupplier = factorySupplier;
    }

    public BaseQueryFactory getFactoryInstance() {
        return factorySupplier.get();
    }

    /**
     * Determines the {@code QueryType} based on the given SQL statement.
     *
     * @param statement The SQL statement.
     * @return The query type associated with the statement, or {@code null} if no
     *         match is found.
     */
    public static QueryType fromStatement(String statement) {
        String statementTrimToLowerCase = statement.trim().toLowerCase(Locale.ROOT);

        if (statementTrimToLowerCase.contains("insert")) {
            return INSERT;
        }
        if (statementTrimToLowerCase.contains("create")) {
            return CREATE;
        }
        if (statementTrimToLowerCase.contains("alter")) {
            return ALTER;
        }
        if (statementTrimToLowerCase.contains("update")) {
            return UPDATE;
        }

        return null;
    }

}
