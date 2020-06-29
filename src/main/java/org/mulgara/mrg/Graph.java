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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * An RDF graph.
 */
public interface Graph extends GraphExt {

  /** A wildcard for use when matching triples. */
  public static final Uri X = Uri.BLANK;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param t The triple to test for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  boolean isAsserted(Triple t);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  boolean isAsserted(SubjectNode s, PredicateNode p, ObjectNode o);

  /**
   * Tests if a resource exists anywhere in the graph.
   * @param r The resource to test.
   * @return <code>true</code> only if the resource is used somewhere in the graph.
   */
  boolean doesResourceExist(Node r);

  /**
   * Gets all the properties for a given subject.
   * @param s The subject.
   * @return A list of property/value pairs.
   */
  List<PropertyValue> getProperties(SubjectNode s);


  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(SubjectNode s, PredicateNode p);

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(SubjectNode s, PredicateNode p);

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for.
   * @param p The property of interest.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(SubjectNode s, PredicateNode p);

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for.
   * @param value The value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(PredicateNode property, ObjectNode value);

  /**
   * Gets all the subjects in the graph.
   * @return All the subjects in the graph.
   */
  public Collection<SubjectNode> getSubjects();

  /**
   * Gets all the predicates in the graph.
   * @return All the predicatess in the graph.
   */
  public Collection<PredicateNode> getPredicates();

  /**
   * Gets all the objects in the graph.
   * @return All the objects in the graph.
   */
  public Collection<ObjectNode> getObjects();

  /**
   * Gets the entire graph as a list of triples.
   * @return All the triples in the graph.
   */
  public List<Triple> getTriples();

  /**
   * Writes the contents of the graph to an output stream as N3.
   * @param out The stream to write to.
   */
  public void exportN3(OutputStream out) throws IOException;

  /**
   * Writes the contents of the graph to an output stream as N3.
   * @param out The stream to write to.
   * @param base The base to write to.
   */
  public void exportN3(OutputStream out, URI base) throws IOException;

  /**
   * Writes the contents of the graph to an output stream as RDF/XML.
   * @param out The stream to write to.
   */
  public void exportXML(OutputStream out) throws IOException;

  /**
   * Writes the contents of the graph to an output stream as RDF/XML.
   * @param out The stream to write to.
   * @param base The base to write to.
   */
  public void exportXML(OutputStream out, URI base) throws IOException;

  /**
   * Gets the number of triples in this graph.
   * @return the number of triples in the graph.
   */
  public long size();

  /**
   * Tests if the graph has any entries.
   * @return <code>true</code> if there are no entries.
   */
  public boolean isEmpty();

  /**
   * Find all triples that match a given pattern. The resulting iterator may be live, and can
   * lead to a {@link java.util.ConcurrentModificationException} if the graph is modified.
   * If this is a possibility, then use {@link #matchSubgraph(SubjectNode, PredicateNode, ObjectNode)} instead.
   * @param s The subject of the triples to match. If <code>null</code> or {@link #X} then all subjects match.
   * @param p The predicate of the triple to add. If <code>null</code> or {@link #X} then all predicates match.
   * @param o The object of the triple to add. If <code>null</code> or {@link #X} then all objects match.
   * @return An Iterator on the matching triples.
   */
  public Iterator<Triple> match(SubjectNode s, PredicateNode p, ObjectNode o);

  /**
   * Returns a subgraph of this graph that only contains the triples that match a given pattern.
   * This method copies all of the required triples. To avoid the overhead of this operation, use
   * {@link #match(SubjectNode, PredicateNode, ObjectNode)} instead.
   * @param s The subject of the triples to match. If <code>null</code> or {@link #X} then all subjects match.
   * @param p The predicate of the triple to add. If <code>null</code> or {@link #X} then all predicates match.
   * @param o The object of the triple to add. If <code>null</code> or {@link #X} then all objects match.
   * @return A Graph containing only the matching triples.
   */
  public Graph matchSubgraph(SubjectNode s, PredicateNode p, ObjectNode o);

}
