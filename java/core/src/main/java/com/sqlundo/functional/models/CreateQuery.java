package com.sqlundo.functional.models;

import com.sqlundo.functional.enums.CreateQueryType;

/**
 * Represents a CREATE query, used to create a new database table. This class
 * extends the {@link Query} class and provides an additional property for the
 * type of create query.
 * <p>
 * The class provides a getter method to retrieve the create query type
 * associated with the query.
 * <p>
 * Instances of this class are immutable once created.
 *
 * @author Luan Nadaletti
 */
public class CreateQuery extends Query {

    private final CreateQueryType createQueryType;

    /**
     * Constructs a CreateQuery object with the provided statement, table name,
     * and create query type.
     *
     * @param statement       The original statement of the CREATE query.
     * @param table           The name of the table being created.
     * @param createQueryType The type of the create query (e.g., TABLE,
     *                        SEQUENCE).
     */
    public CreateQuery(String statement, String table,
                       CreateQueryType createQueryType) {
        super(statement, table);
        this.createQueryType = createQueryType;
    }

    /**
     * Retrieves the create query type associated with the query.
     *
     * @return The create query type.
     */
    public CreateQueryType getCreateQueryType() {
        return createQueryType;
    }

}
