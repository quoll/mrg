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
 * The RDF vocabulary
 */
public class RDF {

  /** The URI of the RDF name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.RDF.BASE);

  /** The subject is an instance of a class. */
  public static final Uri TYPE = new Uri(org.mulgara.mrg.vocab.uri.RDF.TYPE);

  /** The class of unordered containers. */
  public static final Uri BAG = new Uri(org.mulgara.mrg.vocab.uri.RDF.BAG);

  /** The class of ordered containers. */
  public static final Uri SEQ = new Uri(org.mulgara.mrg.vocab.uri.RDF.SEQ);

  /** The class of containers of alternatives. */
  public static final Uri ALT = new Uri(org.mulgara.mrg.vocab.uri.RDF.ALT);

  /** The class of RDF statements. */
  public static final Uri STATEMENT = new Uri(org.mulgara.mrg.vocab.uri.RDF.STATEMENT);

  /** The class of RDF properties. */
  public static final Uri PROPERTY = new Uri(org.mulgara.mrg.vocab.uri.RDF.PROPERTY);

  /** The class of XML literal values. */
  public static final Uri XML_LITERAL = new Uri(org.mulgara.mrg.vocab.uri.RDF.XML_LITERAL);

  /** The class of RDF Lists. */
  public static final Uri LIST = new Uri(org.mulgara.mrg.vocab.uri.RDF.LIST);

  /** A property that appears in RDF/XML to be translated into Collection ordinals. */
  public static final Uri LI = new Uri(org.mulgara.mrg.vocab.uri.RDF.LI);

  /** The subject of the subject RDF statement. */
  public static final Uri SUBJECT = new Uri(org.mulgara.mrg.vocab.uri.RDF.SUBJECT);

  /** The predicate of the subject RDF statement. */
  public static final Uri PREDICATE = new Uri(org.mulgara.mrg.vocab.uri.RDF.PREDICATE);

  /** The object of the subject RDF statement. */
  public static final Uri OBJECT = new Uri(org.mulgara.mrg.vocab.uri.RDF.OBJECT);

  /** Idiomatic property used for structured values. */
  public static final Uri VALUE = new Uri(org.mulgara.mrg.vocab.uri.RDF.VALUE);

  /** The first item in the subject RDF list. */
  public static final Uri FIRST = new Uri(org.mulgara.mrg.vocab.uri.RDF.FIRST);

  /** The rest of the subject RDF list after the first item. */
  public static final Uri REST = new Uri(org.mulgara.mrg.vocab.uri.RDF.REST);

  /** The end of a list. */
  public static final Uri NIL = new Uri(org.mulgara.mrg.vocab.uri.RDF.NIL);

}
