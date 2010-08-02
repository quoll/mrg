package org.mulgara.rdf.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;
import java.net.URI;

import org.mulgara.rdf.*;

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

  protected abstract String getDocument();

  /**
   * Test that graphs can be created
   */
  public void testLoad() throws Exception {
    GraphParser p = getParser(getDocument());
  }

  /**
   * Test the data in the graph
   */
  public void testDataLoad() throws Exception {
    GraphParser p = getParser(getDocument());
    Graph g = p.getGraph();
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
