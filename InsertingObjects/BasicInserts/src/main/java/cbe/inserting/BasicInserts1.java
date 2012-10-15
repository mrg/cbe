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
 * This example inserts a single Person object into the database.
 *
 * @author mrg
 */
public class BasicInserts1
{
    DataContext dataContext = null;

    public BasicInserts1()
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
    }

    public static void main(String[] arguments)
    {
        new BasicInserts1();
    }
}
