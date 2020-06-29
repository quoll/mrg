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
 * A class for holding the Dublin Core vocabulary.
 */
public class DC {

  /** The URI of the DC name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.DC.BASE);

  /** A point or period of time associated with an event in the lifecycle of the resource. */
  public static final Uri DATE = new Uri(org.mulgara.mrg.vocab.uri.DC.DATE);

  /** The spatial or temporal topic of the resource, the spatial applicability of the resource, or the jurisdiction under which the resource is relevant. */
  public static final Uri COVERAGE = new Uri(org.mulgara.mrg.vocab.uri.DC.COVERAGE);

  /** The nature or genre of the resource. */
  public static final Uri TYPE = new Uri(org.mulgara.mrg.vocab.uri.DC.TYPE);

  /** A name given to the resource. */
  public static final Uri TITLE = new Uri(org.mulgara.mrg.vocab.uri.DC.TITLE);

  /** An entity primarily responsible for making the resource. */
  public static final Uri CREATOR = new Uri(org.mulgara.mrg.vocab.uri.DC.CREATOR);

  /** The file format, physical medium, or dimensions of the resource. */
  public static final Uri FORMAT = new Uri(org.mulgara.mrg.vocab.uri.DC.FORMAT);

  /** Information about rights held in and over the resource. */
  public static final Uri RIGHTS = new Uri(org.mulgara.mrg.vocab.uri.DC.RIGHTS);

  /** A related resource from which the described resource is derived. */
  public static final Uri SOURCE = new Uri(org.mulgara.mrg.vocab.uri.DC.SOURCE);

  /** A related resource. */
  public static final Uri RELATION = new Uri(org.mulgara.mrg.vocab.uri.DC.RELATION);

  /** An entity responsible for making the resource available. */
  public static final Uri PUBLISHER = new Uri(org.mulgara.mrg.vocab.uri.DC.PUBLISHER);

  /** An unambiguous reference to the resource within a given context. */
  public static final Uri IDENTIFIER = new Uri(org.mulgara.mrg.vocab.uri.DC.IDENTIFIER);

  /** An account of the resource. */
  public static final Uri DESCRIPTION = new Uri(org.mulgara.mrg.vocab.uri.DC.DESCRIPTION);

  /** A language of the resource. */
  public static final Uri LANGUAGE = new Uri(org.mulgara.mrg.vocab.uri.DC.LANGUAGE);

  /** An entity responsible for making contributions to the resource. */
  public static final Uri CONTRIBUTOR = new Uri(org.mulgara.mrg.vocab.uri.DC.CONTRIBUTOR);

  /** The topic of the resource. */
  public static final Uri SUBJECT = new Uri(org.mulgara.mrg.vocab.uri.DC.SUBJECT);

}
