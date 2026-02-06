package parsers;

import com.sqlundo.exception.UnsupportedQueryException;
import com.sqlundo.models.CreateQuery;
import com.sqlundo.models.InsertQuery;
import com.sqlundo.models.Query;
import com.sqlundo.parsers.QueryParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luan Nadaletti
 *
 */
class QueryParserTest {

    @Test
    void testParseCreateAndInsertQueries() {
        String script = "CREATE TABLE table; INSERT INTO table (column) VALUES ('value');";
        QueryParser parser = new QueryParser(script);

        List<Query> queries = parser.parse();

        assertEquals(2, queries.size(), "Expected 2 queries in the list after parsing, but got a different size.");

        Query insertQuery = queries.get(0);
        assertInstanceOf(InsertQuery.class, insertQuery, "Expected the first query to be an InsertQuery, but it wasn't.");
        Query createQuery = queries.get(1);
        assertInstanceOf(CreateQuery.class, createQuery, "Expected the second query to be a CreateQuery, but it wasn't.");

        assertEquals("INSERT INTO table (column) VALUES ('value');", insertQuery.toString(),
                "The statement in InsertQuery does not match the expected value.");
        assertEquals("CREATE TABLE table;", createQuery.toString(),
                "The statement in CreateQuery does not match the expected value.");
    }

    @Test
    void testUnsupportedQueryThrowsException() {
        QueryParser parser = new QueryParser("INCORRECT QUERY");

        assertThrows(UnsupportedQueryException.class, parser::parse);
    }

    @Test
    void testUpdateQueryIsIgnored() {
        String script = "UPDATE table SET column = value";
        QueryParser parser = new QueryParser(script);

        List<Query> queries = parser.parse();
        assertTrue(queries.isEmpty(), "Queries list must be empty when parsing an update query");
    }
}
