/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Lab 9: Airport Class with Overloaded Constructor
 */

package com.stanstrum.lab09;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab09 {
	public static void main(String[] args) {
		// These values were provided in the details of the assignment.
		Airport sanDiego = new Airport();

		// San Diego International.
		sanDiego.setIdentifier("KSAN");
		sanDiego.setLatitude(32.7335556d);
		sanDiego.setLongitude(-117.1896667d);
		sanDiego.setMagneticVariation(14f);
		sanDiego.setElevation(16.8f);

		// The following values are from <www.gcmap.com>.
		Airport dallas = new Airport();

		// Dallas Fort Worth.
		dallas.setIdentifier("KDFW");
		dallas.setLatitude(32.896233d);
		dallas.setLongitude(-97.037694d);
		dallas.setMagneticVariation(2.85f);
		dallas.setElevation(606f);

		// Dump out the airport information.
		System.out.println("San Diego airport:");
		dumpAiportInfo(sanDiego);

		System.out.println("Dallas airport:");
		dumpAiportInfo(dallas);

		// Calculate distances using different overloads.
		double sanDiegoToDallasStatic = Airport.calcDistance(
			sanDiego.getLatitude(),
			sanDiego.getLongitude(),
			dallas.getLatitude(),
			dallas.getLongitude()
		);

		double sanDiegoToDallasInstanceCoords = sanDiego.calcDistance(
			dallas.getLatitude(),
			dallas.getLongitude()
		);

		double sanDiegoToDallasInstanceComparison = sanDiego.calcDistance(dallas);

		// Assert that these values are the same.
		assert
			(sanDiegoToDallasStatic == sanDiegoToDallasInstanceCoords) &&
			(sanDiegoToDallasInstanceCoords == sanDiegoToDallasInstanceComparison) :
			"Distances differ by invocation";

		// Show that we reached this point in the code -- that
		// the assertion passed.
		System.out.println("Calculated distance betwenn San Diego International Airport and Dallas Fort Worth International Airport:");
		System.out.printf("- %.2f nautical mile(s) using static Airport#calcDistance(double, double, double, double)\n", sanDiegoToDallasStatic);
		System.out.printf("- %.2f nautical mile(s) using instance Airport#calcDistance(double, double)\n", sanDiegoToDallasStatic);
		System.out.printf("- %.2f nautical mile(s) using instance Airport#calcDistance(Airport)\n", sanDiegoToDallasStatic);

		// No need to destroy these objects as they are
		// cleanly handled by the garbage collector.
	}

	/**
	 * A simple example method for using an instance of {@link Airport}.
	 * This method only dumps the information in the provided object.
	 *
	 * @param airport The {@link Airport} instance to dump.
	 */
	private static void dumpAiportInfo(Airport airport) {
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

		// Do the same for magnetic variation.
		char magneticVariationHemisphere = airport.getMagneticVariation() >= 0 ? 'E' : 'W';

		float absoluteMagneticVariation = Math.abs(airport.getMagneticVariation());

		// Dump information about airport to demonstrate functionality.
		System.out.println("Latitude: " + absoluteLatitude + "°" + latitudeHemisphere);
		System.out.println("Longitude: " + absoluteLongitude + "°" + longitudeHemisphere);

		System.out.println("Magnetic Variation: " + absoluteMagneticVariation + "°" + magneticVariationHemisphere);

		System.out.println("Elevation: " + airport.getElevation() + " feet");

		// Print an additional line so that multiple invocations
		// of this method are easily discernible.
		System.out.println();
	}
}
