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

package org.mulgara.rdf;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph with three indexes for complete searchability. Indexes are hash-based, giving constant time lookups.
 * This graph operates the same as other implementations of graph, but is fully indexed.
 */
public class IndexedGraph extends AbstractGraph {

  /** The pos index */
  ThreeTierIndex<PredicateNode,ObjectNode,SubjectNode> pos = new ThreeTierIndex<PredicateNode,ObjectNode,SubjectNode>();

  /** The osp index */
  ThreeTierIndex<ObjectNode,SubjectNode,PredicateNode> osp = new ThreeTierIndex<ObjectNode,SubjectNode,PredicateNode>();

  /**
   * Default constructor for an empty graph.
   */
  public IndexedGraph() {}

  /**
   * Builds a graph with a collection of triples.
   * @param triples The initial triples for the graph.
   */
  public IndexedGraph(Collection<Triple> triples) {
    super(triples);
  }

  /**
   * Use hashmaps for efficient lookups.
   * @return an instance of a HashMap.
   */
  protected <X,Y> Map<X,Y> createMap() {
    return new HashMap<X,Y>();
  }

  /**
   * Use a HashSet for efficient lookups.
   * @return an instance of a HashSet.
   */
  protected <X> Collection<X> createCollection() {
    return new HashSet<X>();
  }

  /**
   * Add a new triple to the graph.
   * @param s The subject of the triple.
   * @param p The predicate of the triple.
   * @param o The object of the triple.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean insert(SubjectNode s, PredicateNode p, ObjectNode o) {
    boolean result = super.insert(s, p, o);
    boolean r2 = pos.put(p, o, s);
    boolean r3 = osp.put(o, s, p);
    assert result == r2;
    assert result == r3;
    return result;
  }

  /**
   * Remove a triple from the graph.
   * @param s The subject of the triple.
   * @param p The predicate of the triple.
   * @param o The object of the triple.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean delete(SubjectNode s, PredicateNode p, ObjectNode o) {
    boolean result = super.delete(s, p, o);
    boolean r2 = pos.remove(p, o, s);
    boolean r3 = osp.remove(o, s, p);
    assert result == r2;
    assert result == r3;
    return result;
  }

  /**
   * Find all triples that match a given pattern.
   * @param s The subject of the triples to match. If <code>null</code> that all subjects match.
   * @param p The predicate of the triple to add. If <code>null</code> that all predicates match.
   * @param o The object of the triple to add. If <code>null</code> that all objects match.
   * @return A Collection of the matching triples.
   */
  public Iterator<Triple> match(SubjectNode s, PredicateNode p, ObjectNode o) {
    if (s == null && p == null && o == null) return getTriples().iterator();
    if (s != null && p != null && o != null) {
      if (spo.isDefinedAt(s)) {
        if (spo.get(s).get(p).contains(o)) return Collections.singletonList(new Triple(s, p, o)).iterator();
      }
      List<Triple> empty = Collections.emptyList();
      return empty.iterator();
    }

    // Simple tests to make it clear what is happening. This can be more efficient, but it would be unclear.
    if (s != null && p != null && o == null) return new TwoFixedIterator(spo, s, p);
    if (s != null && p == null && o == null) return new OneFixedIterator(spo, s);

    if (s == null && p != null && o != null) return new TwoFixedIterator(pos, p, o);
    if (s == null && p != null && o == null) return new OneFixedIterator(pos, p);

    if (s != null && p == null && o != null) return new TwoFixedIterator(osp, o, s);
    if (s == null && p == null && o != null) return new OneFixedIterator(osp, o);
    throw new AssertionError("Unable to handle query pattern");
  }


  /**
   * This class iterates over triples that have two fixed values.
   */
  private class TwoFixedIterator implements Iterator<Triple> {

    /** The index to generate the triples from */
    private final ThreeTierIndex<? extends Node, ? extends Node, ? extends Node> index;

    /** The rotation from the base SPO */
    private final int rotation;

    /** The first fixed value in each triple */
    private final Node first;

    /** The second fixed value in each triple */
    private final Node second;

    /** The iterator over the current data */
    private Iterator<? extends Node> nodes;

    /**
     * Create an iterator on the SPO index.
     * @param index The SPO index
     * @param s The subject to look up.
     * @param p The predicate to look up.
     */
    public TwoFixedIterator(ThreeTierIndex<SubjectNode,PredicateNode,ObjectNode> index, SubjectNode s, PredicateNode p) {
      this.index = index;
      first = s;
      second = p;
      rotation = 0;
      nodes = index.get(s).get(p).iterator();
    }

    /**
     * Create an iterator on the POS index.
     * @param index The POS index
     * @param p The predicate to look up.
     * @param o The object to look up.
     */
    public TwoFixedIterator(ThreeTierIndex<PredicateNode,ObjectNode,SubjectNode> index, PredicateNode p, ObjectNode o) {
      this.index = index;
      first = p;
      second = o;
      rotation = 1;
      nodes = index.get(p).get(o).iterator();
    }

    /**
     * Create an iterator on the OSP index.
     * @param index The OSP index
     * @param o The object to look up.
     * @param s The subject to look up.
     */
    public TwoFixedIterator(ThreeTierIndex<ObjectNode,SubjectNode,PredicateNode> index, ObjectNode o, SubjectNode s) {
      this.index = index;
      first = o;
      second = s;
      rotation = 2;
      nodes = index.get(o).get(s).iterator();
    }

    /**
     * Returns <code>true</code> if the iterator has more elements.
     * @return <code>true</code> if the iterator has more elements.
     */
    public boolean hasNext() {
      return nodes.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration.
     * @throws NoSuchElementException iteration has no more elements.
     */
    public Triple next() {
      Node[] t = new Node[3];
      t[rotation] = first;
      t[(rotation + 1) % 3] = second;
      t[(rotation + 2) % 3] = nodes.next();
      return new Triple(t);
    }

    /**
     * Since removing a value from the third level can have ripple effects from the previous levels,
     * this operation is not being supported.
     * @throws UnsupportedOperationException Due to complexity in handling.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  /**
   * This class iterates over triples that have one fixed value.
   */
  private class OneFixedIterator implements Iterator<Triple> {

    /** The index to generate the triples from */
    private final ThreeTierIndex<? extends Node, ? extends Node, ? extends Node> index;

    /** The rotation from the base SPO */
    private final int rotation;

    /** The first fixed value in each triple */
    private final Node first;

    /** The iterator over the second level of the index */
    private Iterator<Map.Entry<? extends Node,Collection<? extends Node>>> outerIt;

    /** The current value from the outer iterator */
    private Node second;

    /** The iterator over the third level of the index */
    private Iterator<? extends Node> innerIt;

    /**
     * Create an iterator on the SPO index.
     * @param index The SPO index.
     * @param s The subject to look up.
     */
    @SuppressWarnings("unchecked")
    public OneFixedIterator(ThreeTierIndex<SubjectNode,PredicateNode,ObjectNode> index, SubjectNode s) {
      this.index = index;
      first = s;
      rotation = 0;
      outerIt = (Iterator<Map.Entry<? extends Node,Collection<? extends Node>>>)(Iterator)index.get(s).entryIterator();
      init();
    }

    /**
     * Create an iterator on the POS index.
     * @param index The POS index
     * @param p The predicate to look up.
     */
    @SuppressWarnings("unchecked")
    public OneFixedIterator(ThreeTierIndex<PredicateNode,ObjectNode,SubjectNode> index, PredicateNode p) {
      this.index = index;
      first = p;
      rotation = 1;
      outerIt = (Iterator<Map.Entry<? extends Node,Collection<? extends Node>>>)(Iterator)index.get(p).entryIterator();
      init();
    }

    /**
     * Create an iterator on the OSP index.
     * @param index The OSP index
     * @param o The object to look up.
     */
    @SuppressWarnings("unchecked")
    public OneFixedIterator(ThreeTierIndex<ObjectNode,SubjectNode,PredicateNode> index, ObjectNode o) {
      this.index = index;
      first = o;
      rotation = 2;
      outerIt = (Iterator<Map.Entry<? extends Node,Collection<? extends Node>>>)(Iterator)index.get(o).entryIterator();
      init();
    }

    /**
     * Common initialization for all constructors.
     */
    private void init() {
      if (outerIt.hasNext()) {
        Map.Entry<? extends Node,Collection<? extends Node>> kv = outerIt.next();
        second = kv.getKey();
        innerIt = kv.getValue().iterator();
      } else {
        second = null;
        Set<? extends Node> empty = Collections.emptySet();
        innerIt = empty.iterator();
      }
    }

    /**
     * Returns <code>true</code> if the iterator has more elements.
     * @return <code>true</code> if the iterator has more elements.
     */
    public boolean hasNext() {
      boolean result = innerIt.hasNext();
      if (result) return result;
      // this depends on inner collections never being empty
      return outerIt.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration.
     * @throws NoSuchElementException iteration has no more elements.
     */
    public Triple next() {
      if (!innerIt.hasNext()) {
        Map.Entry<? extends Node,Collection<? extends Node>> kv = outerIt.next();
        second = kv.getKey();
        // this depends on inner collections never being empty
        innerIt = kv.getValue().iterator();
      }

      Node[] t = new Node[3];
      t[rotation] = first;
      t[(rotation + 1) % 3] = second;
      t[(rotation + 2) % 3] = innerIt.next();
      return new Triple(t);
    }

    /**
     * Since removing a value from the lower levels can have ripple effects from the top level,
     * this operation is not being supported.
     * @throws UnsupportedOperationException Due to complexity in handling.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

}
