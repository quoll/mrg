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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.mulgara.mrg.parser.GraphParser;
import org.mulgara.mrg.parser.N3GraphParser;

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
