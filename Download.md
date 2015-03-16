If using [Maven](http://maven.apache.org/) then include the following in your `pom.xml` file:
```
  <dependencies>
    ...
    <dependency>
      <groupId>org.mulgara</groupId>
      <artifactId>mrg</artifactId>
      <version>0.7</version>
    </dependency>
     ...
  </dependencies>
```
(At the time of writing, the latest version was 0.7. If there is a more recent release, then update this number)

---

MRG can also be downloaded directly from the [Downloads page](http://code.google.com/p/mrg/downloads/list), or as [source code](http://code.google.com/p/mrg/source/checkout).


If downloading the binary JAR file, then you will also need to get the following JARs:

  * [Joda Time](http://sourceforge.net/projects/joda-time/files/joda-time/) - ([Apache 2.0 License](http://joda-time.sourceforge.net/license.html))
  * [IRI](http://jena.sourceforge.net/iri/index.html) - ([Jena License](http://jena.sourceforge.net/iri/license.html))
  * [ICU4j](http://site.icu-project.org/download) - ([ICU Licence](http://source.icu-project.org/repos/icu/icu/trunk/license.html))
  * [Xerces2 Java](http://xerces.apache.org/mirrors.cgi#binary) - ([Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html))

All except the Joda-time files are only required if you are parsing RDF/XML or N3 files, and can be skipped otherwise. Ultimately, I'd like to get rid of these dependencies.

Joda-time is required for dealing with xsd:dateTime literals.