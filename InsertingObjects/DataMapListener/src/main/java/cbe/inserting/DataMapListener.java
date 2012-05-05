package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example builds upon BasicInserts2.
 *
 * It inserts many Person objects into the database within a single
 * commit/transaction. Upon commit, it logs (via Listeners.java) the pre-persist
 * and post-persist objects through the lifecycle callbacks defined in the
 * Cayenne model (please use Cayenne Modeler to view the DataMap listener
 * section).
 *
 * @author mrg
 */
public class DataMapListener
{
    DataContext dataContext = null;

    public DataMapListener()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = DataContext.createDataContext();

        // Create People records (in the DataContext).
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
     * Helper method to create and initialize a person in a DataContext.
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
        new DataMapListener();
    }
}
