package com.stanstrum.project03;

public class Leg {
  private Airport departureAirport;
  private Airport arrivalAirport;

  public Airport getArrivalAirport() {
    return arrivalAirport;
  }

  public Airport getDepartureAirport() {
    return this.departureAirport;
  }

  public void setDepartureAirport(Airport departureAirport) {
    this.departureAirport = departureAirport;
  }

  public void setArrivalAirport(Airport arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  public Leg(Airport departureAirport, Airport arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
    this.departureAirport = departureAirport;
  }

  public double getDistance() {
    return this.departureAirport.calcDistance(this.arrivalAirport);
  }
}
