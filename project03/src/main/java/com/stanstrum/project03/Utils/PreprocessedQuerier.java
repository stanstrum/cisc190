package com.stanstrum.project03.Utils;

import java.util.Scanner;

/**
 * A class that implements requesting some
 * information from the user and validating it.
 * This allows a wide range of values to be read
 * and validated synchronously rather than having
 * do...while loops everywhere.
 */
public abstract class PreprocessedQuerier<E, P> {
  /**
   * The scanner to use for getting information
   * from the user.
   */
  protected final Scanner scanner;

  /**
   * The constructor for {@link PreprocessedQuerier}.
   *
   * @param scanner The value to set to {@link PreprocessedQuerier#scanner}.
   */
  public PreprocessedQuerier(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * The method which implements presenting the
   * query to the user.
   */
  protected abstract void showPrompt();

  /**
   * The method that handles retrieving a value
   * from the user.
   *
   * @return A successfully read -- but not yet validated -- value.
   *
   * @throws Exception If there was an error when scanning the value.
   */
  protected abstract E scanValue() throws Exception;

  /**
   * The method that verifies a scanned value.  If this method returns
   * `true`, then the value is returned from {@link PreprocessedQuerier#query()},
   * otherwise the user will be prompted again.
   *
   * @param value The value to verify.
   *
   * @return Whether this value is acceptable.
   */
  protected abstract boolean verify(E value);

  /**
   * <p>
   *   This method informs the user that there was an error
   *   with the value read from the console or that the value
   *   was not acceptable.
   * </p>
   * <p>
   *   Note: value <i>may</i> be null in this method as
   *   this method is used after an invalid value was read or
   *   after an error occurred when reading said value.
   * </p>
   *
   * @param value The value read, possibly `null`.
   */
  protected void showError(E value) {
    // Default implementation.
    System.out.println("Try again ...");
  }

  /**
   * <p>
   *   This method allows the querier to perform some operation
   *   to the value before it's returned to the caller of
   *   {@link PreprocessedQuerier#query()}.  If nothing need be
   *   done, then add a stub that returns the value as-is.
   * </p>
   * <p>
   *   Note: this method is only called after it was succesfully
   *   verified.  An invalid value will not be passed into this
   *   method.
   * </p>
   *
   * @param value The value to preprocessed.
   *
   * @return The processed value, which may be of another type.
   */
  protected abstract P preprocess(E value);

  /**
   * The main query logic.  This method, in an infinite loop,
   * will prompt the user, scan for input, validate the input,
   * show an error if the value was invalid or scanning failed,
   * and eventually break once an acceptable value is encountered.
   * Afterwards, the value may be preprocessed before returning from
   * this method.
   *
   * @return A validated value retrieved from the user.
   */
  public final P query() {
    // Initialize value to null.  Note that
    // null may be an acceptable value, however
    // it must be read console (somehow) *first*.
    E value = null;

    // Repeat until a valid value is found.
    while (true) {
      // Prompt the user.
      this.showPrompt();

      // Handle the errors that may occur
      // when scanning.
      try {
        // Get the value.
        value = this.scanValue();

        // Check if the value is acceptable.
        if (this.verify(value)) {
          // The value is valid; stop looping.
          break;
        }
      } catch (Exception e) {
        // Do nothing; we'll try again.
      }

      // This line will after:
      // a) an invalid value was read or
      // b) after there was an error in reading the value.
      // therefore it must be noted that value can be null.
      this.showError(value);
    }

    // Apply the preprocess method onto the value
    // and return the processed value.
    return this.preprocess(value);
  }
}
