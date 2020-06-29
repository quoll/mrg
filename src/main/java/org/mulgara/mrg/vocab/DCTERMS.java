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

package org.mulgara.mrg.vocab;

import org.mulgara.mrg.Uri;

/**
 * A class for holding the Dublin Core terms vocabulary.
 */
public class DCTERMS {

  /** The URI of the DC name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.DC.BASE);

  /** Date on which the resource was changed. */
  public static final Uri MODIFIED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.MODIFIED);

  /** A name given to the resource. */
  public static final Uri TITLE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.TITLE);

  /** An entity that mediates access to the resource and for whom the resource is intended or useful. */
  public static final Uri MEDIATOR = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.MEDIATOR);

  /** A related resource that supplants, displaces, or supersedes the described resource. */
  public static final Uri IS_REPLACED_BY = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_REPLACED_BY);

  /** Date of acceptance of the resource. */
  public static final Uri DATE_ACCEPTED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.DATE_ACCEPTED);

  /** Date of submission of the resource. */
  public static final Uri DATE_SUBMITTED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.DATE_SUBMITTED);

  /** An unambiguous reference to the resource within a given context. */
  public static final Uri IDENTIFIER = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IDENTIFIER);

  /** A related resource that is a version, edition, or adaptation of the described resource. */
  public static final Uri HAS_VERSION = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.HAS_VERSION);

  /** Date of creation of the resource. */
  public static final Uri CREATED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.CREATED);

  /** An entity responsible for making contributions to the resource. */
  public static final Uri CONTRIBUTOR = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.CONTRIBUTOR);

  /** Spatial characteristics of the resource. */
  public static final Uri SPATIAL = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.SPATIAL);

  /** A bibliographic reference for the resource. */
  public static final Uri BIBLIOGRAPHIC_CITATION = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.BIBLIOGRAPHIC_CITATION);

  /** A related resource of which the described resource is a version, edition, or adaptation. */
  public static final Uri IS_VERSION_OF = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_VERSION_OF);

  /** The topic of the resource. */
  public static final Uri SUBJECT = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.SUBJECT);

  /** Date (often a range) that the resource became or will become available. */
  public static final Uri AVAILABLE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.AVAILABLE);

  /** A related resource that is substantially the same as the described resource, but in another format. */
  public static final Uri IS_FORMAT_OF = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_FORMAT_OF);

  /** A class of entity, defined in terms of progression through an educational or training context, for which the described resource is intended. */
  public static final Uri EDUCATION_LEVEL = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.EDUCATION_LEVEL);

  /** Date of formal issuance (e.g., publication) of the resource. */
  public static final Uri ISSUED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ISSUED);

  /** A language of the resource. */
  public static final Uri LANGUAGE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.LANGUAGE);

  /** A related resource that is required by the described resource to support its function, delivery, or coherence. */
  public static final Uri REQUIRES = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.REQUIRES);

  /** An entity primarily responsible for making the resource. */
  public static final Uri CREATOR = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.CREATOR);

  /** A related resource that is included either physically or logically in the described resource. */
  public static final Uri HAS_PART = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.HAS_PART);

  /** The spatial or temporal topic of the resource, the spatial applicability of the resource, or the jurisdiction under which the resource is relevant. */
  public static final Uri COVERAGE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.COVERAGE);

  /** The size or duration of the resource. */
  public static final Uri EXTENT = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.EXTENT);

  /** An account of the resource. */
  public static final Uri DESCRIPTION = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.DESCRIPTION);

  /** The policy governing the addition of items to a collection. */
  public static final Uri ACCRUAL_POLICY = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ACCRUAL_POLICY);

  /** A related resource that is referenced, cited, or otherwise pointed to by the described resource. */
  public static final Uri REFERENCES = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.REFERENCES);

  /** Information about rights held in and over the resource. */
  public static final Uri RIGHTS = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.RIGHTS);

  /** A related resource from which the described resource is derived. */
  public static final Uri SOURCE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.SOURCE);

  /** A related resource in which the described resource is physically or logically included. */
  public static final Uri IS_PART_OF = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_PART_OF);

  /** A related resource that is supplanted, displaced, or superseded by the described resource. */
  public static final Uri REPLACES = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.REPLACES);

  /** Temporal characteristics of the resource. */
  public static final Uri TEMPORAL = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.TEMPORAL);

  /** The material or physical carrier of the resource. */
  public static final Uri MEDIUM = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.MEDIUM);

  /** An alternative name for the resource. */
  public static final Uri ALTERNATIVE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ALTERNATIVE);

  /** Information about who can access the resource or an indication of its security status. */
  public static final Uri ACCESS_RIGHTS = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ACCESS_RIGHTS);

  /** Date of copyright. */
  public static final Uri DATE_COPYRIGHTED = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.DATE_COPYRIGHTED);

  /** The nature or genre of the resource. */
  public static final Uri TYPE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.TYPE);

  /** A class of entity for whom the resource is intended or useful. */
  public static final Uri AUDIENCE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.AUDIENCE);

  /** An established standard to which the described resource conforms. */
  public static final Uri CONFORMS_TO = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.CONFORMS_TO);

  /** A point or period of time associated with an event in the lifecycle of the resource. */
  public static final Uri DATE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.DATE);

  /** The file format, physical medium, or dimensions of the resource. */
  public static final Uri FORMAT = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.FORMAT);

  /** The frequency with which items are added to a collection. */
  public static final Uri ACCRUAL_PERIODICITY = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ACCRUAL_PERIODICITY);

  /** A related resource. */
  public static final Uri RELATION = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.RELATION);

  /** A statement of any changes in ownership and custody of the resource since its creation that are significant for its authenticity, integrity, and interpretation. */
  public static final Uri PROVENANCE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.PROVENANCE);

  /** A related resource that is substantially the same as the pre-existing described resource, but in another format. */
  public static final Uri HAS_FORMAT = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.HAS_FORMAT);

  /** An entity responsible for making the resource available. */
  public static final Uri PUBLISHER = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.PUBLISHER);

  /** A summary of the resource. */
  public static final Uri ABSTRACT = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ABSTRACT);

  /** A list of subunits of the resource. */
  public static final Uri TABLE_OF_CONTENTS = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.TABLE_OF_CONTENTS);

  /** A person or organization owning or managing rights over the resource. */
  public static final Uri RIGHTS_HOLDER = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.RIGHTS_HOLDER);

  /** The method by which items are added to a collection. */
  public static final Uri ACCRUAL_METHOD = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.ACCRUAL_METHOD);

  /** A related resource that references, cites, or otherwise points to the described resource. */
  public static final Uri IS_REFERENCED_BY = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_REFERENCED_BY);

  /** A legal document giving official permission to do something with the resource. */
  public static final Uri LICENSE = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.LICENSE);

  /** A process, used to engender knowledge, attitudes and skills, that the described resource is designed to support. */
  public static final Uri INSTRUCTIONAL_METHOD = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.INSTRUCTIONAL_METHOD);

  /** Date (often a range) of validity of a resource. */
  public static final Uri VALID = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.VALID);

  /** A related resource that requires the described resource to support its function, delivery, or coherence. */
  public static final Uri IS_REQUIRED_BY = new Uri(org.mulgara.mrg.vocab.uri.DCTERMS.IS_REQUIRED_BY);

}
