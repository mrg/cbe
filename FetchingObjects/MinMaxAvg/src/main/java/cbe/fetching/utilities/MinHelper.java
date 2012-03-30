package cbe.fetching.utilities;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.map.DbAttribute;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
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
public class MinHelper
{
    /**
     * Perform a SELECT COUNT(*) for a Cayenne-based query.
     *
     * @param context The DataContext to use to find the DataNode.
     * @param query The Cayenne Query.
     * @return Number of records matching query.
     */
    public static BigDecimal min(DataContext context, SelectQuery query, String column)
    {
        return min(context.getParentDataDomain().getDataNodes().iterator().next(), query, column);
    }

    /**
     * Perform a SELECT MIN(column) for a Cayenne-based query.
     *
     * @param context The DataNode to use to get the database connection.
     * @param query The Cayenne Query.
     * @return Number of records matching query.
     */
    public static BigDecimal min(DataNode node, SelectQuery query, String column)
    {
        Connection        connection        = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = node.getDataSource().getConnection();
            ObjEntity entity = node.getEntityResolver().lookupObjEntity(query.getRoot());
            ObjAttribute attribute = (ObjAttribute) entity.getAttribute(column);
            DbAttribute dbColumn = attribute.getDbAttribute();

            MinTranslator translator = new MinTranslator(dbColumn.getName());
            translator.setAdapter(node.getAdapter());
            translator.setConnection(connection);
            translator.setEntityResolver(node.getEntityResolver());
            translator.setQuery(query);

            preparedStatement = translator.createStatement();

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getBigDecimal(1);

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
