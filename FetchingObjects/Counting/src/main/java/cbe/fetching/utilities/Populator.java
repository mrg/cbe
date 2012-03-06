package cbe.fetching.utilities;

import java.util.ArrayList;
import java.util.List;

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
}
