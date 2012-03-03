package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum RoleType implements ExtendedEnumeration
{
    ADMIN("A"), AUTHOR("W"), EDITOR("E"), MODERATOR("M"), NONE("N");

    private String databaseValue;

    private RoleType(String value)
    {
        databaseValue = value;
    }

    @Override
    public Object getDatabaseValue()
    {
        return databaseValue;
    }
}
