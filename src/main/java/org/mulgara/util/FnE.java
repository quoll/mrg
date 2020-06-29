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

import java.util.concurrent.Callable;

/**
 * Functor template for a function that accepts no parameters, and has a return type.
 * An exception may be thrown.
 * @param <R> The return type of the function.
 * @param <E> The type of an exception that may be thrown from the function.
 *
 * @author Paula Gearon
 */
public interface FnE<R,E extends Exception> extends Callable<R> {

  /**
   * Declares a function template that takes no arguments and returns a value.
   * @return A calculated value.
   * @throws E Can throw an exception of this type.
   */
  R call() throws E;

}
