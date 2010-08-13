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
import org.mulgara.mrg.PackedGraphImpl;
import org.mulgara.mrg.Triple;

/**
 * Unit test for simple App.
 */
public class PackedGraphImplTest extends GraphTest {


  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public PackedGraphImplTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    TestSuite tests = new TestSuite();
    tests.addTest(new PackedGraphImplTest("testCreate"));
    tests.addTest(new PackedGraphImplTest("testProps"));
    tests.addTest(new PackedGraphImplTest("testValues"));
    tests.addTest(new PackedGraphImplTest("testValue"));
    tests.addTest(new PackedGraphImplTest("testList"));
    tests.addTest(new PackedGraphImplTest("testSubject"));
    return tests;
  }

  protected Graph getGraph(Collection<Triple> triples) {
    return new PackedGraphImpl(triples);
  }
}
