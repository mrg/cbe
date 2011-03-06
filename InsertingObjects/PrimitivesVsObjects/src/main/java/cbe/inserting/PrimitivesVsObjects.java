package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.User;
import cbe.inserting.utilities.Populator;

/**
 * This example inserts multiple User objects into the database.  It is
 * similar to the BasicInserts examples, but adds using a Boolean object
 * and a boolean primitive to the User object to show the difference
 * between the two.
 *
 * @author mrg
 */
public class PrimitivesVsObjects
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
        user.setPassword("admin123");
        // "admin" is a Boolean, "enabled" is a boolean.
        user.setAdmin(true); // Autoboxing to a Boolean here.

        // Loop over all the names in our resources file and create users
        // for each of them.  The Populator reads first and last names from
        // a data file in the resources directory.
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

        // Set values for the new user. Defaults the password to the username
        // with "123" appended.
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername((firstName.substring(0, 1) + lastName).toLowerCase());
        user.setPassword(user.getUsername() + "123");

        // Don't enable accounts whose last name starts with an "A".
        if (lastName.startsWith("A"))
            user.setEnabled(false);
        else
            user.setEnabled(true);
    }
}
