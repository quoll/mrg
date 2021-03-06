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

package org.mulgara.mrg;

/**
 * Indicates an internal error in the RDF library.
 */
public class RdfException extends Exception {

  private static final long serialVersionUID = 423957789781892991L;

  public RdfException() { }

  public RdfException(String msg) { super(msg); }

  public RdfException(Throwable cause) { super(cause); }

  public RdfException(String msg, Throwable cause) { super(msg, cause); }

}
