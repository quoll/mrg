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

package org.mulgara.mrg;

import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.Collection;

import org.mulgara.mrg.Graph;
import org.mulgara.mrg.GraphImpl;
import org.mulgara.mrg.Triple;

/**
 * Unit test for simple App.
 */
public class GraphImplTest extends GraphTest {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public GraphImplTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    TestSuite tests = new TestSuite();
    tests.addTest(new GraphImplTest("testCreate"));
    tests.addTest(new GraphImplTest("testProps"));
    tests.addTest(new GraphImplTest("testValues"));
    tests.addTest(new GraphImplTest("testValue"));
    tests.addTest(new GraphImplTest("testList"));
    tests.addTest(new GraphImplTest("testSubject"));
    tests.addTest(new GraphImplTest("testMerge"));
    tests.addTest(new GraphImplTest("testUnion"));
    tests.addTest(new GraphImplTest("testMatch"));
    tests.addTest(new GraphImplTest("testSubgraph"));
    return tests;
  }


  protected Graph getGraph(Collection<Triple> triples) {
    return new GraphImpl(triples);
  }
}
