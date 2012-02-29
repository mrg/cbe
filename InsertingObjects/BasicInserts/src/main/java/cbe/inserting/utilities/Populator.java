package cbe.inserting.utilities;

import java.util.List;

/**
 * Utility class to help populate the database with data.
 *
 * @author mrg
 */
public class Populator
{
    private static List<String> firstNames = null;
    private static List<String> lastNames  = null;
    private static List<String> people     = null;

    private static FileLoader fileLoader = FileLoader.getInstance();


    /**
     * @return All the first names from src/main/resources/firstNames.txt.
     */
    public static List<String> getFirstNames()
    {
        if (firstNames == null)
            firstNames = fileLoader.loadLines("FirstNames.txt");

        return firstNames;
    }

    /**
     * @return All the last names from src/main/resources/lastNames.txt.
     */
    public static List<String> getLastNames()
    {
        if (lastNames == null)
            lastNames = fileLoader.loadLines("LastNames.txt");

        return lastNames;
    }

    /**
     * @return All the first names from src/main/resources/People.txt.
     */
    public static List<String> getPeople()
    {
        if (people == null)
            people = fileLoader.loadLines("People.txt");

        return people;
    }
}
