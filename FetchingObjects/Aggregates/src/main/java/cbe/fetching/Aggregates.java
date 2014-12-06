package cbe.fetching;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.h2.tools.RunScript;

import cbe.fetching.model.Book;
import cbe.fetching.utilities.AggregateUtils;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * @author mrg
 */
public class Aggregates
{
    public static void main(String[] arguments) throws FileNotFoundException, SQLException, URISyntaxException
    {
        // Create a Cayenne ServerRuntime with our Cayenne Model.
        ServerRuntime runtime = new ServerRuntime("cayenne-cbe.xml");

        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = (DataContext) runtime.getContext();

        // Populate the database.
        FileReader data = new FileReader(new File(Aggregates.class.getClassLoader().getResource("data.sql").toURI()));
        RunScript.execute(runtime.getDataSource("cbe").getConnection(), data);

        // Create a Query for Book records.
        SelectQuery query = new SelectQuery(Book.class);

        // Run the aggregate queries.
        BigDecimal min = AggregateUtils.min(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal max = AggregateUtils.max(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal sum = AggregateUtils.sum(dataContext, query, Book.PRICE_PROPERTY);
        BigDecimal avg = AggregateUtils.avg(dataContext, query, Book.PRICE_PROPERTY);
        long count = AggregateUtils.count(dataContext, query);

        // Print the results.
        System.out.println("Minimum Book Price: " + min);
        System.out.println("Maximum Book Price: " + max);
        System.out.println("Sum of Book Prices: " + sum);
        System.out.println("Average Book Price: " + avg);
        System.out.println("Number of Books: " + count);

        // Add a qualifier for Author names starting with "J".
        query.setQualifier(ExpressionFactory.likeIgnoreCaseExp(Book.AUTHOR_PROPERTY, "J%"));

        // Run the aggregate queries.
        min = AggregateUtils.min(dataContext, query, Book.PRICE_PROPERTY);
        max = AggregateUtils.max(dataContext, query, Book.PRICE_PROPERTY);
        sum = AggregateUtils.sum(dataContext, query, Book.PRICE_PROPERTY);
        avg = AggregateUtils.avg(dataContext, query, Book.PRICE_PROPERTY);
        count = AggregateUtils.count(dataContext, query);

        // Print the results.
        System.out.println("Minimum 'J' Author Book Price: " + min);
        System.out.println("Maximum 'J' Author Book Price: " + max);
        System.out.println("Sum of 'J' Author Book Prices: " + sum);
        System.out.println("Average 'J' Author Book Price: " + avg);
        System.out.println("Number of 'J' Author Books: " + count);

        // Make the query use DISTINCT and get a distinct count of authors whose
        // name begins with "J" by counting on the Book's Author property.
        query.setDistinct(true);
        count = AggregateUtils.count(dataContext, query, Book.AUTHOR_PROPERTY);
        System.out.println("Number of 'J' Authors: " + count);
    }
}
