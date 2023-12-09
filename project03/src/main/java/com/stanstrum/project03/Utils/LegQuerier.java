package com.stanstrum.project03.Utils;

import java.util.Scanner;

public abstract class LegQuerier<E, P> extends PreprocessedQuerier<E, P> {
  private int legNum;

  public final void setLegNum(int legNum) {
    this.legNum = legNum;
  }

  protected final int getLegNum() {
    return this.legNum;
  }

  public LegQuerier(Scanner scanner) {
    super(scanner);
  }
}
