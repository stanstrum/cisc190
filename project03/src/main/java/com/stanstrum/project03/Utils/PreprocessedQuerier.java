package com.stanstrum.project03.Utils;

import java.util.Scanner;

public abstract class PreprocessedQuerier<E, P> {
  protected final Scanner scanner;

  public PreprocessedQuerier(Scanner scanner) {
    this.scanner = scanner;
  }

  protected abstract void showPrompt();

  protected abstract E scanValue() throws Exception;
  protected abstract boolean verify(E value);

  /**
   * <p></p>
   * <p>
   *   Note: value <i>may</i> be null in this method as
   *   this method is used after an invalid value was read or
   *   after an error occurred when reading said value.
   * </p>
   *
   * @param value The value read, possibly `null`.
   */
  protected void showError(E value) {
    System.out.println("Try again ...");
  }

  protected abstract P preprocess(E value);

  public final P query() {
    E value = null;

    while (true) {
      this.showPrompt();

      try {
        value = this.scanValue();

        if (this.verify(value)) {
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

    return this.preprocess(value);
  }
}
