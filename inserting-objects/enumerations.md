---
layout: page
root: ".."
title: Cayenne by Example - Enumerations
heading: Enumerations
breadcrumb:
  - name: Cayenne By Example
    link: ..
  - name: Inserting Objects
    link: .
  - name: Enumerations
---

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>InsertingObjects/Enumerations/src/main/java</td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>InsertingObjects/Enumerations/src/main/resources</td>
  </tr>
    <td>Inputs</td>
    <td>N/A</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>
      cd InsertingObjects/BasicInserts<br/>
      mvn clean compile<br/>
      mvn exec:java -Dexec.mainClass=cbe.inserting.Enumerations
    </td>
  </tr>
  <tr>
    <td>View/Edit Model</td>
    <td>mvn cayenne-modeler:run</td>
  </tr>
  </tr>
    <td>Status</td>
    <td>
      Code: Mostly Complete<br/>
      Documentation: In-Progress
    </td>
  <tr>
</table>

Cayenne can natively map database values to Java enumerations.

There are a couple good reasons for doing so.  Firstly, the Cayenne data objects will have setters/getters that receive/return the enumeration values instead of strings or numbers.  This makes it much harder to accidentally set the wrong value, especially with misspelled values.  Second, Java ensures that like enumerations are inherently equal, so you can use the `==` operator on them.

To tell Cayenne to use an enumeration, open Cayenne Modeler and go to the `Attributes` tab for your Java class.  It may be a little confusing, but the pulldown list of types under the `Java Types` column is actually editable and you can type your own values there.  Enter the full dotted package name plus class name of your enumeration (just like Cayenne Modeler includes the full name for Java types, such as `java.lang.String`).

Once you have edited your model and saved it, generate your Java class again.

## Simple Enumerations

Cayenne's basic support is to map your enumeration's name or ordinal value to a database column, depending on if the target database column is a `VARCHAR` OR `NUMBER`, respectively.

Given an enumeration defined as:

{% highlight java linenos %}
public enum Color
{
    RED, GREEN, BLUE;
}
{% endhighlight %}

You will end up with the following Java-to-Database mappings.

Java Enumeration | DB Value (VARCHAR) | DB Value (NUMBER)
---------------- | ------------------ | -----------------
RED              | 'RED'              | 0
GREEN            | 'GREEN'            | 1
BLUE             | 'BLUE'             | 2

While this is fine for simple enumerations, it can be limiting and fragile, especially when you are mapping to `NUMBER` columns.  If the order ever changes or new values are introduced at the beginning or in the middle, all of your previously persisted values will no longer map correctly, because the mapped values are determined by their declared order.

To gain more control, you must use extended enumerations.

## Extended Enumerations

Cayenne's extended enumeration support allows you to specify exactly what each Java enumeration maps to in the database.  To do this, though, you have to help Cayenne out by implementing the `ExtendedEnumeration` interface and provide a single method: `getDatabaseValue`.

Having control over the mapping ensures you won't get bitten by position changes and also allows you to use richer Java names even when the values in the database are cryptic.  For example, `QUARTERLY` could map to `4`.

This example only deals with extended enumerations.  An enumeration for `RoleType` has been created:

{% highlight java linenos %}
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
{% endhighlight %}

To be continued... 
