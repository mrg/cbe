package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum RoleType implements ExtendedEnumeration
{
    ADMINISTRATOR("A"), CUSTOMER("C");

    private RoleType(String value)
    {
      this.value = value;
    }
    
    public Object getDatabaseValue()
    {
      return value;
    }

    String value;
}
