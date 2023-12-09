package com.stanstrum.project03.Utils;

import java.util.Scanner;

/**
 * An extension of the {@link PreprocessedQuerier} that has mechanisms for
 * saving a leg number for usage in the prompt.
 */
public abstract class LegQuerier<E, P> extends PreprocessedQuerier<E, P> {
  /**
   * The leg number to show the user.
   */
  private int legNum;

  /**
   * @param legNum The value to set to {@link LegQuerier#legNum}.
   */
  public final void setLegNum(int legNum) {
    this.legNum = legNum;
  }

  /**
   * @return {@link LegQuerier#legNum}.
   */
  protected final int getLegNum() {
    return this.legNum;
  }

  public LegQuerier(Scanner scanner) {
    super(scanner);
  }
}
