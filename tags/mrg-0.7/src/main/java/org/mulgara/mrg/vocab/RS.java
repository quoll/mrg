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
 * A class for holding the Result Set vocabulary.
 */
public class RS { 

  /** The URI of the RS name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.mrg.vocab.uri.RS.BASE);

  /** The type for result sets */
  public static final Uri RESULT_SET = new Uri(org.mulgara.mrg.vocab.uri.RS.RESULT_SET);

  /** The property for refering to result variables */
  public static final Uri RESULT_VARIABLE = new Uri(org.mulgara.mrg.vocab.uri.RS.RESULT_VARIABLE);

  /** The property for identifying solutions */
  public static final Uri SOLUTION = new Uri(org.mulgara.mrg.vocab.uri.RS.SOLUTION);

  /** The property for identifying bindings for a solution */
  public static final Uri BINDING = new Uri(org.mulgara.mrg.vocab.uri.RS.BINDING);

  /** The property for linking a binding to its variable name */
  public static final Uri VARIABLE = new Uri(org.mulgara.mrg.vocab.uri.RS.VARIABLE);

  /** The property for linking a binding to its value */
  public static final Uri VALUE = new Uri(org.mulgara.mrg.vocab.uri.RS.VALUE);

  /** The property for indexing a binding in order */
  public static final Uri INDEX = new Uri(org.mulgara.mrg.vocab.uri.RS.INDEX);

  /** The property for linking a boolean result to results */
  public static final Uri BOOLEAN = new Uri(org.mulgara.mrg.vocab.uri.RS.BOOLEAN);

}
