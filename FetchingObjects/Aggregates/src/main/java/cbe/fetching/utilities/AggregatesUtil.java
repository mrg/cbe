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
    private static final String MAX = "MAX";
    private static final String MIN = "MIN";
    private static final String AVG = "AVG";
    private static final String SUM = "SUM";
    private static final String COUNT = "COUNT";

    public static BigDecimal sum(DataContext context, SelectQuery query, String objectAttribute)
    {
        return sum(findDataNode(context, query), query, objectAttribute);
    }
    public static BigDecimal avg(DataContext context, SelectQuery query, String objectAttribute)
    {
        return avg(findDataNode(context, query), query, objectAttribute);
    }
    public static BigDecimal min(DataContext context, SelectQuery query, String objectAttribute)
    {
        return min(findDataNode(context, query), query, objectAttribute);
    }
    public static BigDecimal max(DataContext context, SelectQuery query, String objectAttribute)
    {
        return max(findDataNode(context, query), query, objectAttribute);
    }
    public static long count(DataContext context, SelectQuery query)
    {
        return count(context, query, null);
    }
    public static long count(DataContext context, SelectQuery query, String objectAttribute)
    {
        return count(findDataNode(context, query), query, objectAttribute);
    }

    public static BigDecimal sum(DataNode node, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(node, query, objectAttribute, SUM);
    }
    public static BigDecimal avg(DataNode node, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(node, query, objectAttribute, AVG);
    }
    public static BigDecimal min(DataNode node, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(node, query, objectAttribute, MIN);
    }
    public static BigDecimal max(DataNode node, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(node, query, objectAttribute, MAX);
    }
    public static long count(DataNode node, SelectQuery query, String objectAttribute)
    {
        return getResultForFunction(node, query, objectAttribute, COUNT).longValue();
    }

    private static String getDatabaseColumn(DataNode node, SelectQuery query, String objectAttribute)
    {
        ObjEntity    objEntity    = node.getEntityResolver().lookupObjEntity(query.getRoot());
        ObjAttribute objAttribute = (ObjAttribute) objEntity.getAttribute(objectAttribute);

        if (objAttribute == null)
            throw new RuntimeException("Could not locate objAttribute: " + objectAttribute);

        return objAttribute.getDbAttribute().getName();
    }

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

    private static BigDecimal getResultForFunction(DataNode node, SelectQuery query, String objectAttribute, String function)
    {
        String databaseColumn;

        if (function.equals(COUNT) && objectAttribute == null)
            databaseColumn = "*"; // Allow COUNT(*) with no attribute.
        else
            databaseColumn = getDatabaseColumn(node, query, objectAttribute);

        return getResultUsingTranslator(node, query,  new AggregateTranslator(function, databaseColumn));
    }

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
