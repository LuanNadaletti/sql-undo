package factories;

import com.sqlundo.enums.CreateQueryType;
import com.sqlundo.exception.MalformattedQueryException;
import com.sqlundo.factories.CreateQueryFactory;
import com.sqlundo.models.CreateQuery;
import com.sqlundo.models.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertInstanceOf(CreateQuery.class, query);

        CreateQuery createQuery = (CreateQuery) query;
        assertEquals("table_name", createQuery.getTable());
        assertEquals(CreateQueryType.TABLE, createQuery.getCreateQueryType());
        assertEquals(script, createQuery.toString());
    }

    @Test
    void testCreateSequence() {
        String script = "CREATE SEQUENCE sequence_name;";
        CreateQueryFactory factory = new CreateQueryFactory();

        Query query = factory.createQuery(script);
        assertInstanceOf(CreateQuery.class, query);

        CreateQuery createQuery = (CreateQuery) query;
        assertEquals("sequence_name", createQuery.getTable());
        assertEquals(CreateQueryType.SEQUENCE, createQuery.getCreateQueryType());
        assertEquals(script, createQuery.toString());
    }

    @Test
    void testCreateQueryInvalidScript() {
        String script = "CREATE TABLE";
        CreateQueryFactory factory = new CreateQueryFactory();

        assertThrows(MalformattedQueryException.class, () -> factory.createQuery(script));
    }
}