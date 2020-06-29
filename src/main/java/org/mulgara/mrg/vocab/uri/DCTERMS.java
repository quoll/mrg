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
 * A class for holding the Dublin Core terms vocabulary.
 */
public class DCTERMS {

  /** The QName prefix for DC */
  public static final String PREFIX = "dc";

  /** The DC namespace */
  public static final String BASE = "http://purl.org/dc/terms/";

  /** Date on which the resource was changed. */
  public static final URI MODIFIED = URI.create(BASE + "modified");

  /** A name given to the resource. */
  public static final URI TITLE = URI.create(BASE + "title");

  /** An entity that mediates access to the resource and for whom the resource is intended or useful. */
  public static final URI MEDIATOR = URI.create(BASE + "mediator");

  /** A related resource that supplants, displaces, or supersedes the described resource. */
  public static final URI IS_REPLACED_BY = URI.create(BASE + "isReplacedBy");

  /** Date of acceptance of the resource. */
  public static final URI DATE_ACCEPTED = URI.create(BASE + "dateAccepted");

  /** Date of submission of the resource. */
  public static final URI DATE_SUBMITTED = URI.create(BASE + "dateSubmitted");

  /** An unambiguous reference to the resource within a given context. */
  public static final URI IDENTIFIER = URI.create(BASE + "identifier");

  /** A related resource that is a version, edition, or adaptation of the described resource. */
  public static final URI HAS_VERSION = URI.create(BASE + "hasVersion");

  /** Date of creation of the resource. */
  public static final URI CREATED = URI.create(BASE + "created");

  /** An entity responsible for making contributions to the resource. */
  public static final URI CONTRIBUTOR = URI.create(BASE + "contributor");

  /** Spatial characteristics of the resource. */
  public static final URI SPATIAL = URI.create(BASE + "spatial");

  /** A bibliographic reference for the resource. */
  public static final URI BIBLIOGRAPHIC_CITATION = URI.create(BASE + "bibliographicCitation");

  /** A related resource of which the described resource is a version, edition, or adaptation. */
  public static final URI IS_VERSION_OF = URI.create(BASE + "isVersionOf");

  /** The topic of the resource. */
  public static final URI SUBJECT = URI.create(BASE + "subject");

  /** Date (often a range) that the resource became or will become available. */
  public static final URI AVAILABLE = URI.create(BASE + "available");

  /** A related resource that is substantially the same as the described resource, but in another format. */
  public static final URI IS_FORMAT_OF = URI.create(BASE + "isFormatOf");

  /** A class of entity, defined in terms of progression through an educational or training context, for which the described resource is intended. */
  public static final URI EDUCATION_LEVEL = URI.create(BASE + "educationLevel");

  /** Date of formal issuance (e.g., publication) of the resource. */
  public static final URI ISSUED = URI.create(BASE + "issued");

  /** A language of the resource. */
  public static final URI LANGUAGE = URI.create(BASE + "language");

  /** A related resource that is required by the described resource to support its function, delivery, or coherence. */
  public static final URI REQUIRES = URI.create(BASE + "requires");

  /** An entity primarily responsible for making the resource. */
  public static final URI CREATOR = URI.create(BASE + "creator");

  /** A related resource that is included either physically or logically in the described resource. */
  public static final URI HAS_PART = URI.create(BASE + "hasPart");

  /** The spatial or temporal topic of the resource, the spatial applicability of the resource, or the jurisdiction under which the resource is relevant. */
  public static final URI COVERAGE = URI.create(BASE + "coverage");

  /** The size or duration of the resource. */
  public static final URI EXTENT = URI.create(BASE + "extent");

  /** An account of the resource. */
  public static final URI DESCRIPTION = URI.create(BASE + "description");

  /** The policy governing the addition of items to a collection. */
  public static final URI ACCRUAL_POLICY = URI.create(BASE + "accrualPolicy");

  /** A related resource that is referenced, cited, or otherwise pointed to by the described resource. */
  public static final URI REFERENCES = URI.create(BASE + "references");

  /** Information about rights held in and over the resource. */
  public static final URI RIGHTS = URI.create(BASE + "rights");

  /** A related resource from which the described resource is derived. */
  public static final URI SOURCE = URI.create(BASE + "source");

  /** A related resource in which the described resource is physically or logically included. */
  public static final URI IS_PART_OF = URI.create(BASE + "isPartOf");

  /** A related resource that is supplanted, displaced, or superseded by the described resource. */
  public static final URI REPLACES = URI.create(BASE + "replaces");

  /** Temporal characteristics of the resource. */
  public static final URI TEMPORAL = URI.create(BASE + "temporal");

  /** The material or physical carrier of the resource. */
  public static final URI MEDIUM = URI.create(BASE + "medium");

  /** An alternative name for the resource. */
  public static final URI ALTERNATIVE = URI.create(BASE + "alternative");

  /** Information about who can access the resource or an indication of its security status. */
  public static final URI ACCESS_RIGHTS = URI.create(BASE + "accessRights");

  /** Date of copyright. */
  public static final URI DATE_COPYRIGHTED = URI.create(BASE + "dateCopyrighted");

  /** The nature or genre of the resource. */
  public static final URI TYPE = URI.create(BASE + "type");

  /** A class of entity for whom the resource is intended or useful. */
  public static final URI AUDIENCE = URI.create(BASE + "audience");

  /** An established standard to which the described resource conforms. */
  public static final URI CONFORMS_TO = URI.create(BASE + "conformsTo");

  /** A point or period of time associated with an event in the lifecycle of the resource. */
  public static final URI DATE = URI.create(BASE + "date");

  /** The file format, physical medium, or dimensions of the resource. */
  public static final URI FORMAT = URI.create(BASE + "format");

  /** The frequency with which items are added to a collection. */
  public static final URI ACCRUAL_PERIODICITY = URI.create(BASE + "accrualPeriodicity");

  /** A related resource. */
  public static final URI RELATION = URI.create(BASE + "relation");

  /** A statement of any changes in ownership and custody of the resource since its creation that are significant for its authenticity, integrity, and interpretation. */
  public static final URI PROVENANCE = URI.create(BASE + "provenance");

  /** A related resource that is substantially the same as the pre-existing described resource, but in another format. */
  public static final URI HAS_FORMAT = URI.create(BASE + "hasFormat");

  /** An entity responsible for making the resource available. */
  public static final URI PUBLISHER = URI.create(BASE + "publisher");

  /** A summary of the resource. */
  public static final URI ABSTRACT = URI.create(BASE + "abstract");

  /** A list of subunits of the resource. */
  public static final URI TABLE_OF_CONTENTS = URI.create(BASE + "tableOfContents");

  /** A person or organization owning or managing rights over the resource. */
  public static final URI RIGHTS_HOLDER = URI.create(BASE + "rightsHolder");

  /** The method by which items are added to a collection. */
  public static final URI ACCRUAL_METHOD = URI.create(BASE + "accrualMethod");

  /** A related resource that references, cites, or otherwise points to the described resource. */
  public static final URI IS_REFERENCED_BY = URI.create(BASE + "isReferencedBy");

  /** A legal document giving official permission to do something with the resource. */
  public static final URI LICENSE = URI.create(BASE + "license");

  /** A process, used to engender knowledge, attitudes and skills, that the described resource is designed to support. */
  public static final URI INSTRUCTIONAL_METHOD = URI.create(BASE + "instructionalMethod");

  /** Date (often a range) of validity of a resource. */
  public static final URI VALID = URI.create(BASE + "valid");

  /** A related resource that requires the described resource to support its function, delivery, or coherence. */
  public static final URI IS_REQUIRED_BY = URI.create(BASE + "isRequiredBy");
}
