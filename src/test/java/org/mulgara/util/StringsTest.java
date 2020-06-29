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

package org.mulgara.util;

import java.net.URI;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import static org.mulgara.util.Strings.*;

public class StringsTest extends TestCase {

  URI empty = URI.create("");
  URI local = URI.create("name");
  URI shortlocal = URI.create("#");
  URI type = URI.create("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
  URI nsonly = URI.create("http://www.w3.org/1999/02/22-rdf-syntax-ns#");

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testParseQName() {
    assertEquals(new QName(""), parseQName(empty));
    assertEquals(new QName("name"), parseQName(local));
    assertEquals(new QName("#"), parseQName(shortlocal));
    assertEquals(new QName("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "type"), parseQName(type));
    assertEquals(new QName("http://www.w3.org/1999/02/22-rdf-syntax-ns#"), parseQName(nsonly));
  }

  public void testStartOfName() {
    assertEquals(0, startOfName(empty.toString()));
    assertEquals(0, startOfName(local.toString()));
    assertEquals(1, startOfName(shortlocal.toString()));
    assertEquals(43, startOfName(type.toString()));
    assertEquals(43, startOfName(nsonly.toString()));
  }

}
