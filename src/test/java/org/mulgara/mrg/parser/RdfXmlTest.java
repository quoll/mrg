package org.mulgara.mrg.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;
import java.net.URI;

import org.mulgara.mrg.*;
import org.mulgara.mrg.parser.GraphParser;
import org.mulgara.mrg.parser.XMLGraphParser;

/**
 * Unit test for simple App.
 */
public class RdfXmlTest extends ParseTest {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public RdfXmlTest(String testName) {
    super(testName);
  }


  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(RdfXmlTest.class);
  }

  protected GraphParser getParser(String document) throws Exception {
    return new XMLGraphParser(document);
  }

  protected String getDocument() {
    return xmlDoc;
  }


  static final String xmlDoc = "<?xml version=\"1.0\"?>\n" +
      "<!DOCTYPE rdf:RDF [\n" +
      "<!ENTITY rdf 'http://www.w3.org/1999/02/22-rdf-syntax-ns#'>\n" +
      "<!ENTITY ns1 'http://xmlns.com/foaf/0.1/'>\n" +
      "<!ENTITY owl 'http://www.w3.org/2002/07/owl#'>\n" +
      "<!ENTITY dc 'http://purl.org/dc/elements/1.1/'>\n" +
      "<!ENTITY ns0 'foo:'>\n" +
      "<!ENTITY xsd 'http://www.w3.org/2001/XMLSchema#'>\n" +
      "<!ENTITY ns2 'http://biometrics.example/ns#'>\n" +
      "<!ENTITY rdfs 'http://www.w3.org/2000/01/rdf-schema#'>]>\n\n" +

      "<rdf:RDF\n" +
      "    xmlns:rdf=\"&rdf;\"\n" +
      "    xmlns:ns1=\"&ns1;\"\n" +
      "    xmlns:owl=\"&owl;\"\n" +
      "    xmlns:dc=\"&dc;\"\n" +
      "    xmlns:ns0=\"&ns0;\"\n" +
      "    xmlns:xsd=\"&xsd;\"\n" +
      "    xmlns:ns2=\"&ns2;\"\n" +
      "    xmlns:rdfs=\"&rdfs;\">\n\n" +

      "  <rdf:Description rdf:about=\"&ns0;foo\">\n" +
      "    <ns0:bar rdf:resource=\"&ns0;baz\"/>\n" +
      "  </rdf:Description>\n\n" +

      "  <rdf:Description rdf:nodeID=\"node197\">\n" +
      "    <ns2:shoeSize rdf:datatype=\"&xsd;float\">9.5</ns2:shoeSize>\n" +
      "    <ns1:name>Alice Smith</ns1:name>\n" +
      "  </rdf:Description>\n\n" +

      "  <rdf:Description rdf:nodeID=\"node198\">\n" +
      "    <ns2:shoeSize rdf:datatype=\"&xsd;integer\">32</ns2:shoeSize>\n" +
      "    <ns1:name>Bob Smith</ns1:name>\n" +
      "  </rdf:Description>\n\n" +

      "  <rdf:Description rdf:nodeID=\"node199\">\n" +
      "    <ns2:shoeSize rdf:datatype=\"&xsd;integer\">42</ns2:shoeSize>\n" +
      "    <ns1:name>Bob Collins</ns1:name>\n" +
      "  </rdf:Description>\n\n" +

      "  <rdf:Description rdf:nodeID=\"node200\">\n" +
      "    <ns2:shoeSize rdf:datatype=\"&xsd;integer\">10</ns2:shoeSize>\n" +
      "    <ns1:name>Bruce Campbell</ns1:name>\n" +
      "  </rdf:Description>\n\n" +

      "</rdf:RDF>\n";


}
