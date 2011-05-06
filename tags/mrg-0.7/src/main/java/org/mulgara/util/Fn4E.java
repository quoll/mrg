/*
 * Copyright 2011 Paul Gearon.
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
 * Functor template for a function that takes arguments of four different types
 * and returns a value of a fifth type, possibly throwing an exception.
 * @param <T1> The type of the first parameter of the function.
 * @param <T2> The type of the second parameter of the function.
 * @param <T3> The type of the third parameter of the function.
 * @param <T4> The type of the fourth parameter of the function.
 * @param <R> The return type of the function.
 * @param <E> The type of exception that may be thrown from the function.
 *
 * @author Paul Gearon
 */
public interface Fn4E<T1,T2,T3,T4,R,E extends Exception> {

  /**
   * Declares a function template that takes three arguments and returns a value of
   * another type.
   * @param arg1 The first argument.
   * @param arg2 The second argument.
   * @param arg3 The third argument.
   * @param arg4 The fourth argument.
   * @return A value based on arg1, arg2, arg3 and arg4.
   * @throws E An exception that may be thrown from this method.
   */
  R call(T1 arg1, T2 arg2, T3 arg3, T4 arg4) throws E;
}
