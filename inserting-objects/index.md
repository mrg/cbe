---
layout: page
root: ".."
title: Cayenne by Example - Inserting Objects
heading: Inserting Objects
breadcrumb:
  - name: Cayenne By Example
    link: ..
  - name: Inserting Objects
---

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

**NOTE: The first example shows more detail than most, including what Cayenne-generated classes look like and detailed SQL/startup logs, since it is the introductory example.**

## Inserting Objects Examples:

* [Basic Inserts](basic-inserts.html)
* [Enumerations](enumerations.html)
* To-One Relationships
* To-Many Relationships
* Many-to-Many Relationships
* Flattened Relationships
* To-Dependent PK
* Meaningful PKs
* [Callbacks](callbacks.html)
* [Listeners](listeners.html)
* Embeddables
* BLOBs
* Validation

