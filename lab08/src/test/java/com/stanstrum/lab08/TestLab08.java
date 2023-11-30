package com.stanstrum.lab08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * The main class for testing Lab08.  I used JUnit here
 * because the lab is not supposed to have a main method.
 *
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class TestLab08 {
  /**
   * <p>
   *   Tests {@link Lab08#properCase(String)} using movie titles
   *   stored in a CSV file in `src/resources`.
   * </p>
   * <p>
   *   Fails if the return value of {@link Lab08#properCase(String)}
   *   does not match the expected result.
   * </p>
   *
   * @param input The input String to be passed to {@link Lab08#properCase(String)}.
   * @param expected The expected result.
   */
  @ParameterizedTest
  @CsvFileSource(
    resources = "/properCase.csv",
    numLinesToSkip = 1
  )
  @DisplayName("Proper case \"{0}\"")
  public void properCase_ProperlyCapitalizesMovies(String input, String expected) {
    String result = Lab08.properCase(input);

    assertEquals(result, expected);
  }
}
