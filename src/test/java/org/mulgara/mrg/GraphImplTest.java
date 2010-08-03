package org.mulgara.mrg;

import junit.framework.Test;
import junit.framework.TestCase;
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
    return tests;
  }


  protected Graph getGraph(Collection<Triple> triples) {
    return new GraphImpl(triples);
  }
}
