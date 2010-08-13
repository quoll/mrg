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
 * A threesome of values
 */
public class Trio<X,Y,Z> {

  /** The first. */
  public final X _1;

  /** The second. */
  public final Y _2;

  /** The third. */
  public final Z _3;

  /**
   * Create a new trio.
   * @param x The first part of the trio.
   * @param y The second part of the trio.
   * @param z The third part of the trio.
   */
  public Trio(X x, Y y, Z z) {
    _1 = x;
    _2 = y;
    _3 = z;
  }

  /**
   * Gets the first
   */
  public X getFirst() {
    return _1;
  }

  /**
   * Gets the second
   */
  public Y getSecond() {
    return _2;
  }

  /**
   * Gets the third
   */
  public Z getThird() {
    return _3;
  }

  /**
   * Equality method. Based on the wrapped URI.
   */
  public boolean equals(Object o) {
    return (o instanceof Trio) && ((Trio<?,?,?>)o)._1.equals(_1) && ((Trio<?,?,?>)o)._2.equals(_2) && ((Trio<?,?,?>)o)._3.equals(_3);
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
