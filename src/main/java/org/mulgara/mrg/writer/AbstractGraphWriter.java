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

package org.mulgara.mrg.writer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mulgara.mrg.Graph;
import org.mulgara.mrg.Node;
import org.mulgara.mrg.Triple;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.vocab.uri.DC;
import org.mulgara.mrg.vocab.uri.RDF;
import org.mulgara.mrg.vocab.uri.RDFS;
import org.mulgara.mrg.vocab.uri.OWL;
import org.mulgara.mrg.vocab.uri.FOAF;
import org.mulgara.mrg.vocab.uri.RSS;
import org.mulgara.mrg.vocab.uri.VCARD;
import org.mulgara.mrg.vocab.uri.XSD;
import org.mulgara.util.Strings;

public abstract class AbstractGraphWriter implements GraphWriter {

  /** A static list of known namespaces */
  static final Map<String,String> knownNamespaces = new HashMap<String,String>();

  /** The graph to be emitted. */
  protected final Graph graph;

  /** The base for the document. */
  protected final String base;

  /** The registered namespaces to use while emitting the graph, reverse mapped. */
  protected Map<String,String> rns;

  /** A generator for creating namespace prefix names */
  protected PrefixGenerator prefixGen;

  // initialize the known namespaces
  static {
    knownNamespaces.put(RDF.BASE, RDF.PREFIX);
    knownNamespaces.put(RDFS.BASE, RDFS.PREFIX);
    knownNamespaces.put(OWL.BASE, OWL.PREFIX);
    knownNamespaces.put(FOAF.BASE, FOAF.PREFIX);
    knownNamespaces.put(DC.BASE, DC.PREFIX);
    knownNamespaces.put(RSS.BASE, RSS.PREFIX);
    knownNamespaces.put(VCARD.BASE, VCARD.PREFIX);
    knownNamespaces.put(XSD.BASE, XSD.PREFIX);
  }

  /**
   * Create a graph writer with an empty namespace map.
   * @param graph The write to be written.
   */
  @SuppressWarnings("unchecked")
  public AbstractGraphWriter(Graph graph, URI base) {
    this(graph, base, (Map<String,URI>)Collections.EMPTY_MAP);
  }

  /**
   * Create a graph writer with a specified namespace map.
   * @param graph The write to be written.
   * @param ns The namespace map to use. This maps prefixes to namespaces.
   */
  public AbstractGraphWriter(Graph graph, URI base, Map<String,URI> ns) {
    this.graph = graph;
    this.base = base == null ? null : base.toString();
    setNamespaces(ns);
  }

  /**
   * @see org.mulgara.mrg.writer.GraphWriter#setNamespaces(java.util.Map)
   */
  public void setNamespaces(Map<String,URI> ns) {
    rns = new HashMap<String,String>();
    for (Map.Entry<String,URI> e: ns.entrySet()) rns.put(e.getValue().toString(), e.getKey());
  }

  /**
   * @see org.mulgara.mrg.writer.GraphWriter#setNamespacesByString(java.util.Map)
   */
  public void setNamespacesByString(Map<String, String> ns) throws URISyntaxException {
    this.rns = new HashMap<String,String>();
    for (Map.Entry<String,String> e: ns.entrySet()) rns.put(e.getValue(), e.getKey());
  }

  /**
   * Clears out the prefix/namespace set.
   */
  public void resetNamespaces() {
    rns.clear();
  }

  /**
   * Scans the URIs in the graph to find all the namespaces.
   * This populates the internal namespace mappings.
   * Namespace prefixes will be automatically generated, except
   * where namespaces are recognized. This takes linear time, and
   * uses up memory to avoid duplicating work.
   */
  public void scanNamespaces() {
    prefixGen = new PrefixGenerator();
    for (Triple t: graph.getTriples()) {
      if (t.getSubject().getTypeId() == Uri.TYPE_ID) scanUri(t.getSubject());
      scanUri(t.getPredicate());
      // Skip objects can often be strange.
      // If they're namespaced they usually show up as subjects.
      // if (t.getObject().getTypeId() == Uri.TYPE_ID) scanUri(t.getObject());
    }
  }

  /**
   * Gets a mapping of prefixes to their namespaces.
   * @return a map with prefixes as keys and namespace URIs as values.
   */
  public Map<String,URI> getNamespaces() {
    Map<String,URI> ns = new HashMap<String,URI>();
    try {
      for (Map.Entry<String,String> e: rns.entrySet()) ns.put(e.getValue(), new URI(e.getKey()));
    } catch (URISyntaxException e1) {
      throw new IllegalStateException("Invalid URI found in namespaces", e1);
    }
    return ns;
  }

  /**
   * Break a Uri into a namespace and name, then try to find or create a namespace for it.
   * If the namespace from a Uri has already been seen, then ignore it
   * @param n The node that is a Uri to be tested.
   */
  private void scanUri(Node n) {
    String str = ((Uri)n).getURI().toString();
    String name = str.substring(Strings.startOfName(str));
    String namespace = str.substring(0, str.length() - name.length());
    // short circuit if this matches the base or has already been seen
    if (namespace.equals(base) || rns.containsKey(namespace)) return;
    // look in the known namespaces
    String prefix = knownNamespaces.get(namespace);
    if (testNamespace(namespace)) {
      rns.put(namespace, (prefix != null) ? prefix : prefixGen.create());
    }
  }

  /**
   * Tests a potential namespace to see if it is considered to be OK.
   * @param ns The namespace to test.
   * @return <code>true</code> iff the namespace can be used in this type of document.
   */
  protected boolean testNamespace(String ns) {
    return true;
  }
}


/**
 * A simple class for creating unique prefix names.
 */
class  PrefixGenerator {
  /** An internal counter for generating prefix names */
  int id = 1;

  /**
   * Creates a new prefix.
   * @return A new prefix name.
   */
  public String create() {
    return "ns" + id++;
  }
}
