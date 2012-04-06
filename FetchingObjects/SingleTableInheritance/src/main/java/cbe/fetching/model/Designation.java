package cbe.fetching.model;

import cbe.fetching.model.auto._Designation;

public abstract class Designation extends _Designation
{
    @Override
    protected abstract void initializeType();
//    {
//        throw new RuntimeException("Do not try to insert Designation");
//    }
}
