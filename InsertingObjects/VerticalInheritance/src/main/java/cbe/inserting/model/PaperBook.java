package cbe.inserting.model;

import cbe.inserting.constants.BookType;
import cbe.inserting.model.auto._PaperBook;

public class PaperBook extends _PaperBook
{
    @Override
    protected void initializeType()
    {
        setType(BookType.PAPER_BOOK);
    }

}
