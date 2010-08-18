/*
 * Copyright 2010 Paul Gearon.
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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.IndexedGraph;
import org.mulgara.mrg.ObjectNode;
import org.mulgara.mrg.PredicateNode;
import org.mulgara.mrg.SubjectNode;
import org.mulgara.mrg.Triple;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.parser.GraphParser;
import org.mulgara.mrg.vocab.FOAF;
import org.mulgara.mrg.vocab.RDF;

import junit.framework.TestCase;

public abstract class WriterTest extends TestCase {

  static final boolean debugViewing = true;

  public WriterTest() {
    super();
  }

  public WriterTest(String name) {
    super(name);
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testSimpleGraph() throws Exception {
    IndexedGraph g = new IndexedGraph();
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:bleah"));
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:ccc"));
    g.insert(new Uri("foo:bar"), new Uri("foo:ddd"), new Uri("foo:eee"));
    g.insert(new Uri("foo:bar"), new Uri("foo:fff"), new Uri("foo:ggg"));
    g.insert(new Uri("fred:aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("fred:aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("fred:bbb"), new Uri("foo:ddd"), new Uri("fred:ccc"));
    g.insert(new Uri("http://fred.org/test#aaa"), new Uri("foo:baz"), new Bnode("xxx"));
    g.insert(new Bnode("xxx"), new Uri("foo:ddd"), new Uri("fred:eee"));
    g.insert(new Bnode("xxx"), new Uri("foo:fff"), new Uri("fred:ggg"));
    g.insert(new Uri("fred:aaa"), RDF.TYPE, FOAF.PERSON);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GraphWriter writer = newWriter(g, null);
    writer.writeTo(out);
    if (debugViewing) {
      System.out.println("##########################");
      System.out.println(out.toString("UTF-8"));
    }
    Graph g2 = newParser(out.toString("UTF-8")).getGraph();
    assertEquals(g.size(), g2.size());
    for (Triple t: g2.getTriples()) {
      SubjectNode s = t.getSubject();
      PredicateNode p = t.getPredicate();
      ObjectNode o = t.getObject();
      if (s instanceof Bnode) s = null;
      if (o instanceof Bnode) o = null;
      assertTrue("Graph does not contain: " + t, g.match(s, p, o).hasNext());
    }
  }

  public void testNSGraph() throws Exception {
    IndexedGraph g = new IndexedGraph();
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:bleah"));
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:ccc"));
    g.insert(new Uri("foo:bar"), new Uri("foo:ddd"), new Uri("foo:eee"));
    g.insert(new Uri("foo:bar"), new Uri("foo:fff"), new Uri("foo:ggg"));
    g.insert(new Uri("fred:aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("fred:aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("fred:bbb"), new Uri("foo:ddd"), new Uri("fred:ccc"));
    g.insert(new Uri("http://fred.org/test#aaa"), new Uri("foo:baz"), new Bnode("xxx"));
    g.insert(new Bnode("xxx"), new Uri("foo:ddd"), new Uri("fred:eee"));
    g.insert(new Bnode("xxx"), new Uri("foo:fff"), new Uri("fred:ggg"));
    g.insert(new Uri("fred:aaa"), RDF.TYPE, FOAF.PERSON);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    export(g, out, null);
    if (debugViewing) {
      System.out.println("***************************");
      System.out.println(out.toString("UTF-8"));
    }
    Graph g2 = newParser(out.toString("UTF-8")).getGraph();
    assertEquals(g.size(), g2.size());
    for (Triple t: g2.getTriples()) {
      SubjectNode s = t.getSubject();
      PredicateNode p = t.getPredicate();
      ObjectNode o = t.getObject();
      if (s instanceof Bnode) s = null;
      if (o instanceof Bnode) o = null;
      assertTrue("Graph does not contain: " + t, g.match(s, p, o).hasNext());
    }
  }

  public void testBaseGraph() throws Exception {
    IndexedGraph g = new IndexedGraph();
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:bleah"));
    g.insert(new Uri("foo:bar"), new Uri("foo:baz"), new Uri("foo:ccc"));
    g.insert(new Uri("foo:bar"), new Uri("foo:ddd"), new Uri("foo:eee"));
    g.insert(new Uri("foo:bar"), new Uri("foo:fff"), new Uri("foo:ggg"));
    g.insert(new Uri("http://fred.org/test#aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("http://fred.org/test#aaa"), new Uri("foo:baz"), new Bnode());
    g.insert(new Uri("http://fred.org/test#bbb"), new Uri("foo:ddd"), new Uri("http://fred.org/test#ccc"));
    g.insert(new Uri("http://fred.org/test#aaa"), new Uri("foo:baz"), new Bnode("xxx"));
    g.insert(new Bnode("xxx"), new Uri("foo:ddd"), new Uri("http://fred.org/test#eee"));
    g.insert(new Bnode("xxx"), new Uri("foo:fff"), new Uri("http://fred.org/test#ggg"));
    g.insert(new Uri("http://fred.org/test#aaa"), RDF.TYPE, FOAF.PERSON);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    export(g, out, URI.create("http://fred.org/"));
    if (debugViewing) {
      System.out.println("++++++++++++++++++++++++++++");
      System.out.println(out.toString("UTF-8"));
    }
    Graph g2 = newParser(out.toString("UTF-8")).getGraph();
    assertEquals(g.size(), g2.size());
    for (Triple t: g2.getTriples()) {
      SubjectNode s = t.getSubject();
      PredicateNode p = t.getPredicate();
      ObjectNode o = t.getObject();
      if (s instanceof Bnode) s = null;
      if (o instanceof Bnode) o = null;
      assertTrue("Graph does not contain: " + t, g.match(s, p, o).hasNext());
    }
  }

  public void testStrangeNamespace() throws Exception {
    IndexedGraph g = new IndexedGraph();
    g.insert(new Uri("http://dbpedia.org/resource/Owen_Gingerich"), new Uri("http://www.stnews.org/guide.php?guide=Intelligent%20ref"), new Uri("http://www.stnews.org/guide.php?guide=Intelligent%20Design"));
    g.insert(new Uri("http://mpii.de/yago/resource/Owen_Gingerich"), new Uri("http://www.w3.org/2002/07/owl#sameAs"), new Uri("http://dbpedia.org/resource/Owen_Gingerich"));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    export(g, out, URI.create("http://dbpedia.org.org/"));
    if (debugViewing) {
      System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
      System.out.println(out.toString("UTF-8"));
    }
    Graph g2 = newParser(out.toString("UTF-8")).getGraph();
    assertEquals(g.size(), g2.size());
    for (Triple t: g2.getTriples()) {
      SubjectNode s = t.getSubject();
      PredicateNode p = t.getPredicate();
      ObjectNode o = t.getObject();
      if (s instanceof Bnode) s = null;
      if (o instanceof Bnode) o = null;
      assertTrue("Graph does not contain: " + t, g.match(s, p, o).hasNext());
    }
  }

  /**
   * Create a new graph writer.
   * @param g The graph to be written.
   * @param base The base URI to write the graph to.
   * @return A new graph writer.
   */
  abstract GraphWriter newWriter(Graph g, URI base);

  /**
   * Creates a parser for the tested output type.
   * @param s A string to be parsed
   * @return A graph parser.
   */
  abstract GraphParser newParser(String s) throws Exception;

  /**
   * Asks a graph to perform an export, using the tested output type.
   * @param g The graph to export.
   * @param out The output stream to write to.
   * @param base The base URI for the export.
   */
  abstract void export(Graph g, OutputStream out, URI base) throws Exception;
}