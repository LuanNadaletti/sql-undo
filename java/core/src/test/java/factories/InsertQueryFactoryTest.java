package factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.exception.MalformattedQueryException;
import com.sqlundo.functional.factories.InsertQueryFactory;
import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.models.Query;

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
        assertTrue(query instanceof InsertQuery);

        InsertQuery insertQuery = (InsertQuery) query;
        assertEquals(insertQuery.getTable(), "table");
        assertIterableEquals(insertQuery.getFields(), Arrays.asList("field1"));
        assertIterableEquals(insertQuery.getValues(), Arrays.asList("'value1'"));
        assertEquals(insertQuery.toString(), script);
    }

    @Test
    void testInsertQueryMultipleValues() {
        String script = "INSERT INTO table (field1, field2) VALUES ('value1', value2);";
        InsertQueryFactory factory = new InsertQueryFactory();

        Query query = factory.createQuery(script);
        assertTrue(query instanceof InsertQuery);

        InsertQuery insertQuery = (InsertQuery) query;
        assertEquals(insertQuery.getTable(), "table");
        assertIterableEquals(insertQuery.getFields(), Arrays.asList("field1", "field2"));
        assertIterableEquals(insertQuery.getValues(), Arrays.asList("'value1'", "value2"));
        assertEquals(insertQuery.toString(), script);
    }

    @Test
    void testInsertQueryInvalidScript() {
        String script = "INSERT INTO";
        InsertQueryFactory factory = new InsertQueryFactory();

        assertThrows(MalformattedQueryException.class, () -> factory.createQuery(script));
    }
}
