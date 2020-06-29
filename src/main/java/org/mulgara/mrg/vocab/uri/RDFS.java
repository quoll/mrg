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
 * A class for holding the RDFS vocabulary.
 */
public class RDFS {

  /** The QName prefix for RDFS */
  public static final String PREFIX = "rdfs";

  /** The RDFS namespace */
  public static final String BASE = "http://www.w3.org/2000/01/rdf-schema#";

  /** The class of Classes. */
  public static final URI CLASS = URI.create(BASE + "Class");

  /** The comment for a resource. */
  public static final URI COMMENT = URI.create(BASE + "comment");

  /** Further information about a resource. */
  public static final URI SEE_ALSO = URI.create(BASE + "seeAlso");

  /** The class resource, everything. */
  public static final URI RESOURCE = URI.create(BASE + "Resource");

  /** The subject is a subclass of a class. */
  public static final URI SUB_CLASS_OF = URI.create(BASE + "subClassOf");

  /** The subject is a subproperty of a property. */
  public static final URI SUB_PROPERTY_OF = URI.create(BASE + "subPropertyOf");

  /** A human-readable name for the subject. */
  public static final URI LABEL = URI.create(BASE + "label");

  /** A domain of the subject property. */
  public static final URI DOMAIN = URI.create(BASE + "domain");

  /** A range of the subject property. */
  public static final URI RANGE = URI.create(BASE + "range");

  /** The definition of the subject resource. Subproperty of seeAlso. */
  public static final URI IS_DEFINED_BY = URI.create(BASE + "isDefinedBy");

  /** The class of literal values, eg. textual strings and integers. */
  public static final URI LITERAL = URI.create(BASE + "Literal");

  /** The class of RDF containers. */
  public static final URI CONTAINER = URI.create(BASE + "Container");

  /** The class of container membership properties, rdf:_1, rdf:_2, ...,
      all of which are sub-properties of 'member'. */
  public static final URI CONTAINER_MEMBERSHIP_PROPERTY = URI.create(BASE + "ContainerMembershipProperty");

  /** A member of the subject resource. */
  public static final URI MEMBER = URI.create(BASE + "member");

  /** The class of RDF datatypes. */
  public static final URI DATATYPE = URI.create(BASE + "Datatype");

}
