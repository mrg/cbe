package cbe.inserting.utilities;

import java.math.BigDecimal;
import java.util.List;

import org.apache.cayenne.access.DataContext;

import cbe.inserting.model.Book;
import cbe.inserting.model.User;

public class Populator
{
    private static List<String> books      = null;
    private static List<String> firstNames = null;
    private static List<String> lastNames  = null;

    private static FileLoader fileLoader = new FileLoader();


    public static void createBooks()
    {
        DataContext dataContext = DataContext.createDataContext();

        for (String bookEntry : getBooks())
        {
            String parts[] = bookEntry.split("\\|");
            createBook(dataContext, parts[0], parts[1], parts[2]);
        }

        dataContext.commitChanges();
    }

    private static void createBook(DataContext dataContext, String price, String author, String title)
    {
        Book book = dataContext.newObject(Book.class);

        book.setAuthor(author);
        book.setPrice(new BigDecimal(price));
        book.setTitle(title);
    }


    public static void createUsers()
    {
      DataContext dataContext = DataContext.createDataContext();

      User user = dataContext.newObject(User.class);

      user.setFirstName("System");
      user.setLastName("Administrator");
      user.setUsername("admin");
//      user.setRole(RoleType.ADMINISTRATOR);

      for (String firstName : getFirstNames())
        for (String lastName : getLastNames())
          createUser(dataContext, firstName, lastName);

      dataContext.commitChanges();
    }

    private static void createUser(DataContext dataContext, String firstName, String lastName)
    {
      User user = dataContext.newObject(User.class);

      user.setFirstName(firstName);
      user.setLastName(lastName);
//      user.setRole(RoleType.CUSTOMER);
      user.setUsername((firstName.substring(0, 1) + lastName).toLowerCase());
    }


    private static List<String> getBooks()
    {
        if (books == null)
            books = fileLoader.load("Books.txt");

        return books;
    }

    private static List<String> getFirstNames()
    {
        if (firstNames == null)
            firstNames = fileLoader.load("FirstNames.txt");

        return firstNames;
    }

    private static List<String> getLastNames()
    {
        if (lastNames == null)
            lastNames = fileLoader.load("LastNames.txt");

        return lastNames;
    }
}
