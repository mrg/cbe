package cbe.inserting;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import cbe.inserting.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/InsertingObjects/MeaningfulPK
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/inserting-objects/basic-inserts.html
 *
 * This example illustrates inserting objects into the database with a
 * meaningful primary key.
 *
 * NOTE: Using meaningful primary keys is not recommended, but sometimes,
 *       especially when dealing with legacy schemas, it is necessary.
 *
 * @author mrg
 */
public class MeaningfulPK
{
    ObjectContext dataContext = null;
    ServerRuntime runtime     = new ServerRuntime("cayenne-cbe-inserting.xml");

    public MeaningfulPK()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = runtime.getContext();

        // Create new Person objects tracked by the DataContext.
        Person person1 = dataContext.newObject(Person.class);
        Person person2 = dataContext.newObject(Person.class);
        Person person3 = dataContext.newObject(Person.class);

        // Set values.
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setSocialSecurityNumber("111-11-1111");

        person2.setFirstName("Jane");
        person2.setLastName("Smith");
        person2.setSocialSecurityNumber("999-99-9999");

        person3.setFirstName("Tom");
        person3.setLastName("Smith");
        person3.setSocialSecurityNumber("123-45-6789");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new MeaningfulPK();
    }
}
