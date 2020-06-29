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

package org.mulgara.mrg.parser;

import org.mulgara.mrg.WritableGraph;


/**
 * Parses the contents of a stream into a graph representation.
 */
public interface GraphParser {

  /**
   * Gets the parsed graph.
   * @return a writable graph that has been parsed.
   */
  public WritableGraph getGraph();

  /**
   * Return the number of rows parsed.
   * @return the number of processed triples. This may be larger than the size of the graph if there are duplicates.
   */
  public long getProcessedRows();

}
