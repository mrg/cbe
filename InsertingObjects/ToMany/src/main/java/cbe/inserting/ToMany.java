package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Address;
import cbe.inserting.model.Person;
import cbe.inserting.model.Setting;

/**
 * This example creates a to-many relationship (User ->> Settings) along with
 * a to-one relationship (User -> Address).
 *
 * It is based upon the ToOne example.
 *
 * @author mrg
 */
public class ToMany
{
    DataContext dataContext = null;

    public ToMany()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        dataContext = DataContext.createDataContext();

        // Create a new Person object tracked by the DataContext.
        Person person = dataContext.newObject(Person.class);

        // Set values for the new person.
        person.setFirstName("System");
        person.setLastName("Administrator");
        person.setUsername("admin");

        // Create the address (a to-one relationship) for the person.
        Address address = dataContext.newObject(Address.class);

        // Set the address attributes.
        address.setCity("Falls Church");
        address.setState("VA");
        address.setStreet("W Broad Street");
        address.setZip("22046");

        // Assign the address to the person.  Cayenne will figure out how
        // to map the relationship based upon the model and also create
        // the primary keys and foreign keys.
        person.setAddress(address);

        // Create the settings (a to-many relationship) for the person.
        Setting setting1 = dataContext.newObject(Setting.class);
        Setting setting2 = dataContext.newObject(Setting.class);
        Setting setting3 = dataContext.newObject(Setting.class);

        // Set the settings attributes.
        setting1.setKey("theme");
        setting1.setValue("Majestic");

        setting2.setKey("format");
        setting2.setValue("3-pane");

        setting3.setKey("font-size");
        setting3.setValue("13px");

        // Add the settings to the person.  Cayenne will figure out how
        // to map the relationship based upon the model and also create
        // the primary keys and foreign keys.
        person.addToSettings(setting1);
        person.addToSettings(setting2);
        person.addToSettings(setting3);

        // Commit the changes to the database (Person, Address, and Settings
        // records).
        dataContext.commitChanges();
    }

    public static void main(String[] arguments)
    {
        new ToMany();
    }
}
