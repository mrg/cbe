package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Address;
import cbe.inserting.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/ToDependentPK/BasicInserts
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/inserting-objects/to-dependent-pk.html
 *
 * This example inserts a single Person object into the database with a
 * to-one relationship to an Address object, whose ID is taken from the
 * Person.
 *
 * @author mrg
 */
public class ToDependentPK
{
    DataContext dataContext = null;

    public ToDependentPK()
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

        // Create a new Address object tracked by the DataContext.
        Address address = dataContext.newObject(Address.class);

        // Set values for the new address.
        address.setStreet("123 Main Street");
        address.setCity("Everywhere");
        address.setState("NY");
        address.setZipCode("12345-6789");

        // Associate this address with the person.
        person.setAddress(address);

        // Commit the changes to the database.  It is here that the
        // person's PK is pushed into the address's PK.
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new ToDependentPK();
    }
}
