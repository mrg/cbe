
package cbe.testing.model;

import cbe.testing.model.auto._CBEInsertingMap;

public class CBEInsertingMap extends _CBEInsertingMap
{

    private static CBEInsertingMap instance;

    private CBEInsertingMap()
    {
    }

    public static CBEInsertingMap getInstance()
    {
        if (instance == null)
        {
            instance = new CBEInsertingMap();
        }

        return instance;
    }
}
