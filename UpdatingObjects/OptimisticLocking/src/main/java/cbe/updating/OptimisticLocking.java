package cbe.updating;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SelectQuery;

import cbe.updating.model.Person;

/**
 * Cayenne By Example Source Code:
 *   https://github.com/mrg/cbe
 *   https://github.com/mrg/cbe/tree/master/OptimisticLocking/OptimisticLocking
 *
 * Cayenne By Example Documentation:
 *   http://mrg.github.com/cbe/
 *   http://mrg.github.com/cbe/updating-objects/optimistic-locking.html
 *
 * This example inserts a single Person object into the database, updates
 * it in a different context, then updates again in the original context
 * triggering an optimistic locking exception.
 *
 * @author mrg
 */
public class OptimisticLocking
{
    public OptimisticLocking()
    {
        // Create a Cayenne runtime.
        ServerRuntime runtime = new ServerRuntime("cayenne-cbe-updating.xml");

        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        ObjectContext dataContext = runtime.getContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");

        // Commit the changes to the database.
        dataContext.commitChanges();

        // Harsh up the works by changing our data.
        monkeyWithData();

        // Add an e-mail address.
        person.setEmailAddress("admin@example.com");

        // Commit the changes to the database (ka-boom).
        dataContext.commitChanges();
    }

    private void monkeyWithData()
    {
        // Create a Cayenne runtime independent of the first runtime.
        ServerRuntime runtime     = new ServerRuntime("cayenne-cbe-updating.xml");
        ObjectContext dataContext = runtime.getContext();
        SelectQuery   query       = new SelectQuery(Person.class);

        @SuppressWarnings("unchecked")
        List<Person> people = dataContext.performQuery(query);
        Person       admin  = people.get(0);

        // Add an e-mail address.
        admin.setEmailAddress("admin@example.com");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new OptimisticLocking();
    }
}
