---
layout: page
title: Cayenne by Example - Obtaining Cayenne
---

# Obtaining Cayenne

There are three main ways to obtain Cayenne and Cayenne Modeler:

1. Download the platform-specific version of Cayenne which provides a .exe version of Cayenne Modeler for Windows users and a .app version for Mac OS X users.  This option provides a better look and feel for Windows and Mac OS X users.
2. Download the cross-platform version and run as a normal Java application.  Linux users should explore this option.  Windows and Mac OS X users can also use this approach, but the look and feel will not be as nice, especially for Mac OS X users.
3. Maven users can specify not only the Cayenne framework JARs as part of their project, but also a Cayenne Modeler plugin and run directly from Maven.  For some users, especially those behind corporate firewalls that block the platform-specific download, but allow Apache Maven repository downloads, this is probably the best option.  This approach essentially runs the cross-platform version as a Maven plugin.

Note: It is also quite common to integrate options 1 and 3.  This gives Windows and Mac OS X users the advantage of a platform-specific Cayenne Modeler while using Maven for the actual build.

## <a name="downloading">Downloading Cayenne</a>

For options 1 and 2, download the appropriate archive from the [Cayenne](http://cayenne.apache.org/) website.

The platform-specific archives contain the Cayenne framework JARs and the platform-specific Cayenne Modeler.  The cross-platform archive contains the Cayenne framework JARs and a platform-independent version of Cayenne Modeler (also a JAR).

For the platform-specific version, extract the contents and install the .exe (Windows) or the .app (Mac OS X) version of Cayenne Modeler somewhere appropriate for your system.

If not using Maven, copy the Cayenne framework JARs into your Eclipse (or similar) project.

For option 2, you can run Cayenne Modeler from the command-line using:

`java -jar cayenne_dir/bin/CayenneModeler.jar`

## <a name="maven">Using Maven</a>

For Maven users, you need to specify the Cayenne framework and potentially the Cayenne Modeler plugin in the POM.  To run Cayenne Modeler from Maven instead of using a platform-specific version, use:

`mvn cayenne-modeler:run`

A snippet of a `pom.xml` file showing one possible configuration specifying the Cayenne dependency and the Cayenne Modeler plugin:

{% highlight xml %}
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
...
  <properties>
    <cayenne.version>3.0.2</cayenne.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.3.1</version>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
        </configuration>
      </plugin>

      <!-- Needed to run Cayenne Modeler as a Maven target. -->
      <plugin>
        <groupId>org.apache.cayenne.plugins</groupId>
        <artifactId>maven-cayenne-modeler-plugin</artifactId>
        <version>${cayenne.version}</version>
        <configuration>
          <modelFile>src/main/resources/cayenne.xml</modelFile>
        </configuration>
      </plugin>
    </plugins>
  </build>

  <dependencies>
...
    <dependency>
      <groupId>org.apache.cayenne</groupId>
      <artifactId>cayenne-server</artifactId>
      <version>${cayenne.version}</version>
    </dependency>
...
   </dependencies>
</project>
{% endhighlight %}

It is convenient to specify the Cayenne version number in the `<properties>` section.  The plugin and framework dependency can then reference the variable making it easier to change.

The `<plugins>` section specifies the Cayenne Modeler plugin and the `<dependencies>` section specifies the Cayenne dependency (`cayenne-server` is the most frequently used).

