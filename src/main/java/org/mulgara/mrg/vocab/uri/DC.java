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
 * A class for holding the Dublin Core vocabulary.
 */
public class DC {

  /** The QName prefix for DC */
  public static final String PREFIX = "dc";

  /** The DC namespace */
  public static final String BASE = "http://purl.org/dc/elements/1.1/";

  /** A point or period of time associated with an event in the lifecycle of the resource. */
  public static final URI DATE = URI.create(BASE + "date");

  /** The spatial or temporal topic of the resource, the spatial applicability of the resource, or the jurisdiction under which the resource is relevant. */
  public static final URI COVERAGE = URI.create(BASE + "coverage");

  /** The nature or genre of the resource. */
  public static final URI TYPE = URI.create(BASE + "type");

  /** A name given to the resource. */
  public static final URI TITLE = URI.create(BASE + "title");

  /** An entity primarily responsible for making the resource. */
  public static final URI CREATOR = URI.create(BASE + "creator");

  /** The file format, physical medium, or dimensions of the resource. */
  public static final URI FORMAT = URI.create(BASE + "format");

  /** Information about rights held in and over the resource. */
  public static final URI RIGHTS = URI.create(BASE + "rights");

  /** A related resource from which the described resource is derived. */
  public static final URI SOURCE = URI.create(BASE + "source");

  /** A related resource. */
  public static final URI RELATION = URI.create(BASE + "relation");

  /** An entity responsible for making the resource available. */
  public static final URI PUBLISHER = URI.create(BASE + "publisher");

  /** An unambiguous reference to the resource within a given context. */
  public static final URI IDENTIFIER = URI.create(BASE + "identifier");

  /** An account of the resource. */
  public static final URI DESCRIPTION = URI.create(BASE + "description");

  /** A language of the resource. */
  public static final URI LANGUAGE = URI.create(BASE + "language");

  /** An entity responsible for making contributions to the resource. */
  public static final URI CONTRIBUTOR = URI.create(BASE + "contributor");

  /** The topic of the resource. */
  public static final URI SUBJECT = URI.create(BASE + "subject");
  
}
