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

package org.mulgara.mrg.vocab.uri;

import java.net.URI;

/**
 * A class for holding the RDFS vocabulary.
 * TODO: incomplete
 */
public class RDFS {

  /** The QName prefix for RDFS */
  public static final String PREFIX = "rdfs";

  /** The RDFS namespace */
  public static final String BASE = "http://www.w3.org/2000/01/rdf-schema#";

  /** The Class type. */
  public static final URI CLASS = URI.create(BASE + "Class");

  /** The comment for a resource. */
  public static final URI COMMENT = URI.create(BASE + "comment");

  /** Further information about a resource. */
  public static final URI SEE_ALSO = URI.create(BASE + "seeAlso");

}
