package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;

/**
 * Cayenne By Example
 *     https://github.com/mrg/cbe
 *
 * This example inserts a single Person object into the database.
 *
 * @author mrg
 */
public class BasicInserts1
{
    public static void main(String[] arguments)
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }
}
