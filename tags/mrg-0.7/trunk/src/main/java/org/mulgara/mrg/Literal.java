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

/**
 * A Literal node.
 */
public class Literal implements ObjectNode {

  /** The type identifier for Literals */
  public static final int TYPE_ID = 1;

  /** The text of the literal. */
  private final String text;

  /** The language code of the literal. Optional. */
  private final String lang;

  /** The datatype of the literal. Optional. */
  private final URI type;

  /** Optional data value for XSD types that map to structured data. */
  private final Object data;

  /**
   * Create a new simple literal.
   * @param text The text of the literal.
   */
  public Literal(String text) {
    this.text = text.intern();
    lang = null;
    type = null;
    data = null;
  }

  /**
   * Create a typed literal.
   * @param text The text of the literal.
   * @param type The URI of the datatype.
   */
  public Literal(String text, URI type) {
    this.text = text.intern();
    this.type = type;
    data = toData(type, text);
    lang = null;
  }

  /**
   * Create a structured typed literal.
   * @param text The text of the literal.
   * @param val The data value for the literal.
   * @param type The URI of the datatype.
   */
  public Literal(String text, Object val, URI type) {
    this.text = text.intern();
    this.type = type;
    data = val;
    lang = null;
  }

  /**
   * Create a language coded literal.
   * @param text The text of the literal.
   * @param lang The language of the literal.
   */
  public Literal(String text, String lang) {
    this.text = text;
    this.lang = lang.toLowerCase().intern();
    if (lang.indexOf(' ') >= 0) throw new IllegalArgumentException("Language tags may not contain a space");
    this.type = null;
    data = null;
  }

  /**
   * General mechanism for creating a literal.
   * @param text The text of the literal.
   * @param lang The language of the literal.
   * @param type The URI of the datatype.
   */
  public Literal(String text, String lang, URI type) {
    this.text = text.intern();
    if (lang != null) {
      if (type != null) throw new IllegalArgumentException("Literals may not have a language tag and a data type");
      this.type = null;
      this.data = null;
      this.lang = lang.toLowerCase().intern();
      if (lang.indexOf(' ') >= 0) throw new IllegalArgumentException("Language tags may not contain a space");
    } else {
      this.lang = null;
      if (type != null) {
        this.type = type;
        data = toData(type, text);
      } else {
        this.type = null;
        data = null;
      }
    }
  }

  /**
   * Gets the text of this literal.
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the language code of this literal. May be <code>null</code>.
   */
  public String getLang() {
    return lang;
  }

  /**
   * Gets the datatype of this literal. May be <code>null</code>.
   */
  public URI getType() {
    return type;
  }

  /**
   * Gets the typed data from this object.
   * @return Just the lexical form
   */
  public Object getValue() {
    return text;
  }

  /**
   * Equality by value. This is based on the datatype.
   */
  public boolean valueEquals(Literal l) {
    // defer to the standard equality until values are handled.
    return eq(data, l.data);
  }

  /**
   * Equality method. Based on the label.
   */
  public boolean equals(Object o) {
    if (!(o instanceof Literal)) return false;
    Literal l = (Literal)o;
    return text.equals(l.text) && eq(lang, l.lang) && eq(type, l.type);
  }

  private static final boolean eq(Object a, Object b) {
    return (a == null) ? b == null : a.equals(b);
  }

  @SuppressWarnings("unchecked")
  private static final <T> int cmp(Comparable<T> a, Comparable<T> b) {
    if (a == null) {
      if (b == null) return 0;
      return 1;
    }
    if (b == null) return -1;
    return a.compareTo((T)b);
  }

  /**
   * The hashcode, based on the label.
   */
  public int hashCode() {
    int lcode = lang == null ? 0 : lang.hashCode() * 7;
    int tcode = type == null ? 0 : type.hashCode() * 13;
    return text.hashCode() + lcode + tcode;
  }

  /**
   * A type identifier to distinguish from the other types of Node.
   */
  public int getTypeId() {
    return TYPE_ID;
  }

  /**
   * Compares this object with the specified object for order.
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than,
   *         equal to, or greater than the specified object.
   */
  public int compareTo(Node o) {
    int tid = o.getTypeId();
    if (TYPE_ID == tid) {
      Literal l = (Literal)o;
      int c = cmp(type, l.type);
      if (c != 0) return c;
      c = cmp(lang, l.lang);
      if (c != 0) return c;
      return text.compareTo(l.text);
    }
    return tid - TYPE_ID;
  }

  /**
   * Represents this node as a string.
   */
  public String toString() {
    StringBuilder label = new StringBuilder("\"");
    label.append(text).append("\"");
    if (lang != null) label.append("@").append(lang);
    if (type != null) label.append("^^").append(type);
    return label.toString();
  }

  /**
   * Convert to a Java type. This will pick the best type that it can, based on
   * the type URI and the lexical representation. If unable to use anything else
   * a string is returned.
   * @return a Java object with the literal data in it.
   */
  public Object toJava() {
    if (data != null) return data;
    return toData(type, text);
  }

  /**
   * Internal method for converting the literal to Java data. Chooses the best data
   * type available, or text if the data cannot be converted.
   * @return a Java object with the literal data in it.
   */
  private static Object toData(URI type, String text) {
    try {
      if (type != null) return XSDMapper.toData(type, text);
    } catch (Exception e) {
      // unable to convert to data
    }
    return text;
  }

}
