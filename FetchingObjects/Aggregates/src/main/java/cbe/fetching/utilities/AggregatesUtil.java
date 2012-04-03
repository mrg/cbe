package cbe.fetching.utilities;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;

/**
 * Utility to run common database aggregate functions matching a Cayenne-based
 * query.
 *
 * Inspired by Andrey Razumovsky (where "inspired by" means
 * "mostly stolen from") who posted the original code on the Cayenne mailing
 * list.
 *
 * @author mrg
 */
public class AggregatesUtil
{
    // Aggregate function names.
    public static final String AVG   = "AVG";
    public static final String COUNT = "COUNT";
    public static final String MAX   = "MAX";
    public static final String MIN   = "MIN";
    public static final String SUM   = "SUM";

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute of the column to sum.
     * @return The database AVG of the attribute matching the query.
     */
    public static BigDecimal avg(DataContext context, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(findDataNode(context, query), query, objectAttribute, AVG);
    }

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @return The database COUNT of the rows matching the query.
     */
    public static long count(DataContext context, SelectQuery query)
    {
        return count(context, query, null);
    }

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute of the column to sum. Much more useful if
     *            DISTINCT is set for the query.
     * @return The database COUNT of the rows matching the attribute for the query.
     */
    public static long count(DataContext context, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(findDataNode(context, query), query, objectAttribute, COUNT).longValue();
    }

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute of the column to sum.
     * @return The database MAX of the attribute matching the query.
     */
    public static BigDecimal max(DataContext context, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(findDataNode(context, query), query, objectAttribute, MAX);
    }

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute of the column to sum.
     * @return The database MIN of the attribute matching the query.
     */
    public static BigDecimal min(DataContext context, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(findDataNode(context, query), query, objectAttribute, MIN);
    }

    /**
     * @param context
     *            The DataContext used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute of the column to sum.
     * @return The database SUM of the attribute matching the query.
     */
    public static BigDecimal sum(DataContext context, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(findDataNode(context, query), query, objectAttribute, SUM);
    }

    /**
     * @param node
     *            The DataNode used to find the database column.
     * @param query
     *            The SelectQuery used to extract the root ObjEntity of the
     *            query.
     * @param objectAttribute
     *            The Java attribute to map to a database column.
     * @return The name of the database column for the given Java attribute.
     * @throws RuntimeException If the Java attribute is invalid.
     */
    public static String getDatabaseColumn(DataNode node, SelectQuery query, String objectAttribute)
    {
        ObjEntity    objEntity    = node.getEntityResolver().lookupObjEntity(query.getRoot());
        ObjAttribute objAttribute = (ObjAttribute) objEntity.getAttribute(objectAttribute);

        if (objAttribute == null)
            throw new RuntimeException("Could not locate objAttribute: " + objectAttribute);

        return objAttribute.getDbAttribute().getName();
    }

    /**
     * @param context
     *            The DataContext to use to locate the DataNode.
     * @param query
     *            The SelectQuery used to isolate the query root and identify
     *            the correct DataNode (if the model has multiple DataNodes).
     * @return The DataNode for the query or null if not found.
     */
    public static DataNode findDataNode(DataContext context, SelectQuery query)
    {
        for (DataNode node : context.getParentDataDomain().getDataNodes())
        {
            ObjEntity objectEntity = node.getEntityResolver().lookupObjEntity(query.getRoot());

            if (objectEntity != null)
                return node;
        }

        return null;
    }

    /**
     * @param node
     *            The DataNode used to perform the query.
     * @param query
     *            The SelectQuery to perform the query against.
     * @param objectAttribute
     *            The Java attribute for the aggregate function (which will be
     *            mapped to the database column).
     * @param functionName
     *            One the the aggregate functions defined above (such as MIN or
     *            MAX).
     * @return The result of executing the function against the database.
     */
    private static BigDecimal getResultForFunction(DataNode node, SelectQuery query, String objectAttribute, String function)
    {
        String  databaseColumn;
        boolean distinct;

        if (function.equals(COUNT) && objectAttribute == null)
        {
            databaseColumn = "*";   // Allow COUNT(*) with no attribute.
            distinct       = false; // DISTINCT does NOT apply to *.
        }
        else
        {
            databaseColumn = getDatabaseColumn(node, query, objectAttribute);
            distinct       = query.isDistinct();
        }

        return getResultUsingTranslator(node, query,  new AggregateTranslator(function, databaseColumn, distinct));
    }

    /**
     * Does the dirty work of issuing a raw SELECT using the given aggregate
     * translator and returning the result. Since this is designed for aggregate
     * functions, it only expects and looks at the first column of the result.
     *
     * @param node
     *            The DataNode used for the database connection.
     * @param query
     *            The SelectQuery used to root the query and provide qualifiers.
     * @param translator
     *            The AggregateTranslator to use to construct the SQL for the
     *            raw query.
     * @return The numeric result of the aggregate function.
     */
    private static BigDecimal getResultUsingTranslator(DataNode node, SelectQuery query, AggregateTranslator translator)
    {
        Connection        connection        = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = node.getDataSource().getConnection();

            translator.setAdapter(node.getAdapter());
            translator.setConnection(connection);
            translator.setEntityResolver(node.getEntityResolver());
            translator.setQuery(query);

            preparedStatement = translator.createStatement();

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getBigDecimal(1);

            throw new RuntimeException(translator.getFunction() + " query returned no result");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Cannot run " + translator.getFunction() + " query", e);
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
