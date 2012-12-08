---
layout: page
title: Cayenne by Example - Unit Testing
---

# Unit Testing

<table class="pb">
  <tr>
    <td>Cayenne Version</td>
    <td>3.0.2</td>
  </tr>
  <tr>
    <td>Source Directory</td>
    <td>
      UnitTesting/src/main/java</b> (Cayenne Object classes)
      <br/>
      UnitTesting/src/test/java <b>(testing classes)</b>
    </td>
  </tr>
  <tr>
    <td>Resource Directory</td>
    <td>
      UnitTesting/src/main/resources (Cayenne Model files)
    </td>
  </tr>
    <td>Inputs</td>
    <td>N/A</td>
  <tr>
  </tr>
  <tr>
    <td>Compiling/Running</td>
    <td>
      cd UnitTesting<br/>
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

One of the difficulties encountered when unit testing Cayenne objects (this example uses JUnit) is that Cayenne objects want to live in a context.  You can create a single Cayenne object without issue, but if you try to add a relationship without a context, an exception will be raised because Cayenne is trying to do magic behind the scenes to define the relationships keys.  This means your unit tests must create a Cayenne context even if you do not plan on committing any objects to the database.  Of course, if you actually want to commit to a testing database (I prefer an in-memory one), you don't want to use your main Cayenne model (which is configured for deployment).

Cayenne includes three data source factories (and database connection pools) natively:

1. Cayenne Model (top-level XML configuration file, plus DataNode and DataMap XML files).
2. JNDI (top-level XML configuration file, plus DataMap XML files).
3. Apache DBCP (top-level XML configuration file, plus DataMap XML files).

As you can see, the Cayenne Model option includes a DataNode file while JNDI and Apache DBCP do not.  The JNDI option specifies a JNDI lookup name in the top-level XML configuration file.  The Apache DBCP option specifies the configuration file name.

## Model Copying

You can copy your Cayenne Model XML files (in `src/main/resources`) to `src/test/resources` and then open the model in Cayenne Modeler to configure a different test database.  This option is relatively straightforward, but if your model changes, you have to remember to copy the files again.  This can be simplified by deleting the DataMap file in `src/test/resources`, though, and letting the tests load the top-level XML file (and possibly the DataNode) from `src/test/resources`, but pick up the DataMap from `src/main/resources`.  This way you don't have to remember to copy the DataMap when it changes -- and the DataMap is the most likely item to change as you alter tables/columns.  However, if you need to edit the testing Cayenne Model again, Cayenne Modeler won't be able to open the model unless you copy the DataMap file again (and probably delete it afterwards).

## JNDI Lookups

If you are using JNDI, which is typical when developing a web application, it is possible to register your own JNDI name and not have to copy any model files to `src/test/resources`.  This has the advantage of the model being used by your test cases being identical to your production/deployment model.

Cayenne (and JNDI) doesn't make this approach easy, though.  When Cayenne initializes itself from the model and you are using JNDI, it immediately tries to do a JNDI lookup even if it doesn't need the database connection right away.  This throws an exception, of course.  In order to keep using JNDI (so you don't have to copy model files around) requires an actual JNDI lookup, but registering your own JNDI resource is non-trivial.

This example uses [Simple JNDI](http://code.google.com/p/osjava/wiki/SimpleJNDI) to register a data source to a memory-based H2 database in the test suite.  You can then safely create Cayenne contexts without an exception.  You can also create a test database and perform more advanced tests if you like.

### Model Configuration

To be continued...

### Setting up JNDI

You need to register the JNDI context once upon test startup.

{% highlight java linenos %}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws Exception
    {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.memory.MemoryContextFactory");
        System.setProperty("org.osjava.sj.jndi.shared", "true");

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:cbe");
        ds.setUser("sa");
        ds.setPassword("sa");
        Context ctx = new InitialContext();
        ctx.createSubcontext("java:comp/env");

        ctx.bind("jdbc/cbe", ds);

        return new TestSuite(AppTest.class);
    }
{% endhighlight %}

### Test Cases

The first test creates a single Cayenne object.

{% highlight java linenos %}
    public void testPerson()
    {
        // Create a new DataContext.
        DataContext dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");
        person.setUsername("admin");

        assertEquals(person.getUsername(), "admin");
    }
{% endhighlight %}

The second creates two Cayenne objects and joins them in a relationship.

{% highlight java linenos %}
    public void testPersonAndAddress()
    {
        // Create a new DataContext.
        DataContext dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");
        person.setUsername("admin");

        // Create the address (a to-one relationship) for the person.
        Address address = dataContext.newObject(Address.class);

        // Set the address attributes.
        address.setCity("Falls Church");
        address.setState("VA");
        address.setStreet("W Broad Street");
        address.setZip("22046");

        // Assign the address to the person.  Cayenne will figure out how
        // to map the relationship based upon the model and also create
        // the primary keys and foreign keys.
        person.setAddress(address);

        assertEquals(address, person.getAddress());
    }
{% endhighlight %}

### Running Tests

Running a `mvn clean compile test` produces:

{% highlight sql linenos %}
[INFO] Scanning for projects...
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running cbe.inserting.AppTest
Dec 08, 2012 4:56:41 PM org.apache.cayenne.conf.RuntimeLoadDelegate startedLoading
INFO: started configuration loading.
Dec 08, 2012 4:56:41 PM org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataDomain
INFO: loaded domain: CBEInserting
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate loadDataMap
INFO: loaded <map name='CBEInsertingMap' location='CBEInsertingMap.map.xml'>.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
INFO: loading <node name='CBEInsertingNode' datasource='jdbc/cbe' factory='org.apache.cayenne.conf.JNDIDataSourceFactory' schema-update-strategy='org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy'>.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
INFO: using factory: org.apache.cayenne.conf.JNDIDataSourceFactory
Dec 08, 2012 4:56:42 PM org.apache.cayenne.access.QueryLogger logConnect
INFO: Connecting. JNDI path: jdbc/cbe
Dec 08, 2012 4:56:42 PM org.apache.cayenne.access.QueryLogger logConnectSuccess
INFO: +++ Connecting: SUCCESS.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate shouldLoadDataNode
INFO: loaded datasource.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate initAdapter
INFO: no adapter set, using automatic adapter.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate shouldLinkDataMap
INFO: loaded map-ref: CBEInsertingMap.
Dec 08, 2012 4:56:42 PM org.apache.cayenne.conf.RuntimeLoadDelegate finishedLoading
INFO: finished configuration loading in 239 ms.
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.563 sec

Results :

Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
{% endhighlight %}

