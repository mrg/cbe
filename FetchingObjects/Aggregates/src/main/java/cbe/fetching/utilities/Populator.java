package cbe.fetching.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.cayenne.access.DataContext;

import cbe.fetching.model.Book;

/**
 * Utility class to help populate the database with data.
 *
 * @author mrg
 */
public class Populator
{
    // Constants for index positions into data.
    public static int BOOK_AUTHOR = 0;
    public static int BOOK_TITLE  = 1;
    public static int BOOK_PRICE  = 2;

    private static FileLoader     fileLoader = FileLoader.getInstance();
    private static List<String[]> books      = null;


    /**
     * @return All the first names from src/main/resources/People.txt.
     */
    public static List<String[]> getBooks()
    {
        if (books == null)
        {
            books = new ArrayList<String[]>();

            for (String line : fileLoader.loadLines("Books.txt"))
                books.add(line.split("\\|"));
        }

        return books;
    }

    public static void populateDatabase()
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        // Loop over all the names in our resources file and create Persons
        // for each of them.
        for (String[] bookFields : Populator.getBooks())
            createPerson(dataContext, bookFields);

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
        String author = fields[Populator.BOOK_AUTHOR];
        String title  = fields[Populator.BOOK_TITLE];
        String price  = fields[Populator.BOOK_PRICE];

        // Create a new Person object tracked by the DataContext.
        Book book = dataContext.newObject(Book.class);

        book.setAuthor(author);
        book.setTitle(title);
        book.setPrice(new BigDecimal(price));
    }
}
