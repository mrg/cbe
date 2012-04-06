package cbe.fetching.model;

import cbe.fetching.constants.DesignationType;
import cbe.fetching.model.auto._Civilian;

public class Civilian extends _Civilian
{
    @Override
    protected void initializeType()
    {
        setType(DesignationType.CIVILIAN);
    }
}
