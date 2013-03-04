package org.fest.assertions.error;

import com.google.common.base.Optional;

/**
 * 
 * Creates an error message indicating that an Optional which should be present is absent
 * 
 * @author Kornel Kiełczewski
 * @author Joel Costigliola
 */
public final class OptionalShouldBePresent extends BasicErrorMessageFactory {

  public static <T> ErrorMessageFactory shouldBePresent(final Optional<T> actual) {
    return new OptionalShouldBePresent(
        "Expecting Optional to contain a non-null instance but contained nothing (absent Optional)", actual);
  }

  private OptionalShouldBePresent(final String format, final Object... arguments) {
    super(format, arguments);
  }

}
