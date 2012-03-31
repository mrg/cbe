package cbe.fetching;

import java.math.BigDecimal;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.SelectQuery;

import cbe.fetching.model.Book;
import cbe.fetching.utilities.AggregatesUtil;
import cbe.fetching.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * @author mrg
 */
public class AggregatesExample
{
    public static void main(String[] arguments)
    {
        // Populate the database.
        Populator.populateDatabase();

        // Create a new DataContext for the queries.
        DataContext dataContext = DataContext.createDataContext();

        // Create a Query for Book records.
        SelectQuery query = new SelectQuery(Book.class);

        BigDecimal min = AggregatesUtil.min(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal max = AggregatesUtil.max(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal sum = AggregatesUtil.sum(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal avg = AggregatesUtil.avg(dataContext, query, Book.PRICE_PROPERTY);
        long count = AggregatesUtil.count(dataContext, query);

        // Print the results.
        System.out.println("Minimum Book Price: " + min);
        System.out.println("Maximum Book Price: " + max);
        System.out.println("Sum of Book Prices: " + sum);
        System.out.println("Average Book Price: " + avg);
        System.out.println("Number of Books: " + count);
    }
}
