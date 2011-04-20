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

package org.mulgara.mrg;

import java.util.Date;

import org.mulgara.mrg.vocab.uri.XSD;

import junit.framework.TestCase;

/**
 * Tests literal data types and parsing.
 * Currently only tests dateTimes as these are the most complex
 * @author Paul Gearon
 * @date Apr 20, 2011
 */
public class LiteralTest extends TestCase {

  static final long A_TIME = 1303316725870L;
  
  static final String LEXICAL_TIME = "2011-04-20T12:25:14-04:00";

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testConvertDateTime() {
    Date d = new Date(A_TIME);
    Literal l = XSDMapper.literal(d);
    assertEquals(d, l.toJava());
  }

  public void testParseDateTime() {
    Literal l = new Literal(LEXICAL_TIME, XSD.DATE_TIME);
    assertEquals(LEXICAL_TIME, l.getText());
  }

}
