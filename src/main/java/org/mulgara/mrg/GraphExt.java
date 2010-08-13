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
import java.util.List;

/**
 * This class defines all the overloaded methods for Graph.
 * The idea of overloading these methods is to avoid having to create
 * Uri references and literals for every trivial operation.
 * 
 * This class hiererachy is upside down, as the implementation of each
 * of the methods in this class will be defined in terms of the methods
 * found in {@link Graph}. However, this class is defined as a superclass
 * of {@link Graph} to ensure that all Graphs have these extensions defined.
 * 
 * The implementation of the methods in this interface are trivial wrappers
 * to the {@link Graph} methods that they overload. These implementations
 * can be found in {@link AbstractGraphExt}.
 */
public interface GraphExt {

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, PredicateNode p, ObjectNode o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, URI p, ObjectNode o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, PredicateNode p, URI o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, URI p, ObjectNode o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, PredicateNode p, URI o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, URI p, URI o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, URI p, URI o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, PredicateNode p, ObjectNode o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(SubjectNode s, String p, ObjectNode o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, PredicateNode p, String o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, String p, ObjectNode o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, PredicateNode p, String o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(SubjectNode s, String p, String o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, String p, String o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, URI p, ObjectNode o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, PredicateNode p, URI o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, URI p, URI o) throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(URI s, String p, ObjectNode o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(SubjectNode s, String p, URI o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(URI s, String p, URI o) throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, PredicateNode p, String o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(SubjectNode s, URI p, String o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   */
  public boolean isAsserted(URI s, URI p, String o);

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The object of the triple to search for, as a URI.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, String p, URI o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a String.
   * @param p The predicate of the triple to search for, as a URI.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(String s, URI p, String o)
      throws URISyntaxException;

  /**
   * Tests if a triple has been asserted. Be careful of blank nodes, as
   * they will only match if they are exactly alike.
   * @param s The subject of the triple to search for, as a URI.
   * @param p The predicate of the triple to search for, as a String.
   * @param o The literal object of the triple to search for, as a String.
   * @return <code>true</code> only if the triple exists in the graph.
   * @throws URISyntaxException if a string could not be converted to a Uri.
   */
  public boolean isAsserted(URI s, String p, String o)
      throws URISyntaxException;

  /**
   * Tests if a resource exists anywhere in the graph, where the resource is a Uri reference.
   * @param r The resource to test.
   * @return <code>true</code> only if the resource is used somewhere in the graph.
   */
  public boolean doesResourceExist(URI r);

  /**
   * Tests if a resource exists anywhere in the graph, where the resource is string
   * representing a literal.
   * @param r The string form of the literal resource to test.
   * @return <code>true</code> only if the resource is used somewhere in the graph.
   */
  public boolean doesResourceExist(String r);

  /**
   * Gets all the properties for a given subject, with a URI as the subject.
   * @param s The subject as a URI.
   * @return A list of property/value pairs.
   */
  public List<PropertyValue> getProperties(URI s);

  /**
   * Gets all the properties for a given subject, with a string representing a URI as the subject.
   * @param s The subject as a string.
   * @return A list of property/value pairs.
   */
  public List<PropertyValue> getProperties(String s) throws URISyntaxException;

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest, as a URI.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(SubjectNode s, URI p);

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(URI s, PredicateNode p);

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest, as a URI.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(URI s, URI p);

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest, as a string.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(SubjectNode s, String p)
      throws URISyntaxException;

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(String s, PredicateNode p)
      throws URISyntaxException;

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest, as a string.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(String s, String p)
      throws URISyntaxException;

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest, as a string.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(URI s, String p) throws URISyntaxException;

  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest, as a URI.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(String s, URI p) throws URISyntaxException;

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest, as a URI.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(SubjectNode s, URI p);

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(URI s, PredicateNode p);

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest, as a URI.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(URI s, URI p);

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest, as a string.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(SubjectNode s, String p) throws URISyntaxException;

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(String s, PredicateNode p)
      throws URISyntaxException;

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest, as a string.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(String s, String p) throws URISyntaxException;

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a URI.
   * @param p The property of interest, as a string.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(URI s, String p) throws URISyntaxException;

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest, as a URI.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(String s, URI p) throws URISyntaxException;

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as a URI.
   * @param p The property of interest.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(URI s, PredicateNode p);

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as a string.
   * @param p The property of interest.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(String s, PredicateNode p)
      throws URISyntaxException;

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for.
   * @param p The property of interest, as a URI.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(SubjectNode s, URI p);

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as a URI.
   * @param p The property of interest, as a URI.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(URI s, URI p);

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as a string.
   * @param p The property of interest, as a URI.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(String s, URI p) throws URISyntaxException;

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for.
   * @param p The property of interest, as a string.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(SubjectNode s, String p)
      throws URISyntaxException;

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as  URI.
   * @param p The property of interest, as a string.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(URI s, String p) throws URISyntaxException;

  /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for, as a string.
   * @param p The property of interest, as a string.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(String s, String p)
      throws URISyntaxException;

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a URI.
   * @param value The value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(URI property, ObjectNode value);

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a string.
   * @param value The value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(String property, ObjectNode value)
      throws URISyntaxException;

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a URI.
   * @param value The value being looked for, as a URI.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(URI property, URI value);

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a string.
   * @param value The value being looked for, as a URI.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(String property, URI value)
      throws URISyntaxException;

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for.
   * @param value The literal value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(PredicateNode property, String value);

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a URI.
   * @param value The literal value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(URI property, String value);

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for, as a string.
   * @param value The literal value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(String property, String value)
      throws URISyntaxException;

}