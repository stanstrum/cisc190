/* Author: Stan Strum (555-010-9379)
 * Date: 3 Nov 2023
 * Description: Lab 6: Airport Class w/ Static Method
 */

package com.stanstrum.lab06;

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
	 * <p>
	 *   Note: using a float here since it seems that
	 *   magnetic variation is not often measured to
	 *   extreme precision.
	 * </p>
	 */
	private float magneticVariation;

	/**
	 * Elevation of airport in feet.
	 *
	 * <p>
	 *   Note: This field represents the MSL (Mean Sea Level)
	 *   elevation of the airport.
	 * </p>
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

	/**
	 * From assignment details:
	 * "The radius of the earth is a constant and its value in radians is r = 10800 / PI".
	 *
	 * <p>
	 *   Note: this is not correct for two reasons:
	 * </p>
	 *
	 * <ol>
	 *   <li>
	 *     Earth's radius is not the same at all points on the surface.
	 *     It is most analogous to an ellipsoid, which has a variable radius
	 *     depending on the point on the surface being measured.
	 *   </li>
	 *   <li>
	 *     A circle's radius is always one radian, since that is what
	 *     a radian means.  This value approximately equals 3437.74, which is an
	 *     approximation of the Earth's radius in nautical miles.
	 *   </li>
	 * </ol>
	 */
	public static double EARTH_RADIUS = 10800 / Math.PI;

	/**
	 * Approximates the distance between a two pairs of lat/long
	 * coordinates using a Great-Circle distance formula.
	 *
	 * <p>
	 *   Note: Earth is not a perfect sphere.  Use an ellipsoid arclength
	 *   function that utilizes the polar radii to calculate a more precise distance.
	 * </p>
	 *
	 * <p>
	 *   Additionally, this should probably take in two Airport objects
	 *   and calculate the distances using their latitude &amp; longitude
	 *   values.  Otherwise, it doesn't make a whole lot of sense
	 *   to have this method belong to Airport -- being effectively
	 *   invariant to the Airport class altogether.
	 * </p>
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

		// "Distance = Radius * DeltaAngle" -- Distance here is actually
		// in nautical miles due to the EARTH_RADIUS constant.
		double distance = Airport.EARTH_RADIUS * deltaAngle;

		// "... should return the distance in nautical miles ..."
		return distance;
	}
}
