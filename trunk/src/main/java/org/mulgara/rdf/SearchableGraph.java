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

import java.util.Iterator;

/**
 * An RDF graph that is fully searchable.
 * This provides the basic read operation required for SPARQL.
 */
public interface SearchableGraph extends Graph {

  /**
   * Find all triples that match a given pattern.
   * @param s The subject of the triples to match. If <code>null</code> that all subjects match.
   * @param p The predicate of the triple to add. If <code>null</code> that all predicates match.
   * @param o The object of the triple to add. If <code>null</code> that all objects match.
   * @return A Collection of the matching triples.
   */
  public Iterator<Triple> match(SubjectNode s, PredicateNode p, ObjectNode o);
}
