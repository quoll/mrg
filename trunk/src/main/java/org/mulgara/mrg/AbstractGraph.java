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

package org.mulgara.mrg;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mulgara.mrg.writer.GraphWriter;
import org.mulgara.mrg.writer.N3Writer;
import org.mulgara.mrg.writer.XMLWriter;
import org.mulgara.util.Pair;
import org.mulgara.util.Trio;
import org.mulgara.util.Fn2;

import static org.mulgara.mrg.vocab.RDF.*;

/**
 * An RDF graph. This uses a single index by default, and the indexes are built out of maps and collections
 * that are returned by the abstract methods createMap and createCollection respectively.
 * To implement this graph, simply define these methods to return appropriate Map and Collection
 * implementations. Extra indexes may also be added, which will allow getObjects and getPredicates
 * to be more efficiently implemented. An example of this is shown in {@link IndexedGraph}.
 * TODO: test objects on insertion to avoid storing duplicate nodes
 */
public abstract class AbstractGraph extends AbstractGraphExt implements Graph, WritableGraph, SearchableGraph {

  /** The spo index */
  ThreeTierIndex<SubjectNode,PredicateNode,ObjectNode> spo = new ThreeTierIndex<SubjectNode,PredicateNode,ObjectNode>();

  /** A functional closure that constructs PropertyValues */
  private static final PropValFactory propValFactory = new PropValFactory();

  /**
   * Default constructor for an empty graph.
   */
  public AbstractGraph() {}

  /**
   * Builds a graph with a collection of triples.
   * @param triples The initial triples for the graph.
   */
  public AbstractGraph(Collection<Triple> triples) {
    for (Triple t: triples) insert(t);
  }

  /**
   * Add a new triple to the graph.
   * @param s The subject of the triple.
   * @param p The predicate of the triple.
   * @param o The object of the triple.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean insert(SubjectNode s, PredicateNode p, ObjectNode o) {
    return spo.put(s, p, o);
  }

  /**
   * Add a new triple to the graph.
   * @param triple The complete triple to add.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean insert(Triple triple) {
    return spo.put(triple.getSubject(), triple.getPredicate(), triple.getObject());
  }

  /**
   * Remove a triple from the graph.
   * @param s The subject of the triple.
   * @param p The predicate of the triple.
   * @param o The object of the triple.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean delete(SubjectNode s, PredicateNode p, ObjectNode o) {
    return spo.remove(s, p, o);
  }

  /**
   * Remove a triple from the graph.
   * @param triple The complete triple to remove.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean delete(Triple triple) {
    return spo.remove(triple.getSubject(), triple.getPredicate(), triple.getObject());
  }

  /**
   * Gets all the properties for a given subject.
   * @param s The subject.
   * @return A list of property/value pairs.
   */
  @SuppressWarnings("unchecked")
  public List<PropertyValue> getProperties(SubjectNode s) {
    if (spo.isDefinedAt(s)) {
      return (List<PropertyValue>)spo.get(s).getEntries(propValFactory);
    } else {
      return Collections.emptyList();
    }
  }

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param t The triple to test for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(Triple t) {
    return isAsserted(t.getSubject(), t.getPredicate(), t.getObject());
  }

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, PredicateNode p, ObjectNode o) {
    TwoTierIndex<PredicateNode,ObjectNode> po = spo.get(s);
    if (po != null) {
      Collection<ObjectNode> os = po.get(p);
      return os != null && os.contains(o);
    }
    return false;
  }

  /**
   * Gets all the properties for a given subject as raw pairs.
   * @param s The subject.
   * @return A list of property/value pairs.
   */
  public List<Pair<PredicateNode,ObjectNode>> getRawProperties(SubjectNode s) {
    if (spo.isDefinedAt(s)) {
      return spo.get(s).getEntries();
    } else {
      return Collections.emptyList();
    }
  }

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(SubjectNode s, PredicateNode p) {
    List<ObjectNode> result;
    if (spo.isDefinedAt(s) && spo.get(s).isDefinedAt(p)) {
      result = new ArrayList<ObjectNode>();
      result.addAll(spo.get(s).get(p));
    } else result = Collections.emptyList();
    return result;
  }

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(SubjectNode s, PredicateNode p) {
    ObjectNode result = null;
    if (spo.isDefinedAt(s) && spo.get(s).isDefinedAt(p)) {
      Iterator<ObjectNode> i = spo.get(s).get(p).iterator();
      if (i.hasNext()) result = i.next();
    }
    return result;
  }

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for.
   * @param p The property of interest.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(SubjectNode s, PredicateNode p) {
    return getList(getValue(s, p));
  }

  /**
   * Get an RDF list from an object node that is refered to as being of type rdf:List
   * @param head The head of the list. This came from the object of a statement.
   *             If invalid as a list, then an empty list is returned.
   * @return The contents of the RDF list.
   */
  private List<ObjectNode> getList(ObjectNode head) {
    List<ObjectNode> result;
    if (head instanceof SubjectNode) {
      SubjectNode h = (SubjectNode)head;
      ObjectNode headData = getValue(h, FIRST);
      if (headData == null) {
        result = new LinkedList<ObjectNode>();
      } else {
        result = getList(getValue(h, REST));
        ((LinkedList<ObjectNode>)result).addFirst(headData);
      }
    } else {
      result = Collections.emptyList();
    }
    return result;
  }


  /**
   * Gets all the subjects that share a given property/value.
   * This method accumulates the subjects, though a graph with more complete indexes
   * could just look it up.
   * @param property The property being looked for.
   * @param value The value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(PredicateNode property, ObjectNode value) {
    List<SubjectNode> results = new LinkedList<SubjectNode>();
    for (Map.Entry<SubjectNode,TwoTierIndex<PredicateNode,ObjectNode>> rawEntry: spo.getRawEntries()) {
      TwoTierIndex<PredicateNode,ObjectNode> pv = rawEntry.getValue();
      Collection<ObjectNode> vals = pv.get(property);
      if (vals.contains(value)) results.add(rawEntry.getKey());
    }
    return results;
  }

  /**
   * Tests if a resource exists anywhere in the graph.
   * @param r The resource to test.
   * @return <code>true</code> only if the resource is used somewhere in the graph.
   */
  public boolean doesResourceExist(Node r) {
    return false;
  }

  /**
   * Gets all the subjects in the graph.
   * @return All the subjects in the graph.
   */
  public Collection<SubjectNode> getSubjects() {
    return spo.getKeySet();
  }

  /**
   * Gets all the predicates in the graph.
   * @return All the predicatess in the graph.
   */
  public Collection<PredicateNode> getPredicates() {
    Set<PredicateNode> predicates = new HashSet<PredicateNode>();
    for (Trio<SubjectNode,PredicateNode,ObjectNode> t: spo.getEntries()) predicates.add(t._2);
    return predicates;
  }

  /**
   * Gets all the objects in the graph.
   * @return All the objects in the graph.
   */
  public Collection<ObjectNode> getObjects() {
    Set<ObjectNode> objects = new HashSet<ObjectNode>();
    for (Trio<SubjectNode,PredicateNode,ObjectNode> t: spo.getEntries()) objects.add(t._3);
    return objects;
  }


  /**
   * Writes the contents of the graph to an output stream as N3.
   * @param out The stream to write to.
   */
  @Override
  public void exportN3(OutputStream out) throws IOException {
    exportN3(out, null);
  }

  /**
   * Writes the contents of the graph to an output stream as N3.
   * @param out The stream to write to.
   * @param base The base to write to.
   */
  @Override
  public void exportN3(OutputStream out, URI base) throws IOException {
    GraphWriter writer = new N3Writer(this, base);
    writer.scanNamespaces();
    writer.writeTo(out);
  }

  /**
   * Writes the contents of the graph to an output stream as RDF/XML.
   * @param out The stream to write to.
   */
  @Override
  public void exportXML(OutputStream out) throws IOException {
    exportXML(out, null);
  }

  /**
   * Writes the contents of the graph to an output stream as RDF/XML.
   * @param out The stream to write to.
   * @param base The base to write to.
   */
  public void exportXML(OutputStream out, URI base) throws IOException {
    GraphWriter writer = new XMLWriter(this, base);
    writer.scanNamespaces();
    writer.writeTo(out);
  }

  /**
   * Gets the entire graph as a list of triples.
   * @return All the triples in the graph.
   */
  public List<Triple> getTriples() {
    List<Triple> results = new ArrayList<Triple>();
    for (Trio<SubjectNode,PredicateNode,ObjectNode> t: spo.getEntries()) results.add(new Triple(t));
    return results;
  }

  /**
   * Find all triples that match a given pattern.
   * @param s The subject of the triples to match. If <code>null</code> that all subjects match.
   * @param p The predicate of the triple to add. If <code>null</code> that all predicates match.
   * @param o The object of the triple to add. If <code>null</code> that all objects match.
   * @return A Collection of the matching triples.
   */
  public Iterator<Triple> match(SubjectNode s, PredicateNode p, ObjectNode o) {
    return new FilteredIterator(s, p, o, getTriples());
  }

  /**
   * Gets the number of triples in this graph.
   * @return the number of triples in the graph.
   */
  public long size() {
    return spo.size();
  }

  /**
   * Tests if the graph has any entries.
   * @return <code>true</code> if there are no entries.
   */
  public boolean isEmpty() {
    return spo.isEmpty();
  }

  /**
   * Defines the map type to use in this implementation.
   * @return an instance of a Map.
   */
  protected abstract <X,Y> Map<X,Y> createMap();

  /**
   * Defines the collection type to use in this implementation.
   * @return an instance of a Collection.
   */
  protected abstract <X> Collection<X> createCollection();

  /**
   * An abstraction of the structure of mapping a value to a map of values to a collection.
   * Set to package scope.
   */
  class ThreeTierIndex<A extends Node, B extends Node, C extends Node> {
    /** The map that serves as the index. */
    private Map<A,TwoTierIndex<B,C>> index = createMap();

    /** An empty index used when no data is available. */
    private final TwoTierIndex<B,C> EMPTY = new EmptyTwoTier<B,C>();

    /**
     * Retrieve a mapping for a given key.
     * @param key The key to get the mapping for.
     * @return A TwoTierIndex associated with a given key. This will be empty if the key is not stored.
     */
    public TwoTierIndex<B,C> get(A key) {
      TwoTierIndex<B,C> i = index.get(key);
      return i == null ? EMPTY : i;
    }

    /**
     * Puts a triple into the index.
     * @param a The first level of the index.
     * @param b The second level of the index.
     * @param c The third level of the index.
     * @return <code>true</code> if the value already existed, <code>false</code> otherwise.
     */
    public boolean put(A a, B b, C c) {
      TwoTierIndex<B,C> i = index.get(a);
      if (i == null) {
        i = new TwoTierIndex<B,C>();
        index.put(a, i);
      }
      return i.put(b, c);
    }

    /**
     * Removes a triple from the index.
     * @param a The first level of the index.
     * @param b The second level of the index.
     * @param c The third level of the index.
     * @return <code>true</code> if the value already existed, <code>false</code> otherwise.
     */
    public boolean remove(A a, B b, C c) {
      TwoTierIndex<B,C> i = index.get(a);
      if (i == null) return false;
      boolean result = i.remove(b, c);
      if (i.isEmpty()) index.remove(a);
      return result;
    }

    /**
     * Indicates if this index contains a particular key.
     * @return <code>true</code> iff the key appears in at least one statement.
     */
    public boolean isDefinedAt(A a) {
      return index.containsKey(a);
    }

    /**
     * Creates a List of 3-tuples, representing all of the entries being indexed.
     * This will fully materialize the represented triples that are typically
     * stored in a tree representation.
     * @return a List of Trio objects containing the first, second and third level of the index.
     */
    public List<Trio<A,B,C>> getEntries() {
      if (index.isEmpty()) return Collections.emptyList();
      List<Trio<A,B,C>> result = new ArrayList<Trio<A,B,C>>();
      for (Map.Entry<A,TwoTierIndex<B,C>> entry: index.entrySet()) {
        A key = entry.getKey();
        for (Pair<B,C> pair: entry.getValue().getEntries()) {
          result.add(new Trio<A,B,C>(key, pair.getFirst(), pair.getSecond()));
        }
      }
      return result;
    }

    /**
     * Returns the number of virtual triples in this index.
     * @return the number of triples to be returned from getEntries().size()
     */
    public long size() {
      long accumulator = 0L;
      for (TwoTierIndex<B,C> value: index.values()) accumulator += value.size();
      return accumulator;
    }

    /**
     * Tests if the index has any entries.
     * @return <code>true</code> if there are no entries.
     */
    public boolean isEmpty() {
      return index.isEmpty();
    }

    /**
     * Returns the internal entries from this index, in their raw form. For internal use within the outer class.
     * @return a List of Trio objects containing the first, second and third level of the index.
     */
    Set<Map.Entry<A,TwoTierIndex<B,C>>> getRawEntries() {
      return index.entrySet();
    }

    /**
     * Returns the keys from this index. For internal use within the outer class.
     * @return a Set of keys from the index.
     */
    Set<A> getKeySet() {
      return index.keySet();
    }

  }


  /**
   * A key value mapping, where each key can map to multiple values.
   * Set to package scope.
   */
  class TwoTierIndex<A extends Node, B extends Node> {
    /** The map that serves as the index. */
    private final Map<A,Collection<B>> index;

    private final PairFactory<A,B> pairFactory = new PairFactory<A,B>() {
      public Pair<A,B> fn(A a, B b) { return new Pair<A,B>(a, b); }
    };

    /**
     * Create a new index. Initializes the index with the value of init.
     */
    public TwoTierIndex() {
      index = init();
    }

    /**
     * Creates the index object.
     * @return A new Map object.
     */
    protected Map<A,Collection<B>> init() {
      return createMap();
    }

    /**
     * Get all of the entries from the internal map.
     * @return an iterator with all of the pairs from the index.
     */
    public Iterator<Map.Entry<A,Collection<B>>> entryIterator() {
      return index.entrySet().iterator();
    }

    /**
     * Retrieve a value associated with a key.
     * @return The set of values for a given key,
     *         or <code>null</code> if there is no entry for that key.
     */
    public Collection<B> get(A key) {
      Collection<B> result = index.get(key);
      if (result == null) result = Collections.emptySet();
      return result;
    }

    /**
     * Gets a list of all the entries in this index.
     * @return a List of Pairs.
     */
    @SuppressWarnings("unchecked")
    public List<Pair<A,B>> getEntries() {
      return (List<Pair<A,B>>)getEntries(pairFactory);
    }

    /**
     * Gets a list of all the entries in this index.
     * @return a List of Pairs.
     */
    public List<? extends Pair<A,B>> getEntries(PairFactory<A,B> factory) {
      if (index.isEmpty()) return Collections.emptyList();
      List<Pair<A,B>> result = new ArrayList<Pair<A,B>>();
      for (Map.Entry<A,Collection<B>> entry: index.entrySet()) {
        A key = entry.getKey();
        for (B value: entry.getValue()) {
          result.add(factory.fn(key, value));
        }
      }
      return result;
    }

    /**
     * Adds a new key/value to the index.
     * @param key The key of the tuple.
     * @param value The value of the tuple.
     * @return <code>true</code> if the pair already existed, <code>false</code> if it did not.
     */
    public boolean put(A key, B value) {
      Collection<B> s = index.get(key);
      boolean result;
      if (s == null) {
        result = false;
        s = createCollection();
        index.put(key, s);
      } else {
        result = s.contains(value);
      }
      if (!result) s.add(value);
      return result;
    }

    /**
     * Removes a key/value from the index.
     * @param key The key of the tuple.
     * @param value The value of the tuple.
     * @return <code>true</code> if the pair already existed, <code>false</code> if it did not.
     */
    public boolean remove(A key, B value) {
      Collection<B> s = index.get(key);
      if (s == null) return false;
      boolean result = s.remove(value);
      if (s.isEmpty()) index.remove(key);
      return result;
    }

    /**
     * Indicates if this index contains a particular key.
     * @return <code>true</code> iff the key appears in at least once.
     */
    public boolean isDefinedAt(A a) {
      return index.containsKey(a);
    }

    /**
     * Returns the size of the virtual pairs in the index.
     * @return the size of the pairs in this index.
     */
    public long size() {
      long accumulator = 0L;
      for (Collection<B> value: index.values()) accumulator += value.size();
      return accumulator;
    }

    /**
     * Tests if the index has any entries.
     * @return <code>true</code> if there are no entries.
     */
    public boolean isEmpty() {
      return index.isEmpty();
    }

  }

  /** An extension of the TwoTierIndex that is always empty. */
  private class EmptyTwoTier<A extends Node, B extends Node> extends TwoTierIndex<A,B> {
    protected Map<A,Collection<B>> init() { return Collections.emptyMap(); }
  }

  /** Signature of a functional closure for constructing Pairs. */
  private interface PairFactory<A,B> extends Fn2<A,B,Pair<A,B>> { }

  /** A factory class for constructing PropertyValues as an extension of Pairs. */
  private static class PropValFactory implements PairFactory<PredicateNode,ObjectNode> {
    public PropertyValue fn(PredicateNode prop, ObjectNode val) {
      return new PropertyValue(prop, val);
    }
  }

}
