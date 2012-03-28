package cbe.inserting;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.constants.BookType;
import cbe.inserting.constants.ElectronicBookFormat;
import cbe.inserting.constants.PaperBookFormat;
import cbe.inserting.model.ElectronicBook;
import cbe.inserting.model.PaperBook;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * This example shows how to use vertical inheritance.
 *
 * It inserts many Person objects into the database within a single
 * commit/transaction.
 *
 * The data is read from 'People.txt' under resources (loaded by Populator).
 *
 * It adds setting a person's password to demonstrate overriding the
 * setPassword() method in the Person.java subclass, which automatically hashes
 * the value passed in (so that the person's password is not saved in plain
 * text).
 *
 * @author mrg
 */
public class VerticalInheritance
{
    public static void main(String[] arguments)
    {
        // Create a new DataContext. This will also initialize the Cayenne
        // Framework.
        DataContext dataContext = DataContext.createDataContext();

        ElectronicBook e1 = dataContext.newObject(ElectronicBook.class);
        e1.setFormat(ElectronicBookFormat.EPUB);
        e1.setIsbn("123");
        e1.setType(BookType.ELECTRONIC_BOOK);
        e1.setSize(1100);
        e1.setAuthor("Tad Williams");
        e1.setTitle("City of Golden Shadow");
        e1.setSeries("Otherland");
        e1.setSequence(1);

        ElectronicBook e2 = dataContext.newObject(ElectronicBook.class);
        e2.setFormat(ElectronicBookFormat.EPUB);
        e2.setIsbn("123");
        e2.setType(BookType.ELECTRONIC_BOOK);
        e2.setSize(1000);
        e2.setAuthor("Tad Williams");
        e2.setTitle("River of Blue Fire");
        e2.setSeries("Otherland");
        e2.setSequence(2);

        ElectronicBook e3 = dataContext.newObject(ElectronicBook.class);
        e3.setFormat(ElectronicBookFormat.EPUB);
        e3.setIsbn("123");
        e3.setType(BookType.ELECTRONIC_BOOK);
        e3.setSize(950);
        e3.setAuthor("Tad Williams");
        e3.setTitle("Mountain of Black Glass");
        e3.setSeries("Otherland");
        e3.setSequence(3);

        ElectronicBook e4 = dataContext.newObject(ElectronicBook.class);
        e4.setFormat(ElectronicBookFormat.EPUB);
        e4.setIsbn("123");
        e4.setType(BookType.ELECTRONIC_BOOK);
        e4.setSize(1050);
        e4.setAuthor("Tad Williams");
        e4.setTitle("Sea of Silver Light");
        e4.setSeries("Otherland");
        e4.setSequence(4);

        PaperBook p1 = dataContext.newObject(PaperBook.class);
        p1.setFormat(PaperBookFormat.PAPER_COVER);
        p1.setIsbn("123");
        p1.setType(BookType.PAPER_BOOK); // FIXME: Should be done on creation.
        p1.setAuthor("Tad Williams");
        p1.setTitle("Tailchaser's Song");

        PaperBook p2 = dataContext.newObject(PaperBook.class);
        p2.setFormat(PaperBookFormat.PAPER_COVER);
        p2.setIsbn("123");
        p2.setType(BookType.PAPER_BOOK);
        p2.setAuthor("Tad Williams");
        p2.setTitle("The Dragonbone Chair");
        p2.setSeries("Memory, Sorrow, and Thorn");
        p2.setSequence(1);

        PaperBook p3 = dataContext.newObject(PaperBook.class);
        p3.setFormat(PaperBookFormat.PAPER_COVER);
        p3.setIsbn("123");
        p3.setType(BookType.PAPER_BOOK);
        p3.setAuthor("Tad Williams");
        p3.setTitle("Stone of Farewell");
        p3.setSeries("Memory, Sorrow, and Thorn");
        p3.setSequence(2);

        PaperBook p4 = dataContext.newObject(PaperBook.class);
        p4.setFormat(PaperBookFormat.PAPER_COVER);
        p4.setIsbn("123");
        p4.setType(BookType.PAPER_BOOK);
        p4.setAuthor("Tad Williams");
        p4.setTitle("To Green Angel Tower");
        p4.setSeries("Memory, Sorrow, and Thorn");
        p4.setSequence(3);

        // Commit the changes to the database.
        dataContext.commitChanges();
    }
}
