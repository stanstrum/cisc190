package com.stanstrum.project03.FlightInfo;

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
   * The speed in knots of the this leg.
   */
  private double speed;

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
   * @return {@link Leg#speed}
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Constructor for {@link Leg}.
   *
   * @param departureAirport The {@link Airport} that this leg begins at.
   * @param arrivalAirport The {@link Airport} that this leg ends at.
   */
  public Leg(Airport departureAirport, Airport arrivalAirport, double speed) {
    this.arrivalAirport = arrivalAirport;
    this.departureAirport = departureAirport;
    this.speed = speed;
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

  // 1 hour = 60 minutes * 60 seconds = 3600 seconds.
  private static final int SECONDS_PER_HOUR = 3600;

  /**
   * Calculates the duration of the flight time using the distance
   * between {@link Leg#departureAirport} and {@link Leg#arrivalAirport}.
   *
   * @return The duration of this leg in seconds.
   */
  public int getTime() {
    double hours = this.getDistance() / this.speed;
    double seconds = hours * (double)SECONDS_PER_HOUR;

    return (int)seconds;
  }
}
