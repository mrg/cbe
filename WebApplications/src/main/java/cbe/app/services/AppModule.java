package cbe.app.services;

import javax.sql.DataSource;

import org.apache.cayenne.access.DataContext;
import org.apache.commons.lang.BooleanUtils;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Startup;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationVersion;

public class AppModule
{
    @Startup
    public void initializeDatabase()
    {
        DataContext context    = DataContext.createDataContext();
        DataSource  dataSource = context.getParentDataDomain().getNode("CBE").getDataSource(); //.getConnection();
        Flyway      flyway     = new Flyway();

        flyway.setDataSource(dataSource);
        flyway.setInitialVersion("1.0");
        flyway.setInitOnMigrate(true);
        flyway.setTarget(MigrationVersion.LATEST);
        flyway.migrate();
    }

    public static void bind(ServiceBinder binder)
    {
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration)
    {
        boolean productionMode = BooleanUtils.toBoolean(System.getProperty(SymbolConstants.PRODUCTION_MODE));

        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        configuration.add(SymbolConstants.APPLICATION_VERSION, "CBE-1.0");
    }
}
