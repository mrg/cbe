---
layout: page
title: Cayenne by Example - Differences with Hibernate
heading: Differences with Hibernate
breadcrumb:
  - name: Cayenne By Example
    link: ..
  - name: Differences with Hibernate
---

Stubbing out -- needs more details.

## No LIE

If an object that has a lazily loaded relationship calls that relationship, Hibernate requires the DB session to be open. If the session is not open, Hibernate will throw a Lazy Initialization Exception. The Hibernate community is not in agreement on how to handle a Lazy Loading. The two main camps are to either keep the transaction open until all after the relationship in question is loaded, or write a custom getter for the lazy loaded relationship at the DAO level. Keeping the session open forces the the view layer to deal with data access, defeating the purpose of a segregated data access layer. Loading the relationships with DAO methods is cumbersome and hard to maintain.  

Cayenne lazily loads all relationships by default. When a lazily loaded relationship is fetched and there is no database session, Cayenne opens a new session and preforms the query. Prefetching is available for cases in which eager loading is desirable. Cayenne keeps the segregation of the data access and view because the super-class of object that is being asked for the relationship handles the data interaction, similar to the custom getter case in Hibernate, without the need for extra methods/maintenance. 

## No TOE

Cayenne doesn't have a TransientObjectException.

## No proxies

Cayenne doesn't create proxies.

## No need for Spring to do Transaction Management

Cayenne's contexts (DataContext, ObjectContext, etc) provide transaction management.

## No need for DAOs

In Hibernate, it is common to create DAOs (Data Access Objects) to insert/update/delete objects.  This pattern doesn't fit well with Cayenne's context management.

## POJO vs OO

Hibernate uses the POJO (Plain Old Java Object) approach while Cayenne uses OO (Object-Oriented classes).

## Cayenne Modeler

Cayenne includes a GUI database modeler as part of the standard distribution, which is actively used and maintained by the framework developers.  This GUI makes it much easier for developers to get up-to-speed modeling the database/Java layers.
