---
layout: page
root: ".."
title: Cayenne by Example - Inserting Objects
---

# Inserting Objects


<!---
Insert into 1 table -- individual insert, group insert
Insert into 1 table, primitives vs objects
Insert into 1 table, enumerations
Insert into 2 tables -- to-one relationship
Insert into 2 tables -- to-many relationship
Insert into 1 table -- blob
Insert into 1 table -- validation
Insert into N tables (load all test data for further examples)
Rollbacks?
auto initialize new objects / callbacks
recursive relationships
flattened relationships?
meaningful pk
show cayenne states somewhere
listeners/callbacks
to dep pk
-->


These examples will show how to create new Java objects and register them in a DataContext, set their values, and commit to the database.

The examples will progress in complexity as more and more data is used to build a database to use for the later examples on fetching, updating, and deleting.
