package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.User;

/**
 * This example inserts a single User object into the database.
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

        // Create a new User object tracked by the DataContext.
        User user = dataContext.newObject(User.class);

        // Set values for the new user.
        user.setFirstName("System");
        user.setLastName("Administrator");
        user.setUsername("admin");

        // Commit the changes to the database.
        dataContext.commitChanges();
    }
}
