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

package org.mulgara.mrg.parser;

import org.mulgara.mrg.RdfException;

/**
 * Indicates a parsing error while parsing RDF data.
 */
public class ParseException extends RdfException {

  private static final long serialVersionUID = -6851379334791196804L;

  public ParseException() { }

  public ParseException(String msg) { super(msg); }

  public ParseException(Throwable cause) { super(cause); }

  public ParseException(String msg, Throwable cause) { super(msg, cause); }

}
