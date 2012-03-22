/*
 * Created on Mar 22, 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2012 the original author or authors.
 */
package org.fest.assertions.condition;

import org.fest.assertions.core.Condition;
import org.fest.util.VisibleForTesting;

/**
 * Returns {@code true} if the condition is not satisfied.
 * 
 * @author Nicolas François
 */
public class Not<T> extends Condition<T>{

	@VisibleForTesting final Condition<T> condition;

	  /**
	   * Creates a new </code>{@link Not}</code>.
	   * @param conditions the conditions to inverse.
	   */	
	public static <T> Not<T> not(Condition<T> condition){
		return new Not<T>(condition);
	}
	
	@Override
	public boolean matches(T value) {
		return !condition.matches(value);
	}
	
	private Not(Condition<T> condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return String.format("not :<%s>", condition);
	}

}
