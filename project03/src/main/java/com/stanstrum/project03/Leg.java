package com.stanstrum.project03;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Leg {
  /**
   * The {@link Airport} that this leg departs (begins) from.
   */
  private Airport departureAirport;

  /**
   * The {@link Airport} that this leg arrives (ends) at.
   */
  private Airport arrivalAirport;

  /**
   * @return {@link Leg#arrivalAirport}
   */
  public Airport getArrivalAirport() {
    return arrivalAirport;
  }

  /**
   * @return {@link Leg#departureAirport}
   */
  public Airport getDepartureAirport() {
    return this.departureAirport;
  }

  /**
   * Constructor for {@link Leg}.
   *
   * @param departureAirport The {@link Airport} that this leg begins at.
   * @param arrivalAirport The {@link Airport} that this leg ends at.
   */
  public Leg(Airport departureAirport, Airport arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
    this.departureAirport = departureAirport;
  }

  /**
   * Calculates the distance in nautical miles from the departure airport
   * and the arrival airport.
   *
   * @return The distance in nautical miles.
   *
   * @see {@link Airport#calcDistance(double, double, double, double)}
   */
  public double getDistance() {
    return this.departureAirport.calcDistance(this.arrivalAirport);
  }
}
