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

package org.mulgara.mrg.vocab.uri;

import java.net.URI;

/**
 * Vocabulary for OWL.
 */
public class OWL {

  /** The QName prefix for OWL */
  public static final String PREFIX = "owl";

  /** The OWL namespace. */
  public static final String BASE = "http://www.w3.org/2002/07/owl#";

  /** Top */
  public static final URI THING = URI.create(BASE + "Thing");

  /** Bottom */
  public static final URI NOTHING = URI.create(BASE + "Thing");

  /** The class of classes. */
  public static final URI CLASS = URI.create(BASE + "Class");

  /** The class of things that are all different. */
  public static final URI ALL_DIFFERENT = URI.create(BASE + "AllDifferent");

  /** The class of things that are restricted. */
  public static final URI RESTRICTION = URI.create(BASE + "Restriction");

  /** The class of object properties. */
  public static final URI OBJECT_PROPERTY = URI.create(BASE + "ObjectProperty");

  /** The class of datatype properties (where the value is a literal). */
  public static final URI DATATYPE_PROPERTY = URI.create(BASE + "DatatypeProperty");

  /** The class of transitive object properties. */
  public static final URI TRANSITIVE_PROPERTY = URI.create(BASE + "TransitiveProperty");

  /** The class of symmetric object properties. */
  public static final URI SYMMETRIC_PROPERTY = URI.create(BASE + "SymmetricProperty");

  /** The class of functional object properties. */
  public static final URI FUNCTIONAL_PROPERTY = URI.create(BASE + "FunctionalProperty");

  /** The class of inverse functional object properties. */
  public static final URI INVERSE_FUNCTIONAL_PROPERTY = URI.create(BASE + "InverseFunctionalProperty");

  /** The class of annotation properties. */
  public static final URI ANNOTATION_PROPERTY = URI.create(BASE + "AnnotationProperty");

  /** The class of ontologies. */
  public static final URI ONTOLOGY = URI.create(BASE + "Ontology");

  /** The class properties of an ontology. */
  public static final URI ONTOLOGY_PROPERTY = URI.create(BASE + "OntologyProperty");

  /** The class for classes that should no longer be used. */
  public static final URI DEPRECATED_CLASS = URI.create(BASE + "DeprecatedClass");

  /** The class for properties that should no longer be used. */
  public static final URI DEPRECATED_PROPERTY = URI.create(BASE + "DeprecatedProperty");

  /** The class for a range of data that a property can refer to. */
  public static final URI DATA_RANGE = URI.create(BASE + "DataRange");

  ////////////////////////////////////
  // Properties
  ////////////////////////////////////

  /** Equivalent class property. */
  public static final URI EQUIVALENT_CLASS = URI.create(BASE + "equivalentClass");

  /** Disjoint With property. */
  public static final URI DISJOINT_WITH = URI.create(BASE + "disjointWith");

  /** Equivalent property property. */
  public static final URI EQUIVALENT_PROPERTY = URI.create(BASE + "equivalentProperty");

  /** Same As property. */
  public static final URI SAME_AS = URI.create(BASE + "sameAs");

  /** Different From property. */
  public static final URI DIFFERENT_FROM = URI.create(BASE + "differentFrom");

  /** Distinct Members property. */
  public static final URI DISTINCT_MEMBERS = URI.create(BASE + "distinctMembers");

  /** A property for a union of other classes. */
  public static final URI UNION_OF = URI.create(BASE + "unionOf");

  /** A property for an intersection of other classes. */
  public static final URI INTERSECTION_OF = URI.create(BASE + "intersectionOf");

  /** A property for the complement of another class. */
  public static final URI COMPLEMENT_OF = URI.create(BASE + "complementOf");

  /** A property for an enumeration class. */
  public static final URI ONE_OF = URI.create(BASE + "oneOf");

  /** A property for associating a restriction with a property. */
  public static final URI ON_PROPERTY = URI.create(BASE + "onProperty");

  /** A restriction property for the universal qualifier. */
  public static final URI ALL_VALUES_FROM = URI.create(BASE + "allValuesFrom");

  /** A restriction property for specifying a property value. */
  public static final URI HAS_VALUE = URI.create(BASE + "hasValue");

  /** A restriction property for the existential qualifier. */
  public static final URI SOME_VALUES_FROM = URI.create(BASE + "someValuesFrom");

  /** A restriction property for minimal numeric cardinality. */
  public static final URI MIN_CARDINALITY = URI.create(BASE + "minCardinality");

  /** A restriction property for maximum numeric cardinality. */
  public static final URI MAX_CARDINALITY = URI.create(BASE + "maxCardinality");

  /** A restriction property synonymous with max and min cardinality set to the same value. */
  public static final URI CARDINALITY = URI.create(BASE + "cardinality");

  /** A property for describing that two properties are the inverse of each other. */
  public static final URI INVERSE_OF = URI.create(BASE + "inverseOf");

  /** An ontology property for importing another ontology. */
  public static final URI IMPORTS = URI.create(BASE + "imports");

  /** An annotation property for versioning. */
  public static final URI VERSION_INFO = URI.create(BASE + "versionInfo");

  /** An ontology property for describing a previous version. */
  public static final URI PRIOR_VERSION = URI.create(BASE + "priorVersion");

  /** An ontology property for describing compatibility with a previous version. */
  public static final URI BACKWARD_COMPATIBLE_WITH = URI.create(BASE + "backwardCompatibleWith");

  /** An ontology property for describing incompatibility with a previous version. */
  public static final URI INCOMPATIBLE_WITH = URI.create(BASE + "incompatibleWith");

}
