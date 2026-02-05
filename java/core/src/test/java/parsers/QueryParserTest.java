package parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.exception.UnsupportedQueryException;
import com.sqlundo.functional.models.CreateQuery;
import com.sqlundo.functional.models.InsertQuery;
import com.sqlundo.functional.models.Query;
import com.sqlundo.functional.parsers.QueryParser;

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
		assertTrue(insertQuery instanceof InsertQuery, "Expected the first query to be an InsertQuery, but it wasn't.");
		Query createQuery = queries.get(1);
		assertTrue(createQuery instanceof CreateQuery, "Expected the second query to be a CreateQuery, but it wasn't.");

		insertQuery = (InsertQuery) insertQuery;
		assertEquals("INSERT INTO table (column) VALUES ('value');", insertQuery.toString(),
				"The statement in InsertQuery does not match the expected value.");
		createQuery = (CreateQuery) createQuery;
		assertEquals("CREATE TABLE table;", createQuery.toString(),
				"The statement in CreateQuery does not match the expected value.");
	}

	@Test
	void testUnsupportedQueryThrowsException() {
		QueryParser parser = new QueryParser("INCORRECT QUERY");

		assertThrows(UnsupportedQueryException.class, () -> parser.parse());
	}

	@Test
	void testUpdateQueryIsIgnored() {
		String script = "UPDATE table SET column = value";
		QueryParser parser = new QueryParser(script);

		List<Query> queries = parser.parse();
		assertTrue(queries.isEmpty(), "Queries list must be empty when parsing an update query");
	}
}
