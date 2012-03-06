package cbe.fetching.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.query.SelectQuery;

public class CountHelper
{
    public static long count(DataContext context, SelectQuery query)
    {
        return count(context, query, context.getParentDataDomain().getDataNodes().iterator().next());
    }

    public static long count(DataContext context, SelectQuery query, DataNode node)
    {
        Connection        connection        = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = node.getDataSource().getConnection();

            CountTranslator translator = new CountTranslator();

            translator.setAdapter(node.getAdapter());
            translator.setConnection(connection);
            translator.setEntityResolver(context.getEntityResolver());
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
