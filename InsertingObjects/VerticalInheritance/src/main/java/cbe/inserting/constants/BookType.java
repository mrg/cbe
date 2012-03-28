package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum BookType implements ExtendedEnumeration
{
    ELECTRONIC_BOOK("E"), PAPER_BOOK("P");

    private String databaseValue;

    private BookType(String value)
    {
        databaseValue = value;
    }

    @Override
    public Object getDatabaseValue()
    {
        return databaseValue;
    }
}
