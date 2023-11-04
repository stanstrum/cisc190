/* Author: Stan Strum (555-010-9379)
 * Date: 1 Nov 2023
 * Description: Lab 5: Airport Class Demonstration
 */

package com.stanstrum.lab05;

/**
 * @author stanstrum
 */
public class Lab05 {
	public static void main(String[] args) {
		// These values were provided in the details of the assignment.
		Airport sanDiego = new Airport();

		// San Diego International
		sanDiego.setIdentifier("KSAN");
		sanDiego.setLatitude(32.7335556d);
		sanDiego.setLongitude(-117.1896667d);
		sanDiego.setMagneticVariation(14f);
		sanDiego.setElevation(16.8f);

		// The following values are from <www.gcmap.com>
		Airport dallas = new Airport();

		// Dallas Fort Worth
		dallas.setIdentifier("KDFW");
		dallas.setLatitude(32.896233d);
		dallas.setLongitude(-97.037694d);
		dallas.setMagneticVariation(2.85f);
		dallas.setElevation(606f);

		Airport london = new Airport();

		// London Heathrow
		london.setIdentifier("EGLL");
		london.setLatitude(51.4775d);
		london.setLongitude(-0.461388d);
		london.setMagneticVariation(0.68f);
		london.setElevation(83f);

		System.out.println("San Diego airport:");
		doThingsWithAirport(sanDiego);

		System.out.println("Dallas airport:");
		doThingsWithAirport(dallas);

		System.out.println("London airport:");
		doThingsWithAirport(london);

		// No need to destroy these objects as they are
		// cleanly handled by the garbage collector.
	}

	/**
	 * A simple example method for using an instance of {@link Airport}.
	 * This method only dumps the information in the provided object.
	 *
	 * @param airport The {@link Airport} instance to dump.
	 */
	private static void doThingsWithAirport(Airport airport) {
		// Rather than throwing an error, just show a message
		// in the case that we have a null Airport
		if (airport == null) {
			System.out.println("Airport object is null");

			return;
		};

		System.out.println("ICAO Identifier: " + airport.getIdentifier());

		// Customarily, the coordinates 0°, 0° are written with north and
		// east despite the fact that they do not necessarily pertain to
		// any hemisphere.
		char latitudeHemisphere = airport.getLatitude() >= 0 ? 'N' : 'S';
		char longitudeHemisphere = airport.getLongitude() >= 0 ? 'E' : 'W';

		double absoluteLatitude = Math.abs(airport.getLatitude());
		double absoluteLongitude = Math.abs(airport.getLongitude());

		// Do the same for magnetic variation
		char magneticVariationHemisphere = airport.getMagneticVariation() >= 0 ? 'E' : 'W';

		float absoluteMagneticVariation = Math.abs(airport.getMagneticVariation());

		// Dump information about airport to demonstrate functionality
		System.out.println("Latitude: " + absoluteLatitude + "°" + latitudeHemisphere);
		System.out.println("Longitude: " + absoluteLongitude + "°" + longitudeHemisphere);

		System.out.println("Magnetic Variation: " + absoluteMagneticVariation + "°" + magneticVariationHemisphere);

		System.out.println("Elevation: " + airport.getElevation() + " feet");

		// Print an additional line so that multiple invocations
		// of this method are easily discernible.
		System.out.println();
	}
}
