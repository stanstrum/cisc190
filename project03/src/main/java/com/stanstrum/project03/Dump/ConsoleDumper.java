package com.stanstrum.project03.Dump;

import java.util.List;

import com.stanstrum.project03.Airport;
import com.stanstrum.project03.Constants;
import com.stanstrum.project03.Leg;

public class ConsoleDumper {
  /**
   * A method that dumps to the console a list of Legs that make up the trip.
   * @param legs
   */
  public static void dump(List<Leg> legs) {
    System.out.println("Leg\tFrom\tTo\tDist\tTime (mins)");

    double totalDistance = 0d;
    double totalTimeMins = 0d;

    // Iterate through the list with an index counter.
    for (int i = 0; i < legs.size(); i++) {
      Leg leg = legs.get(i);

      Airport departure = leg.getDepartureAirport();
      Airport arrival = leg.getArrivalAirport();
      double distance = leg.getDistance() * Constants.MI_PER_NMI;

      double timeMins = distance / Constants.KNOTS_PER_HOUR * (double)Constants.MINUTES_PER_HOUR;

      // Print out the row.  I'm sure there's a nicer
      // utility to format a string with many variables,
      // but if it ain't broke ...
      System.out.printf(
        "%d\t%s\t%s\t%.0f\t%.0f\n",
        // Leg number adjusted from zero-indexed.
        i + 1,
        departure.getIdentifier(), arrival.getIdentifier(),
        distance, timeMins
      );

      // Accumulate totals.
      totalDistance += distance;
      totalTimeMins += timeMins;
    }

    // Print total row.
    System.out.printf("Total\t\t\t%.0f\t%.0f\n", totalDistance, totalTimeMins);
  }
}
