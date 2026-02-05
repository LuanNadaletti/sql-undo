package com.sqlundo.functional.reversers;

import com.sqlundo.functional.models.Query;

/**
 * The QueryReverser class is an abstract base class for query reversers. It
 * provides a common interface for all query reverser implementations and
 * defines the contract for reversing queries.
 * <p>
 * To create a custom query reverser, you should extend this class and implement
 * the {@link #reverse(Query)} method.
 *
 * @author Luan Nadaletti
 */
public abstract class QueryReverser {

    /**
     * Reverses the given query and returns the reversed version as a string.
     *
     * @param query The query to be reversed.
     * @return The reversed query as a string.
     */
    public abstract String reverse(Query query);

}
