package cbe.fetching.utilities;

import org.apache.cayenne.access.trans.SelectTranslator;

/**
 * Min() translator utility which extracts the SQL for a Cayenne-based query and
 * makes a SELECT MIN query from it.
 *
 * @author mrg
 */
public class AggregateTranslator extends SelectTranslator
{
    private final String function;
    private final String parameter;

    /**
     * Specialized translator, which produces a SELECT MIN(column) from the
     * Cayenne Query, such as SELECT MIN(PRICE).
     *
     * @param column
     *            A valid database column that can go between the parenthesis of
     *            a SELECT MIN(). Columns referenced must match the DB schema,
     *            not the Cayenne attribute.
     */
    public AggregateTranslator(String function, String parameter)
    {
        this.function = function;
        this.parameter = parameter;
    }

    public String getFunction()
    {
        return function;
    }

    public String getParameter()
    {
        return parameter;
    }

    @Override
    public String createSqlString() throws Exception
    {
        String sql = super.createSqlString();

        return "SELECT " + function + "(" + parameter + ")"
                + sql.substring(sql.indexOf(" FROM "));
    }
}
