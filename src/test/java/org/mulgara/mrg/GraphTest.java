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

package org.mulgara.mrg;

import junit.framework.TestCase;

import java.util.*;

import org.mulgara.mrg.AppendableGraph;
import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.Literal;
import org.mulgara.mrg.Node;
import org.mulgara.mrg.ObjectNode;
import org.mulgara.mrg.PropertyValue;
import org.mulgara.mrg.SubjectNode;
import org.mulgara.mrg.Triple;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.vocab.RDF;

import static org.mulgara.mrg.Graph.X;

/**
 * Unit test for simple App.
 */
public abstract class GraphTest extends TestCase {

  Uri fred = Uri.create("ex:fred");
  Uri barney = Uri.create("ex:barney");
  Uri wilma = Uri.create("ex:wilma");
  Uri betty = Uri.create("ex:betty");
  Uri person = Uri.create("foaf:Person");
  Uri hasName = Uri.create("foaf:hasName");
  Uri knows = Uri.create("foaf:knows");
  Uri hasNeighbors = Uri.create("ex:hasNeighbors");
  Uri type = RDF.TYPE;
  Uri list = RDF.LIST;
  Uri first = RDF.FIRST;
  Uri rest = RDF.REST;
  Uri nil = RDF.NIL;
  Bnode blank1 = new Bnode();

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public GraphTest(String testName) {
    super(testName);
  }


  /**
   * Test that graphs can be created
   */
  public void testCreate() throws Exception {
    List<Triple> empty = Collections.emptyList();
    Graph graph = getGraph(empty);
    assertTrue(graph.isEmpty());
    List<Triple> triples = getTriples();
    for (Triple t: triples) {
      assertTrue(((AppendableGraph)graph).insert(t));
    }
    assertFalse(graph.isEmpty());
    assertEquals(triples.size(), graph.size());
    assertFalse(((AppendableGraph)graph).insert(triples.get(0)));
  }

  /**
   * Test that properties can be read as expected
   */
  public void testProps() throws Exception {
    Graph graph = getGraph(getTriples());
    List<PropertyValue> props = graph.getProperties(fred);
    assertEquals(props.size(), 6);
    props = graph.getProperties(barney);
    assertEquals(props.size(), 2);
    for (PropertyValue prop: props) {
      if (prop.getKey().equals(type)) {
        assertEquals(prop.getValue(), person);
      } else if (prop.getKey().equals(hasName)) {
        assertEquals(prop.getValue(), new Literal("Barney"));
      } else {
        assertTrue("Found unexpected key of: " + prop.getKey() + " [" + prop.getValue() + "]", false);
      }
    }
  }

  /**
   * Test property values
   */
  public void testValues() throws Exception {
    Graph graph = getGraph(getTriples());
    List<ObjectNode> vals = graph.getValues(fred, knows);
    assertEquals(vals.size(), 3);
    Set<Node> known = new HashSet<Node>(vals);
    Set<Node> expected = new HashSet<Node>();
    expected.add(barney);
    expected.add(wilma);
    expected.add(betty);
    assertEquals(known, expected);
    vals = graph.getValues(fred, fred);
    assertTrue(vals.isEmpty());
  }

  /**
   * Test property value
   */
  public void testValue() throws Exception {
    Graph graph = getGraph(getTriples());
    ObjectNode val = graph.getValue(fred, type);
    assertEquals(val, person);
    val = graph.getValue(fred, fred);
    assertEquals(val, null);
  }

  /**
   * Test list retrieval
   */
  public void testList() throws Exception {
    Graph graph = getGraph(getTriples());
    List<ObjectNode> list = graph.getRdfList(fred, hasNeighbors);
    Iterator<ObjectNode> i = list.iterator();
    assertEquals(i.next(), barney);
    assertEquals(i.next(), betty);
    assertFalse(i.hasNext());
    list = graph.getRdfList(barney, hasNeighbors);
    assertTrue(list.isEmpty());
  }

  /**
   * Test subject discovery
   */
  public void testSubject() throws Exception {
    Graph graph = getGraph(getTriples());
    List<SubjectNode> subjects = graph.getSubjects(type, person);
    assertEquals(subjects.size(), 4);
    assertTrue(subjects.contains(fred));
    assertTrue(subjects.contains(barney));
    assertTrue(subjects.contains(wilma));
    assertTrue(subjects.contains(betty));
    subjects = graph.getSubjects(hasName, new Literal("Fred"));
    assertEquals(subjects.size(), 1);
    assertEquals(subjects.get(0), fred);
    subjects = graph.getSubjects(hasName, new Literal("Foo"));
    assertTrue(subjects.isEmpty());
  }

  public void testMerge() throws Exception {
    AppendableGraph g1 = (AppendableGraph)getGraph(getSimple1());
    AppendableGraph g2 = (AppendableGraph)getGraph(getSimple2());

    g1.mergeInto(g2);
    assertEquals(4, g1.getTriples().size());
    Bnode b1 = (Bnode)g1.getValue(fred, knows);
    Bnode b2 = (Bnode)g1.getValue(barney, knows);
    assertNotSame(b1, b2);

    g2.mergeInto(g2);
    assertEquals(3, g2.getTriples().size());
    List<ObjectNode> n = g2.getValues(barney, knows);
    assertEquals(2, n.size());
    assertNotSame(n.get(0), n.get(1));
    assertTrue(n.get(0) instanceof Bnode);
    assertTrue(n.get(1) instanceof Bnode);
  }

  public void testUnion() throws Exception {
    AppendableGraph g1 = (AppendableGraph)getGraph(getSimple1());
    AppendableGraph g2 = (AppendableGraph)getGraph(getSimple2());

    g1.unionInto(g2);
    assertEquals(4, g1.getTriples().size());
    Bnode b1 = (Bnode)g1.getValue(fred, knows);
    Bnode b2 = (Bnode)g1.getValue(barney, knows);
    assertEquals(blank1, b1);
    assertEquals(blank1, b2);

    g2.unionInto(g2);
    assertEquals(2, g2.getTriples().size());
    List<ObjectNode> n = g2.getValues(barney, knows);
    assertEquals(1, n.size());
    assertEquals(n.get(0), blank1);
  }

  /**
   * Test matching
   */
  public void testMatch() throws Exception {
    Graph graph = getGraph(getTriples());
    Iterator<Triple> i = graph.match(null, hasName, null);
    Set<Uri> names = getNames();
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertFalse(i.hasNext());

    i = graph.match(X, hasName, X);
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertFalse(i.hasNext());

    i = graph.matchX((SubjectNode)null, hasName, "Fred");
    assertEquals(new Triple(fred, hasName, "Fred"), i.next());
    assertFalse(i.hasNext());

    i = graph.matchX(X, hasName, "Fred");
    assertEquals(new Triple(fred, hasName, "Fred"), i.next());
    assertFalse(i.hasNext());

    i = graph.matchX(fred, hasName, "Fred");
    assertEquals(new Triple(fred, hasName, "Fred"), i.next());
    assertFalse(i.hasNext());
  }

  /**
   * Test matching on subgraphs
   */
  public void testSubgraph() throws Exception {
    Graph graph = getGraph(getTriples());
    Graph g = graph.matchSubgraph((SubjectNode)null, hasName, (SubjectNode)null);
    Iterator<Triple> i = g.getTriples().iterator();
    Set<Uri> names = getNames();
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertTrue(names.contains(i.next()._1));
    assertFalse(i.hasNext());
    i = graph.matchSubgraphX((SubjectNode)null, hasName, "Fred").getTriples().iterator();
    assertEquals(new Triple(fred, hasName, new Literal("Fred")), i.next());
    assertFalse(i.hasNext());
    i = graph.matchSubgraphX(fred, hasName, "Fred").getTriples().iterator();
    assertEquals(new Triple(fred, hasName, new Literal("Fred")), i.next());
    assertFalse(i.hasNext());
  }

  protected abstract Graph getGraph(Collection<Triple> triples);

  final Set<Uri> getNames() {
    Set<Uri> names = new HashSet<Uri>();
    names.add(fred);
    names.add(barney);
    names.add(wilma);
    names.add(betty);
    return names;
  }

  final List<Triple> getTriples() {
    Bnode _1 = new Bnode();
    Bnode _2 = new Bnode();

    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new Triple(fred, type, person));
    triples.add(new Triple(barney, type, person));
    triples.add(new Triple(wilma, type, person));
    triples.add(new Triple(betty, type, person));
    triples.add(new Triple(fred, hasName, "Fred"));
    triples.add(new Triple(barney, hasName, "Barney"));
    triples.add(new Triple(wilma, hasName, "Wilma"));
    triples.add(new Triple(betty, hasName, "Betty"));
    triples.add(new Triple(fred, knows, barney));
    triples.add(new Triple(fred, knows, wilma));
    triples.add(new Triple(fred, knows, betty));
    triples.add(new Triple(fred, hasNeighbors, _1));
    triples.add(new Triple(_1, type, list));
    triples.add(new Triple(_1, first, barney));
    triples.add(new Triple(_1, rest, _2));
    triples.add(new Triple(_2, first, betty));
    triples.add(new Triple(_2, rest, nil));
    return triples;
  }

  final List<Triple> getSimple1() {
    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new Triple(fred, type, person));
    triples.add(new Triple(fred, knows, blank1));
    return triples;
  }

  final List<Triple> getSimple2() {
    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new Triple(barney, type, person));
    triples.add(new Triple(barney, knows, blank1));
    return triples;
  }
}
