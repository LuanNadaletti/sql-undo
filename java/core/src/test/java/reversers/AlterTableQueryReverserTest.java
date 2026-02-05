package reversers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sqlundo.functional.enums.AlterType;
import com.sqlundo.functional.models.AlterTableQuery;
import com.sqlundo.functional.reversers.AlterTableQueryReverser;

class AlterTableQueryReverserTest {

    @Test
    void testReverseAlterTableQueryAddColumn() {
        AlterTableQuery alterTableQuery = new AlterTableQuery("ALTER TABLE table ADD COLUMN column1", "table", "ADD",
                "column1", null, AlterType.COLUMN);

        AlterTableQueryReverser reverser = new AlterTableQueryReverser();

        assertEquals("ALTER TABLE table DROP COLUMN column1;",
                reverser.reverse(alterTableQuery));
    }

    @Test
    void testReverseAlterTableQueryAddConstraint() {
        AlterTableQuery alterTableQuery = new AlterTableQuery("ALTER TABLE table ADD CONSTRAINT constraint1", "table", "ADD",
                "constraint1", null, AlterType.CONSTRAINT);

        AlterTableQueryReverser reverser = new AlterTableQueryReverser();

        assertEquals("ALTER TABLE table DROP CONSTRAINT constraint1;",
                reverser.reverse(alterTableQuery));
    }

    @Test
    void testReverseAlterTableQueryAddWithoutKeyword() {
        AlterTableQuery alterTableQuery = new AlterTableQuery("ALTER TABLE table ADD column1", "table", "ADD",
                "column1", null, AlterType.NONE);

        AlterTableQueryReverser reverser = new AlterTableQueryReverser();

        assertEquals("ALTER TABLE table DROP column1;",
                reverser.reverse(alterTableQuery));
    }
}
