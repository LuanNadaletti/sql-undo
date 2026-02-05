package reversers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.enums.CreateQueryType;
import com.sqlundo.functional.models.CreateQuery;
import com.sqlundo.functional.reversers.CreateQueryReverser;

class CreateQueryReverserTest {

    @Test
    void testCreateQueryTableReverser() {
        CreateQuery createQuery = new CreateQuery("CREATE TABLE table1;", "table1", CreateQueryType.TABLE);
        CreateQueryReverser reverser = new CreateQueryReverser();

        assertEquals("DROP TABLE table1;", reverser.reverse(createQuery));
    }

    @Test
    void testCreateQuerySequenceReverser() {
        CreateQuery createQuery = new CreateQuery("CREATE SEQUENCE sequence1;", "sequence1", CreateQueryType.SEQUENCE);
        CreateQueryReverser reverser = new CreateQueryReverser();

        assertEquals("DROP SEQUENCE sequence1;", reverser.reverse(createQuery));
    }
}
