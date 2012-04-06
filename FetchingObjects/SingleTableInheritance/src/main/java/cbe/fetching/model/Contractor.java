package cbe.fetching.model;

import cbe.fetching.constants.DesignationType;
import cbe.fetching.model.auto._Contractor;

public class Contractor extends _Contractor
{
    @Override
    protected void initializeType()
    {
        setType(DesignationType.CONTRACTOR);
    }
}
