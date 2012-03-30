package cbe.fetching.utilities;

import org.apache.cayenne.access.trans.SelectTranslator;

/**
 * Count translator utility which extracts the SQL for a Cayenne-based query and
 * makes a SELECT COUNT query from it.
 *
 * @author mrg
 */
public class MinTranslator extends SelectTranslator
{
    private final String column;

    /**
     * Specialized translator, which produces a SELECT COUNT(expression) from
     * the Cayenne Query, such as SELECT COUNT(DISTINCT DEPARTMENT).
     *
     * @param expression
     *            A valid SQL expression that can go between the parenthesis of
     *            a SELECT COUNT().  Columns referenced must match the DB schema,
     *            not the Cayenne attribute.
     */
    public MinTranslator(String column)
    {
        this.column = column;
    }

    @Override
    public String createSqlString() throws Exception
    {
        String sql = super.createSqlString();

        return "SELECT MIN(" + column + ")" + sql.substring(sql.indexOf(" FROM "));
    }
}
