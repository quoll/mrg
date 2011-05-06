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
 * Vocabulary for XSD.
 * TODO: Not complete
 */
public class XSD {

  /** The URI of the XSD name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.XSD.BASE);

  /** URI for the XML Schema <code>xsd:float</code> datatype; */
  public static final Uri BOOLEAN = new Uri(org.mulgara.mrg.vocab.uri.XSD.BOOLEAN);

  /** URI for the XML Schema <code>xsd:float</code> datatype; */
  public static final Uri FLOAT = new Uri(org.mulgara.mrg.vocab.uri.XSD.FLOAT);

  /** URI for the XML Schema <code>xsd:double</code> datatype; */
  public static final Uri DOUBLE = new Uri(org.mulgara.mrg.vocab.uri.XSD.DOUBLE);

  /** URI for the XML Schema <code>xsd:decimal</code> datatype. */
  public static final Uri DECIMAL = new Uri(org.mulgara.mrg.vocab.uri.XSD.DECIMAL);

  /** URI for the XML Schema <code>integer</code> datatype. Subtype of {@link #DECIMAL}. */
  public static final Uri INTEGER = new Uri(org.mulgara.mrg.vocab.uri.XSD.INTEGER);

  /** URI for the XML Schema <code>long</code> datatype. Subtype of {@link #INTEGER}. */
  public static final Uri LONG = new Uri(org.mulgara.mrg.vocab.uri.XSD.LONG);

  /** URI for the XML Schema <code>int</code> datatype. Subtype of {@link #LONG}. */
  public static final Uri INT = new Uri(org.mulgara.mrg.vocab.uri.XSD.INT);

  /** URI for the XML Schema <code>short</code> datatype. Subtype of {@link #INT}. */
  public static final Uri SHORT = new Uri(org.mulgara.mrg.vocab.uri.XSD.SHORT);

  /** URI for the XML Schema <code>byte</code> datatype. Subtype of {@link #SHORT}. */
  public static final Uri BYTE = new Uri(org.mulgara.mrg.vocab.uri.XSD.BYTE);

}
