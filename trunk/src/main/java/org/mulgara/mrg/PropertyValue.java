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

import org.mulgara.util.Pair;

/**
 * An RDF property value
 */
public class PropertyValue extends Pair<PredicateNode,ObjectNode> {

  /**
   * Create a new property value.
   * @param p The property.
   * @param o The value.
   */
  public PropertyValue(PredicateNode p, ObjectNode o) {
    super(p, o);
  }

  /**
   * Create a new triple. This is an overloaded version of the first constructor
   * accepting Nodes instead of the more specific types. Consequently, this method
   * can throw a ClassCastException if the wrong types are provided.
   * @param p The predicate.
   * @param o The object.
   * @throws ClassCastException If an incompatible type is provided in  p or o.
   */
  public PropertyValue(Node p, Node o) {
    super((PredicateNode)p, (ObjectNode)o);
  }

  /**
   * Create a new property value.
   * @param pair The generalized property value.
   */
  public PropertyValue(Pair<PredicateNode,ObjectNode> pair) {
    super(pair._1, pair._2);
  }

  /**
   * Create a new property value with a string literal.
   * @param p The property.
   * @param o The object.
   */
  public PropertyValue(PredicateNode p, String o) {
    super(p, new Literal(o));
  }

  /**
   * Gets the predicate
   */
  public PredicateNode getPredicate() {
    return _1;
  }

  /**
   * Gets the predicate
   */
  public PredicateNode getProperty() {
    return _1;
  }

  /**
   * Gets the object
   */
  public ObjectNode getObject() {
    return _2;
  }

  /**
   * Gets the value
   */
  public ObjectNode getValue() {
    return _2;
  }

  /**
   * Equality method.
   */
  public boolean equals(Object o) {
    return (o instanceof PropertyValue) && ((PropertyValue)o)._1.equals(_1) && ((PropertyValue)o)._2.equals(_2);
  }
  
  /**
   * Prints a simple representation of the property value.
   */
  public String toString() {
    return "[" + _1 + " -> " + _2 + "]";
  }
}
