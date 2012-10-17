---
layout: page
title: Cayenne by Example - Lifecycle Events
---

# Lifecycle Events

Cayenne supports lifecycle events.  There are two different types of handlers for these events for which you can register: callbacks and listeners.  A callback handler is for Cayenne objects and each event will only be sent to the *specific* Cayenne object that registered for the event and had a lifecycle change.  A listener handler receives events for *all* Cayenne objects when you register to be notified of lifecycle changes.

Event            | Trigger
---------------- | --------------
PostAdd          | When `newObject` is called upon an `ObjectContext` to create and register a new object, but after the `ObjectId` is created and the `ObjectContext` is set.
PrePersist       | When `commitChanges` or `commitChangesToParent` is called upon an `ObjectContext`, but *prior to actually committing to the database* and *prior to any `validateFor*` methods* are called.  **NOTE: This is for NEW objects.**
PreUpdate        | When `commitChanges` or `commitChangesToParent` is called upon an `ObjectContext`, but *prior to actually committing to the database* and *prior to any `validateFor*` methods* are called.  **NOTE: This is for UPDATED objects.**
PreRemove        | When `deleteObject` is called upon an `ObjectContext`, but *prior to actually deleting from the database*.  Also includes all objects that will be deleted as a result of CASCADE delete rule.
PostPersist      | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a new object has been sent to the database*.
PostUpdate       | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a modified object has been sent to the database*.
PostRemove       | When `commitChanges` is called upon an `ObjectContext`, but *after the commit of a deleted object  has been sent to the database*.
PostLoad         | &rarr; When `commitChanges` is called upon an `ObjectContext` and *after the object is fetched*.<br/>&rarr; When `rollbackChanges` is called upon an `ObjectContext` and *after the object is reverted*.<br/>&rarr; Any time a faulted object is resolved (such as a relationship being fetched).
