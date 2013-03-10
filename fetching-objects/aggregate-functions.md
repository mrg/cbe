---
layout: page
root: ".."
title: Cayenne by Example - Aggregate Functions
heading: Aggregate Functions
breadcrumb:
  - name: Cayenne By Example
    link: ..
  - name: Fetching Objects
    link: .
  - name: Aggregate Functions
---

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Project Directory</td>
    <td>
      <a href="https://github.com/mrg/cbe/tree/master/FetchingObjects/Aggregates">
        FetchingObjects/Aggregates
      </a>
    </td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>
      <a href="https://github.com/mrg/cbe/tree/master/FetchingObjects/Aggregates/src/main/java">
        FetchingObjects/Aggregates/src/main/java
      </a>
    </td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>
      <a href="https://github.com/mrg/cbe/tree/master/FetchingObjects/Aggregates/src/main/resources">
        FetchingObjects/Aggregates/src/main/resources
      </a>
    </td>
  </tr>
    <td>Inputs</td>
    <td>N/A</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>
      cd FetchingObjects/Aggregates<br/>
      mvn clean compile<br/>
      mvn exec:java -Dexec.mainClass=cbe.fetching.Aggregates
    </td>
  </tr>
  <tr>
    <td>View/Edit Model</td>
    <td>mvn cayenne-modeler:run</td>
  </tr>
  </tr>
    <td>Status</td>
    <td>
      Code: Mostly Done<br/>
      Documentation: Needs work
    </td>
  <tr>
</table>

Cayenne doesn't natively support aggregate database functions, but you can incorporate this example into your application if you need this functionality.

This example is a bit different in that it doesn't illustrate native Cayenne functionality, but uses a custom `AggregatesUtil` class to implement the functionality.  The documentation is more about showing how to use `AggregatesUtil` than explaining how it works, but you can certainly explore the class to learn more about working with Cayenne at a lower level (custom SQL is generated).

The `AggregatesUtil` class includes methods to perform a COUNT(*), SUM(column), AVG(column), MIN(column), and MAX(column) of database records using a Cayenne query.  These methods return `long` or `BigDecimal` results instead of normal Cayenne objects.

TBD...more to come.

*The `AggregatesUtil` class (and supporting classes) was inspired by Andrey Razumovsky (where "inspired by" means "mostly stolen from") who posted the original code on the Cayenne mailing list.*

