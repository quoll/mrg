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
public class N3Test extends ParseTest {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public N3Test(String testName) {
    super(testName);
  }


  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(N3Test.class);
  }

  protected GraphParser getParser(String document) throws Exception {
    return new N3GraphParser(document);
  }

  protected String getDocument() {
    return n3doc;
  }


  static final String n3doc = "@prefix foaf:       <http://xmlns.com/foaf/0.1/> .\n" +
    "@prefix eg:         <http://biometrics.example/ns#> .\n" +
    "@prefix xsd:        <http://www.w3.org/2001/XMLSchema#> .\n\n" +
    "_:a  foaf:name       \"Alice Smith\".\n" +
    "_:a  eg:shoeSize     \"9.5\"^^xsd:float .\n\n" +
    "_:b  foaf:name       \"Bob Collins\".\n" +
    "_:b  eg:shoeSize     \"42\"^^xsd:integer .\n\n" +
    "_:c  foaf:name       \"Bob Smith\".\n" +
    "_:c  eg:shoeSize     \"32\"^^xsd:integer .\n\n" +
    "_:d  foaf:name       \"Bruce Campbell\".\n" +
    "_:d  eg:shoeSize     \"10\"^^xsd:integer .\n\n";


}
