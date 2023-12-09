package com.stanstrum.project03.Utils;

import java.util.Scanner;

public abstract class Querier<E> extends PreprocessedQuerier<E, E> {
  public Querier(Scanner scanner) {
    super(scanner);
  }

  @Override
  protected final E preprocess(E value) {
    return value;
  }
}
