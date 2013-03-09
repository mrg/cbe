---
layout: page
title: Cayenne by Example - Custom Templates
heading: Custom Templates
breadcrumb:
  - name: Cayenne By Example
    link: ..
  - name: Custom Templates
---

Cayenne includes default [Velocity](http://velocity.apache.org/)-based templates used to generate your Cayenne classes from the model.  These default templates are sufficient for most uses, but you can create custom templates if desired.

This [GitHub directory](https://github.com/mrg/cbe/tree/master/Templates) contains example custom templates, `superclass.vm` and `subclass.vm`, for Cayenne superclasses and subclasses.  These are based upon the original templates, but have the following changes:

* A stronger warning about making changes that will get overwritten (superclass).
* Added a `serialVersionUID` to eliminate warnings (superclass and subclass).
* Added comments for different sections (superclass and subclass).
* Added `RELATIONSHIP_` in the relationship property constants (superclass).
* Changed setters/getters to use the property constants instead of duplicating the strings (superclass).

To use these (or other) custom templates, put a copy in your project and add them to your Cayenne Modeler preferences under the **Templates** section as "subclass" and "superclass".  (If you use different names, substitute the different names below.)

When generating classes, use the **Advanced** option with the following suggested settings:

**Generation Mode:** Entity and Embeddable generation<br />
**Generator Version:** 1.2<br />
**Subclass Template:** `subclass` (or your different template name)<br />
**Superclass Template:** `superclass` (or your different template name)<br />
**Output Pattern:** *.java<br />
**Make Pairs:** checked<br />
**Overwrite Subclasses:** unchecked<br />
**Use Package Path:** checked<br />
