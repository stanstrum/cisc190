package com.stanstrum.project03.Dump;

import java.util.List;

import com.stanstrum.project03.Constants;
import com.stanstrum.project03.FlightInfo.Airport;
import com.stanstrum.project03.FlightInfo.Leg;

public class ConsoleDumper {
  /**
   * A method that dumps to the console a list of Legs that make up the trip.
   *
   * @param legs The flights that make up the itinerary.
   */
  public static void dump(List<Leg> legs) {
    System.out.println("Leg\tFrom\tTo\tDist (mi)\tSpeed (kt)\tTime (min)");

    // Accumulated values to show in totals row.
    double totalDistance = 0d;
    double totalTimeMins = 0d;

    // Iterate through the list with an index counter.
    for (int i = 0; i < legs.size(); i++) {
      Leg leg = legs.get(i);

      Airport departure = leg.getDepartureAirport();
      Airport arrival = leg.getArrivalAirport();

      // getTime returns seconds.
      double timeMins = leg.getTime() / (double)Constants.SECS_PER_MIN;
      // getDistance retursn knots.
      double distance = leg.getDistance() * Constants.MI_PER_NMI;

      // Print out the row.  I'm sure there's a nicer
      // utility to format a string with many variables,
      // but if it ain't broke ...
      System.out.printf(
        "%d\t%s\t%s\t%.0f\t\t%.0f\t\t%.0f\n",
        // Leg number adjusted from zero-indexed.
        i + 1,
        departure.getIdentifier(),
        arrival.getIdentifier(),
        distance,
        leg.getSpeed(),
        timeMins
      );

      // Accumulate totals.
      totalDistance += distance;
      totalTimeMins += timeMins;
    }

    // Print total row.
    System.out.printf("Total\t\t\t%.0f\t\t\t\t%.0f\n", totalDistance, totalTimeMins);
  }
}
