package com.sqlundo.functional.parsers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sqlundo.functional.enums.QueryType;
import com.sqlundo.functional.exception.UnsupportedQueryException;
import com.sqlundo.functional.factories.BaseQueryFactory;
import com.sqlundo.functional.models.Query;

/**
 * The QueryParser class is responsible for parsing a given SQL script and
 * returning a list of all SQL statements found in the script.
 *
 * <p>
 * This class uses a set of methods to perform the analysis and extraction of
 * SQL statements. The SQL script is provided through the constructor, and the
 * {@link #parse()} method is used to initiate the parsing.
 * </p>
 *
 * @author Luan Nadaletti
 * @see Query
 */
public class QueryParser {

	private static final char STRING_DELIMITER = '\'';

	private final String script;
	private final List<Query> queries = new LinkedList<>();

	private int currentIndex;
	private boolean insideString;

	/**
	 * Creates a new instance of QueryParser with the provided SQL script.
	 *
	 * @param script The SQL script to be parsed.
	 */
	public QueryParser(String script) {
		this.script = script;
	}

	/**
	 * Parses the provided SQL script and returns a list of all SQL statements
	 * found.
	 *
	 * @return A list of all SQL statements found in the script.
	 */
	public List<Query> parse() {
		StringBuilder currentStatement = new StringBuilder();

		while (currentIndex < script.length()) {
			char character = getNextCharacter();

			if (character == STRING_DELIMITER) {
				handleStringDelimiter();
			}

			if (!insideString && character == ';') {
				currentStatement.append(character);
				addQuery(formatStatement(currentStatement.toString()));
				currentStatement.setLength(0);
			} else {
				currentStatement.append(character);
			}
		}

		if (!currentStatement.isEmpty()) {
			addQuery(formatStatement(currentStatement.toString()));
		}

		Collections.reverse(queries);
		return queries;
	}

	/**
	 * Adds a new query to the list of queries.
	 *
	 * @param statement The SQL statement to be added.
	 */
	private void addQuery(String statement) {
		QueryType queryType = QueryType.fromStatement(statement);
		if (QueryType.UPDATE.equals(queryType)) {
			return;
		}

		if (queryType == null) {
			throw new UnsupportedQueryException("Unable to identify query type:: " + statement);
		}

		BaseQueryFactory factory = queryType.getFactoryInstance();

		queries.add(factory.createQuery(statement));
	}

	/**
	 * Formats the SQL statement by trimming whitespace and ensuring it ends with a
	 * semicolon.
	 *
	 * @param statement The SQL statement to be formatted.
	 * @return The formatted SQL statement.
	 */
	private String formatStatement(String statement) {
		String trimmedStatement = statement.trim();
		if (!trimmedStatement.endsWith(";")) {
			trimmedStatement += ";";
		}

		return trimmedStatement;
	}

	/**
	 * Retrieves the next character from the SQL script.
	 *
	 * @return The next character from the script.
	 */
	private char getNextCharacter() {
		return script.charAt(currentIndex++);
	}

	/**
	 * Handles the string delimiter ('), toggling the inside/outside string state.
	 */
	private void handleStringDelimiter() {
		insideString = !insideString;
	}

}
