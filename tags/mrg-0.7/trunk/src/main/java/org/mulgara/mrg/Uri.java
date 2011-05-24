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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A wrapper for a URI. This allows the use of URI as a Node.
 * TODO: create Uri with factory from the graph. If a uri string is already
 * internal (u == u.intern()) then it is already in the graph, and we want
 * to find it with the existing indexes so that we can reuse the object.
 */
public class Uri implements SubjectNode, PredicateNode, ObjectNode {

  /** The type identifier for Uri */
  public static final int TYPE_ID = 0;

  /** A constant value used as a marker for wildcards. */
  static final Uri BLANK = new Uri((URI)null);

  /** The wrapped URI. */
  private final URI uri;

  /**
   * Convenience to a new URI.
   * @param u The string form of the URI to wrap.
   */
  public Uri(String u) throws URISyntaxException {
    this.uri = new URI(u.intern());
  }

  /**
   * Create a new URI.
   * @param uri The URI to wrap.
   */
  public Uri(URI uri) {
    this.uri = uri;
  }

  /**
   * Safe creation of a URI. This should only be used for valid constants.
   * @param u The string form of the URI to wrap.
   */
  public static Uri create(String u) {
    return new Uri(URI.create(u.intern()));
  }

  /**
   * Gets the wrapped URI.
   */
  public URI getURI() {
    return uri;
  }

  /**
   * A type identifier to distinguish from the other types of Node.
   */
  public int getTypeId() {
    return TYPE_ID;
  }

  /**
   * Equality method. Based on the wrapped URI.
   */
  public boolean equals(Object o) {
    return (o instanceof Uri) && ((Uri)o).uri.equals(uri);
  }

  /**
   * The hashcode, based on the uri.
   */
  public int hashCode() {
    return uri.hashCode();
  }

  /**
   * Compares this object with the specified object for order.
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than,
   *         equal to, or greater than the specified object.
   */
  public int compareTo(Node o) {
    int tid = o.getTypeId();
    return (TYPE_ID == tid) ? uri.compareTo(((Uri)o).uri) : tid - TYPE_ID;
  }

  /**
   * Represents this node as a string.
   */
  public String toString() {
    return "<" + uri + ">";
  }

}
