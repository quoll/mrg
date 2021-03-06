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

package org.mulgara.mrg.writer;

import java.io.OutputStream;
import java.net.URI;

import org.mulgara.mrg.Graph;
import org.mulgara.mrg.parser.GraphParser;
import org.mulgara.mrg.parser.XMLGraphParser;

public class RdfXmlWriterTest extends WriterTest {

  /**
   * Create a new graph writer.
   * @param g The graph to be written.
   * @param base The base URI to write the graph to.
   * @return A new graph writer.
   */
  GraphWriter newWriter(Graph g, URI base) {
    return new XMLWriter(g, base);
  }

  /**
   * Creates a parser for the tested output type.
   * @param s A string to be parsed
   * @return A graph parser.
   */
  GraphParser newParser(String s) throws Exception {
    return new XMLGraphParser(s);
  }

  /**
   * Asks a graph to perform an export, using the tested output type.
   * @param g The graph to export.
   * @param out The output stream to write to.
   * @param base The base URI for the export.
   */
  void export(Graph g, OutputStream out, URI base) throws Exception {
    g.exportXML(out, base);
  }

}
