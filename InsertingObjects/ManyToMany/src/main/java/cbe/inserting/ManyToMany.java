package cbe.inserting;

import java.util.ArrayList;
import java.util.List;

import org.apache.cayenne.access.DataContext;
import org.apache.commons.lang.StringUtils;

import cbe.inserting.model.Role;
import cbe.inserting.model.User;

/**
 * Hello world!
 *
 */
public class ManyToMany
{
    public static void main( String[] args )
    {
        DataContext dc = DataContext.createDataContext();

        Role admin = dc.newObject(Role.class);
        admin.setName("ADMIN");

        Role moderator = dc.newObject(Role.class);
        moderator.setName("MODERATOR");

        Role customer = dc.newObject(Role.class);
        customer.setName("CUSTOMER");

        dc.commitChanges();

        User a1 = dc.newObject(User.class);
        a1.setName("admin1");
        a1.addToRoles(admin);
        a1.addToRoles(moderator);

        User a2 = dc.newObject(User.class);
        a2.setName("admin2");
        a2.addToRoles(admin);

        User m1 = dc.newObject(User.class);
        m1.setName("moderator1");
        m1.addToRoles(moderator);
        m1.addToRoles(customer);

        User m2 = dc.newObject(User.class);
        m2.setName("moderator2");
        m2.addToRoles(moderator);

        User c1 = dc.newObject(User.class);
        c1.setName("customer1");
        c1.addToRoles(customer);
        c1.addToRoles(moderator);

        User c2 = dc.newObject(User.class);
        c2.setName("customer2");
        c2.addToRoles(customer);

        User c3 = dc.newObject(User.class);
        c3.setName("customer3");
        c3.addToRoles(customer);

        System.out.println("Admins: " + getNames(admin.getUsers()));
        System.out.println("Moderators: " + getNames(moderator.getUsers()));
        System.out.println("Customers: " + getNames(customer.getUsers()));

        dc.commitChanges();
    }

    private static String getNames(List<User> users)
    {
        List<String> names = new ArrayList<String>();

        for (User user : users)
            names.add(user.getName());

        return StringUtils.join(names, ", ");
    }
}
