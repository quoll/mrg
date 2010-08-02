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

package org.mulgara.util;

import java.io.UnsupportedEncodingException;

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
}
