package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;
import cbe.inserting.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 * 
 * This example builds upon BasicInserts2 and inserts many Person objects into
 * the database within a single commit/transaction.
 * 
 * It adds setting a person's password to demonstrate overriding the
 * setPassword() method in the Person.java subclass, which automatically hashes
 * the value passed in (so that the person's password is not saved in plain
 * text).
 * 
 * @author mrg
 */
public class BasicInserts3
{
    public static void main(String[] arguments)
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        // Loop over all the names in our resources file and create Persons
        // for each of them.
        for (String[] personFields : Populator.getPeople())
            createPerson(dataContext, personFields);

        // Commit the changes to the database.
        dataContext.commitChanges();
    }

    /**
     * Helper method to create and initialize a person in a DataContext.
     *
     * @param dataContext The DataContext to register the person.
     * @param fields The data fields from the People.txt file.
     */
    private static void createPerson(DataContext dataContext, String[] fields)
    {
    	// Extract field values.
    	String firstName    = fields[Populator.PERSON_FIRST_NAME];
    	String lastName     = fields[Populator.PERSON_LAST_NAME];
    	String emailAddress = fields[Populator.PERSON_EMAIL_ADDRESS];

        // Create a new Person object tracked by the DataContext.
    	Person person = dataContext.newObject(Person.class);

        // Set values for the new person. Defaults the password to the e-mail
        // address with "123" appended.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);
        person.setPassword(emailAddress + "123");
    }
}
