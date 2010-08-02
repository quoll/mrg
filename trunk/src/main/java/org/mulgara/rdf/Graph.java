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

import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * An RDF graph.
 */
public interface Graph {

  /**
   * Gets all the properties for a given subject.
   * @param s The subject.
   * @return A list of property/value pairs.
   */
  List<PropertyValue> getProperties(SubjectNode s);


  /**
   * Gets all the properties for a given subject, with a URI as the subject.
   * @param s The subject as a URI.
   * @return A list of property/value pairs.
   */
  List<PropertyValue> getProperties(URI s);


  /**
   * Gets all the properties for a given subject, with a string representing a URI as the subject.
   * @param s The subject as a string.
   * @return A list of property/value pairs.
   */
  List<PropertyValue> getProperties(String s) throws URISyntaxException;


  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(SubjectNode s, PredicateNode p);

  ////// START OVERLOADED METHODS FOR getValues

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
  public List<ObjectNode> getValues(SubjectNode s, String p) throws URISyntaxException;


  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(String s, PredicateNode p) throws URISyntaxException;


  /**
   * Gets all the values for a given property on a subject.
   * @param s The subject to get the properties for, as a string.
   * @param p The property of interest, as a string.
   * @return The list of values for the property on that subject.
   */
  public List<ObjectNode> getValues(String s, String p) throws URISyntaxException;


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

  ////// END OF OVERLOADED METHODS FOR getValues

  /**
   * Gets a single value for a given property on a subject.
   * @param s The subject to get the properties for.
   * @param p The property of interest.
   * @return The first values for the property on that subject.
   */
  public ObjectNode getValue(SubjectNode s, PredicateNode p);

  ////// START OVERLOADED METHODS FOR getValue

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
  public ObjectNode getValue(String s, PredicateNode p) throws URISyntaxException;


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

  ////// END OF OVERLOADED METHODS FOR getValue

 /**
   * Gets an rdf:List property from an object. If more than one
   * value exists for this property, then returns the first and assumes it's a list.
   * @param s The subject to get the property for.
   * @param p The property of interest.
   * @return The list associates with the property on that subject.
   */
  public List<ObjectNode> getRdfList(SubjectNode s, PredicateNode p);

  ////// START OF OVERLOADED METHODS FOR getRdfList

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
  public List<ObjectNode> getRdfList(String s, PredicateNode p) throws URISyntaxException;

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
  public List<ObjectNode> getRdfList(SubjectNode s, String p) throws URISyntaxException;

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
  public List<ObjectNode> getRdfList(String s, String p) throws URISyntaxException;

  ////// END OF OVERLOADED METHODS FOR getRdfList

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for.
   * @param value The value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(PredicateNode property, ObjectNode value);

  ////// START OF OVERLOADED METHODS FOR getSubjects

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
  public List<SubjectNode> getSubjects(String property, ObjectNode value) throws URISyntaxException;

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
  public List<SubjectNode> getSubjects(String property, URI value) throws URISyntaxException;

  ////// END OF OVERLOADED METHODS FOR getSubjects

  /**
   * Gets all the subjects that share a given property/value.
   * @param property The property being looked for.
   * @param value The literal value being looked for.
   * @return The subjects that have the value for the property.
   */
  public List<SubjectNode> getSubjects(PredicateNode property, String value);

  ////// START OF OVERLOADED METHODS FOR getSubjects with literal value

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
  public List<SubjectNode> getSubjects(String property, String value) throws URISyntaxException;

  ////// END OF OVERLOADED METHODS FOR getSubjects with literal value


  /**
   * Gets the entire graph as a list of triples.
   * @return All the triples in the graph.
   */
  public List<Triple> getTriples();


  /**
   * Gets the number of triples in this graph.
   * @return the number of triples in the graph.
   */
  public long size();

  /**
   * Tests if the graph has any entries.
   * @return <code>true</code> if there are no entries.
   */
  public boolean isEmpty();

}
