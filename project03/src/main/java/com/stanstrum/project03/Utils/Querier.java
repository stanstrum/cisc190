package com.stanstrum.project03.Utils;

import java.util.Scanner;

/**
 * An extension of the {@link PreprocessedQuerier} that
 * utilizes no preprocessing, thus needing only one template
 * argument.
 */
public abstract class Querier<E> extends PreprocessedQuerier<E, E> {
  public Querier(Scanner scanner) {
    super(scanner);
  }

  @Override
  protected final E preprocess(E value) {
    return value;
  }
}
