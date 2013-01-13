<!---
The sidebar is included by the page layout.  Because included
files are not processed through Markdown syntax, the content
is captured in the 'sidebar' variable, then ran through the
'markdownify' plugin at the bottom to translate Markdown to
HTML in the included layout.
-->

{% capture sidebar %}

# Contents

* [Introduction]({{ root }}index.html)
  * [Apache Cayenne]({{ root }}index.html#cayenne)
  * [Cayenne by Example]({{ root }}index.html#cbe)
    * [Source Code]({{ root }}index.html#source)
    * [Running the Examples]({{ root }}index.html#running)
    * [License]({{ root }}index.html#license)
* [Obtaining Cayenne]({{ root }}obtaining-cayenne.html)
  * [Downloading Cayenne]({{ root }}obtaining-cayenne.html#downloading)
  * [Using Maven]({{ root }}obtaining-cayenne.html#maven)
* [Cayenne Modeler]({{ root }}cayenne-modeler.html)
  * [Startup]({{ root }}cayenne-modeler.html#startup)
  * [Main Window]({{ root }}cayenne-modeler.html#main_window)
    * [Main Toolbar]({{ root }}cayenne-modeler.html#main_toolbar)
    * [Navigation Pane]({{ root }}cayenne-modeler.html#navigation_pane)
      * [DataDomain, DataMap, DataNode]({{ root }}cayenne-modeler.html#domain_map_node)
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
* [Cayenne Contexts]({{ root }}cayenne-contexts.html)
* [Lifecycle Events]({{ root }}lifecycle-events.html)
* [Examples]({{ root }}examples.html)
* [Inserting Objects]({{ root }}inserting-objects)
  * [Basic Inserts]({{ root }}inserting-objects/basic-inserts.html)
    * [Basic Inserts 1]({{ root }}inserting-objects/basic-inserts.html#one)
    * [Basic Inserts 2]({{ root }}inserting-objects/basic-inserts.html#two)
    * [Basic Inserts 3]({{ root }}inserting-objects/basic-inserts.html#three)
  * [Enumerations]({{ root }}inserting-objects/enumerations.html)
  * To-One Relationships
  * To-Many Relationships
  * Many-to-Many Relationships
  * Initialize New Objects
  * BLOBs
  * Validation
  * To-Dependent PK
  * Meaningful PK
  * Flattened Relationships
  * [Callbacks]({{ root }}inserting-objects/callbacks.html)
  * [Listeners]({{ root }}inserting-objects/listeners.html)
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
* [Unit Testing]({{ root }}unit-testing.html)
* Web Applications
* [Differences with Hibernate]({{ root }}differences-hibernate.html)
* [Credits and Resources]({{ root }}credits-resources.html)

{% endcapture %}

{{ sidebar | markdownify }}
