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

import org.mulgara.rdf.Uri;

/**
 * A class for holding the Really Simple Syndication vocabulary.
 */
public class RSS {

  /** The URI of the RSS name space. */
  public static final Uri BASE_URI = Uri.create(org.mulgara.rdf.vocab.uri.RSS.BASE);

  public static final Uri CHANNEL = new Uri(org.mulgara.rdf.vocab.uri.RSS.CHANNEL);
  public static final Uri ITEM = new Uri(org.mulgara.rdf.vocab.uri.RSS.ITEM);
  public static final Uri DESCRIPTION = new Uri(org.mulgara.rdf.vocab.uri.RSS.DESCRIPTION);
  public static final Uri IMAGE = new Uri(org.mulgara.rdf.vocab.uri.RSS.IMAGE);
  public static final Uri ITEMS = new Uri(org.mulgara.rdf.vocab.uri.RSS.ITEMS);
  public static final Uri LINK = new Uri(org.mulgara.rdf.vocab.uri.RSS.LINK);
  public static final Uri NAME = new Uri(org.mulgara.rdf.vocab.uri.RSS.NAME);
  public static final Uri TEXTINPUT = new Uri(org.mulgara.rdf.vocab.uri.RSS.TEXTINPUT);
  public static final Uri TITLE = new Uri(org.mulgara.rdf.vocab.uri.RSS.TITLE);
  public static final Uri URL = new Uri(org.mulgara.rdf.vocab.uri.RSS.URL);

}
