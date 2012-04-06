package cbe.fetching.model;

import cbe.fetching.constants.DesignationType;
import cbe.fetching.model.auto._Military;

public class Military extends _Military
{
    @Override
    protected void initializeType()
    {
        setType(DesignationType.MILITARY);
    }
}
