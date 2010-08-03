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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.namespace.QName;

import org.mulgara.util.Fn1;
import org.mulgara.util.Strings;

import static org.mulgara.mrg.vocab.uri.XSD.*;

/**
 * A utility class for mapping datatypes in Java to XSD datatypes
 * and back
 */
public class XSDMapper {

  private static final Class<?> BYTE_ARRAY_CLASS = new byte[0].getClass();

  /** All the constructors for known data types, keyed by URI in the XSD namespace.  */
  private static Map<URI,Fn1<String,Object>> constructors = new HashMap<URI,Fn1<String,Object>>();

  /** Maps classes to URIs that can represent that class. */
  private static Map<Class<?>,URI> typeMapper = new HashMap<Class<?>,URI>();

  /** The default instance for date parsing in the current locale.*/
  private static DateFormat dateFormat = DateFormat.getInstance();

  /**
   * Uses the type URI to select a parser for the
   * text and returns the appropriate kinds of Object.
   * @param type The URI of the data type.
   * @param text The text of the data.
   * @return The newly constructed data.
   * @throws IllegalArgumentException If the type is unkonwn, or the type cannot be parsed.
   * @throws NumberFormatException If the type is numeric and the text cannot be parsed.
   */
  public static Object toData(URI type, String text) {
    Fn1<String,Object> cons = constructors.get(type);
    if (cons == null) throw new IllegalArgumentException("Unknown data type: " + type);
    return cons.fn(text);
  }

  /**
   * Sets the locale for date parsing.
   * @param style The formatting style for dates.
   * @param Locale The locale to use for date parsing.
   * @see {DateFormat#getDateInstance(int,Locale)} for details.
   */
  public static void setLocale(int style, Locale aLocale) {
    dateFormat = DateFormat.getDateInstance(style, aLocale);
  }

  /**
   * Sets the locale for date parsing.
   * @param dateStyle The formatting style for dates.
   * @param timeStyle The formatting style for dates.
   * @param Locale The locale to use for date parsing.
   * @see {DateFormat#getDateTimeInstance(int,int,Locale)} for details.
   */
  public static void setLocale(int dateStyle, int timeStyle, Locale aLocale) {
    dateFormat = DateFormat.getDateTimeInstance(dateStyle, timeStyle, aLocale);
  }

  /**
   * Create a literal based on a Java object. The best possible type is chosen.
   * @param o The object to convert to a literal.
   * @return a new literal with the data and types set, if possible.
   */
  public Literal literal(Object o) {
    if (BYTE_ARRAY_CLASS.isInstance(o)) return literal((byte[])o);
    URI t = typeMapper.get(o.getClass());
    return (t == null) ? new Literal(o.toString(), t, ANY_SIMPLE_TYPE) : new Literal(o.toString(), o, t);
  }

  public Literal literal(Date d) {
    return new Literal(d.toString(), d, DATE);
  }

  public Literal literal(float n) {
    return new Literal(Float.toString(n), n, FLOAT);
  }

  public Literal literal(double n) {
    return new Literal(Double.toString(n), n, DOUBLE);
  }

  public Literal literal(byte n) {
    return new Literal(Byte.toString(n), n, BYTE);
  }

  public Literal literal(short n) {
    return new Literal(Short.toString(n), n, SHORT);
  }

  public Literal literal(int n) {
    return new Literal(Integer.toString(n), n, INT);
  }

  public Literal literal(long n) {
    return new Literal(Long.toString(n), n, LONG);
  }

  public Literal literal(boolean n) {
    return new Literal(Boolean.toString(n), n, BOOLEAN);
  }

  public Literal literal(byte[] data) {
    return new Literal(Strings.toHexString(data), data, HEX_BINARY);
  }

  public Literal literal(Number n) {
    return new Literal(n.toString(), n, typeMapper.get(n.getClass()));
  }

  static {
    constructors.put(BYTE, new Fn1<String,Object>() {
      public Byte fn(String text) { return Byte.valueOf(text); }
    });
    constructors.put(SHORT, new Fn1<String,Object>() {
      public Short fn(String text) { return Short.valueOf(text); }
    });
    constructors.put(INT, new Fn1<String,Object>() {
      public Integer fn(String text) { return Integer.valueOf(text); }
    });
    constructors.put(LONG, new Fn1<String,Object>() {
      public Long fn(String text) { return Long.valueOf(text); }
    });
    constructors.put(INTEGER, new Fn1<String,Object>() {
      public Object fn(String text) {
        BigInteger i = new BigInteger(text);
        return (i.bitLength() >= Long.SIZE) ? i : i.longValue();
      }
    });
    constructors.put(DECIMAL, new Fn1<String,Object>() {
      public Object fn(String text) {
        BigDecimal d = new BigDecimal(text);
        try {
          BigInteger i = d.toBigIntegerExact();
          return (i.bitLength() >= Long.SIZE) ? i : i.longValue();
        } catch (ArithmeticException e) {
          double dbl = d.doubleValue();
          return (dbl == Double.NEGATIVE_INFINITY || dbl == Double.POSITIVE_INFINITY) ? d : dbl;
        }
      }
    });
    constructors.put(FLOAT, new Fn1<String,Object>() {
      public Float fn(String text) { return Float.valueOf(text); }
    });
    constructors.put(DOUBLE, new Fn1<String,Object>() {
      public Double fn(String text) { return Double.valueOf(text); }
    });
    constructors.put(QNAME, new Fn1<String,Object>() {
      public QName fn(String text) {
        int c = text.indexOf(':');
        return (c >= 0) ? new QName(text.substring(0, c), text.substring(c + 1)) : new QName(text);
      }
    });
    constructors.put(ANY_URI, new Fn1<String,Object>() {
      public URI fn(String text) { return URI.create(text); }
    });
    constructors.put(BOOLEAN, new Fn1<String,Object>() {
      public Boolean fn(String text) { return Boolean.valueOf(text); }
    });
    constructors.put(HEX_BINARY, new Fn1<String,Object>() {
      public byte[] fn(String text) { return Strings.parseHexToBytes(text); }
    });
    constructors.put(STRING, new Fn1<String,Object>() {
      public String fn(String text) { return text; }
    });
    constructors.put(DATE_TIME, new Fn1<String,Object>() {
      public Date fn(String text) {
        try {
          return dateFormat.parse(text);
        } catch (ParseException e) {
          throw new IllegalArgumentException("Unable to parse date: " + text, e);
        }
      }
    });
  }

  static {
    typeMapper.put(Date.class, DATE);
    typeMapper.put(URI.class, ANY_URI);
    typeMapper.put(QName.class, QNAME);
    typeMapper.put(Boolean.class, BOOLEAN);
    typeMapper.put(Byte.class, BYTE);
    typeMapper.put(Short.class, SHORT);
    typeMapper.put(Integer.class, INT);
    typeMapper.put(Long.class, LONG);
    typeMapper.put(Float.class, FLOAT);
    typeMapper.put(Double.class, DOUBLE);
    typeMapper.put(BigInteger.class, INTEGER);
    typeMapper.put(BigDecimal.class, DECIMAL);
  }
}
