/*
 * Copyright 2010 Paula Gearon.
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

import org.mulgara.util.Trio;

/**
 * An RDF triple
 */
public class Triple extends Trio<SubjectNode,PredicateNode,ObjectNode> {

  /**
   * Create a new triple.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, PredicateNode p, ObjectNode o) {
    super(s, p, o);
  }

  /**
   * Create a new triple. This is an overloaded version of the first constructor
   * accepting Nodes instead of the more specific types. Consequently, this method
   * can throw a ClassCastException if the wrong types are provided.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws ClassCastException If an incompatible type is provided in s, p or o.
   */
  public Triple(Node s, Node p, Node o) {
    super((SubjectNode)s, (PredicateNode)p, (ObjectNode)o);
  }

  /**
   * Create a new triple.
   * @param t The generalized Triple.
   */
  public Triple(Trio<SubjectNode,PredicateNode,ObjectNode> t) {
    super(t.getFirst(), t.getSecond(), t.getThird());
  }

  /**
   * Create a new triple.
   * @param t An array of the triple elements. The parameters must be of the correct types.
   * @throws ClassCastException If the array contains Nodes of the wrong types for their position.
   */
  public Triple(Node[] t) {
    super((SubjectNode)t[0], (PredicateNode)t[1], (ObjectNode)t[2]);
  }

  /**
   * Create a new triple with a string literal.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, PredicateNode p, String o) {
    super(s, p, new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(URI s, PredicateNode p, ObjectNode o) {
    super(new Uri(s), p, o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, URI p, ObjectNode o) {
    super(s, new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, PredicateNode p, URI o) {
    super(s, p, new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(URI s, URI p, ObjectNode o) {
    super(new Uri(s), new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, URI p, URI o) {
    super(s, new Uri(p), new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(URI s, PredicateNode p, URI o) {
    super(new Uri(s), p, new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(URI s, URI p, URI o) {
    super(new Uri(s), new Uri(p), new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, PredicateNode p, ObjectNode o) throws URISyntaxException {
    super(new Uri(s), p, o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(SubjectNode s, String p, ObjectNode o) throws URISyntaxException {
    super(s, new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, String p, ObjectNode o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, URI p, ObjectNode o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(URI s, String p, ObjectNode o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), o);
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(SubjectNode s, String p, String o) throws URISyntaxException {
    super(s, new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(SubjectNode s, URI p, String o) {
    super(s, new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(SubjectNode s, String p, URI o) throws URISyntaxException {
    super(s, new Uri(p), new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, PredicateNode p, String o) throws URISyntaxException {
    super(new Uri(s), p, new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   */
  public Triple(URI s, PredicateNode p, String o) {
    super(new Uri(s), p, new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, PredicateNode p, URI o) throws URISyntaxException {
    super(new Uri(s), p, new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, String p, String o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(URI s, String p, String o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(URI s, URI p, String o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(URI s, String p, URI o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, URI p, String o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Literal(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, URI p, URI o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Uri(o));
  }

  /**
   * Create a new triple. Convenience constructor with automatic type conversion.
   * @param s The subject.
   * @param p The predicate.
   * @param o The object.
   * @throws URISyntaxException A string could not be converted to a Uri.
   */
  public Triple(String s, String p, URI o) throws URISyntaxException {
    super(new Uri(s), new Uri(p), new Uri(o));
  }


  /**
   * Gets the subject
   */
  public SubjectNode getSubject() {
    return _1;
  }

  /**
   * Gets the predicate
   */
  public PredicateNode getPredicate() {
    return _2;
  }

  /**
   * Gets the object
   */
  public ObjectNode getObject() {
    return _3;
  }

  /**
   * Equality method. Based on the wrapped URI.
   */
  public boolean equals(Object o) {
    return (o instanceof Triple) && ((Triple)o)._1.equals(_1) && ((Triple)o)._2.equals(_2) && ((Triple)o)._3.equals(_3);
  }
  
  /**
   * Prints a simple representation of the triple.
   */
  public String toString() {
    return "[" + _1 + " " + _2 + " " + _3 + "]";
  }
}
