package cbe.fetching.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.query.SelectQuery;

/**
 * Utility to count the number of records in the database matching a
 * Cayenne-based query.
 *
 * Inspired by Andrey Razumovsky (where "inspired by" means
 * "mostly stolen from") who posted the code on the Cayenne mailing list.
 *
 * @author mrg
 */
public class CountHelper
{
    /**
     * Perform a SELECT COUNT(*) for a Cayenne-based query.
     *
     * @param context The DataContext to use to find the DataNode.
     * @param query The Cayenne Query.
     * @return Number of records matching query.
     */
    public static long count(DataContext context, SelectQuery query)
    {
        return count(context.getParentDataDomain().getDataNodes().iterator().next(), query);
    }

    /**
     * Perform a SELECT COUNT(*) for a Cayenne-based query.
     *
     * @param context The DataNode to use to get the database connection.
     * @param query The Cayenne Query.
     * @return Number of records matching query.
     */
    public static long count(DataNode node, SelectQuery query)
    {
        Connection        connection        = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = node.getDataSource().getConnection();

            CountTranslator translator = new CountTranslator();
            translator.setAdapter(node.getAdapter());
            translator.setConnection(connection);
            translator.setEntityResolver(node.getEntityResolver());
            translator.setQuery(query);

            preparedStatement = translator.createStatement();

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getLong(1);

            throw new RuntimeException("Count query returned no result");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Cannot count", e);
        }
        finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (connection != null)
                    connection.close();
            }
            catch (Exception ex)
            {
                throw new RuntimeException("Cannot close connection", ex);
            }
        }
    }
}
