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

package org.mulgara.rdf.vocab.uri;

import java.net.URI;

/**
 * A class for holding the Result Set vocabulary.
 */
public class RS { 

  /** The QName prefix for ResultSet */
  public static final String pREFIX = "rs";

  /** The ResultSet namespace. */
  public static final String BASE = "http://www.w3.org/2001/sw/DataAccess/tests/result-set#";

  /** The type for result sets */
  public static final URI RESULT_SET = URI.create(BASE + "ResultSet");

  /** The property for refering to result variables */
  public static final URI RESULT_VARIABLE = URI.create(BASE + "resultVariable");

  /** The property for identifying solutions */
  public static final URI SOLUTION = URI.create(BASE + "solution");

  /** The property for identifying bindings for a solution */
  public static final URI BINDING = URI.create(BASE + "binding");

  /** The property for linking a binding to its variable name */
  public static final URI VARIABLE = URI.create(BASE + "variable");

  /** The property for linking a binding to its value */
  public static final URI VALUE = URI.create(BASE + "value");

  /** The property for indexing a binding in order */
  public static final URI INDEX = URI.create(BASE + "index");

  /** The property for linking a boolean result to results */
  public static final URI BOOLEAN = URI.create(BASE + "boolean");

}
