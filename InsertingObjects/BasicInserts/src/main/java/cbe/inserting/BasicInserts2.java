package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/InsertingObjects/BasicInserts
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/inserting-objects/basic-inserts.html
 *
 * This example builds upon BasicInserts1.
 *
 * It inserts many Person objects into the database within a single
 * commit/transaction.
 *
 * @author mrg
 */
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
