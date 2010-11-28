package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.User;
import cbe.inserting.utilities.Populator;

/**
 * This example inserts many User objects into the database within a single
 * commit/transaction.
 *
 * @author mrg
 */
public class BasicInserts2
{
    public static void main(String[] arguments)
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        // Create a new User object tracked by the DataContext.
        User user = dataContext.newObject(User.class);

        // Set values for the new user. In this case, we are initializing
        // an administrator.
        user.setFirstName("System");
        user.setLastName("Administrator");
        user.setUsername("admin");

        // Loop over all the names in our resources file and create users
        // for each of them.
        for (String firstName : Populator.getFirstNames())
          for (String lastName : Populator.getLastNames())
            createUser(dataContext, firstName, lastName);

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    /**
     * Helper method to create and initialize a user in a DataContext.
     *
     * @param dataContext The DataContext to register the user.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     */
    private static void createUser(DataContext dataContext, String firstName, String lastName)
    {
        // Create a new User object tracked by the DataContext.
        User user = dataContext.newObject(User.class);

        // Set values for the new user.
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername((firstName.substring(0, 1) + lastName).toLowerCase());
    }
}
