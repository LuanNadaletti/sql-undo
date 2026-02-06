package factories;

import com.sqlundo.exception.MalformattedQueryException;
import com.sqlundo.factories.InsertQueryFactory;
import com.sqlundo.models.InsertQuery;
import com.sqlundo.models.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Luan Nadaletti
 *
 */
class InsertQueryFactoryTest {

    @Test
    void testInsertQuerySingleValue() {
        String script = "INSERT INTO table (field1) VALUES ('value1');";
        InsertQueryFactory factory = new InsertQueryFactory();

        Query query = factory.createQuery(script);
        assertInstanceOf(InsertQuery.class, query);

        InsertQuery insertQuery = (InsertQuery) query;
        assertEquals("table", insertQuery.getTable());
        assertIterableEquals(insertQuery.getFields(), List.of("field1"));
        assertIterableEquals(insertQuery.getValues(), List.of("'value1'"));
        assertEquals(script, insertQuery.toString());
    }

    @Test
    void testInsertQueryMultipleValues() {
        String script = "INSERT INTO table (field1, field2) VALUES ('value1', value2);";
        InsertQueryFactory factory = new InsertQueryFactory();

        Query query = factory.createQuery(script);
        assertInstanceOf(InsertQuery.class, query);

        InsertQuery insertQuery = (InsertQuery) query;
        assertEquals("table", insertQuery.getTable());
        assertIterableEquals(insertQuery.getFields(), List.of("field1", "field2"));
        assertIterableEquals(insertQuery.getValues(), List.of("'value1'", "value2"));
        assertEquals(script, insertQuery.toString());
    }

    @Test
    void testInsertQueryInvalidScript() {
        String script = "INSERT INTO";
        InsertQueryFactory factory = new InsertQueryFactory();

        assertThrows(MalformattedQueryException.class, () -> factory.createQuery(script));
    }
}
