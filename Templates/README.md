Custom Templates
================

This directory contains example custom templates, `superclass.vm` and `subclass.vm`, for Cayenne superclasses and subclasses, plus Maven integration (in the `pom.xml`) to generate these Cayenne classes.  To generate the classes using Maven, you can run from the command-line with the Cayenne `cgen` goal whenever you update your model:

    $ mvn cayenne:cgen

The example code is entirely based upon [BasicInserts](https://github.com/mrg/cbe/tree/master/InsertingObjects/BasicInserts) (#1).  The only difference is in the generated classes.

See [Custom Templates](http://mrg.github.com/cbe/custom-templates.html) for more details, including how to generate the classes from within Cayenne Modeler.
