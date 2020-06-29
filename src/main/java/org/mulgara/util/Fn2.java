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

/**
 * Functor template for a function that takes arguments of two different types
 * and returns a value of a third type, without throwing an exception.
 * @param <T1> The type of the first parameter for the function.
 * @param <T2> The type of the second parameter for the function.
 * @param <R> The return type of the function.
 *
 * @author Paula Gearon
 */
public interface Fn2<T1,T2,R> extends Fn2E<T1,T2,R,RuntimeException>{

}
