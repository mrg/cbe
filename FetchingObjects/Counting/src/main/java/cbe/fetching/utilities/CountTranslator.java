package cbe.fetching.utilities;

import org.apache.cayenne.access.trans.SelectTranslator;

/**
 * Count translator utility which extracts the SQL for a Cayenne-based query and
 * makes a SELECT COUNT query from it.
 *
 * Inspired by Andrey Razumovsky (where "inspired by" means
 * "mostly stolen from") who posted the code on the Cayenne mailing list.
 *
 * @author mrg
 */
public class CountTranslator extends SelectTranslator
{
    private final String expression;

    /**
     * Default translator, which produces a SELECT COUNT(*) from the Cayenne
     * Query.
     */
    public CountTranslator()
    {
        this.expression = "*";
    }

    /**
     * Specialized translator, which produces a SELECT COUNT(expression) from
     * the Cayenne Query, such as SELECT COUNT(DISTINCT DEPARTMENT).
     *
     * @param expression
     *            A valid SQL expression that can go between the parenthesis of
     *            a SELECT COUNT().  Columns referenced must match the DB schema,
     *            not the Cayenne attribute.
     */
    public CountTranslator(String expression)
    {
        this.expression = expression;
    }

    @Override
    public String createSqlString() throws Exception
    {
        String sql = super.createSqlString();

        return "SELECT COUNT(" + expression + ")" + sql.substring(sql.indexOf(" FROM "));
    }
}
