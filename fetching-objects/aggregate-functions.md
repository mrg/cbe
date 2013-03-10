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

Cayenne doesn't natively support aggregate database functions, but you can incorporate these examples into your application if you need this functionality.

* [Counting](counting.html) - Perform a COUNT(*) of database records using a Cayenne query.
* [More Aggregates](aggregates.html) - Includes the COUNT(*) example and adds SUM(column), AVG(column), MIN(column), and MAX(column) of database records using a Cayenne query.

