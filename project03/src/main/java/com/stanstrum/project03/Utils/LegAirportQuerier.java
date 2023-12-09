package com.stanstrum.project03.Utils;

import java.util.Scanner;

import com.stanstrum.project03.FlightInfo.Airport;

public abstract class LegAirportQuerier extends LegQuerier<String, Airport> {
  public LegAirportQuerier(Scanner scanner) {
    super(scanner);
  }

  @Override
  protected final String scanValue() {
    return this.scanner.nextLine();
  }

  @Override
  protected final boolean verify(String code) {
    return Resources.hasAirport(code);
  }

  @Override
  protected final void showError(String code) {
    System.out.println("Unknown airport code: " + code.toUpperCase());
  }

  @Override
  protected final Airport preprocess(String code) {
    return Resources.getAirport(code);
  }
}
