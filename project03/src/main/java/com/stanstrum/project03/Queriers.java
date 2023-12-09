package com.stanstrum.project03;

import java.util.Scanner;

import com.stanstrum.project03.Utils.Querier;
import com.stanstrum.project03.Utils.LegAirportQuerier;

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
        "Enter the identifier for the departure airport (e.g. SAN) for leg %d: ",
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
        "Enter the identifier for the arrival airport for leg %d: ",
        this.getLegNum()
      );
    }
  }
}
