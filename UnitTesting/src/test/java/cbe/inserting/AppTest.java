package cbe.inserting;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.cayenne.access.DataContext;
import org.h2.jdbcx.JdbcDataSource;

import cbe.testing.model.Address;
import cbe.testing.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/UnitTesting
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/unit-testing.html
 *
 * This example shows how to register with a JNDI naming service for unit
 * testing with Cayenne.  It contains two tests:
 *   1) Create and test a single Person object.
 *   2) Create and test a single Person/Address object with a to-one
 *      relationship (Person -> Address).
 *
 * It is based upon the ToOne example.
 *
 * @author mrg
 */
public class AppTest extends TestCase
{
    @Override
    protected void setUp()
    {
    }

    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }

    /**
     * Register a data source with JNDI for all unit tests in this test suite.
     *
     * @return The suite of tests being tested.
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
}
