
package cbe.inserting.constants;

import org.apache.cayenne.ExtendedEnumeration;

public enum ProductType implements ExtendedEnumeration
{
    BOOK("BOOK"), GAME("GAME");

    private ProductType(String value)
    {
      this.value = value;
    }
    
    public Object getDatabaseValue()
    {
      return value;
    }

    String value;
}
