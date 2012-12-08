package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Address;
import cbe.inserting.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/InsertingObjects/ToOne
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/inserting-objects/to-one.html
 *
 * This example inserts a single Person object into the database along
 * with their address using a to-one relationship (Person -> Address).
 *
 * It is based upon the BasicInserts1 example.
 *
 * @author mrg
 */
public class ToOne
{
    DataContext dataContext = null;

    public ToOne()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = DataContext.createDataContext();

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

        // Commit the changes to the database (both the Person
        // and Address records).
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new ToOne();
    }
}
