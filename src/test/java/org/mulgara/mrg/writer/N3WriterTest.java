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

package org.mulgara.mrg.writer;

import static org.mulgara.mrg.writer.N3Writer.*;

import java.io.OutputStream;
import java.net.URI;

import org.mulgara.mrg.Graph;
import org.mulgara.mrg.parser.GraphParser;
import org.mulgara.mrg.parser.N3GraphParser;

public class N3WriterTest extends WriterTest {

  public void testN3escape() {
    assertEquals(n3escape("foo"), "foo");
    assertEquals(n3escape(""), "");
    assertEquals(n3escape("he said, \"hello\""), "he said, \\\"hello\\\"");
    assertEquals(n3escape("\ttwo\tcolumns"), "\\ttwo\\tcolumns");
    assertEquals(n3escape("test of the Ꭶ character"), "test of the \\u13A6 character");
    assertEquals(n3escape("fooᎦ"), "foo\\u13A6");
    assertEquals(n3escape("Ꭶ"), "\\u13A6");
    assertEquals(n3escape("he said, \"hello Ꭶ\""), "he said, \\\"hello \\u13A6\\\"");
    assertEquals(n3escape("\tthree\tᎦ\tcolumns"), "\\tthree\\t\\u13A6\\tcolumns");
    assertEquals(n3escape("test of the 𝄞 character"), "test of the \\U0001D11E character");
    assertEquals(n3escape("test of both the Ꭶ and the 𝄞 characters"), "test of both the \\u13A6 and the \\U0001D11E characters");
    assertEquals(n3escape("foo𝄞"), "foo\\U0001D11E");
    assertEquals(n3escape("𝄞"), "\\U0001D11E");
    assertEquals(n3escape("he said, \"hello 𝄞\""), "he said, \\\"hello \\U0001D11E\\\"");
    assertEquals(n3escape("\tthree\t𝄞\tcolumns"), "\\tthree\\t\\U0001D11E\\tcolumns");
  }

  public void testSimpleN3escape() {
    assertEquals(simpleN3escape("foo"), "foo");
    assertEquals(simpleN3escape(""), "");
    assertEquals(simpleN3escape("he said, \"hello\""), "he said, \\\"hello\\\"");
    assertEquals(simpleN3escape("\ttwo\tcolumns"), "\\ttwo\\tcolumns");
    assertEquals(simpleN3escape("test of the Ꭶ character"), "test of the \\u13A6 character");
  }

  /**
   * Create a new graph writer.
   * @param g The graph to be written.
   * @param base The base URI to write the graph to.
   * @return A new graph writer.
   */
  GraphWriter newWriter(Graph g, URI base) {
    return new N3Writer(g, base);
  }

  /**
   * Creates a parser for the tested output type.
   * @param s A string to be parsed
   * @return A graph parser.
   */
  GraphParser newParser(String s) throws Exception {
    return new N3GraphParser(s);
  }

  /**
   * Asks a graph to perform an export, using the tested output type.
   * @param g The graph to export.
   * @param out The output stream to write to.
   * @param base The base URI for the export.
   */
  void export(Graph g, OutputStream out, URI base) throws Exception {
    g.exportN3(out, base);
  }

}
