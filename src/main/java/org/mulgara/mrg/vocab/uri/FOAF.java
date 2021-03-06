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
 * A class for holding the FOAF vocabulary.
 * TODO: incomplete
 */
public class FOAF {

  /** The QName prefix for FOAF */
  public static final String PREFIX = "foaf";

  /** The DC namespace */
  public static final String BASE = "http://xmlns.com/foaf/0.1/";

  /** The first name relationship. */
  public static final URI FIRST_NAME = URI.create(BASE + "firstName");

  /** The name relationship. */
  public static final URI NAME = URI.create(BASE + "name");

  /** The knows relationship. */
  public static final URI KNOWS = URI.create(BASE + "knows");

  /** The Person class. */
  public static final URI PERSON = URI.create(BASE + "Person");

}
