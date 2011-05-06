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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

/**
 * This class groups all the overloaded methods for Graph together, and keeps them
 * out of the way of the Graph implementation. Each method simply wraps its
 * parameters appropriately and passes them on to the main method.
 * 
 * Wherever a URI appears, this will be converted to a {@link Uri} reference.
 * If a String is used in place of a SubjectNode or PredicateNode, then it will
 * also be converted to a {@link Uri}, and hence is subject to a URI syntax check.
 * If a String is used in place of an ObjectNode, then it will converted to an
 * untyped literal instead. Therefore, if you need a URI in the object position,
 * you will need to create either a {@link URI} or a {@link Uri}, since a string
 * in this position will always be converted to a literal.
 * 
 * The graph explicitly implements GraphExt, since these are the only methods that
 * it will implement. However, this declaration was not required, since all
 * {@link Graph}s are also {@link GraphExt}s.
 */
public abstract class AbstractGraphExt implements Graph, GraphExt {

  public boolean isAsserted(URI s, PredicateNode p, ObjectNode o) { return isAsserted(new Uri(s), p, o); }
  public boolean isAsserted(SubjectNode s, URI p, ObjectNode o) { return isAsserted(s, new Uri(p), o); }
  public boolean isAsserted(SubjectNode s, PredicateNode p, URI o) { return isAsserted(s, p, new Uri(o)); }
  public boolean isAsserted(URI s, URI p, ObjectNode o) { return isAsserted(new Uri(s), new Uri(p), o); }
  public boolean isAsserted(URI s, PredicateNode p, URI o) { return isAsserted(new Uri(s), p, new Uri(o)); }
  public boolean isAsserted(SubjectNode s, URI p, URI o) { return isAsserted(s, new Uri(p), new Uri(o)); }
  public boolean isAsserted(URI s, URI p, URI o) { return isAsserted(new Uri(s), new Uri(p), new Uri(o)); }
  public boolean isAsserted(String s, PredicateNode p, ObjectNode o)
      throws URISyntaxException { return isAsserted(new Uri(s), p, o); }
  public boolean isAsserted(SubjectNode s, String p, ObjectNode o)
      throws URISyntaxException { return isAsserted(s, new Uri(p), o); }
  public boolean isAsserted(SubjectNode s, PredicateNode p, String o) { return isAsserted(s, p, new Literal(o)); }
  public boolean isAsserted(String s, String p, ObjectNode o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), o); }
  public boolean isAsserted(String s, PredicateNode p, String o)
      throws URISyntaxException { return isAsserted(new Uri(s), p, new Literal(o)); }
  public boolean isAsserted(SubjectNode s, String p, String o)
      throws URISyntaxException { return isAsserted(s, new Uri(p), new Literal(o)); }
  public boolean isAsserted(String s, String p, String o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Literal(o)); }
  public boolean isAsserted(String s, URI p, ObjectNode o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), o); }
  public boolean isAsserted(String s, PredicateNode p, URI o)
      throws URISyntaxException { return isAsserted(new Uri(s), p, new Uri(o)); }
  public boolean isAsserted(String s, URI p, URI o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Uri(o)); }
  public boolean isAsserted(URI s, String p, ObjectNode o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), o); }
  public boolean isAsserted(SubjectNode s, String p, URI o)
      throws URISyntaxException { return isAsserted(s, new Uri(p), new Uri(o)); }
  public boolean isAsserted(URI s, String p, URI o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Uri(o)); }
  public boolean isAsserted(URI s, PredicateNode p, String o) { return isAsserted(new Uri(s), p, new Literal(o)); }
  public boolean isAsserted(SubjectNode s, URI p, String o) { return isAsserted(s, new Uri(p), new Literal(o)); }
  public boolean isAsserted(URI s, URI p, String o) { return isAsserted(new Uri(s), new Uri(p), new Literal(o)); }
  public boolean isAsserted(String s, String p, URI o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Uri(o)); }
  public boolean isAsserted(String s, URI p, String o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Literal(o)); }
  public boolean isAsserted(URI s, String p, String o)
      throws URISyntaxException { return isAsserted(new Uri(s), new Uri(p), new Literal(o)); }

  public List<PropertyValue> getProperties(URI s) { return getProperties(new Uri(s)); }
  public List<PropertyValue> getProperties(String s)
      throws URISyntaxException { return getProperties(new Uri(s)); }

  public List<ObjectNode> getValues(SubjectNode s, URI p) { return getValues(s, new Uri(p)); }
  public List<ObjectNode> getValues(URI s, PredicateNode p) { return getValues(new Uri(s), p); }
  public List<ObjectNode> getValues(URI s, URI p) { return getValues(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getValues(SubjectNode s, String p) throws URISyntaxException { return getValues(s, new Uri(p)); }
  public List<ObjectNode> getValues(String s, PredicateNode p) throws URISyntaxException { return getValues(new Uri(s), p); }
  public List<ObjectNode> getValues(URI s, String p) throws URISyntaxException { return getValues(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getValues(String s, URI p) throws URISyntaxException { return getValues(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getValues(String s, String p) throws URISyntaxException { return getValues(new Uri(s), new Uri(p)); }

  public ObjectNode getValue(SubjectNode s, URI p) { return getValue(s, new Uri(p)); }
  public ObjectNode getValue(URI s, PredicateNode p) { return getValue(new Uri(s), p); }
  public ObjectNode getValue(URI s, URI p) { return getValue(new Uri(s), new Uri(p)); }
  public ObjectNode getValue(SubjectNode s, String p) throws URISyntaxException { return getValue(s, new Uri(p)); }
  public ObjectNode getValue(String s, PredicateNode p) throws URISyntaxException { return getValue(new Uri(s), p); }
  public ObjectNode getValue(URI s, String p) throws URISyntaxException { return getValue(new Uri(s), new Uri(p)); }
  public ObjectNode getValue(String s, URI p) throws URISyntaxException { return getValue(new Uri(s), new Uri(p)); }
  public ObjectNode getValue(String s, String p) throws URISyntaxException { return getValue(new Uri(s), new Uri(p)); }

  public List<ObjectNode> getRdfList(SubjectNode s, URI p) { return getRdfList(s, new Uri(p)); }
  public List<ObjectNode> getRdfList(URI s, PredicateNode p) { return getRdfList(new Uri(s), p); }
  public List<ObjectNode> getRdfList(URI s, URI p) { return getRdfList(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getRdfList(SubjectNode s, String p) throws URISyntaxException { return getRdfList(s, new Uri(p)); }
  public List<ObjectNode> getRdfList(String s, PredicateNode p) throws URISyntaxException { return getRdfList(new Uri(s), p); }
  public List<ObjectNode> getRdfList(URI s, String p) throws URISyntaxException { return getRdfList(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getRdfList(String s, URI p) throws URISyntaxException { return getRdfList(new Uri(s), new Uri(p)); }
  public List<ObjectNode> getRdfList(String s, String p) throws URISyntaxException { return getRdfList(new Uri(s), new Uri(p)); }

  public List<SubjectNode> getSubjects(PredicateNode s, URI p) { return getSubjects(s, new Uri(p)); }
  public List<SubjectNode> getSubjects(PredicateNode property, String value) { return getSubjects(property, new Literal(value)); }
  public List<SubjectNode> getSubjects(URI s, ObjectNode p) { return getSubjects(new Uri(s), p); }
  public List<SubjectNode> getSubjects(URI s, URI p) { return getSubjects(new Uri(s), new Uri(p)); }
  public List<SubjectNode> getSubjects(URI s, String p) { return getSubjects(new Uri(s), p); }
  public List<SubjectNode> getSubjects(String s, ObjectNode p)
      throws URISyntaxException { return getSubjects(new Uri(s), p); }
  public List<SubjectNode> getSubjects(String s, URI p)
      throws URISyntaxException { return getSubjects(new Uri(s), new Uri(p)); }
  public List<SubjectNode> getSubjects(String s, String p)
      throws URISyntaxException { return getSubjects(new Uri(s), p); }

  public boolean doesResourceExist(URI r) { return doesResourceExist(new Uri(r)); }
  public boolean doesResourceExist(String r) { return doesResourceExist(new Literal(r)); }

  public Iterator<Triple> matchX(URI s, PredicateNode p, ObjectNode o) { return match(newUri(s), p, o); }
  public Iterator<Triple> matchX(SubjectNode s, URI p, ObjectNode o) { return match(s, newUri(p), o); }
  public Iterator<Triple> matchX(SubjectNode s, PredicateNode p, URI o) { return match(s, p, newUri(o)); }
  public Iterator<Triple> matchX(URI s, URI p, ObjectNode o) { return match(newUri(s), newUri(p), o); }
  public Iterator<Triple> matchX(URI s, PredicateNode p, URI o) { return match(newUri(s), p, newUri(o)); }
  public Iterator<Triple> matchX(SubjectNode s, URI p, URI o) { return match(s, newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(URI s, URI p, URI o) { return match(newUri(s), newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(String s, PredicateNode p, ObjectNode o)
      throws URISyntaxException { return match(newUri(s), p, o); }
  public Iterator<Triple> matchX(SubjectNode s, String p, ObjectNode o)
      throws URISyntaxException { return match(s, newUri(p), o); }
  public Iterator<Triple> matchX(SubjectNode s, PredicateNode p, String o) { return match(s, p, newLiteral(o)); }
  public Iterator<Triple> matchX(String s, String p, ObjectNode o)
      throws URISyntaxException { return match(newUri(s), newUri(p), o); }
  public Iterator<Triple> matchX(String s, PredicateNode p, String o)
      throws URISyntaxException { return match(newUri(s), p, newLiteral(o)); }
  public Iterator<Triple> matchX(SubjectNode s, String p, String o)
      throws URISyntaxException { return match(s, newUri(p), newLiteral(o)); }
  public Iterator<Triple> matchX(String s, String p, String o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newLiteral(o)); }
  public Iterator<Triple> matchX(String s, URI p, ObjectNode o)
      throws URISyntaxException { return match(newUri(s), newUri(p), o); }
  public Iterator<Triple> matchX(String s, PredicateNode p, URI o)
      throws URISyntaxException { return match(newUri(s), p, newUri(o)); }
  public Iterator<Triple> matchX(String s, URI p, URI o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(URI s, String p, ObjectNode o)
      throws URISyntaxException { return match(newUri(s), newUri(p), o); }
  public Iterator<Triple> matchX(SubjectNode s, String p, URI o)
      throws URISyntaxException { return match(s, newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(URI s, String p, URI o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(URI s, PredicateNode p, String o) { return match(newUri(s), p, newLiteral(o)); }
  public Iterator<Triple> matchX(SubjectNode s, URI p, String o) { return match(s, newUri(p), newLiteral(o)); }
  public Iterator<Triple> matchX(URI s, URI p, String o) { return match(newUri(s), newUri(p), newLiteral(o)); }
  public Iterator<Triple> matchX(String s, String p, URI o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newUri(o)); }
  public Iterator<Triple> matchX(String s, URI p, String o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newLiteral(o)); }
  public Iterator<Triple> matchX(URI s, String p, String o)
      throws URISyntaxException { return match(newUri(s), newUri(p), newLiteral(o)); }

  public Graph matchSubgraphX(URI s, PredicateNode p, ObjectNode o) { return matchSubgraph(newUri(s), p, o); }
  public Graph matchSubgraphX(SubjectNode s, URI p, ObjectNode o) { return matchSubgraph(s, newUri(p), o); }
  public Graph matchSubgraphX(SubjectNode s, PredicateNode p, URI o) { return matchSubgraph(s, p, newUri(o)); }
  public Graph matchSubgraphX(URI s, URI p, ObjectNode o) { return matchSubgraph(newUri(s), newUri(p), o); }
  public Graph matchSubgraphX(URI s, PredicateNode p, URI o) { return matchSubgraph(newUri(s), p, newUri(o)); }
  public Graph matchSubgraphX(SubjectNode s, URI p, URI o) { return matchSubgraph(s, newUri(p), newUri(o)); }
  public Graph matchSubgraphX(URI s, URI p, URI o) { return matchSubgraph(newUri(s), newUri(p), newUri(o)); }
  public Graph matchSubgraphX(String s, PredicateNode p, ObjectNode o)
      throws URISyntaxException { return matchSubgraph(newUri(s), p, o); }
  public Graph matchSubgraphX(SubjectNode s, String p, ObjectNode o)
      throws URISyntaxException { return matchSubgraph(s, newUri(p), o); }
  public Graph matchSubgraphX(SubjectNode s, PredicateNode p, String o) { return matchSubgraph(s, p, newLiteral(o)); }
  public Graph matchSubgraphX(String s, String p, ObjectNode o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), o); }
  public Graph matchSubgraphX(String s, PredicateNode p, String o)
      throws URISyntaxException { return matchSubgraph(newUri(s), p, newLiteral(o)); }
  public Graph matchSubgraphX(SubjectNode s, String p, String o)
      throws URISyntaxException { return matchSubgraph(s, newUri(p), newLiteral(o)); }
  public Graph matchSubgraphX(String s, String p, String o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newLiteral(o)); }
  public Graph matchSubgraphX(String s, URI p, ObjectNode o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), o); }
  public Graph matchSubgraphX(String s, PredicateNode p, URI o)
      throws URISyntaxException { return matchSubgraph(newUri(s), p, newUri(o)); }
  public Graph matchSubgraphX(String s, URI p, URI o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newUri(o)); }
  public Graph matchSubgraphX(URI s, String p, ObjectNode o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), o); }
  public Graph matchSubgraphX(SubjectNode s, String p, URI o)
      throws URISyntaxException { return matchSubgraph(s, newUri(p), newUri(o)); }
  public Graph matchSubgraphX(URI s, String p, URI o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newUri(o)); }
  public Graph matchSubgraphX(URI s, PredicateNode p, String o) { return matchSubgraph(newUri(s), p, newLiteral(o)); }
  public Graph matchSubgraphX(SubjectNode s, URI p, String o) { return matchSubgraph(s, newUri(p), newLiteral(o)); }
  public Graph matchSubgraphX(URI s, URI p, String o) { return matchSubgraph(newUri(s), newUri(p), newLiteral(o)); }
  public Graph matchSubgraphX(String s, String p, URI o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newUri(o)); }
  public Graph matchSubgraphX(String s, URI p, String o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newLiteral(o)); }
  public Graph matchSubgraphX(URI s, String p, String o)
      throws URISyntaxException { return matchSubgraph(newUri(s), newUri(p), newLiteral(o)); }

  private static final Uri newUri(String s) throws URISyntaxException {
    return s == null ? null : new Uri(s);
  }

  private static final Uri newUri(URI u) {
    return u == null ? null : new Uri(u);
  }

  private static final Literal newLiteral(String s) {
    return s == null ? null : new Literal(s);
  }

}