---
layout: page
title: Introduction
---

# Introduction

## <a name="cayenne">Apache Cayenne</a>

[Apache Cayenne](http://cayenne.apache.org/) (or simply Cayenne, for short) is an Object-Relational Mapping (ORM) framework for Java programmers.  Cayenne maps Java classes to database tables and automatically generates SQL to move data back-and-forth between Java objects and the database, which is a tedious task if done manually.  An ORM simplifies accessing a relational database (such as Oracle, MySQL, PostgreSQL, SQL Server, H2, etc) and insulates developers from writing common SQL and data marshaling code.

Some of the features of Cayenne include:

* A GUI (Cayenne Modeler) for mapping database tables and columns to Java classes and attributes, mapping relationships, generating Java classes, defining callback and listener life cycle events, configuring caching options, configuring database connection pools, reverse engineering databases, database schema generation, defining shared queries, and mapping stored procedures.
* Transactional management features (such as rollback, undo, commit) using Cayenne's control layer (the "context" -- described later) or optionally deferring to the container (such as JBoss) if in a J2EE environment.
* Query language for database-independent run-time SQL generation of SELECT statements (including table joins).
* Automatic generation of INSERT, UPDATE, and DELETE statements when committing the object graph to the database.  Cayenne determines the order of operations (the dependency graph) needed to do the commit without the developer having to worry about the order objects were added or edited.
* Automatic transfer of database records and column data to Java objects and back.
* Relationship management for pre-fetching data or lazy-loading data.  Lazy-loading is handled completely transparently by Cayenne and no special effort is required by the developer to make it work.
* Optimistic locking support against real data instead of version number columns to ensure data integrity.  When using a version number, it is possible an outside source (such as a database administrator or another application) updated a value and neglected to increment the version number.  Cayenne can detect real data changes and prevent overwriting them by optimistically locking on the actual data.
* Support for multiple databases, including accessing multiple databases (even if the databases are from different vendors) at the same time.
* Remote Object Persistence (ROP) for client/server operation.
* XML serialization.

## <a name="cbe">Cayenne by Example</a>

Cayenne by Example illustrates some of the major features of the Cayenne framework through small-scale examples.  The goal of this book isn't to illustrate every possible aspect of Cayenne, but to illustrate the most common features in order to bootstrap someone into effectively using Cayenne.

Before the examples, however, is an introduction to Cayenne Modeler, which is the primary tool for modeling your database and mapping Java classes to database entities.  Following the chapter on Cayenne Modeler is an introduction into Cayenne contexts and then the examples themselves.

### Source Code

In addition to this book, there is source code (complete Maven projects) for each example.  The source code for these examples is available at GitHub.  If you discover an error in the examples or this book and have a GitHub account, please open an issue so it can be fixed.

To actually run the code you'll need to download and install the following if you don't already have them:

* Java 1.6, although Java 1.5 is probably fine (Cayenne targets 1.5 and above).  [required]
* Maven 2.2.1 or higher.  [required]
* Cayenne 3.0.2 if you want to run the platform-specific Windows or OS X version of Cayenne Modeler.  [optional]  (You'll be able to run Cayenne Modeler generically through Maven, regardless.)
Eclipse with the Maven plugin installed if you wish to edit/run the examples within Eclipse. Once you have downloaded the examples, import them into Eclipse using the Maven Import option.  [optional]

That's it.  Maven will download everything else you need, such as the Cayenne dependencies and the H2 Database.