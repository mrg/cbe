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

    private static FileLoader fileLoader = new FileLoader();


    /**
     * @return All the first names from src/main/resources/firstNames.txt.
     */
    public static List<String> getFirstNames()
    {
        if (firstNames == null)
            firstNames = fileLoader.load("FirstNames.txt");

        return firstNames;
    }

    /**
     * @return All the last names from src/main/resources/lastNames.txt.
     */
    public static List<String> getLastNames()
    {
        if (lastNames == null)
            lastNames = fileLoader.load("LastNames.txt");

        return lastNames;
    }
}
