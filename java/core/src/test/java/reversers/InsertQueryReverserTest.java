package reversers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.reversers.InsertQueryReverser;

class InsertQueryReverserTest {

    @Test
    void testRevertInsertQuery() {
        InsertQuery insertQuery = new InsertQuery("INSERT INTO table (field1, field2) VALUES (value1, null)", "table",
                Arrays.asList("field1", "field2"), Arrays.asList("value1", "null"));

        InsertQueryReverser reverser = new InsertQueryReverser();

        assertEquals("DELETE FROM table WHERE field1 = value1 AND field2 IS null;", reverser.reverse(insertQuery));
    }
}
