package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;
import cbe.inserting.utilities.Populator;

/**
 * This example inserts many Person objects into the database within a single
 * commit/transaction.
 *
 * @author mrg
 */
public class BasicInserts3
{
    public static void main(String[] arguments)
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person. In this case, we are initializing
        // an administrator.
        person.setFirstName("System");
        person.setLastName("Administrator");
        person.setUsername("admin");
        person.setPassword("admin123");

        // Loop over all the names in our resources file and create Persons
        // for each of them.
        for (String[] personFields : Populator.getPeople())
        {
        	String firstName = personFields[Populator.PERSON_FIRST_NAME];
        	String lastName  = personFields[Populator.PERSON_LAST_NAME];

            createPerson(dataContext, firstName, lastName);
        }

        // Commit the changes to the database.
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
    }
}
