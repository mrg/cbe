package cbe.fetching;

import java.util.List;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.SelectQuery;

import cbe.fetching.model.Contractor;
import cbe.fetching.model.Designation;
import cbe.fetching.model.Person;
import cbe.fetching.utilities.Populator;

/**
 * Cayenne By Example - https://github.com/mrg/cbe
 *
 * @author mrg
 */
public class SingleTableInheritance
{
    public static void main(String[] arguments)
    {
        // Populate the database.
        Populator.populateDatabase();

        // Create a new DataContext for the queries.
        DataContext dataContext = DataContext.createDataContext();

        Person person = dataContext.newObject(Person.class);
        Contractor contractor = dataContext.newObject(Contractor.class);

        person.setFirstName("John");
        person.setLastName("Doe");
        person.setDesignation(contractor);

        dataContext.commitChanges();

        SelectQuery query = new SelectQuery(Person.class);
//
//        List<Person> people = dataContext.performQuery(query);
//
//        person = people.get(0);
//
//        System.out.println(person);
//        System.out.println(person.getDesignation());
//        System.out.println(person.getDesignation().getClass());

        query = new SelectQuery(Designation.class);

        List<Designation> designations = dataContext.performQuery(query);

        Designation designation = designations.get(0);

        System.out.println(designation);
        System.out.println(designation.getPerson());
        System.out.println(designation.getClass());

    }
}
