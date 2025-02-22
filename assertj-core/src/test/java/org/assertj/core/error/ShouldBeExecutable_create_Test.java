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
package org.assertj.core.error;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeExecutable.shouldBeExecutable;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;
import static org.mockito.Mockito.mock;

import java.nio.file.Path;
import org.assertj.core.internal.TestDescription;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link ShouldBeExecutable}</code>.
 *
 * @author Olivier Demeijer
 *
 */
class ShouldBeExecutable_create_Test {

  private static final TestDescription TEST_DESCRIPTION = new TestDescription("Test");

  @Test
  void should_create_error_message_for_File() {
    // GIVEN
    FakeFile file = new FakeFile("xyz");
    ErrorMessageFactory factory = shouldBeExecutable(file);
    // WHEN
    String message = factory.create(TEST_DESCRIPTION, STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo("[Test] %nExpecting actual:%n  xyz%nto be executable.".formatted(file));
  }

  @Test
  void should_create_error_message_for_Path() {
    // GIVEN
    final Path path = mock(Path.class);
    ErrorMessageFactory factory = shouldBeExecutable(path);
    // WHEN
    String message = factory.create(TEST_DESCRIPTION, STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo("[Test] %nExpecting actual:%n  %s%nto be executable.".formatted(path));
  }
}
