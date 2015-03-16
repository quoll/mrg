How to load up data into a graph.

# Introduction #

The primary use of the Graph API is for ease of reading the data from a graph. To get the graph in the first place the data is either inserted manually (see AppendableGraph and WritableGraph), built from a set of Triples, or loaded from a graph serialization.

# Construction from Triples #

Most times it is not necessary to choose a Graph implementation yourself. Instead, a graph will be returned from some operation that reads or creates graphs, and the graph will be accessed using the [read-only Graph API](GraphReading.md).

If you do ever want to create a graph manually, then each implementation supports two constructors: a default constructor, and a constructor that accepts a Collection of Triples. The default constructor will create an empty graph, at which point data will need to be added using either the AppendableGraph or WritableGraph APIs. The Collection of Triple constructor will create a graph which has every triple from the collection inserted.

The current implementations of Graph are:
  * **GraphImpl**:  a simple implementation based on nested hash maps.
  * **IndexedGraph**:  like GraphImpl, this graph uses nested hash maps. It also indexes the data in several ways, so all querying operations can return in constant time.
  * **PackedGraphImpl**:  a simple implementation using tree maps. This uses much less memory than GraphImpl, but operations are logarithmic time, _at best_. Some queries are executed in linear time.
  * **PackedIndexedGraph**:  similar to IndexedGraph, but using tree maps. The result is a much smaller memory footprint than IndexedGraph with _all_ queries executing in logarithmic time (as opposed to PackedGraphImpl where some queries are in linear time).

# Loading #

Graphs are loaded using a one of the parsers. For the moment, parsers will always return an instance of GraphImpl containing the parsed data.

At this stage, the Graph API only supports Turtle ([N3](http://www.w3.org/DesignIssues/Notation3.html)) and [RDF/XML](http://www.w3.org/TR/rdf-syntax-grammar/) files. Turtle is the RDF subset of N3, and also supports NTriples (.nt) files. The parsers for these formats are:
  * org.mulgara.rdf.parser.N3GraphParser
  * org.mulgara.rdf.parser.XMLGraphParser

To parse a graph in one of these formats, just create an instance of one of these files, providing either a string or a Java InputStream. For instance, to load Turtle text:

```
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.parser.*;
static import org.mulgara.mrg.vocab.FOAF;


String text = "@prefix foaf:       <http://xmlns.com/foaf/0.1/> .\n" +
    "@prefix eg:         <http://biometrics.example/ns#> .\n" +
    "@prefix xsd:        <http://www.w3.org/2001/XMLSchema#> .\n" +
    "_:a  foaf:name       "Alice".\n" +
    "_:a  eg:shoeSize     "9.5"^^xsd:float .\n" +
    "_:b  foaf:name       "Bob".\n" +
    "_:b  eg:shoeSize     "42"^^xsd:integer .\n";

GraphParser parser = new N3GraphParser(text);
Graph graph = parser.getGraph();

// see what we read
Node bob = graph.getSubjects(FOAF.NAME, "Bob").get(0);
Node shoeSize = graph.getValue(bob, "http://biometrics.example/ns#shoeSize");
System.out.println("Bob's shoe size is: " + shoeSize);
```


To load RDF/XML from a stream:

```
import org.mulgara.rdf.Graph;
import org.mulgara.rdf.parser.*;
import org.mulgara.rdf.vocab.*;

URL rdfs = URL.create("http://www.w3.org/2000/01/rdf-schema#");
Graph graph = new XMLGraphParser(rdfs.openStream()).getGraph();

// read the commend on rdfs:seeAlso
Literal comment = (Literal)graph.getValue(RDFS.SEE_ALSO, RDFS.COMMENT);
System.out.println("comment on rdfs:seeAlso is: " + comment.getText());
```