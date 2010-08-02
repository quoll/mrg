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

package org.mulgara.rdf.vocab;

import org.mulgara.rdf.Uri;

/**
 * Vocabulary for OWL.
 */
public class OWL {

  /** The URI of the OWL name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.rdf.vocab.uri.OWL.BASE);

  /** Top */
  public static final Uri THING = new Uri(org.mulgara.rdf.vocab.uri.OWL.THING);

  /** Bottom */
  public static final Uri NOTHING = new Uri(org.mulgara.rdf.vocab.uri.OWL.NOTHING);

  /** The class of classes. */
  public static final Uri CLASS = new Uri(org.mulgara.rdf.vocab.uri.OWL.CLASS);

  /** The class of things that are all different. */
  public static final Uri ALL_DIFFERENT = new Uri(org.mulgara.rdf.vocab.uri.OWL.ALL_DIFFERENT);

  /** The class of things that are restricted. */
  public static final Uri RESTRICTION = new Uri(org.mulgara.rdf.vocab.uri.OWL.RESTRICTION);

  /** The class of object properties. */
  public static final Uri OBJECT_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.OBJECT_PROPERTY);

  /** The class of datatype properties (where the value is a literal). */
  public static final Uri DATATYPE_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.DATATYPE_PROPERTY);

  /** The class of transitive object properties. */
  public static final Uri TRANSITIVE_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.TRANSITIVE_PROPERTY);

  /** The class of symmetric object properties. */
  public static final Uri SYMMETRIC_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.SYMMETRIC_PROPERTY);

  /** The class of functional object properties. */
  public static final Uri FUNCTIONAL_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.FUNCTIONAL_PROPERTY);

  /** The class of inverse functional object properties. */
  public static final Uri INVERSE_FUNCTIONAL_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.INVERSE_FUNCTIONAL_PROPERTY);

  /** The class of annotation properties. */
  public static final Uri ANNOTATION_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.ANNOTATION_PROPERTY);

  /** The class of ontologies. */
  public static final Uri ONTOLOGY = new Uri(org.mulgara.rdf.vocab.uri.OWL.ONTOLOGY);

  /** The class properties of an ontology. */
  public static final Uri ONTOLOGY_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.ONTOLOGY_PROPERTY);

  /** The class for classes that should no longer be used. */
  public static final Uri DEPRECATED_CLASS = new Uri(org.mulgara.rdf.vocab.uri.OWL.DEPRECATED_CLASS);

  /** The class for properties that should no longer be used. */
  public static final Uri DEPRECATED_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.DEPRECATED_PROPERTY);

  /** The class for a range of data that a property can refer to. */
  public static final Uri DATA_RANGE = new Uri(org.mulgara.rdf.vocab.uri.OWL.DATA_RANGE);

  ////////////////////////////////////
  // Properties
  ////////////////////////////////////

  /** Equivalent class property. */
  public static final Uri EQUIVALENT_CLASS = new Uri(org.mulgara.rdf.vocab.uri.OWL.EQUIVALENT_CLASS);

  /** Disjoint With property. */
  public static final Uri DISJOINT_WITH = new Uri(org.mulgara.rdf.vocab.uri.OWL.DISJOINT_WITH);

  /** Equivalent property property. */
  public static final Uri EQUIVALENT_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.EQUIVALENT_PROPERTY);

  /** Same As property. */
  public static final Uri SAME_AS = new Uri(org.mulgara.rdf.vocab.uri.OWL.SAME_AS);

  /** Different From property. */
  public static final Uri DIFFERENT_FROM = new Uri(org.mulgara.rdf.vocab.uri.OWL.DIFFERENT_FROM);

  /** Distinct Members property. */
  public static final Uri DISTINCT_MEMBERS = new Uri(org.mulgara.rdf.vocab.uri.OWL.DISTINCT_MEMBERS);

  /** A property for a union of other classes. */
  public static final Uri UNION_OF = new Uri(org.mulgara.rdf.vocab.uri.OWL.UNION_OF);

  /** A property for an intersection of other classes. */
  public static final Uri INTERSECTION_OF = new Uri(org.mulgara.rdf.vocab.uri.OWL.INTERSECTION_OF);

  /** A property for the complement of another class. */
  public static final Uri COMPLEMENT_OF = new Uri(org.mulgara.rdf.vocab.uri.OWL.COMPLEMENT_OF);

  /** A property for an enumeration class. */
  public static final Uri ONE_OF = new Uri(org.mulgara.rdf.vocab.uri.OWL.ONE_OF);

  /** A property for associating a restriction with a property. */
  public static final Uri ON_PROPERTY = new Uri(org.mulgara.rdf.vocab.uri.OWL.ON_PROPERTY);

  /** A restriction property for the universal qualifier. */
  public static final Uri ALL_VALUES_FROM = new Uri(org.mulgara.rdf.vocab.uri.OWL.ALL_VALUES_FROM);

  /** A restriction property for specifying a property value. */
  public static final Uri HAS_VALUE = new Uri(org.mulgara.rdf.vocab.uri.OWL.HAS_VALUE);

  /** A restriction property for the existential qualifier. */
  public static final Uri SOME_VALUES_FROM = new Uri(org.mulgara.rdf.vocab.uri.OWL.SOME_VALUES_FROM);

  /** A restriction property for minimal numeric cardinality. */
  public static final Uri MIN_CARDINALITY = new Uri(org.mulgara.rdf.vocab.uri.OWL.MIN_CARDINALITY);

  /** A restriction property for maximum numeric cardinality. */
  public static final Uri MAX_CARDINALITY = new Uri(org.mulgara.rdf.vocab.uri.OWL.MAX_CARDINALITY);

  /** A restriction property synonymous with max and min cardinality set to the same value. */
  public static final Uri CARDINALITY = new Uri(org.mulgara.rdf.vocab.uri.OWL.CARDINALITY);

  /** A property for describing that two properties are the inverse of each other. */
  public static final Uri INVERSE_OF = new Uri(org.mulgara.rdf.vocab.uri.OWL.INVERSE_OF);

  /** An ontology property for importing another ontology. */
  public static final Uri IMPORTS = new Uri(org.mulgara.rdf.vocab.uri.OWL.IMPORTS);

  /** An annotation property for versioning. */
  public static final Uri VERSION_INFO = new Uri(org.mulgara.rdf.vocab.uri.OWL.VERSION_INFO);

  /** An ontology property for describing a previous version. */
  public static final Uri PRIOR_VERSION = new Uri(org.mulgara.rdf.vocab.uri.OWL.PRIOR_VERSION);

  /** An ontology property for describing compatibility with a previous version. */
  public static final Uri BACKWARD_COMPATIBLE_WITH = new Uri(org.mulgara.rdf.vocab.uri.OWL.BACKWARD_COMPATIBLE_WITH);

  /** An ontology property for describing incompatibility with a previous version. */
  public static final Uri INCOMPATIBLE_WITH = new Uri(org.mulgara.rdf.vocab.uri.OWL.INCOMPATIBLE_WITH);

}
