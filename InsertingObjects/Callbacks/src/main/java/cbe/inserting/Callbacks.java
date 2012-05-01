package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example inserts a single Person object into the database and uses
 * Cayenne's lifecycle callbacks to automatically set the create and modify
 * dates in the Person. Use Cayenne Modeler to view the Person's Callbacks tab.
 * This example is based upon BasicInserts1.
 *
 * @author mrg
 */
public class Callbacks
{
    public static void main(String[] arguments) throws InterruptedException
    {
        System.out.println("Initializing Cayenne");

        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        System.out.println("Creating objects");

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Admin");

        System.out.println("Persisting [1]");

        // Commit the changes to the database.
        dataContext.commitChanges();

        // Sleep for 2 seconds.
        Thread.sleep(2000);

        System.out.println("Modifying objects");

        // Update the person's last name.
        person.setLastName("Administrator");

        System.out.println("Persisting [2]");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }
}
