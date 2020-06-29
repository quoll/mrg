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

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public interface GraphWriter {

  /**
   * Registers a set of prefix/namespace pairs to use when writing the graph.
   * @param ns A map of prefix strings to namespace URIs.
   */
  void setNamespaces(Map<String,URI> ns);

  /**
   * Registers a set of prefix/namespace pairs to use when writing the graph.
   * @param ns A map of prefix strings to namespace strings.
   * @throws URISyntaxException If one or more of the namespaces is not a valid URI.
   */
  void setNamespacesByString(Map<String,String> ns) throws URISyntaxException;

  /**
   * Clears out the prefix/namespace set.
   */
  void resetNamespaces();

  /**
   * Scans the URIs in the graph to find all the namespaces.
   * Namespace prefixes will be automatically generated, except
   * where namespaces are recognized.
   */
  void scanNamespaces();

  /**
   * Gets a mapping of prefixes to their namespaces.
   * @return a map with prefixes as keys and namespace URIs as values.
   */
  Map<String,URI> getNamespaces();

  /**
   * Writes the graph to a stream.
   * @param out The stream to write the graph to.
   * @throws IOException
   */
  void writeTo(OutputStream out) throws IOException;
}
