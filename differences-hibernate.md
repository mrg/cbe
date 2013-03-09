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

Hibernate will throw a LazyInitializationException when you attempt to fault a record into memory after Hibernate's session has closed.  This typically happens in web applications between two-or-more request/response cycles from/to the user's browser.  Cayenne will automatically open a database connection and fault objects into memory on-demand and does not suffer from this limitation.

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
