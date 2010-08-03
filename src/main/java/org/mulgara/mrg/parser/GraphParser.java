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

package org.mulgara.mrg.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.mulgara.jena.rdf.arp.ALiteral;
import org.mulgara.jena.rdf.arp.ARP;
import org.mulgara.jena.rdf.arp.ARPOptions;
import org.mulgara.jena.rdf.arp.ARPHandlers;
import org.mulgara.jena.rdf.arp.AResource;
import org.mulgara.jena.rdf.arp.StatementHandler;
import org.mulgara.jena.rdf.arp.ParseException;
import org.mulgara.jena.rdf.arp.impl.Location;
import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Graph;
import org.mulgara.mrg.GraphImpl;
import org.mulgara.mrg.Literal;
import org.mulgara.mrg.Node;
import org.mulgara.mrg.ObjectNode;
import org.mulgara.mrg.PredicateNode;
import org.mulgara.mrg.SubjectNode;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.WritableGraph;


import static org.mulgara.util.Strings.toUtf8Bytes;
import static org.mulgara.jena.rdf.arp.ARPErrorNumbers.EM_ERROR;

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
