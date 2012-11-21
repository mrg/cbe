Inserting Objects
=================

These examples focus on using Cayenne to insert objects into a database.

* Basic Inserts: Very basic inserts of a single entity (no relationships).  Includes inserting a single object, inserting many objects (within a single transaction), and overriding accessor methods, such as setPassword(), to modify the the data stored in the database.
* Callbacks: Shows how to receive lifecycle event callbacks for Cayenne objects.
* DataMapListener: Shows how to receive lifecycle event callbacks for **ALL** Cayenne objects.
* Enumerations: Shows how to map Java enumerations to Cayenne attributes and database columns.
* Many to Many: Shows how to map a many-to-many relationship while flattening the join table.
* Primitives vs Objects: Shows how using primitives (such as an int) differs from using objects (which allow null values).
* To Many: Shows how to map a to-many relationship.
* To One: Shows how to map a to-one relationship.
* Vertical Inheritance: Shows how to map vertical inheritance.
