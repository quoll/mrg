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


/**
 * An RDF graph that triples can be added to.
 */
public interface AppendableGraph extends Graph {

  /**
   * Add a new triple to the graph.
   * @param s The subject of the triple to add.
   * @param p The predicate of the triple to add.
   * @param o The object of the triple to add.
   * @return <code>true</code> if the triple did not exist in the graph.
   */
  public boolean insert(SubjectNode s, PredicateNode p, ObjectNode o);

  /**
   * Add a new triple to the graph.
   * @param triple The complete triple to add.
   * @return <code>true</code> if the triple was already in the graph.
   */
  public boolean insert(Triple triple);

}
