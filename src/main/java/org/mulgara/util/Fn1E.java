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

/**
 * Functor template for a function that takes one type and returns another.
 * An exception may be thrown.
 * @param <T1> The parameter type of the function.
 * @param <T2> The return type of the function.
 * @param <E> The exception type that the function may throw.
 *
 * @author Paul Gearon
 */
public interface Fn1E<T1,T2,E extends Exception> {

  /**
   * Declares a function template that takes one argument and returns a value of
   * another type.
   * @param arg The single argument.
   * @return A value based on arg.
   * @throws E Can throw an exception of this type.
   */
  T2 fn(T1 arg) throws E;
}
