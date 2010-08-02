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

package org.mulgara.rdf.vocab;

import org.mulgara.rdf.Uri;;

/**
 * A class for holding the Dublin Core vocabulary.
 * TODO: incomplete
 */
public class DC {

  /** The URI of the DC name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.rdf.vocab.uri.DC.BASE);

  /** The creator relationship. */
  public static final Uri CREATOR = new Uri(org.mulgara.rdf.vocab.uri.DC.CREATOR);

}
