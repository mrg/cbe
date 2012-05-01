
package cbe.inserting.model;

import java.util.Date;

import cbe.inserting.model.auto._Person;

public class Person extends _Person
{
    @Override
    protected void onPrePersist()
    {
        System.out.println("Setting CREATE date");
        setCreateDate(new Date());
    }

    @Override
    protected void onPreUpdate()
    {
        System.out.println("Setting MODIFY date");
        setModifyDate(new Date());
    }
}
