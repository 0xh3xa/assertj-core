/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.core.internal.objectarrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldNotContain.shouldNotContain;
import static org.assertj.core.internal.ErrorMessages.iterableValuesToLookForIsEmpty;
import static org.assertj.core.internal.ErrorMessages.iterableValuesToLookForIsNull;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ObjectArraysBaseTest;
import org.junit.jupiter.api.Test;

class ObjectArrays_assertDoesNotContainAnyElementsOf_Test extends ObjectArraysBaseTest {

  @Test
  void should_pass_if_actual_does_not_contain_any_elements_of_given_iterable() {
    arrays.assertDoesNotContainAnyElementsOf(someInfo(), actual, newArrayList("Han"));
  }

  @Test
  void should_pass_if_actual_does_not_contain_any_elements_of_given_iterable_even_if_duplicated() {
    arrays.assertDoesNotContainAnyElementsOf(someInfo(), actual, newArrayList("Han", "Han", "Anakin"));
  }

  @Test
  void should_throw_error_if_given_iterable_is_empty() {
    assertThatIllegalArgumentException().isThrownBy(() -> arrays.assertDoesNotContainAnyElementsOf(someInfo(), actual,
                                                                                                   Collections.<String> emptyList()))
                                        .withMessage(iterableValuesToLookForIsEmpty());
  }

  @Test
  void should_throw_error_if_given_iterable_is_null() {
    assertThatNullPointerException().isThrownBy(() -> arrays.assertDoesNotContainAnyElementsOf(someInfo(), actual,
                                                                                               null))
                                    .withMessage(iterableValuesToLookForIsNull());
  }

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertDoesNotContainAnyElementsOf(someInfo(), null,
                                                                                                              newArrayList("Yoda")))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_actual_contains_one_element_of_given_iterable() {
    AssertionInfo info = someInfo();
    List<String> list = newArrayList("Vador", "Yoda", "Han");

    Throwable error = catchThrowable(() -> arrays.assertDoesNotContainAnyElementsOf(info, actual, list));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotContain(actual, list.toArray(), newLinkedHashSet("Yoda")));
  }

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------

  @Test
  void should_pass_if_actual_does_not_contain_any_elements_of_given_iterable_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertDoesNotContainAnyElementsOf(someInfo(), actual, newArrayList("Han"));
  }

  @Test
  void should_pass_if_actual_does_not_contain_any_elements_of_given_iterable_even_if_duplicated_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertDoesNotContainAnyElementsOf(someInfo(), actual,
                                                                         newArrayList("Han", "Han", "Anakin"));
  }

  @Test
  void should_fail_if_actual_contains_one_element_of_given_iterable_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    List<String> expected = newArrayList("LuKe", "YODA", "Han");

    Throwable error = catchThrowable(() -> arraysWithCustomComparisonStrategy.assertDoesNotContainAnyElementsOf(info, actual,
                                                                                                                expected));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotContain(actual, expected.toArray(), newLinkedHashSet("LuKe", "YODA"),
                                                    caseInsensitiveStringComparisonStrategy));
  }
}
