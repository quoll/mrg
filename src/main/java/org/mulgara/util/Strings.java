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

package org.mulgara.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * String utilities.
 */
public class Strings {

  /** The character encoding to support */ 
  private static final String UTF8 = "UTF-8"; 

  /**
   * Converts a string to a byte array with UTF8.
   * Any problems are emitted as an InternalError instead of an Exception.
   * @param str The string to convert.
   * @return A byte array representing the string.
   * @throws InternalError If UTF-8 encoding fails.
   */
  public static final byte[] toUtf8Bytes(String str) {
    try {
      return str.getBytes(UTF8);
    } catch (UnsupportedEncodingException e) {
      throw new InternalError("Unable to encode with: " + UTF8);
    }
  }


  /**
   * Converts a string of hexadecimal characters into a byte array.
   * This is the inverse operation to {@link #toHexString}
   * @param hex The string of hex characters. These must appear in pairs that form bytes.
   * @return An array of bytes containing the data from the hex string.
   * @throws IllegalArgumentException If the string has an odd number of characters, or any characters are not valid hexadecimal characters.
   */
  public static final byte[] parseHexToBytes(String hex) {
    int len = hex.length();
    if (len % 2 != 0) throw new IllegalArgumentException("Hexadecimal string must have an even number of characters");
    byte[] result = new byte[len >> 1];
    for (int i = 0, d = 0; i < hex.length(); i += 2, d++) {
      byte b = (byte)(toNybble(hex.charAt(i)) << 4 | toNybble(hex.charAt(i + 1)));
      result[d] = b;
    }
    return result;
  }


  /**
   * Converts a byte array to a string with each byte being represented by a pair of
   * hexadecimal characters. This is the inverse operation of {@link #parseHexToBytes}.
   * @param data The data to convert.
   * @return A string representing the hexadecimal value of the byte array.
   */
  public static final String toHexString(byte[] data) {
    StringBuilder s = new StringBuilder();
    for (byte b: data) {
      s.append(toHexChar((b & 0xF0) >> 4));
      s.append(toHexChar(b & 0x0F));
    }
    return s.toString();
  }


  /**
   * Convert a single hexadecimal character into a nybble (4-bit number).
   * @param c The character to be encoded.
   * @return A 4 bit value representing the character.
   * @throws IllegalArgumentException if the character is not a valid hex character.
   */
  private static final byte toNybble(char c) {
    byte b;
    if (c >= 'a') b = (byte)(c - 'a' + 10);
    else if (c >= 'A') b = (byte)(c - 'A' + 10);
    else if (c >= 0) b = (byte)(c - '0');
    else throw new IllegalArgumentException("Character '" + c + "' is not hex");
    if (b > 0xF) throw new IllegalArgumentException("Character '" + c + "' is not hex"); 
    return b;
  }


  /**
   * Converts a number between 0 and 15 into a hex character.
   * @param n The number to convert.
   * @return A hex character if n is 0-15. Undefined if n is not in this range.
   */
  private static final char toHexChar(int n) {
    return (n > 9) ? (char)('A' - 10 + n) : (char)('0' + n);
  }

  /**
   * Converts a URI into a QName, based entirely on parsing.
   * @param u The URI to parse.
   * @return A QName containing the URI as a namespace/localpart combination, or the entire
   *         uri as a localpart if it could not be parsed.
   */
  public static QName parseQName(URI u) {
    String s = u.toString();
    int start = startOfName(s);
    if (start == 0 || start == s.length()) return new QName(s);
    return new QName(s.substring(0, start), s.substring(start));
  }

  /**
   * Determines the start of a name in a URI that can be turned into a QName.
   * @param s The string to search.
   * @return The index of the first character in the name.
   */
  public static int startOfName(String s) {
    for (int i = s.length() - 1; i >= 0; i--) {
      if (!localNameChar(s.charAt(i))) {
        // found the place where a name has to start after
        for (int p = i + 1; p < s.length(); p++) {
          if (localStartNameChar(s.charAt(p))) return p;
        }
        return s.length();
      }
    }
    return 0;
  }

  /**
   * Indicates if the char is a valid XML name character, minus the colon character.
   * @see http://www.w3.org/TR/REC-xml/#NT-NameStartChar
   * @param c The character to test.
   * @return <code>true</code> if the character is a valid start to an XML name.
   */
  private static final boolean localNameChar(char c) {
    return localStartNameChar(c) ||
           c == '-' || c == '.' || (c >= '0' && c <= '9') ||
           c == 0xB7 || (c >= 0x0300 && c <= 0x036F) || (c >= 0x203F && c <= 0x2040);
  }

  /**
   * Indicates if the char is a valid start for an XML name character, minus the colon character.
   * @see http://www.w3.org/TR/REC-xml/#NT-NameStartChar
   * @param c The character to test.
   * @return <code>true</code> if the character is a valid start to an XML name.
   */
  private static final boolean localStartNameChar(char c) {
    return (c >= 'A' && c <= 'Z') ||
           (c >= 'a' && c <= 'z') ||
           c == '_' ||
           (c >= 0xC0 && c <= 0xD6) ||
           (c >= 0xD8 && c <= 0xF6) ||
           (c >= 0xF8 && c <= 0x2FF) ||
           (c >= 0x370 && c <= 0x37D) ||
           (c >= 0x37F && c <= 0x1FFF) ||
           (c >= 0x200C && c <= 0x200D) ||
           (c >= 0x2070 && c <= 0x218F) ||
           (c >= 0x2C00 && c <= 0x2FEF) ||
           (c >= 0x3001 && c <= 0xD7FF) ||
           (c >= 0xF900 && c <= 0xFDCF) ||
           (c >= 0xFDF0 && c <= 0xFFFD) ||
           (c >= 0x10000 && c <= 0xEFFFF);
  }

  /** A single indent for use when pretty printing. */
  static final private String SINGLE_INDENT_STR = "  ";

  /** The size of indents to use for pretty printing. */
  static final private int SINGLE_INDENT_SIZE = SINGLE_INDENT_STR.length();

  /** The largest pre-set indent string. */
  static final private String MAX_INDENT = "                    ";

  /**
   * An array of indent strings used by {@link #i(int)}.
   * This is pre-generated to avoid creating them while emiting xml.
   */
  static final String[] INDENT = new String[MAX_INDENT.length() / SINGLE_INDENT_SIZE];

  // Build the indents using the MAX_INDENT as a raw data source
  static {
    for (int i = 0; i < INDENT.length; i++) INDENT[i] = MAX_INDENT.substring(0, i * SINGLE_INDENT_SIZE);
  }

  /**
   * Returns a string containing an indentation.
   * @param indent The number of indentations to use.
   * @return A String containing the appropriate indentation.
   */
  public static String indent(int indent) {
    if (indent < INDENT.length) return INDENT[indent];
    StringBuilder sbi = new StringBuilder(MAX_INDENT);
    for (int i = INDENT.length; i < indent; i++) sbi.append(SINGLE_INDENT_STR);
    return sbi.toString();
  }

  /** An internal structure for mapping characters disallowed in XML to their escape codes. */
  private static final Map<Character,String> escapes = new HashMap<Character,String>();
  static {
    escapes.put('&', "&amp;");
    escapes.put('<', "&lt;");
    escapes.put('\r', "&#13;");
    escapes.put('>', "&gt;");
    escapes.put('"', "&quot;");
    escapes.put('\'', "&apos;");
  }

  /**
   * Search for disallowed XML characters in a string, and replace them with their escape codes.
   * @param s The string to escape.
   * @return A new version of the input string, with XML escaping done.
   */
  public static String xmlEscape(String s) {
    StringBuilder result = null;
    for(int i = 0, max = s.length(), delta = 0; i < max; i++) {
      char c = s.charAt(i);
      String escCode = escapes.get(c);

      if (escCode != null) {
        if (result == null) result = new StringBuilder(s);
        result.replace(i + delta, i + delta + 1, escCode);
        delta += (escCode.length() - 1);
      }
    }
    return (result == null) ? s : result.toString();
  }

}
