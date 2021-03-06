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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.mulgara.jena.n3.turtle.TurtleEventHandler;
import org.mulgara.jena.n3.turtle.parser.TurtleParser;
import org.mulgara.mrg.*;



import static org.mulgara.util.Strings.toUtf8Bytes;

/**
 * Parses the contents of an N3 file into a graph representation.
 */
public class N3GraphParser implements TurtleEventHandler, GraphParser {

  /** Logging for this class. */
  private static final Logger logger = Logger.getLogger(XMLGraphParser.class.getName());

  /** The graph that is parsed from the input data. */
  private final WritableGraph graph;

  /** The number of triples parsed. */
  private long triples = 0;

  /**
   * Create a graph from a string.
   * @param s The string containing the N3.
   */
  public N3GraphParser(String s) throws ParseException, IOException {
    this(s, new GraphImplFactory());
  }

  /**
   * Create a graph from a file.
   * @param f The file containing the N3.
   */
  public N3GraphParser(File f) throws ParseException, IOException {
    this(f, new GraphImplFactory());
  }

  /**
   * Create a graph from an InputStream.
   * @param is The input stream with the graph data.
   */
  public N3GraphParser(InputStream is) throws ParseException, IOException {
    this(is, new GraphImplFactory());
  }

  /**
   * Create a graph from a string.
   * @param s The string containing the N3.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public N3GraphParser(String s, GraphFactory graphFactory) throws ParseException, IOException {
    this(new ByteArrayInputStream(toUtf8Bytes(s)), graphFactory);
  }

  /**
   * Create a graph from a file.
   * @param f The file containing the N3.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public N3GraphParser(File f, GraphFactory graphFactory) throws ParseException, IOException {
    this(new FileInputStream(f), graphFactory);
  }

  /**
   * Create a graph from an InputStream.
   * @param is The input stream with the graph data.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public N3GraphParser(InputStream is, GraphFactory graphFactory) throws ParseException, IOException {
    graph = graphFactory.createGraph();
    try {
      TurtleParser parser = new TurtleParser(is);
      parser.setEventHandler(this);
      parser.parse();
    } catch (Exception e) {
      throw new ParseException("Error reading N3 from stream", e);
    }
  }

  /**
   * Create a graph from a string.
   * @param s The string containing the N3.
   */
  public static WritableGraph parse(String s) throws ParseException, IOException {
    return new N3GraphParser(s).getGraph();
  }

  /**
   * Create a graph from a file.
   * @param f The file containing the N3.
   */
  public static WritableGraph parse(File f) throws ParseException, IOException {
    return new N3GraphParser(f).getGraph();
  }

  /**
   * Create a graph from an InputStream.
   * @param is The input stream with the graph data.
   */
  public static WritableGraph parse(InputStream is) throws ParseException, IOException {
    return new N3GraphParser(is).getGraph();
  }

  /**
   * Create a graph from a string.
   * @param s The string containing the N3.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public static WritableGraph parse(String s, GraphFactory graphFactory) throws ParseException, IOException {
    return new N3GraphParser(s, graphFactory).getGraph();
  }

  /**
   * Create a graph from a file.
   * @param f The file containing the N3.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public static WritableGraph parse(File f, GraphFactory graphFactory) throws ParseException, IOException {
    return new N3GraphParser(f, graphFactory).getGraph();
  }

  /**
   * Create a graph from an InputStream.
   * @param is The input stream with the graph data.
   * @param graphFactory A mechanism for creating a graph to populate.
   */
  public static WritableGraph parse(InputStream is, GraphFactory graphFactory) throws ParseException, IOException {
    return new N3GraphParser(is, graphFactory).getGraph();
  }

  /**
   * Retrieves the ResultSet that this parser built.
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
   * Collect a triple from the parser.
   * @param line The line number that the parser has reached.
   * @param col The column number that the parser has reached.
   * @param triple The parsed triple/
   */
  public void triple(int line, int col, Triple triple) {
    try {
      graph.insert(triple);
      triples++;
    } catch (ClassCastException e) {
      error("Bad triple at line: " + line + ", column: " + col + ": " + triple);
    }
  }

  /**
   * Shouldn't need to know about prefixes at this level.
   * @param line The line number that the parser has reached.
   * @param col The column number that the parser has reached.
   * @param prefix The prefix to be mapped.
   * @param iri The IRI to map the prefix to.
   */
  public void prefix(int line, int col, String prefix, String iri) {
    // do nothing
  }

  /**
   * This function handles formulas, but we ignore them.
   * @param line The line number that the parser has reached.
   * @param col The column number that the parser has reached.
   */
  public void endFormula(int line, int col) {
    // do nothing
  }

  /**
   * This function handles formulas, but we ignore them.
   * @param line The line number that the parser has reached.
   * @param col The column number that the parser has reached.
   */
  public void startFormula(int line, int col) {
    error("Unsupported formula at line: " + line + " column: " + col);
  }
}
