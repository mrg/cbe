package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.constants.RoleType;
import cbe.inserting.model.Person;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example builds upon BasicInserts3.
 *
 * It inserts many Person objects into the database within a single
 * commit/transaction.  A Role is added in order to illustrate mapping
 * Java enumerations to database values. Cayenne uses the RoleType
 * enumeration to handle the translation values (look at the Java Type
 * in Cayenne Modeler).
 *
 * @author mrg
 */
public class Enumerations
{
    DataContext dataContext = null;

    public Enumerations()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = DataContext.createDataContext();

        // Create People records (in the DataContext).
        createPerson("System", "Administrator", "admin@example.com", "ADMIN");
        createPerson("Aaron", "Caldwell", "acaldwell@example.com", "AUTHOR");
        createPerson("Heidi", "Freeman", "hfreeman@example.com", "EDITOR");
        createPerson("Marcus", "Kerr", "mkerr@example.com", "MODERATOR");
        createPerson("Rose", "Newton", "rnewton@example.com", "EDITOR");
        createPerson("Ulric", "Reeves", "ureeves@example.com", "NONE");
        createPerson("Victoria", "Waters", "vwaters@example.com", "AUTHOR");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    /**
     * Helper method to create and initialize a person in a DataContext.
     */
    private void createPerson(String firstName,
                              String lastName,
                              String emailAddress,
                              String roleType)
    {
        // Create a new Person object tracked by the DataContext.
    	Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);

        // Default the password to the e-mail address with "123" appended.
        person.setPassword(emailAddress + "123");

        // Convert the role (a String) to a RoleType.
        person.setRole(RoleType.valueOf(roleType));
    }


    public static void main(String[] arguments)
    {
        new Enumerations();
    }
}
