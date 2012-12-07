---
layout: page
root: ".."
title: Cayenne by Example - Unit Testing
---

# Unit Testing {% include back-to-inserting-objects.html %}

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>
      InsertingObjects/UnitTesting/src/main/java</b>
      InsertingObjects/UnitTesting/src/test/java <b>(additional location)</b>
    </td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>InsertingObjects/UnitTesting/src/main/resources</td>
  </tr>
    <td>Inputs</td>
    <td>N/A</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>
      cd InsertingObjects/UnitTesting<br/>
      mvn clean compile<br/>
      mvn test
    </td>
  </tr>
  <tr>
    <td>View/Edit Model</td>
    <td>mvn cayenne-modeler:run</td>
  </tr>
  </tr>
    <td>Status</td>
    <td>
      Code: Completed<br/>
      Documentation: In-Progress
    </td>
  <tr>
</table>

One of the difficulties encountered when unit testing Cayenne objects (this example specifically focuses on JUnit) is that Cayenne objects want to live in a context.  You can create a single Cayenne object without issue, but if you try to add a relationship without a context, an exception will be raised.  This means your unit tests must create a Cayenne context even if you do not plan on committing any objects to the database.

Of course, in a unit test, you will most likely want to use a testing database (particularly an in-memory one).  Cayenne includes three data source factories (and database connection pools) natively:

1. Cayenne Model
2. JNDI
3. Apache DBCP

One option is to copy your Cayenne model XML files (in `src/main/resources`) to `src/test/resources` and then open them in Cayenne modeler and configure a different test database.

To be continued...
