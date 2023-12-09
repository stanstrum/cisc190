package com.stanstrum.project03;

import java.util.Scanner;

import com.stanstrum.project03.Utils.LegAirportQuerier;
import com.stanstrum.project03.Utils.LegQuerier;
import com.stanstrum.project03.Utils.Querier;

public class Queriers {
  public static class LegsQuerier extends Querier<Integer> {
    LegsQuerier(Scanner scanner) {
      super(scanner);
    }

    @Override
    protected void showPrompt() {
      System.out.print("How many legs does your itinerary have? ");
    }

    @Override
    protected Integer scanValue() {
      String line = this.scanner.nextLine();

      return Integer.parseInt(line);
    }

    @Override
    protected boolean verify(Integer value) {
      return value >= 1;
    }
  }

  public static class ArrivalLegAirportQuerier extends LegAirportQuerier {
    public ArrivalLegAirportQuerier(Scanner scanner) {
      super(scanner);
    }

    @Override
    protected void showPrompt() {
      System.out.printf(
        "Enter the identifier for the arrival airport for leg %d: ",
        this.getLegNum()
      );
    }
  }

  public static class DepartureLegAirportQuerier extends LegAirportQuerier {
    public DepartureLegAirportQuerier(Scanner scanner) {
			super(scanner);
		}

    @Override
    protected void showPrompt() {
      System.out.printf(
        "Enter the identifier for the departure airport (e.g. SAN) for leg %d: ",
        this.getLegNum()
      );
    }
  }

  public static class LegSpeedQuerier extends LegQuerier<Double, Double> {
    public LegSpeedQuerier(Scanner scanner) {
      super(scanner);
    }

    @Override
    protected void showPrompt() {
      System.out.printf("Enter the speed for leg %d: ", this.getLegNum());
    }

    @Override
    protected Double scanValue() throws Exception {
      String line = this.scanner.nextLine();

      return Double.parseDouble(line);
    }

    @Override
    protected boolean verify(Double value) {
      // Must be greater than zero since the formula for
      // duration is distance / speed.  If speed is zero,
      // duration is infinity.
      return value > 0;
    }

    // We hae to implement this since our superclass
    // uses the base PreprocessedQuerier, and the LegAirportQuerier
    // needs the preprocess method.
    @Override
    protected Double preprocess(Double value) {
      // Do nothing.
      return value;
    }
  }
}
