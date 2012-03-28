package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum PaperBookFormat implements ExtendedEnumeration
{
    HARD_COVER("H"), PAPER_COVER("P"), TRADE_COVER("T");

    private String databaseValue;

    private PaperBookFormat(String value)
    {
        databaseValue = value;
    }

    @Override
    public Object getDatabaseValue()
    {
        return databaseValue;
    }
}
