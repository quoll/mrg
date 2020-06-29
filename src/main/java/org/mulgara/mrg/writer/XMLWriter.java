/*
 * Copyright 2010 Paula Gearon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mulgara.mrg.writer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.lang.String.format;

import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.Literal;
import org.mulgara.mrg.ObjectNode;
import org.mulgara.mrg.PredicateNode;
import org.mulgara.mrg.PropertyValue;
import org.mulgara.mrg.SubjectNode;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.vocab.RDF;
import org.mulgara.util.Strings;
import static org.mulgara.util.Strings.indent;

/**
 * Creates a writer specifically for N3 output.
 */
public class XMLWriter extends AbstractGraphWriter implements GraphWriter {

  /** A set of subjects that have already been written */
  private Set<SubjectNode> seen = new HashSet<SubjectNode>();

  /**
   * Creates a graph writer.
   * @param graph The graph to be written by this writer.
   */
  @SuppressWarnings("unchecked")
  public XMLWriter(Graph graph, URI base) {
    this(graph, base, (Map<String,URI>)Collections.EMPTY_MAP);
  }

  /**
   * Creates a graph writer.
   * @param graph The graph to be written by this writer.
   * @param ns The namespaces to use while emitting this graph.
   */
  public XMLWriter(Graph graph, URI base, Map<String,URI> ns) {
    super(graph, base);
    this.scanNamespaces();
    for (Map.Entry<String,URI> e: ns.entrySet()) rns.put(e.getValue().toString(), e.getKey());
  }

  /**
   * Scans the graph for valid namespaces. Adds in the RDF namespace by default, even
   * if this is not explicitly used in the graph.
   * @see org.mulgara.mrg.writer.AbstractGraphWriter#scanNamespaces()
   */
  public void scanNamespaces() {
    rns.put(org.mulgara.mrg.vocab.uri.RDF.BASE, org.mulgara.mrg.vocab.uri.RDF.PREFIX);
    super.scanNamespaces();
  }

  /**
   * @see org.mulgara.mrg.writer.GraphWriter#writeTo(java.io.OutputStream)
   */
  public void writeTo(OutputStream out) {
    PrintStream o = new PrintStream(out);
    o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

    emitEntities(o);

    emitRdfStart(o);
    for (SubjectNode s: graph.getSubjects()) {
      if (!seen.contains(s)) emitSubject(o, s, 2);
    }
    emitRdfEnd(o);
  }

  void emitEntities(PrintStream o) {
    if (!rns.isEmpty()) {
      o.print("<!DOCTYPE rdf:RDF [");
      for (Map.Entry<String,String> n: rns.entrySet()) {
        String ns = n.getKey();
        if (!isInvalidEntity(ns)) {
          o.print(format("\n  <!ENTITY %s '%s'>", n.getValue(), n.getKey()));
        }
      }
      o.println("]>\n");
    }
  }

  void emitRdfStart(PrintStream o) {
    o.print("<rdf:RDF");
    boolean first = true;
    for (Map.Entry<String,String> n: rns.entrySet()) {
      String indent;
      if (first) {
        first = false;
        indent = " ";
      } else indent = "\n" + indent(4) + " ";
      String ns = n.getKey();
      if (isInvalidEntity(ns)) {
        o.print(format("%sxmlns:%s=\"%s\"", indent, n.getValue(), n.getKey()));
      } else {
        o.print(format("%sxmlns:%s=\"&%s;\"", indent, n.getValue(), n.getValue()));
      }
    }
    if (base != null) {
      String indent = first ? " " : "\n         ";
      o.print(format("%sxml:base=\"%s\"", indent, base));
    }
    o.println(">\n");
  }

  void emitRdfEnd(PrintStream o) {
    o.println("</rdf:RDF>");
  }

  /**
   * Writes a subject and all of its properties. Record all written subjects.
   * If already seen, then just write the node and exit without writing the properties.
   * @param o The print stream to send output to
   * @param s The subject to emit.
   * @param indent The indent level.
   */
  void emitSubject(PrintStream o, SubjectNode s, int i) {
    boolean alreadySeen  = seen.contains(s);
    seen.add(s);

    String id;
    if (s.getTypeId() == Uri.TYPE_ID) {
      id = format("rdf:about=\"%s\"", formatForAbout((Uri)s));
    } else {
      assert s instanceof Bnode;
      id = format("rdf:nodeID=\"%s\"", ((Bnode)s).getLabel());
    }

    o.print(indent(i));
    List<PropertyValue> props = graph.getProperties(s);
    if (props.isEmpty() || alreadySeen) {
      o.println(format("<rdf:Description %s/>", id));
    } else {
      o.println(format("<rdf:Description %s>", id));
      for (PropertyValue pv: props) {
        PredicateNode property = pv.getProperty();
        ObjectNode value = pv.getValue();
        // all variations get indented this much
        o.print(indent(i + 1));
        // non-Literal objects
        if (value.getTypeId() != Literal.TYPE_ID) {
          emitPropVal(o, i + 1, (Uri)property, value);
        } else {
          // The value is a literal
          emitPropVal(o, (Uri)property, (Literal)value);
        }
      }
      o.print(indent(i));
      o.println("</rdf:Description>");
    }
  }

  /**
   * Prints a single property/value pair where the value is a Uri or a Bnode.
   * @param o The print stream to use as output.
   * @param i The indent amount.
   * @param property The property.
   * @param value The literal value for the property.
   */
  protected void emitPropVal(PrintStream o, int i, Uri property, ObjectNode value) {
    List<PropertyValue> objProps = graph.getProperties((SubjectNode)value);
    if (!objProps.isEmpty()) {
      // nest as a new subject
      o.println(format("<%s>", formatForTag((Uri)property)));
      emitSubject(o, (SubjectNode)value, i + 1);
      o.print(indent(i));
      o.println(format("</%s>", formatForTag((Uri)property)));
      return;
    }
    // inline a single property-value
    if (value.getTypeId() == Uri.TYPE_ID) {
      o.println(format("<%s rdf:resource=\"%s\"/>", formatForTag((Uri)property), formatForAbout((Uri)value)));
    } else {
      o.println(format("<%s rdf:parseType=\"Resource\"/>", formatForTag((Uri)property)));
    }
  }

  /**
   * Prints a single property/value pair where the value is a literal.
   * @param o The print stream to use as output.
   * @param property The property.
   * @param value The literal value for the property.
   */
  protected void emitPropVal(PrintStream o, Uri property, Literal value) {
    String text = Strings.xmlEscape(value.getText());
    String lang = value.getLang();
    URI type = value.getType();
    String prop = formatForTag(property);
    if (lang != null) {
      // language coded literal
      o.println(format("<%s xml:lang=\"%s\">%s</%s>", prop, lang, text, prop));
    } else if (type != null) {
      // typed literal. XML Literals are a special case
      if (type.equals(RDF.XML_LITERAL.getURI())) {
        // should parse for namespaces, but this seems excessive
        o.println(format("<%s rdf:parseType=\"Literal\">%s</%s>", prop, text, prop));
      } else {
        o.println(format("<%s rdf:datatype=\"%s\">%s</%s>", prop, type.toString(), text, prop));
      }
    } else {
      // plain old vanilla literal
      o.println(format("<%s>%s</%s>", prop, text, prop));
    }
  }

  /**
   * Format a URI reference for use in an about=.
   * @param u The URI reference to present.
   * @return A string suitable for about=""
   */
  protected String formatForAbout(Uri u) {
    String str = u.getURI().toString();
    if (base != null && str.startsWith(base)) {
      return esc(str.substring(base.length()));
    }
    String name = str.substring(Strings.startOfName(str));
    String namespace = str.substring(0, str.length() - name.length());
    String prefix = rns.get(namespace);
    return (prefix == null || isInvalidEntity(namespace)) ? esc(str) : new StringBuilder("&").append(esc(prefix)).append(";").append(name).toString();
  }


  /**
   * Format a URI reference for use in an XML tag.
   * @param u The URI reference to present.
   * @return A QName.
   */
  protected String formatForTag(Uri u) {
    String str = u.getURI().toString();
    if (base != null && str.startsWith(base)) {
      return new StringBuilder(":").append(str.substring(base.length())).toString();
    }
    String name = str.substring(Strings.startOfName(str));
    String namespace = str.substring(0, str.length() - name.length());
    String prefix = rns.get(namespace);
    if (prefix == null) throw new Error("Unable to serialize URI of: " + u);
    return new StringBuilder(prefix).append(":").append(name).toString();
  }

  /**
   * Tests a potential namespace to see if it is considered to be OK.
   * @param ns The namespace to test.
   * @return <code>true</code> iff the namespace can be used in this type of document.
   */
  protected boolean testNamespace(String ns) {
    return ns.indexOf('&') == -1;
  }

  /**
   * Tests a potential entity value to see if it can be used.
   * @param e The entity value to test.
   * @return <code>true</code> iff the value can be used as an entity value.
   */
  private static final boolean isInvalidEntity(String e) {
    return (e.indexOf('%') >= 0 || e.indexOf('&') >= 0);
  }

  /**
   * Escapes a string to make it suitable for use in an attribute
   * @param s The string to escape.
   * @return A new string with illegal characters mapped to entities.
   */
  private static final String esc(String s) {
    return s.replace("&", "&amp;");
  }
}
