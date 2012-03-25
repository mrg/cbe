package cbe.fetching;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import cbe.fetching.model.Person;
import cbe.fetching.utilities.CountHelper;
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
public class Counting
{
    public static void main(String[] arguments)
    {
    	// Populate the database.
    	Populator.populateDatabase();

    	// Create a new DataContext for the queries.
        DataContext dataContext = DataContext.createDataContext();

        // Create a Query for Person records.
        SelectQuery query = new SelectQuery(Person.class);

        // Count the number of Person records based upon the Query.
        // Since the Query is unrestricted, it will return the count
        // of all Person records.
        long allPersons = CountHelper.count(dataContext, query);

        // Create an Expression to restrict Person records to first
        // names beginning with an "A".
        Expression expression =
            ExpressionFactory.likeIgnoreCaseExp(Person.FIRST_NAME_PROPERTY, "A%");

        // Add the Expression to the Person query.
        query.setQualifier(expression);

        // Count the number of Person records based upon the query.
        long restrictedPersons = CountHelper.count(dataContext, query);

        // Print the results.
        System.out.println("Number of Person records: " + allPersons);
        System.out.println("Number of 'A' Person records: " + restrictedPersons);
    }
}
