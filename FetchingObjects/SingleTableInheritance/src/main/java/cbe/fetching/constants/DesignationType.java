package cbe.fetching.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum DesignationType implements ExtendedEnumeration
{
    CIVILIAN("Civilian"), CONTRACTOR("Contractor"), MILITARY("Military");

    private String databaseValue;

    private DesignationType(String databaseValue)
    {
        this.databaseValue = databaseValue;
    }

    public Object getDatabaseValue()
    {
        return databaseValue;
    }

}
