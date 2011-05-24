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

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;

/**
 * A graph that uses tree sets and maps to keep the memory footprint smaller.
 * This gives log(N) lookups.
 */
public class PackedGraphImpl extends AbstractGraph {

  /**
   * Default constructor for an empty graph.
   */
  public PackedGraphImpl() {}

  /**
   * Builds a graph with a collection of triples.
   * @param triples The initial triples for the graph.
   */
  public PackedGraphImpl(Collection<Triple> triples) {
    super(triples);
  }

  /**
   * Use hashmaps for efficient lookups.
   * @return an instance of a HashMap.
   */
  protected <X,Y> Map<X,Y> createMap() {
    return new TreeMap<X,Y>();
  }

  /**
   * Use a HashSet for efficient lookups.
   * @return an instance of a HashSet.
   */
  protected <X> Collection<X> createCollection() {
    return new TreeSet<X>();
  }
}
