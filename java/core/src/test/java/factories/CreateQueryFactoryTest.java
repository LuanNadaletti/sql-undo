package factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.enums.CreateQueryType;
import com.sqlundo.functional.exception.MalformattedQueryException;
import com.sqlundo.functional.factories.CreateQueryFactory;
import com.sqlundo.functional.models.CreateQuery;
import com.sqlundo.functional.models.Query;

/**
 *
 * @author Luan Nadaletti
 */
class CreateQueryFactoryTest {

    @Test
    void testCreateTable() {
        String script = "CREATE TABLE table_name;";
        CreateQueryFactory factory = new CreateQueryFactory();

        Query query = factory.createQuery(script);
        assertTrue(query instanceof CreateQuery);

        CreateQuery createQuery = (CreateQuery) query;
        assertEquals(createQuery.getTable(), "table_name");
        assertEquals(createQuery.getCreateQueryType(), CreateQueryType.TABLE);
        assertEquals(createQuery.toString(), script);
    }

    @Test
    void testCreateSequence() {
        String script = "CREATE SEQUENCE sequence_name;";
        CreateQueryFactory factory = new CreateQueryFactory();

        Query query = factory.createQuery(script);
        assertTrue(query instanceof CreateQuery);

        CreateQuery createQuery = (CreateQuery) query;
        assertEquals(createQuery.getTable(), "sequence_name");
        assertEquals(createQuery.getCreateQueryType(), CreateQueryType.SEQUENCE);
        assertEquals(createQuery.toString(), script);
    }

    @Test
    void testCreateQueryInvalidScript() {
        String script = "CREATE TABLE";
        CreateQueryFactory factory = new CreateQueryFactory();

        assertThrows(MalformattedQueryException.class, () -> factory.createQuery(script));
    }
}