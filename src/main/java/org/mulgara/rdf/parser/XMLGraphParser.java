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

package org.mulgara.rdf.parser;

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
import org.mulgara.jena.rdf.arp.impl.Location;

import org.mulgara.rdf.Bnode;
import org.mulgara.rdf.Node;
import org.mulgara.rdf.Literal;
import org.mulgara.rdf.Uri;
import org.mulgara.rdf.SubjectNode;
import org.mulgara.rdf.PredicateNode;
import org.mulgara.rdf.ObjectNode;
import org.mulgara.rdf.WritableGraph;
import org.mulgara.rdf.Graph;
import org.mulgara.rdf.GraphImpl;

import static org.mulgara.util.Strings.toUtf8Bytes;
import static org.mulgara.jena.rdf.arp.ARPErrorNumbers.EM_ERROR;

/**
 * Parses the contents of an RDF/XML file into a graph representation.
 */
public class XMLGraphParser implements StatementHandler, ErrorHandler, GraphParser {

  /** Logging for this class. */
  private static final Logger logger = Logger.getLogger(XMLGraphParser.class.getName());

  /** The graph that is parsed from the input data. */
  private WritableGraph graph = new GraphImpl();

  /** The number of triples parsed. */
  private long triples = 0;

  /**
   * Create a graph from a string.
   * @param s The string containing the RDF/XML.
   */
  public XMLGraphParser(String s) throws ParseException, IOException {
    this(new ByteArrayInputStream(toUtf8Bytes(s)));
  }

  /**
   * Create a graph from an InputStream.
   * @param is The input stream with the graph data.
   */
  public XMLGraphParser(InputStream is) throws ParseException, IOException {
    ARP arp = new ARP();

    ARPOptions options = arp.getOptions();
    options.setEmbedding(true);
    options.setLaxErrorMode();

    ARPHandlers handlers = arp.getHandlers();
    handlers.setErrorHandler(this);
    handlers.setStatementHandler(this);

    try {
      arp.load(is);
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new ParseException(e);
    }
  }

  /**
   * Gets the parsed graph.
   */
  public WritableGraph getGraph() {
    return graph;
  }


  /**
   * Return the number of rows parsed.
   */
  public long getProcessedRows() {
    return (int)triples;
  }


  /**
   * General error logging.
   */
  protected void error(String msg) {
    logger.log(Level.WARNING, msg);
  }

  /**
   * Log an exception as an error.
   */
  protected void error(Exception arg0, String arg1) {
    error("Error in data. " + arg0 + ": " + arg1);
  }

  /**
   * Called to record a statement in the input.
   * @param subject The subject of the triple.
   * @param predicate The predicate of the triple.
   * @param obj The literal object of the triple.
   */
  public void statement(AResource subject, AResource predicate, ALiteral obj) {
    addTriple(toNode(subject), toNode(predicate), toNode(obj));
  }

  /**
   * Called to record a statement in the input.
   * @param subject The subject of the triple.
   * @param predicate The predicate of the triple.
   * @param obj The object resource of the triple.
   */
  public void statement(AResource subject, AResource predicate, AResource obj) {
    addTriple(toNode(subject), toNode(predicate), toNode(obj));
  }

  /**
   * Indicates an error detected in the XML stream.
   * @param e The exception describing the error condition.
   */
  public void error(SAXParseException e) {
    error("Graph parse error: line " + e.getLineNumber() + ", column " + e.getColumnNumber() + ": " + e.getMessage());
  }

  /**
   * Indicates an unrecoverable error in the XML stream.
   * @param e The exception describing the error condition.
   * @throws ParseException The exception that will halt the parsing process after this method has completed.
   */
  public void fatalError(SAXParseException e) throws SAXException {
    error("Graph parse error: line " + e.getLineNumber() + ", column " + e.getColumnNumber() + ": " + e.getMessage());
    throw e;
  }

  /**
   * Indicates a warning condition in the XML stream.
   * @param e The exception describing the warning condition.
   */
  public void warning(SAXParseException e) {
    error("Graph parse warning: line " + e.getLineNumber() + ", column " + e.getColumnNumber() + ": " + e.getMessage());
  }

  /**
   * Internal method to add a triple to the graph.
   * @param s The subject of the triple.
   * @param p The predicate of the triple.
   * @param o The object of the triple.
   */
  private void addTriple(Node s, Node p, Node o) {
    graph.insert((SubjectNode)s, (PredicateNode)p, (ObjectNode)o);
  }

  /**
   * Converts a parsed literal into an RDF node.
   * @param l The AST element representing the literal.
   * @return A new Node representation of the literal.
   */
  private Node toNode(ALiteral l) {
    String str = l.toString();
    Node result;

    URI datatype = null;
    try {
      String u = l.getDatatypeURI();
      datatype = (u != null) ? new URI(u) : null;
    } catch (URISyntaxException e) {
      // no readable datatype
    }
    if (datatype != null) {
      result = new Literal(str, datatype);
    } else {
      String lang = l.getLang();
      if (lang == null || lang.equals("")) {
        result = new Literal(str);
      } else {
        result = new Literal(str, lang);
      }
    }
    return result;
  }

  /**
   * Converts a parsed resource into an RDF node.
   * @param r The AST element representing the resource.
   * @return a new Node representation of the resource.
   */
  private Node toNode(AResource r) {
    try {
      return (r.isAnonymous()) ? new Bnode(r.getAnonymousID()) : new Uri(r.getURI());
    } catch (URISyntaxException e) {
      throw new InternalError("Parsed URI could not be constructed");
    }
  }

}
