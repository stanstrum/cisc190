/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Lab 9: Airport Class with Overloaded Constructor
 */

package com.stanstrum.lab09;

/**
 * An object to represent a physical airport and some
 * of its most important attributes.
 */
public class Airport {
	/**
	 * ICAO identifier for the airport; e.g.:
	 * KSAN for Lindbergh Field.
	 */
	private String identifier;

	/**
	 * Latitude represented in degrees north.
	 * A negative value here represents degrees south.
	 */
	private double latitude;

	/**
	 * Longitude represented in degrees east.
	 * A negative value here represents degrees west.
	 */
	private double longitude;

	/**
	 * <p>
	 *   Magnetic variation represented in degrees east.
	 *   A negative value here represents degrees west.
	 *   A zero value here means no magnetic variation.
	 * </p>
	 * <p>
	 *   Note: using a float here since it seems that
	 *   magnetic variation is not often measured to
	 *   extreme precision.
	 * </p>
	 */
	private float magneticVariation;

	/**
	 * <p>
	 *   Elevation of airport in feet.
	 * </p>
	 * <p>
	 *   Note: this field represents the MSL (Mean Sea Level)
	 *   elevation of the airport.
	 * </p>
	 */
	private float elevation;

	/**
	 * Constructor for {@link Airport}.  Defaults
	 * all field values to zeroes and empty strings.
	 */
	public Airport() {
		// This stub method was not necessary when
		// the class had no overloaded constructors
		// since Java auto-implements a no-op constructor
		// when none are specified.
	}

	/**
	 * Overload for {@link Airport#Airport()} that takes coordinates
	 * as arguments.
	 *
	 * @param latitude The latitude in degrees of the airport.
	 * @param longitude The longitude in degrees of the airport.
	 */
	public Airport(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return {@link Airport#identifier}
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * @param identifier The value to set to {@link Airport#identifier}.
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return {@link Airport#latitude}
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * @param latitude The value to set to {@link Airport#latitude}.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return {@link Airport#longitude}
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * @param longitude The value to set to {@link Airport#longitude}.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return {@link Airport#magneticVariation}
	 */
	public float getMagneticVariation() {
		return this.magneticVariation;
	}

	/**
	 * @param magneticVariation The value to set to {@link Airport#magneticVariation}.
	 */
	public void setMagneticVariation(float magneticVariation) {
		this.magneticVariation = magneticVariation;
	}

	/**
	 * @return {@link Airport#elevation}
	 */
	public float getElevation() {
		return this.elevation;
	}

	/**
	 * @param elevation The value to set to {@link Airport#elevation}.
	 */
	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	/**
	 * An approximation of Earth's radius in nautical miles.
	 */
	public static double EARTH_RADIUS = 10800 / Math.PI;

	/**
	 * Approximates the distance between a two pairs of lat/long
	 * coordinates using a Great-Circle distance formula.
	 *
	 * @param latOrigin Latitude (in degrees) of the origin point.
	 * @param longOrigin Longitude (in degrees) of the origin point.
	 * @param latDestin Latitude (in degrees) of the destination point.
	 * @param longDestin Longitude (in degrees) of the destination point.
	 *
	 * @return The distance between these points in nautical miles.
	 */
	public static double calcDistance(double latOrigin, double longOrigin, double latDestin, double longDestin) {
		// "Because the coordinates on earth are given in degrees,
		// you need to convert the degrees into radians by multiplying
		// the degrees * PI / 180."
		double latOriginRad = (latOrigin * Math.PI) / 180;
		double longOriginRad = (longOrigin * Math.PI) / 180;

		double latDestinRad = (latDestin * Math.PI) / 180;
		double longDestinRad = (longDestin * Math.PI) / 180;

		// "DeltaAngle = acos(sin(Lat1) * sin(Lat2) + cos(Lat1) * cos(Lat2) * cos(Long1 – Long2))"
		double deltaAngle = Math.acos(
			Math.sin(latOriginRad) * Math.sin(latDestinRad) +
			Math.cos(latOriginRad) * Math.cos(latDestinRad) * Math.cos(longOriginRad - longDestinRad)
		);

		// "Distance = Radius * DeltaAngle" -- Distance here is
		// in nautical miles due to the EARTH_RADIUS constant.
		double distance = Airport.EARTH_RADIUS * deltaAngle;

		// "... should return the distance in nautical miles ..."
		return distance;
	}

	/**
	 * An overload for {@link Airport#calcDistance(double, double, double, double)} to be
	 * used by instances of {@link Airport} to calculate their distance from another
	 * {@link Airport} instance.
	 *
	 * @param other The other {@link Airport} to calculate the distance from.
	 *
	 * @return The distance between the airports.
	 *
	 * @see {@link Airport#calcDistance(double, double, double, double)} for more information.
	 */
	public double calcDistance(Airport other) {
		return Airport.calcDistance(this.latitude, this.longitude, other.latitude, other.longitude);
	}

	/**
	 * An overload for {@link Airport#calcDistance(double, double, double, double)} to be used
	 * by instances of {@link Airport} to calculate their distance from a pair of
	 * latitude/longitude coordinates.
	 *
	 * @param otherLatitude The other latitude to calculate the distance from.
	 * @param otherLongitude The other longitude to calculate the distance from.
	 *
	 * @return The distance between this {@link Airport} and the provided coordinates.
	 *
	 * @see {@link Airport#calcDistance(double, double, double, double)} for more information.
	 */
	public double calcDistance(double otherLatitude, double otherLongitude) {
		return Airport.calcDistance(this.latitude, this.longitude, otherLatitude, otherLongitude);
	}
}
