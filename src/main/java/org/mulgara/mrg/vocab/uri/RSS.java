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
 * A class for holding the Really Simple Syndication vocabulary.
 */
public class RSS {

  /** The QName prefix for DC */
  public static final String PREFIX = "rss";

  /** The RSS namespace */
  public static final String BASE = "http://purl.org/rss/1.0/";

  public static final URI CHANNEL = URI.create(BASE + "channel");
  public static final URI ITEM = URI.create(BASE + "item");
  public static final URI DESCRIPTION = URI.create(BASE + "description");
  public static final URI IMAGE = URI.create(BASE + "image");
  public static final URI ITEMS = URI.create(BASE + "items");
  public static final URI LINK = URI.create(BASE + "link");
  public static final URI NAME = URI.create(BASE + "name");
  public static final URI TEXTINPUT = URI.create(BASE + "textinput");
  public static final URI TITLE = URI.create(BASE + "title");
  public static final URI URL = URI.create(BASE + "url");

}
