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
* [Obtaining Cayenne](obtaining-cayenne.html)
  * [Downloading Cayenne](obtaining-cayenne.html#downloading)
  * [Using Maven](obtaining-cayenne.html#maven)
* [Cayenne Modeler](cayenne-modeler.html)
  * Startup
  * Main Window
  * DataDomain Editor
  * DataMap Editor
  * DataNode Editor
  * Generate Classes
  * Reverse Engineer Schema
  * Preferences
* [Cayenne Contexts](cayenne-contexts.html)
* [Examples](examples.html)
* Inserting Objects
  * Basic Inserts
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
* Differences with Hibernate
* References, Resources, and Credits
  
{% endcapture %}

{{ sidebar | markdownify }}
