package cbe.fetching.utilities;

import org.apache.cayenne.access.trans.SelectTranslator;

public class CountTranslator extends SelectTranslator
{
    private final String expression;

    public CountTranslator()
    {
        this.expression = "*";
    }

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
