package cbe.inserting.model;

import cbe.inserting.constants.BookType;
import cbe.inserting.model.auto._ElectronicBook;

public class ElectronicBook extends _ElectronicBook
{
    @Override
    protected void initializeType()
    {
        setType(BookType.ELECTRONIC_BOOK);
    }
}
