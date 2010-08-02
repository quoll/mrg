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
import java.util.Iterator;

/**
 * An Iterator&lt;Triple&gt; that only returns triples that match a pattern.
 * Only instantiated by other members of this package.
 */
class FilteredIterator implements Iterator<Triple> {

  /** The subject of the pattern to filter on. */
  private SubjectNode subject;

  /** The predicate of the pattern to filter on. */
  private PredicateNode predicate;

  /** The object of hte pattern to filter on. */
  private ObjectNode object;

  /** The initial iterator to filter. */
  private Iterator<Triple> original;

  /** The next triple to return. */
  private Triple nextTriple;

  /**
   * The main constructor.
   * @param subject The subject of the pattern to filter on. <code>null</code> means don't filter.
   * @param predicate The predicate of the pattern to filter on. <code>null</code> means don't filter.
   * @param object The object of the pattern to filter on. <code>null</code> means don't filter.
   * @param original The iterator to be filtered.
   */
  public FilteredIterator(SubjectNode subject, PredicateNode predicate, ObjectNode object, Iterator<Triple> original) {
    this.subject = subject;
    this.predicate = predicate;
    this.object = object;
    this.original = original;
    nextTriple = findNext();
  }

  /**
   * This constructor creates an iterator for a collection of triples.
   * @param subject The subject of the pattern to filter on. <code>null</code> means don't filter.
   * @param predicate The predicate of the pattern to filter on. <code>null</code> means don't filter.
   * @param object The object of the pattern to filter on. <code>null</code> means don't filter.
   * @param data The Collection to iterate over and filter.
   */
  public FilteredIterator(SubjectNode subject, PredicateNode predicate, ObjectNode object, Collection<Triple> data) {
    this(subject, predicate, object, data.iterator());
  }

  /**
   * Returns <code>true</code> if the iteration has more elements.
   * @return <code>true</code> if the iterator has more elements.
   */
  public boolean hasNext() {
    return nextTriple != null;
  }

  /**
   * Returns the next element in the iteration.
   * @return the next element in the iteration.
   * @throws NoSuchElementException iteration has no more elements.
   */
  public Triple next() {
    Triple result = nextTriple;
    nextTriple = findNext();
    return result;
  }

  /**
   * This method cannot be supported, as it is impossible to know if there is more data without first
   * retrieving it, so hasNext will necessarily make the underlying iterator move on.
   * @throws UnsupportedOperationException As this cannot be implemented in a filter.
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Retrieve the next element that this filter will match.
   * @return the next Triple that matches the filter, or <code>null</code> if nothing is left.
   */
  private Triple findNext() {
    while (original.hasNext()) {
      Triple value = original.next();
      if (match(value)) return value;
    }
    return null;
  }

  /**
   * Test if a given triple matches the pattern provided to this filter.
   * @param value The triple to test.
   * @return <code>true</code> if the value matches the pattern. <code>false</code> otherwise.
   */
  private boolean match(Triple value) {
    return ((subject == null || subject.equals(value.getSubject())) &&
        (predicate == null || predicate.equals(value.getPredicate())) &&
        (object == null || object.equals(value.getObject())));
  }
}
