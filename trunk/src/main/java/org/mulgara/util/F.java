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
 * This class defines generic methods for currying the function closures
 * defined in this package.
 *
 * @author Paul Gearon
 */
public class F {

  /**
   * Curry a 1 argument function down to a function with 0 arguments.
   * @param fna The function closure to curry down.
   * @param arg The argument to apply to fna when currying.
   * @param <T1> The type of argument that fna accepts, which is also the type for arg.
   * @param <T2> The return type of fna.
   * @return A new function closure that takes no parameters.
   */
  static final <T1,T2> Fn<T2> curry(final Fn1<T1,T2> fna, final T1 arg) {
    return new Fn<T2>() { public T2 call() { return fna.call(arg); } };
  }

  /**
   * Curry a 2 argument function down to a function with 1 arguments.
   * @param fna The function closure to curry down.
   * @param arg The argument to apply to fna when currying.
   * @param <T1> The type of the first argument that fna accepts, which is also the type for arg.
   * @param <T2> The type of the second argument that fna accepts. This is the type
   *             for the remaining parameter in the returned function.
   * @param <R> The return type of the fna function.
   * @return A new function closure that takes 1 parameter of type T2 and returns
   *         a value of type R..
   */
  static final <T1,T2,R> Fn1<T2,R> curry(final Fn2<T1,T2,R> fna, final T1 arg) {
    return new Fn1<T2,R>() { public R call(T2 a) { return fna.call(arg, a); } };
  }

}
