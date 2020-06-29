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
 * Functor template for a function that takes one type and returns another without
 * throwing an exception.
 * @param <T1> The parameter type of the function.
 * @param <R> The return type of the function.
 *
 * @author Paula Gearon
 */
public interface Fn1<T1,R> extends Fn1E<T1,R,RuntimeException> {

}
