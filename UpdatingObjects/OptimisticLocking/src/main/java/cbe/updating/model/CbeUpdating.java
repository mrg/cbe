package cbe.updating.model;

import cbe.updating.model.auto._CbeUpdating;

public class CbeUpdating extends _CbeUpdating
{
    private static CbeUpdating instance;

    private CbeUpdating()
    {
    }

    public static CbeUpdating getInstance()
    {
        if (instance == null)
        {
            instance = new CbeUpdating();
        }

        return instance;
    }
}
