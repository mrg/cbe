package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum ElectronicBookFormat implements ExtendedEnumeration
{
    EPUB("EPUB"), LRF("LRF"), MOBI("MOBI");

    private String databaseValue;

    private ElectronicBookFormat(String value)
    {
        databaseValue = value;
    }

    @Override
    public Object getDatabaseValue()
    {
        return databaseValue;
    }
}
