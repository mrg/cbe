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
 *   http://mrg.github.com/cbe/basic-inserts.html
 *
 * This example builds upon BasicInserts2.
 *
 * It inserts many Person objects into the database within a single
 * commit/transaction.
 *
 * It adds setting a person's password to demonstrate overriding the
 * setPassword() method in the Person.java subclass, which automatically hashes
 * the value passed in (so that the person's password is not saved in plain
 * text).
 *
 * @author mrg
 */
public class BasicInserts3
{
    DataContext dataContext = null;

    public BasicInserts3()
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

        // Default the password to the e-mail address with "123" appended.
        person.setPassword(emailAddress + "123");
    }


    public static void main(String[] arguments)
    {
        new BasicInserts3();
    }
}
