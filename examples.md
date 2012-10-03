---
layout: page
title: Cayenne by Example - Examples
---

# Examples

The examples are grouped into major sections for inserting, updating, and deleting, plus some other more specialized examples after that.

We'll start off with examples of inserting Java objects into the database.  This may seem a little backward at first.  Most people would probably like to see how to fetch Java objects from the database before learning how to insert objects.  (If you are one of those, feel free to skip ahead.)  The reason for inserting before fetching is to be able to bootstrap data into the database.  Later on, the fetching and deleting examples will use data from the inserting section.

To try to keep the examples clearer, there will be many projects to illustrate various features.  This will hopefully reduce the amount of Java code associated with demonstrating a single feature and keep the specifics of that example clearer.

All of the examples will share these common elements, however:

* A Cayenne model.  Cayenne Modeler generates the model which maps the database to Java.  When practical, several programs will share the same model, but many times the model will need to be changed slightly to illustrate a point.  Rather than putting too much into one example, it will be split up into a different project with a different model.  **You are encouraged to use Cayenne Modeler to view the mapping.**
* One or more Java classes representing the Java mapping to the database tables.  Cayenne Modeler generates the Java classes (and subclasses), too.
* A database.  These examples use H2, which is a nice open-source embeddable Java database which can create in-memory databases.  This is great for tests or examples where you don't want to litter the disk with actual files or have the overhead of a full database server.  The examples will all run in-memory and not modify the file system.
* A DataContext, which is the typical Cayenne Context discussed above.  The DataContext will manage your Java objects and handle committing them to the database.

Each example also contains a "project box" which lists the location (relative to the [GitHub project](https://github.com/mrg/cbe)) for the Java code and the resources (Cayenne Model and data files) with instructions on how to compile and run them if you choose.  Even if you don't have a GitHub account, you can still download the project as a .tar.gz or a .zip archive to run the examples using the links at the top of the page.  You can also use GitHub to explore the code if you don't want to download the project.  A project box resembles the following:

<table>
  <tr>
    <td>Source Directory</td>
    <td>Example/Project/src/main/java</td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>Example/Project/src/main/resources</td>
  </tr>
    <td>Inputs</td>
    <td>Input files relative to the Resource Directory.</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>cd Example/Project<br/>mvn clean compile<br/>mvn exec:java -Dexec.mainClass=Project1</td>
  </tr>
</table>

These examples will also typically omit the common/mundane steps of creating the model and generating the Java classes.  Use Cayenne Modeler to explore the model and your favorite editor/viewer to explore the generated Java classes if you desire.
