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

package org.mulgara.util;

/**
 * A threesom of values
 */
public class Trio<A,B,C> {

  /** The first. */
  public final A _1;

  /** The second. */
  public final B _2;

  /** The third. */
  public final C _3;

  /**
   * Create a new trio.
   * @param a The first part of the trio.
   * @param b The second part of the trio.
   * @param c The third part of the trio.
   */
  public Trio(A a, B b, C c) {
    _1 = a;
    _2 = b;
    _3 = c;
  }

  /**
   * Gets the first
   */
  public A getFirst() {
    return _1;
  }

  /**
   * Gets the second
   */
  public B getSecond() {
    return _2;
  }

  /**
   * Gets the third
   */
  public C getThird() {
    return _3;
  }

  /**
   * Equality method. Based on the wrapped URI.
   */
  public boolean equals(Object o) {
    return (o instanceof Trio) && ((Trio)o)._1.equals(_1) && ((Trio)o)._2.equals(_2) && ((Trio)o)._3.equals(_3);
  }

  /**
   * The hashcode, based on the first and second.
   */
  public int hashCode() {
    return _1.hashCode() * 11 + _2.hashCode() * 7 + _3.hashCode();
  }

  /**
   * Represents this node as a string.
   */
  public String toString() {
    return "(" + _1 + "," + _2 + "," + _3 + ")";
  }

}
