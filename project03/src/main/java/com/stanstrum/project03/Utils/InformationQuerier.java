package com.stanstrum.project03.Utils;

import java.util.Scanner;

public abstract class InformationQuerier<E> {
  private final Scanner scanner;

  private E value = null;

  public final E getValue() {
    return value;
  }

  public final void setValue(E value) {
    this.value = value;
  }

  InformationQuerier(Scanner scanner) {
    this.scanner = scanner;
  }

  protected abstract E scanValue();
  protected abstract boolean verify(E value);

  protected abstract void showPrompt();

  protected void showError() {
    System.out.println("Try again ...");
  }

  public final void queryInfo() {
    E value;

    while (true) {
      this.showPrompt();

      value = this.scanValue();

      if (this.verify(value)) {
        break;
      }

      this.showError();
    }

    this.setValue(value);
  }
}
