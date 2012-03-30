package cbe.fetching;

import java.math.BigDecimal;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.SelectQuery;

import cbe.fetching.model.Book;
import cbe.fetching.utilities.MinHelper;
import cbe.fetching.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example builds upon BasicInserts3.
 *
 * It counts how many Person objects are in the database.
 *
 * @author mrg
 */
public class MinMaxAvg
{
    public static void main(String[] arguments)
    {
    	// Populate the database.
    	Populator.populateDatabase();

    	// Create a new DataContext for the queries.
        DataContext dataContext = DataContext.createDataContext();

        // Create a Query for Book records.
        SelectQuery query = new SelectQuery(Book.class);

        BigDecimal min = MinHelper.min(dataContext, query, Book.PRICE_PROPERTY);


        // Print the results.
        System.out.println("Minimum Book Price: " + min);
    }
}
