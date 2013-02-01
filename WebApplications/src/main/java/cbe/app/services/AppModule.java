package cbe.app.services;

import org.apache.commons.lang.BooleanUtils;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule
{
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
