package org.mulgara.mrg;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.net.URI;
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
