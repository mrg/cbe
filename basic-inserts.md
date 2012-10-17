---
layout: page
title: Cayenne by Example - Basic Inserts
---

{% include back-to-inserting-objects.html %}

# Basic Inserts

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>InsertingObjects/BasicInserts/src/main/java</td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>InsertingObjects/BasicInserts/src/main/resources</td>
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
      mvn exec:java -Dexec.mainClass=cbe.inserting.BasicInserts1<br/>
      mvn exec:java -Dexec.mainClass=cbe.inserting.BasicInserts2<br/>
      mvn exec:java -Dexec.mainClass=cbe.inserting.BasicInserts3</td>
  </tr>
  </tr>
    <td>View/Edit Model</td>
    <td>mvn cayenne-modeler:run</td>
  <tr>
  </tr>
    <td>Status</td>
    <td>
      Code: Completed<br/>
      Documentation: In-Progress
    </td>
  <tr>
</table>

The Basic Inserts example focuses on a single `PEOPLE` table and a corresponding `Person.java` class (the database table being named in the plural and the Java class in the singular). Open the model (located in the resource directory) in Cayenne Modeler to see full class names, optimistic locking attributes, primary key generation strategies, etc.

The `PEOPLE` table is defined as:

Database Column            | Java Attribute       | Other
-------------------------- | -------------------- | --------------
id: BIGINT                 | N/A                  | Primary Key
email_address: VARCHAR(50) | emailAddress: String |
first_name: VARCHAR(25)    | firstName: String    |
last_name: VARCHAR(25)     | lastName: String     |
password: VARCHAR(40)      | password: String     |

## <a name="one">Basic Inserts 1: A Single Insert</a>

This example is about as simple as you can get.  It inserts a single `PEOPLE` record into the database.  It creates a `DataContext` (line 9), creates and registers a new Java `Person` object in the `DataContext` (line 12), sets values in the `Person` (lines 15-17), and then commits the `Person` to the `PEOPLE` database table (line 20).

{% highlight java linenos %}
public class BasicInserts1
{
    DataContext dataContext = null;

    public BasicInserts1()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");
        person.setEmailAddress("admin@example.com");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new BasicInserts1();
    }
}
{% endhighlight %}

As you can see, the `DataContext` is used not only to create new objects (the `Person`), but also to track changes made to the `Person` and commit those changes (inside a transaction) to the database.  Some ORMs are object-centric and require you to call a save/insert/update/delete type method on each object (and call them in the correct order), but Cayenne manages all objects registered in a `DataContext` and knows if they need to be inserted, updated, or deleted when you call `commitChanges` and the order of operations for committing those changes to the database.

When the program is run, it produces output similar to the following (timestamps and INFO prefixes have been stripped):

{% highlight sql linenos %}
org.apache.cayenne.conf.RuntimeLoadDelegate startedLoading
started configuration loading.
org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataDomain
loaded domain: CBEInserting
org.apache.cayenne.conf.RuntimeLoadDelegate loadDataMap
loaded <map name='CBEInsertingMap' location='CBEInsertingMap.map.xml'>.
org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
loading <node name='CBEInsertingNode' datasource='CBEInsertingNode.driver.xml' factory='org.apache.cayenne.conf.DriverDataSourceFactory' schema-update-strategy='org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy'>.
org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
using factory: org.apache.cayenne.conf.DriverDataSourceFactory
org.apache.cayenne.conf.DriverDataSourceFactory load
loading driver information from 'CBEInsertingNode.driver.xml'.
org.apache.cayenne.conf.DriverDataSourceFactory$DriverHandler init
loading driver org.h2.Driver
org.apache.cayenne.conf.DriverDataSourceFactory$LoginHandler init
loading user name and password.
org.apache.cayenne.access.QueryLogger logPoolCreated
Created connection pool: jdbc:h2:mem:cbe
	Driver class: org.h2.Driver
	Min. connections in the pool: 1
	Max. connections in the pool: 10
org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
loaded datasource.
org.apache.cayenne.conf.RuntimeLoadDelegate initAdapter
no adapter set, using automatic adapter.
org.apache.cayenne.conf.RuntimeLoadDelegate shouldLinkDataMap
loaded map-ref: CBEInsertingMap.
org.apache.cayenne.conf.RuntimeLoadDelegate finishedLoading
finished configuration loading in 148 ms.
org.apache.cayenne.access.QueryLogger logConnect
Opening connection: jdbc:h2:mem:cbe
	Login: null
	Password: *******
org.apache.cayenne.access.QueryLogger logConnectSuccess
+++ Connecting: SUCCESS.
org.apache.cayenne.access.QueryLogger logBeginTransaction
--- transaction started.
org.apache.cayenne.access.QueryLogger log
Detected and installed adapter: org.apache.cayenne.dba.h2.H2Adapter
org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy processSchemaUpdate
No schema detected, will create mapped tables
org.apache.cayenne.access.QueryLogger logQuery
CREATE TABLE PEOPLE (first_name VARCHAR(25) NULL, id BIGINT NOT NULL, last_name VARCHAR(25) NULL, PRIMARY KEY (id))
org.apache.cayenne.access.QueryLogger logQuery
CREATE TABLE AUTO_PK_SUPPORT (  TABLE_NAME CHAR(100) NOT NULL,  NEXT_ID BIGINT NOT NULL,  PRIMARY KEY(TABLE_NAME))
org.apache.cayenne.access.QueryLogger logQuery
DELETE FROM AUTO_PK_SUPPORT WHERE TABLE_NAME IN ('PEOPLE')
org.apache.cayenne.access.QueryLogger logQuery
INSERT INTO AUTO_PK_SUPPORT (TABLE_NAME, NEXT_ID) VALUES ('PEOPLE', 200)
org.apache.cayenne.access.QueryLogger log
Detected and installed adapter: org.apache.cayenne.dba.h2.H2Adapter
org.apache.cayenne.access.QueryLogger logQueryStart
--- will run 2 queries.
org.apache.cayenne.access.QueryLogger logQuery
SELECT NEXT_ID FROM AUTO_PK_SUPPORT WHERE TABLE_NAME = 'PEOPLE'
org.apache.cayenne.access.QueryLogger logSelectCount
=== returned 1 row. - took 5 ms.
org.apache.cayenne.access.QueryLogger logQuery
UPDATE AUTO_PK_SUPPORT SET NEXT_ID = NEXT_ID + 20 WHERE TABLE_NAME = 'PEOPLE'
org.apache.cayenne.access.QueryLogger logUpdateCount
=== updated 1 row.
org.apache.cayenne.access.QueryLogger logQueryStart
--- will run 1 query.
org.apache.cayenne.access.QueryLogger logQuery
INSERT INTO PEOPLE (email_address, first_name, id, last_name, password) VALUES (?, ?, ?, ?, ?)
org.apache.cayenne.access.QueryLogger logQueryParameters
[bind: 1->email_address:'admin@example.com', 2->first_name:'System', 3->id:200, 4->last_name:'Administrator', 5->password:NULL]
org.apache.cayenne.access.QueryLogger logUpdateCount
=== updated 1 row.
org.apache.cayenne.access.QueryLogger logCommitTransaction
+++ transaction committed.
{% endhighlight %}

Further examples will skip the initialization of Cayenne to focus on the main example, but for this first example, each step will be explained.

* Lines 1-2: Cayenne starts to initialize itself when the first `DataContext` is created.  This won't happen for additional `DataContext` creations.  This loads the `cayenne.xml` file (in the resources directory).  Cayenne searches the Java `CLASSPATH` to locate this file.
* Lines 3-4: The `cayenne.xml` file specified a `DataDomain` called `CBEInserting` which needed to be loaded and initialized.
* Lines 5-27: The `DataDomain` specified a `DataMap` named `CBEInsertingMap` which needed to be loaded from `CBEInsertingMap.map.xml`.  A `DataMap` contains the mappings for the Java classes, database tables, and relationships, stored procedures, named queries, etc.
* Lines 7-25: The `DataDomain` specified a `DataNode` named `CBEInsertingNode` which needed to be loaded from `CBEInsertingNode.driver.xml`.  A `DataNode` contains database connection information and is mapped to a `DataMap` so that Cayenne knows how to route queries to the database.  Although this example only has one `DataNode`, Cayenne supports multiple `DataNode` connections to different databases and can route queries accordingly.  You can see that loading the `DataNode` initializes the username, password, connection limits, etc.  Note: This example uses Cayenne's built-in database connection facility and the output/configuration differs if you choose another strategy (such as using a JNDI connection).
* Line 26: The `DataMap` is linked to the `DataNode` so the Cayenne knows how to route queries.
* Line 27: The `DataMap` is initialized.
* Lines 28-29: The Cayenne model (`DataDomains`, `DataMaps`, and `DataNodes`) is initialized, but only the model -- the database still hasn't been accessed.
* Lines 30-51: Cayenne needs to access the database from the `dataContext.commitChanges` call.  Because this is the first access to the database, Cayenne needs to login to the database.  Because we are using the `CreateIfNoSchemaStrategy` strategy, which is useful for unit tests or examples such as this, Cayenne will automatically create the database schema from the Cayenne model if the schema is missing.  You can see Cayenne creating the `PEOPLE` table on line 43 and the `AUTO_PK_SUPPORT` support table on line 45, which is used by Cayenne's built-in primary key generator (although other primary key generation options are available, such as MySQL's auto increment).
* Line 49: Cayenne's `AUTO_PK_SUPPORT` primary key generation starts at 200 by default.
* Lines 52-61: Cayenne needs a primary key for the `Person` it is about to insert, so it fetches the current primary key value from `AUTO_PK_SUPPORT` and then increments it by 20.  Cayenne by default caches 20 primary keys to improve insert performance.  Line 61 reports that one record in `AUTO_PK_SUPPORT` was updated.
* Lines 62-71: Cayenne is finally ready to insert our single record into the database.  Line 65 issues the `INSERT` to the database, line 67 binds the values to insert (notice the `id` of 200, which came from the `AUTO_PK_SUPPORT` table).  Line 69 confirms one row was updated.  Line 71 confirms the transaction was committed, which began way back on line 37.

## <a name="two">Basic Inserts 2: Multiple Inserts</a>

{% highlight java linenos %}
public class BasicInserts2
{
    DataContext dataContext = null;

    public BasicInserts2()
    {
         // Create a new DataContext. This will also initialize the Cayenne
         // Framework.
         dataContext = DataContext.createDataContext();

         // Create Persons (in the DataContext).
         createPerson("Aaron", "Caldwell", "acaldwell@example.com");
         createPerson("Heidi", "Freeman", "hfreeman@example.com");
         createPerson("Marcus", "Kerr", "mkerr@example.com");
         createPerson("Rose", "Newton", "rnewton@example.com");
         createPerson("Ulric", "Reeves", "ureeves@example.com");
         createPerson("Victoria", "Waters", "vwaters@example.com");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    /**
     * Helper method to create and initialize a Person in a DataContext.
     */
    private void createPerson(String firstName, String lastName, String emailAddress)
    {
        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);
    }


    public static void main(String[] arguments)
    {
        new BasicInserts2();
    }
}
{% endhighlight %}

## <a name="three">Basic Inserts 3: Multiple Inserts with Overridden Method</a>

This example is identical to Basic Inserts 2, except the `createPerson` method makes one additional call to to `setPassword` on line 15:

{% highlight java linenos %}
    /**
     * Helper method to create and initialize a Person in a DataContext.
     */
    private void createPerson(String firstName, String lastName, String emailAddress)
    {
        // Create a new Person object tracked by the DataContext.
    	Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);

        // Default the password to the e-mail address with "123" appended.
        person.setPassword(emailAddress + "123");
    }
{% endhighlight %}

Normal methods, such as `setFirstName` and `setLastName` call the Cayenne-generated setter (or getter) in the base class (`_Person.java`).  However, Cayenne allows you to override methods or provide additional methods in the subclass (`Person.java`).  In the case of a password, we don't want to store a plain-text password, but instead want to store a hashed value.  Therefore, the `setPassword` method is overridden to automatically hash the plain-text value:

{% highlight java linenos %}
public class Person extends _Person
{
    /**
     * Override the default setPassword() method generated by Cayenne to
     * automatically hash the plain-text password before storing it in the
     * database.
     *
     * @see cbe.inserting.model.auto._User#setPassword(java.lang.String)
     */
    @Override
    public void setPassword(String plainTextPassword)
    {
        // A real password handler would do more than this.  Read:
        // http://www.owasp.org/index.php/Hashing_Java
        super.setPassword(DigestUtils.shaHex(plainTextPassword));
    }
}
{% endhighlight %}

Unless the developer goes out of their way to circumvent the automatic hashing, then they'll never have to worry about a plain-text password accidentally being saved to the database.

{% include back-to-inserting-objects.html %}
