<!---
The sidebar is included by the page layout.  Because included
files are not processed through Markdown syntax, the content
is captured in the 'sidebar' variable, then ran through the
'markdownify' plugin at the bottom to translate Markdown to
HTML in the included layout.
-->

{% capture sidebar %}

# Contents

* [Introduction](index.html)
  * [Apache Cayenne](index.html#cayenne)
  * [Cayenne by Example](index.html#cbe)
    * [Source Code](index.html#source)
* [Obtaining Cayenne](obtaining-cayenne.html)
  * [Downloading Cayenne](obtaining-cayenne.html#downloading)
  * [Using Maven](obtaining-cayenne.html#maven)
* [Cayenne Modeler](cayenne-modeler.html)
  * [Startup](cayenne-modeler.html#startup)
  * [Main Window](cayenne-modeler.html#main_window)
    * [Main Toolbar](cayenne-modeler.html#main_toolbar)
    * [Navigation Pane](cayenne-modeler.html#navigation_pane)
      * [DataDomain, DataMap, DataNode](cayenne-modeler.html#domain_map_node)
    * Detail Pane
  * DataDomain Editor
    * DataDomain Configuration
    * Cache Configuration
  * DataMap Editor
    * DbEntities
      * Entity
      * Attributes
      * Relationships
    * ObjEntities
      * Entity
      * Attributes
      * Relationships
      * Callbacks
      * Listeners
  * DataNode Editor
    * Main
    * Adapter
    * Password Encoder
  * Generate Classes
  * Reverse Engineer Schema
  * Preferences
    * General
    * Local Data Sources
    * Classpath
    * Templates
* [Cayenne Contexts](cayenne-contexts.html)
* [Examples](examples.html)
* [Inserting Objects](inserting-objects.html)
  * [Basic Inserts](basic-inserts.html)
    * [Basic Inserts 1](basic-inserts.html#one)
    * [Basic Inserts 2](basic-inserts.html#two)
    * [Basic Inserts 3](basic-inserts.html#three)
  * Enumerations
  * To-One Relationships
  * To-Many Relationships
  * Many-to-Many Relationships
  * Initialize New Objects
  * BLOBs
  * Validation
  * To-Dependent PK
  * Meaningful PK
  * Flattened Relationships
  * Callbacks and Listeners
* Fetching Objects
  * Select Queries
  * SQL Template Queries
  * Paginated Queries
  * Named Queries
  * Relationship Query
  * Relationships
  * Order By
  * Fetch Limits
  * Invalidate and Refetch
  * Prefetching and Lazy Loading
  * Raw Row Fetching
* Updating Objects
  * Fetch and Update
  * Optimistic Locking
  * Child Contexts
* Deleting Objects
  * Deleting Single Object
  * Deleting Multiple Objects
  * Delete Rules
  * Query Chain
* Web Application
* [Differences with Hibernate](differences-hibernate.html)
* References, Resources, and Credits

{% endcapture %}

{{ sidebar | markdownify }}
