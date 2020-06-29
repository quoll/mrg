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

import junit.framework.TestCase;

import java.util.*;
import java.net.URI;

import org.mulgara.mrg.*;
import org.mulgara.mrg.parser.GraphParser;

/**
 * Unit test for simple App.
 */
public abstract class ParseTest extends TestCase {

  private Uri name = Uri.create("http://xmlns.com/foaf/0.1/name");
  private Uri shoeSize = Uri.create("http://biometrics.example/ns#shoeSize");
  private URI integer = URI.create("http://www.w3.org/2001/XMLSchema#integer");

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ParseTest(String testName) {
    super(testName);
  }

  protected abstract GraphParser getParser(String document) throws Exception;

  protected abstract Graph parse(String document) throws Exception;

  protected abstract String getDocument();

  /**
   * Test that graphs can be created
   */
  public void testLoad() throws Exception {
    @SuppressWarnings("unused")
    GraphParser p = getParser(getDocument());
  }

  /**
   * Test the data in the graph
   */
  public void testDataLoad() throws Exception {
    verifyData(constructionLoad());
  }

  /**
   * Test the static parse function
   */
  public void testLoadFunction() throws Exception {
    verifyData(parse(getDocument()));
  }

  ///////////////// end of tests ////////////////

  /**
   * Load a graph using the parser constructor/
   */
  Graph constructionLoad() throws Exception {
    GraphParser p = getParser(getDocument());
    return p.getGraph();
  }

  /**
   * Tests the composition of a graph.
   * @param g The graph to test.
   */
  void verifyData(Graph g) throws Exception {
    List<SubjectNode> s = g.getSubjects(name, new Literal("Bruce Campbell"));
    assertEquals(s.size(), 1);
    Node n = s.get(0);
    assertTrue(n instanceof Bnode);
    Bnode d = (Bnode)n;
    ObjectNode ten = g.getValue(d, shoeSize);
    assertTrue(ten instanceof Literal);
    assertEquals("10", ((Literal)ten).getText());
    assertEquals(integer, ((Literal)ten).getType());
  }
}
