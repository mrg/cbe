package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Person;
import cbe.inserting.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 * 
 * This example builds upon BasicInserts1.
 * 
 * It inserts many Person objects into the database within a single
 * commit/transaction.
 * 
 * The data is read from 'People.txt' under resources (loaded by Populator)
 * and adds an email address field.
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

        // Set values for the new person.
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);
    }
}
