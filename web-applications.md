---
layout: page
title: Cayenne by Example - Web Applications
---

# Web Applications

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>
      WebApplications/src/main/java</b> (Cayenne Object classes)
    </td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>
      WebApplications/src/main/resources (Cayenne Model files)
    </td>
  </tr>
    <td>Inputs</td>
    <td>N/A</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>
      cd WebApplications<br/>
      mvn clean compile<br/>
      mvn jetty:run<br/>
      http://localhost:7890/cbe/
    </td>
  </tr>
  <tr>
    <td>View/Edit Model</td>
    <td>mvn cayenne-modeler:run</td>
  </tr>
  </tr>
    <td>Status</td>
    <td>
      Code: Very Lacking<br/>
      Documentation: Very Lacking
    </td>
  <tr>
</table>

This example illustrates integrating Cayenne into a Java-based web application. The following frameworks and techniques are utilized:

* Cayenne for ORM operations.
* [Tapestry](http://tapestry.apache.org/) for the web framework.
* [Jetty](http://www.eclipse.org/jetty/) for the application server.
* [Flyway](http://flywaydb.org/) for database migrations.
* Plus JNDI connections, running Jetty from within Maven, etc.

