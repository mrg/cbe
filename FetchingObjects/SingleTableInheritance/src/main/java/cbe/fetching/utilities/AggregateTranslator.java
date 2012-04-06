package cbe.fetching.utilities;

import org.apache.cayenne.access.trans.SelectTranslator;

/**
 * Aggregate translator utility which extracts the SQL for a Cayenne-based query and
 * makes a custom SELECT query from it.
 *
 * @author mrg
 */
public class AggregateTranslator extends SelectTranslator
{
    private final String function;
    private final String parameter;
    private final boolean distinct;

    /**
     * Aggregate translator which produces a SELECT function(parameter) from a
     * Cayenne Query, such as SELECT MIN(PRICE), with the option to add DISTINCT
     * to the parameter.
     *
     * @param function
     *            The aggregate function to use.
     * @param parameter
     *            A valid database column that can go between the parenthesis of
     *            the aggregate function. Columns referenced must match the DB
     *            schema, not the Cayenne attribute.
     * @param distinct
     *            Controls if DISTINCT is added to the parameter.
     */
    public AggregateTranslator(String function, String parameter, boolean distinct)
    {
        this.function  = function;
        this.parameter = parameter;
        this.distinct  = distinct;
    }

    /**
     * @return The aggregate function used for this translator.
     */
    public String getFunction()
    {
        return function;
    }

    /**
     * @return The aggregate parameter (usually a database column) used for this
     *         translator.
     */
    public String getParameter()
    {
        return parameter;
    }

    /**
     * @return If distinct is added to the aggregate function used for this
     *         translator.
     */
    public boolean isDistinct()
    {
        return this.distinct;
    }

    /**
     * Constructs the SQL for this translator.
     *
     * @see org.apache.cayenne.access.trans.SelectTranslator#createSqlString()
     */
    @Override
    public String createSqlString() throws Exception
    {
        String sql = super.createSqlString();

        return "SELECT "                     +
               function                      +
               "("                           +
               (distinct ? "DISTINCT " : "") +
               parameter                     +
               ")"                           +
               sql.substring(sql.indexOf(" FROM "));
    }
}
