package cbe.inserting;

import java.util.Random;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.constants.RoleType;
import cbe.inserting.model.Address;
import cbe.inserting.model.Person;
import cbe.inserting.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example creates a to-one relationship (Person -> Address).
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

    }

    public static void main(String[] arguments)
    {



        // Create a new Person object tracked by the DataContext.
        Person admin = dataContext.newObject(Person.class);

        // Set values for the new person. In this case, we are initializing
        // an administrator.
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setEnabled(true);
        admin.setRole(RoleType.ADMIN);

        // Loop over all the names in our resources file and create persons
        // for each of them.
        for (String firstName : Populator.getFirstNames())
            for (String lastName : Populator.getLastNames())
                createPerson(dataContext, firstName, lastName);

        // Commit the changes to the database.  This includes Person
        // and Address records.
        dataContext.commitChanges();
    }

    /**
     * Helper method to create and initialize a person in a DataContext.
     *
     * @param dataContext The DataContext to register the person.
     * @param firstName The person's first name.
     * @param lastName The person's last name.
     */
    private static void createPerson(DataContext dataContext, String firstName, String lastName)
    {
        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person. Defaults the password to the username
        // with "123" appended.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setUsername((firstName.substring(0, 1) + lastName).toLowerCase());
        person.setPassword(person.getUsername() + "123");

        // Don't enable accounts whose last name starts with an "A".
        if (lastName.startsWith("A"))
            person.setEnabled(false);
        else
            person.setEnabled(true);

        // Accounts whose last name starts with a "B" are moderators.
        if (lastName.startsWith("B"))
            person.setRole(RoleType.MODERATOR);
        else
            person.setRole(RoleType.CUSTOMER);

        // Create an address for this person.
        createAddress(person);
    }

    /**
     * Creates an address for a person.
     *
     * @param person The person to create the address.
     */
    private static void createAddress(Person person)
    {
        // Extract the DataContext from the person.  We are creating a
        // relationship and Cayenne requires that related objects reside
        // in the same DataContext, so be sure to use the same one.
        DataContext dataContext = (DataContext) person.getObjectContext();

        // Create the new address in the person's DataContext.
        Address address = dataContext.newObject(Address.class);

        // Set the address attributes.
        address.setCity("Falls Church");
        address.setState("VA");
        address.setStreet(getRandomStreet());
        address.setZip("22046");

        // Assign the address to the person.  Cayenne will figure out how
        // to map the relationship based upon the model and also create
        // the primary keys and foreign keys.
        person.setAddress(address);
    }

    /**
     * @return A made-up street address.
     */
    private static String getRandomStreet()
    {
        int    number = new Random().nextInt(1000) + 1;
        String street;

        switch (new Random().nextInt(5))
        {
            case 0: street = "W Broad Street"; break;
            case 1: street = "N Washington Street"; break;
            case 2: street = "N West Street"; break;
            case 3: street = "Great Falls Street"; break;
            case 4: street = "Annandale Road"; break;
            default: street = "Lincoln Avenue";
        }

        return number + " " + street;
    }
}
