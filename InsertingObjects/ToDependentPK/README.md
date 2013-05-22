To Dependent Primarky Key (PK)
==============================

This example shows a one-to-one relationship where the PK of an address record is dependent upon the PK of the person it is related to.

Cayenne will insert the person first (using the database to generate the PK), then use the PK of the person to insert the address (copy the person's PK to the address's PK).

This corresponds to Cayenne Modeler's "To Dep PK" checkbox for DbEntities under the Relationships tab.  The "To Dep PK" checkbox indicates that the database entity which has it checked is the "master" record whose PK value is pushed to the "child" record on the other side of the relationship.  (Open the model to see this setting.)

