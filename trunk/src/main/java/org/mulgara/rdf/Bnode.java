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

/**
 * A blank node.
 */
public class Bnode implements SubjectNode, ObjectNode {

  /** The type identifier for Bnode */
  private static final int TYPE_ID = 2;

  /** An internal counter to help create anonymous nodes */
  private static long idCounter = 0;

  /** The label used to identify this blank node. */
  private final String label;

  /**
   * Create a new blank node.
   * @param label The label used to uniquely identify the node.
   */
  public Bnode(String label) {
    this.label = (label.charAt(0) != '_') ? "_:" + label : label;
  }

  /**
   * Create a new anonymous blank node.
   */
  public Bnode() {
    this.label = "_:A" + idCounter++;
  }

  /**
   * Equality method. Based on the label.
   */
  public boolean equals(Object o) {
    return (o instanceof Bnode) && ((Bnode)o).label.equals(label);
  }

  /**
   * The hashcode, based on the label.
   */
  public int hashCode() {
    return label.hashCode();
  }

  /**
   * Represents this node as a string.
   */
  public String toString() {
    return "<" + label + ">";
  }

  /**
   * A type identifier to distinguish from the other types of Node.
   */
  public int getTypeId() {
    return TYPE_ID;
  }

  /**
   * Compares this object with the specified object for order.
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than,
   *         equal to, or greater than the specified object.
   */
  public int compareTo(Node o) {
    int tid = o.getTypeId();
    return (TYPE_ID == tid) ? label.compareTo(((Bnode)o).label) : tid - TYPE_ID;
  }

}
