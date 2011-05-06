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

package org.mulgara.mrg.vocab;

import org.mulgara.mrg.Uri;

/**
 * A class for holding the RDFS vocabulary.
 */
public class RDFS {

  /** The Uri of the RDFS name space. */
  public static final Uri BASE_Uri = Uri.create(org.mulgara.mrg.vocab.uri.RDFS.BASE);

  /** The Class type. */
  public static final Uri CLASS = new Uri(org.mulgara.mrg.vocab.uri.RDFS.CLASS);

  /** The comment for a resource. */
  public static final Uri COMMENT = new Uri(org.mulgara.mrg.vocab.uri.RDFS.COMMENT);

  /** Further information about a resource. */
  public static final Uri SEE_ALSO = new Uri(org.mulgara.mrg.vocab.uri.RDFS.SEE_ALSO);

  /** The class resource, everything. */
  public static final Uri RESOURCE = new Uri(org.mulgara.mrg.vocab.uri.RDFS.RESOURCE);

  /** The subject is a subclass of a class. */
  public static final Uri SUB_CLASS_OF = new Uri(org.mulgara.mrg.vocab.uri.RDFS.SUB_CLASS_OF);

  /** The subject is a subproperty of a property. */
  public static final Uri SUB_PROPERTY_OF = new Uri(org.mulgara.mrg.vocab.uri.RDFS.SUB_PROPERTY_OF);

  /** A human-readable name for the subject. */
  public static final Uri LABEL = new Uri(org.mulgara.mrg.vocab.uri.RDFS.LABEL);

  /** A domain of the subject property. */
  public static final Uri DOMAIN = new Uri(org.mulgara.mrg.vocab.uri.RDFS.DOMAIN);

  /** A range of the subject property. */
  public static final Uri RANGE = new Uri(org.mulgara.mrg.vocab.uri.RDFS.RANGE);

  /** The definition of the subject resource. Subproperty of seeAlso. */
  public static final Uri IS_DEFINED_BY = new Uri(org.mulgara.mrg.vocab.uri.RDFS.IS_DEFINED_BY);

  /** The class of literal values, eg. textual strings and integers. */
  public static final Uri LITERAL = new Uri(org.mulgara.mrg.vocab.uri.RDFS.LITERAL);

  /** The class of RDF containers. */
  public static final Uri CONTAINER = new Uri(org.mulgara.mrg.vocab.uri.RDFS.CONTAINER);

  /** The class of container membership properties, rdf:_1, rdf:_2, ...,
      all of which are sub-properties of 'member'. */
  public static final Uri CONTAINER_MEMBERSHIP_PROPERTY = new Uri(org.mulgara.mrg.vocab.uri.RDFS.CONTAINER_MEMBERSHIP_PROPERTY);

  /** A member of the subject resource. */
  public static final Uri MEMBER = new Uri(org.mulgara.mrg.vocab.uri.RDFS.MEMBER);

  /** The class of RDF datatypes. */
  public static final Uri DATATYPE = new Uri(org.mulgara.mrg.vocab.uri.RDFS.DATATYPE);

}
