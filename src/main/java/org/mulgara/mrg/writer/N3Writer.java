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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.Literal;
import org.mulgara.mrg.Node;
import org.mulgara.mrg.ObjectNode;
import org.mulgara.mrg.PredicateNode;
import org.mulgara.mrg.PropertyValue;
import org.mulgara.mrg.SubjectNode;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.vocab.RDF;
import org.mulgara.util.Strings;

/**
 * Creates a writer specifically for N3 output.
 */
public class N3Writer extends AbstractGraphWriter implements GraphWriter {

  private static final String UTF8 = "UTF-8";

  /** A cached copy of the object nodes for the graph */
  private Collection<ObjectNode> objects = null;

  /**
   * Creates a graph writer.
   * @param graph The graph to be written by this writer.
   */
  public N3Writer(Graph graph, URI base) {
    super(graph, base);
  }

  /**
   * Creates a graph writer.
   * @param graph The graph to be written by this writer.
   * @param ns The namespaces to use while emitting this graph.
   */
  public N3Writer(Graph graph, URI base, Map<String, URI> ns) {
    super(graph, base, ns);
  }

  /**
   * @see org.mulgara.mrg.writer.GraphWriter#writeTo(java.io.OutputStream)
   */
  public void writeTo(OutputStream out) {
    PrintStream o;
    try {
      o = new PrintStream(out, false, UTF8);
    } catch (UnsupportedEncodingException e) {
      throw new Error("Unable to load UTF-8 encoding", e);
    }
    if (base != null) o.println(String.format("@base <%s>.", base));
    for (Map.Entry<String,String> n: rns.entrySet()) {
      o.println(String.format("@prefix %s: <%s> .", n.getValue(), n.getKey()));
    }
    o.println();
    for (SubjectNode s: graph.getSubjects()) {
      switch (s.getTypeId()) {
      case Uri.TYPE_ID:
        o.print(formatUri((Uri)s));
        o.print(" ");
        writeProperties(o, graph.getProperties(s));
        o.println(" .");
        break;
      case Bnode.TYPE_ID:
        assert s instanceof Bnode;
        if (graphObjects().contains(s)) {
          // not going to search for cycles, just print this as a raw bNode
          o.print(s);
          o.print(" ");
          writeProperties(o, graph.getProperties(s));
          o.println(" .");
        } else {
          o.print("[");
          writeProperties(o, graph.getProperties(s));
          o.println("] .");
        }
      }
    }
    o.flush();
  }

  /**
   * Prints all property values together, using ; and , separators.
   * @param o The output to print to.
   * @param pvs The property/values to be written.
   */
  protected void writeProperties(PrintStream o, List<PropertyValue> pvs) {
    PredicateNode lastPredicate = null;
    for (PropertyValue pv: pvs) {
      PredicateNode pred = pv.getPredicate();
      ObjectNode obj = pv.getObject();
      if (!pred.equals(lastPredicate)) {
        if (lastPredicate != null) o.print(";\n        ");
        o.print(formatUri(pred) + " ");
      } else {
        if (lastPredicate != null) o.print(",\n                ");
      }
      lastPredicate = pred;
      o.print(format(obj));
    }
  }

  /**
   * Format a URI reference. If it starts with a known prefix, then use that,
   * otherwise it will be written in full form, surrounded by angle brackets.
   * @param u The URI reference to present.
   * @return Either a prefixed QName, or a full URI with bracketing.
   */
  protected String formatUri(Uri u) {
    String str = u.getURI().toString();
    if (base != null && str.startsWith(base)) {
      return new StringBuilder("<").append(str.substring(base.length())).append(">").toString();
    }
    String name = str.substring(Strings.startOfName(str));
    String namespace = str.substring(0, str.length() - name.length());
    String prefix = rns.get(namespace);
    return (prefix != null) ? new StringBuilder(prefix).append(":").append(name).toString()
                            : new StringBuilder("<").append(str).append(">").toString();
  }

  /**
   * Overloads {@link #formatUri(Uri)} to avoid the need to cast.
   * @param p The predicate node to print.
   * @return The formatted Uri reference.
   */
  protected String formatUri(PredicateNode p) {
    if (p.equals(RDF.TYPE)) return "a";
    return formatUri((Uri)p);
  }

  /**
   * Formats a node for printing.
   * @param n The node to format.
   * @return A formatted string for the node.
   */
  protected String format(Node n) {
    switch (n.getTypeId()) {
    case Uri.TYPE_ID: return formatUri((Uri)n);
    case Bnode.TYPE_ID: return n.toString();
    case Literal.TYPE_ID: return formatLiteral((Literal)n);
    }
    throw new Error("Unknown Node type: " + n);
  }

  /**
   * Formats a literal for printing.
   * @param l The literal to be formatted.
   * @return A formatted version of the literal.
   */
  protected String formatLiteral(Literal l) {
    StringBuilder label = new StringBuilder("\"");
    label.append(n3escape(l.getText())).append("\"");
    String lang = l.getLang();
    URI type = l.getType();
    if (lang != null) label.append("@").append(lang);
    if (type != null) label.append("^^").append(type);
    return label.toString();
  }
  
  /**
   * Gets all objects in the graph. This never changes, so only get it once.
   * @return All the objects in the graph.
   */
  private Collection<ObjectNode> graphObjects() {
    if (objects == null) objects = graph.getObjects();
    return objects;
  }


  /** Contains a map of characters to their N3 encodings. */
  private static final Map<Character,String> n3map = new HashMap<Character,String>();

  // initialize the N3 encoding map
  static {
    n3map.put('\n', "\\n");
    n3map.put('\\', "\\\\");
    n3map.put('\'', "\\'");
    n3map.put('"', "\\\"");
    n3map.put('\r', "\\r");
    n3map.put('\t', "\\t");
  }

  /**
   * Performs escaping of text according to the rules in N3.
   * {@link http://www.w3.org/DesignIssues/Notation3}
   * @param s The string to escape.
   * @return The escaped form of the string.
   */
  static String n3escape(String s) {
    if (s.codePointCount(0, s.length()) == s.length()) return simpleN3escape(s);

    StringBuilder b = new StringBuilder(s);
    int i = 0;
    while (i < b.length()) {
      int codePoint = b.codePointAt(i);
      if (codePoint > 0x07FF) {
        String str;
        int inc;
        if (codePoint > 0xFFFF) {
          str = String.format("\\U00%06X", codePoint);
          inc = 10;
        } else {
          str = String.format("\\u%04X", codePoint);
          inc = 6;
        }
        b.replace(i, b.offsetByCodePoints(i, 1), str);
        i += inc;
      } else {
        // codepoint is a single char
        String rep = n3map.get((char)codePoint);
        if (rep != null) {
          b.replace(i, i + 1, rep);
          i += rep.length();
        } else {
          i++;
        }
      }
    }
    return b.toString();
  }

  /**
   * A simplified N3 escape where all the characters are in the BMP and can be represented
   * as a single char.
   * @param s The string to escape.
   * @return An escaped form of the string.
   */
  static String simpleN3escape(String s) {
    StringBuilder b = new StringBuilder(s);
    int i = 0;
    while (i < b.length()) {
      char c = b.charAt(i);
      if ((int)c >= 0x7F) {
        String str = String.format("\\u%04X", (int)c);
        b.replace(i, i + 1, str);
        i += 6;
      } else {
        String rep = n3map.get(c);
        if (rep != null) {
          b.replace(i, i + 1, rep);
          i += rep.length();
        } else {
          i++;
        }
      }
    }
    return b.toString();
  }

}
