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
	 * Magnetic variation represented in degrees east.
	 * A negative value here represents degrees west.
	 * A zero value here means no magnetic variation.
	 *
	 * @note Using a float here since it seems that
	 * magnetic variation is not often measured to
	 * extreme precision.
	 */
	private float magneticVariation;

	/**
	 * Elevation of airport in feet.
	 *
	 * @note This field represents the MSL (Mean Sea Level)
	 * elevation of the airport.
	 */
	private float elevation;

	/**
	 * @return {@link Airport#identifier}
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * @param identifier The value to set to {@link Airport#identifier}
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
	 * @param latitude The value to set to {@link Airport#latitude}
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
	 * @param longitude The value to set to {@link Airport#longitude}
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
	 * @param magneticVariation The value to set to {@link Airport#magneticVariation}
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
	 * @param elevation The value to set to {@link Airport#elevation}
	 */
	public void setElevation(float elevation) {
		this.elevation = elevation;
	}
}
