---
layout: page
title: Cayenne by Example - Lifecycle Events
---

# Lifecycle Events

Cayenne supports lifecycle events for DataObjects interacting with their context during creation, loading, saving, or deleting.   These lifecycle events give you the opportunity to inject handlers into the lifecycle of your data objects.  Lifecycle events are typically defined in Cayenne Modeler, but can also be defined programmatically.

There are two different types of handlers you can create:

1. Callbacks
2. Listeners

A *callback* is for individual DataObjects and each event will only be sent to the *specific* DataObject that is registered for the event and had a lifecycle event change.

A *listener* receives events for *all* DataObjects when you register to be notified of lifecycle changes.  You can register listeners to receive events for all DataObjects of a single class or to receive events for all DataObjects.

Both callbacks and listeners allow multiple handlers to be registered for the same event.

The supported lifecycle events and description of what triggers them is summarized in the following table.

Event            | Trigger
---------------- | --------------
PostAdd          | When `newObject` is called upon an `ObjectContext` to create and register a new object, but after the `ObjectId` is created and the `ObjectContext` is set.  This is an excellent place to initialize a `DataObject` via a callback handler.
PrePersist       | When `commitChanges` or `commitChangesToParent` is called upon an `ObjectContext`, but *prior to actually committing to the database* and *prior to any `validateFor*` methods* are called.  **NOTE: This is for NEW objects.**  This is an excellent place to set a "creation date" attribute on objects via a callback handler.
PreUpdate        | When `commitChanges` or `commitChangesToParent` is called upon an `ObjectContext`, but *prior to actually committing to the database* and *prior to any `validateFor*` methods* are called.  **NOTE: This is for UPDATED objects.**  This is an excellent place to set a "modification date" attribute on objects via a callback handler.
PreRemove        | When `deleteObject` is called upon an `ObjectContext`, but *prior to actually deleting from the database*.  Also includes all objects that will be deleted as a result of CASCADE delete rule.
PostPersist      | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a new object has been sent to the database*.
PostUpdate       | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a modified object has been sent to the database*.
PostRemove       | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a deleted object  has been sent to the database*.
PostLoad         | &rarr; When `commitChanges` is called upon an `ObjectContext` and *after the object is fetched*.<br/>&rarr; When `rollbackChanges` is called upon an `ObjectContext` and *after the object is reverted*.<br/>&rarr; Any time a faulted object is resolved (such as a relationship being fetched).

See [callbacks](inserting-objects/callbacks.html) and [listeners](inserting-objects/listeners.html) for examples on how to use lifecycle events.


# Configuring Handlers via Cayenne Model

TBD

# Configuring Handlers Programmatically

TBD
