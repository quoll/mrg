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
 * TODO: incomplete
 */
public class RDFS {

  /** The URI of the RDFS name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.RDFS.BASE);

  /** The Class type. */
  public static final Uri CLASS = new Uri(org.mulgara.mrg.vocab.uri.RDFS.CLASS);

  /** The comment for a resource. */
  public static final Uri COMMENT = new Uri(org.mulgara.mrg.vocab.uri.RDFS.COMMENT);

  /** Further information about a resource. */
  public static final Uri SEE_ALSO = new Uri(org.mulgara.mrg.vocab.uri.RDFS.SEE_ALSO);

}
