package cbe.fetching.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.cayenne.access.DataContext;

import cbe.fetching.model.Person;

/**
 * Utility class to help populate the database with data.
 *
 * @author mrg
 */
public class Populator
{
	// Constants for index positions into data.
	public static int PERSON_FIRST_NAME    = 0;
	public static int PERSON_LAST_NAME     = 1;
	public static int PERSON_EMAIL_ADDRESS = 2;
	
    private static FileLoader     fileLoader = FileLoader.getInstance();
    private static List<String[]> people     = null;


    /**
     * @return All the first names from src/main/resources/People.txt.
     */
    public static List<String[]> getPeople()
    {
        if (people == null)
        {
        	people = new ArrayList<String[]>();
        	
        	for (String line : fileLoader.loadLines("People.txt"))
        		people.add(line.split("\\|"));
        }
        
        return people;
    }
    
    public static void populateDatabase()
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
