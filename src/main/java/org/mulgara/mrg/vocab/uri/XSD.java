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
 * Vocabulary for XSD.
 */
public class XSD {

  /** The QName prefix for XSD */
  public static final String PREFIX = "xsd";

  /** The namespace for XSD data. */
  public static final String BASE = "http://www.w3.org/2001/XMLSchema#";

  /**
   * URI for the XML Schema <code>xsd:anySimpleType</code> datatype.
   * This is the base type for all XSD datatypes.
   */
  public static final URI ANY_SIMPLE_TYPE = URI.create(BASE + "anySimpleType");

  /** URI for the XML Schema <code>xsd:boolean</code> datatype. */
  public static final URI BOOLEAN = URI.create(BASE + "boolean");

  /** URI for the XML Schema <code>xsd:float</code> datatype. */
  public static final URI FLOAT = URI.create(BASE + "float");

  /** URI for the XML Schema <code>xsd:double</code> datatype. */
  public static final URI DOUBLE = URI.create(BASE + "double");

  /** URI for the XML Schema <code>xsd:decimal</code> datatype. */
  public static final URI DECIMAL = URI.create(BASE + "decimal");

  /** URI for the XML Schema <code>xsd:integer</code> datatype. Subtype of {@link #DECIMAL}. */
  public static final URI INTEGER = URI.create(BASE + "integer");

  /** URI for the XML Schema <code>xsd:long</code> datatype. Subtype of {@link #INTEGER}. */
  public static final URI LONG = URI.create(BASE + "long");

  /** URI for the XML Schema <code>xsd:int</code> datatype. Subtype of {@link #LONG}. */
  public static final URI INT = URI.create(BASE + "int");

  /** URI for the XML Schema <code>xsd:short</code> datatype. Subtype of {@link #INT}. */
  public static final URI SHORT = URI.create(BASE + "short");

  /** URI for the XML Schema <code>xsd:byte</code> datatype. Subtype of {@link #SHORT}. */
  public static final URI BYTE = URI.create(BASE + "byte");

  /** URI for the XML Schema <code>xsd:nonPositiveInteger</code> datatype. Subtype of {@link #INTEGER}. */
  public static final URI NON_POSITIVE_INTEGER = URI.create(BASE + "nonPositiveInteger");

  /** URI for the XML Schema <code>xsd:negativeInteger</code> datatype. Subtype of {@link #NON_POSITIVE_INTEGER}. */
  public static final URI NEGATIVE_INTEGER = URI.create(BASE + "negativeInteger");

  /** URI for the XML Schema <code>xsd:nonNegativeInteger</code> datatype. Subtype of {@link #INTEGER}. */
  public static final URI NON_NEGATIVE_INTEGER = URI.create(BASE + "nonNegativeInteger");

  /** URI for the XML Schema <code>xsd:positiveInteger</code> datatype. Subtype of {@link #NON_NEGATIVE_INTEGER}. */
  public static final URI POSITIVE_INTEGER = URI.create(BASE + "positiveInteger");

  /** URI for the XML Schema <code>xsd:unsignedLong</code> datatype. Subtype of {@link #NON_NEGATIVE_INTEGER}. */
  public static final URI UNSIGNED_LONG = URI.create(BASE + "unsignedLong");

  /** URI for the XML Schema <code>xsd:unsignedInt</code> datatype. Subtype of {@link #UNSIGNED_LONG}. */
  public static final URI UNSIGNED_INT = URI.create(BASE + "unsignedInt");

  /** URI for the XML Schema <code>xsd:unsignedShort</code> datatype. Subtype of {@link #UNSIGNED_SHORT}. */
  public static final URI UNSIGNED_SHORT = URI.create(BASE + "unsignedShort");

  /** URI for the XML Schema <code>xsd:unsignedByte</code> datatype. Subtype of {@link #UNSIGNED_BYTE}. */
  public static final URI UNSIGNED_BYTE = URI.create(BASE + "unsignedByte");

  /** URI for the XML Schema <code>xsd:date</code> datatype. */
  public static final URI DATE = URI.create(BASE + "date");

  /** URI for the XML Schema <code>xsd:dateTime</code> datatype. */
  public static final URI DATE_TIME = URI.create(BASE + "dateTime");

  /** URI for the XML Schema <code>xsd:time</code> datatype. */
  public static final URI TIME = URI.create(BASE + "time");

  /** URI for the XML Schema <code>xsd:QName</code> datatype. */
  public static final URI QNAME = URI.create(BASE + "QName");

  /** URI for the XML Schema <code>xsd:anyURI</code> datatype. */
  public static final URI ANY_URI = URI.create(BASE + "anyURI");

  /** URI for the XML Schema <code>xsd:hexBinary</code> datatype. */
  public static final URI HEX_BINARY = URI.create(BASE + "hexBinary");

  /** URI for the XML Schema <code>xsd:base64Binary</code> datatype. */
  public static final URI BASE64_BINARY = URI.create(BASE + "base64Binary");

  /** URI for the XML Schema <code>xsd:string</code> datatype. */
  public static final URI STRING = URI.create(BASE + "string");

  /** URI for the XML Schema <code>xsd:normalizedString</code> datatype. Subtype of {@link #STRING}. */
  public static final URI NORMALIZED_STRING = URI.create(BASE + "normalizedString");

  /** URI for the XML Schema <code>xsd:token</code> datatype. Subtype of {@link #NORMALIZED_STRING}. */
  public static final URI TOKEN = URI.create(BASE + "token");

  /** URI for the XML Schema <code>xsd:language</code> datatype. Subtype of {@link #TOKEN}. */
  public static final URI LANGUAGE = URI.create(BASE + "language");

  /** URI for the XML Schema <code>xsd:Name</code> datatype. Subtype of {@link #TOKEN}. */
  public static final URI NAME = URI.create(BASE + "Name");

  /** URI for the XML Schema <code>xsd:NCName</code> datatype. Subtype of {@link #NAME}. */
  public static final URI NCNAME = URI.create(BASE + "NCName");

  /** URI for the XML Schema <code>xsd:ID</code> datatype. Subtype of {@link #NCNAME}. */
  public static final URI ID = URI.create(BASE + "ID");

  /** URI for the XML Schema <code>xsd:IDREF</code> datatype. Subtype of {@link #NCNAME}. */
  public static final URI IDREF = URI.create(BASE + "IDREF");

  /** URI for the XML Schema <code>xsd:IDREFS</code> datatype. Derived by list from {@link #IDREF}. */
  public static final URI IDREFS = URI.create(BASE + "IDREFS");

  /** URI for the XML Schema <code>xsd:ENTITY</code> datatype. Subtype of {@link #NCNAME}. */
  public static final URI ENTITY = URI.create(BASE + "ENTITY");

  /** URI for the XML Schema <code>xsd:ENTITIES</code> datatype. Derived by list from {@link #ENTITY}. */
  public static final URI ENTITIES = URI.create(BASE + "ENTITIES");

  /** URI for the XML Schema <code>xsd:NMTOKEN</code> datatype. Subtype of {@link #TOKEN}. */
  public static final URI NMTOKEN = URI.create(BASE + "NMTOKEN");

  /** URI for the XML Schema <code>xsd:NMTOKENS</code> datatype. Derived by list from {@link #NMTOKEN}. */
  public static final URI NMTOKENS = URI.create(BASE + "NMTOKENS");

  /** URI for the XML Schema <code>xsd:duration</code> datatype. */
  public static final URI DURATION = URI.create(BASE + "duration");

  /** URI for the XML Schema <code>xsd:gYearMonth</code> datatype. */
  public static final URI G_YEAR_MONTH = URI.create(BASE + "gYearMonth");

  /** URI for the XML Schema <code>xsd:gYear</code> datatype. */
  public static final URI G_YEAR = URI.create(BASE + "gYear");

  /** URI for the XML Schema <code>xsd:gMonthDay</code> datatype. */
  public static final URI G_MONTH_DAY = URI.create(BASE + "gMonthDay");

  /** URI for the XML Schema <code>xsd:gDay</code> datatype. */
  public static final URI G_DAY = URI.create(BASE + "gDay");

  /** URI for the XML Schema <code>xsd:gMonth</code> datatype. */
  public static final URI G_MONTH = URI.create(BASE + "gMonth");

}
