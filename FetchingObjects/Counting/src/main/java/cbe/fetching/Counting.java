package cbe.fetching;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import cbe.fetching.model.Book;
import cbe.fetching.utilities.CountHelper;
import cbe.fetching.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * It counts how many Book objects are in the database.
 *
 * @author mrg
 */
public class Counting
{
    public static void main(String[] arguments)
    {
    	// Populate the database.
    	Populator.populateDatabase();

    	// Create a new DataContext for the queries.
        DataContext dataContext = DataContext.createDataContext();

        // Create a Query for Book records.
        SelectQuery query = new SelectQuery(Book.class);

        // Count the number of Book records based upon the Query.
        // Since the Query is unrestricted, it will return the count
        // of all Book records.
        long allBooks = CountHelper.count(dataContext, query);

        // Create an Expression to restrict Book records to author
        // names beginning with an "J".
        Expression expression =
            ExpressionFactory.likeIgnoreCaseExp(Book.AUTHOR_PROPERTY, "J%");

        // Add the Expression to the Book query.
        query.setQualifier(expression);

        // Count the number of Book records based upon the query.
        long qualifiedBooks = CountHelper.count(dataContext, query);

        // Print the results.
        System.out.println("Number of Book records: " + allBooks);
        System.out.println("Number of 'J' Book Author records: " + qualifiedBooks);
    }
}
