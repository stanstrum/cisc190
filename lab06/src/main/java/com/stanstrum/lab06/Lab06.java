/* Author: Stan Strum (555-010-9379)
 * Date: 3 Nov 2023
 * Description: Lab 6: Airport Class w/ Static Method
 */

package com.stanstrum.lab06;

import java.util.Scanner;

/**
 * @author Stan Strum <stanleystrum@gmail.com>
 */
public class Lab06 {
  /**
   * From assignment details:
   * "[Statute] miles ... is ... nautical miles * 1.15."
   */
  private static double MILES_PER_NAUTICAL_MILE = 1.15;

	public static void main(String[] args) {
		// Scanner for reading input from stdin.
		Scanner scanner = new Scanner(System.in);

		// Forward-declare variables for readability.
		double latOrigin, longOrigin;
		double latDestin, longDestin;

		// Get double values for origin.
		System.out.print("Enter origin latitude: ");
		latOrigin = scanner.nextDouble();

		System.out.print("Enter origin longitude: ");
		longOrigin = scanner.nextDouble();

		// Get double values for destination.
		System.out.print("Enter destination latitude: ");
		latDestin = scanner.nextDouble();

		System.out.print("Enter destination longitude: ");
		longDestin = scanner.nextDouble();

		double distanceNauticalMi = Airport.calcDistance(latOrigin, longOrigin, latDestin, longDestin);

		// Also compute distance in statute miles.
		double distanceMi = distanceNauticalMi * MILES_PER_NAUTICAL_MILE;

		// Print out information.
		System.out.printf("The distance between those points is %.1f nautical miles.\n", distanceNauticalMi);
		System.out.printf("The distance between those points is %.1f statute miles.\n", distanceMi);

		// Close scanner
		scanner.close();
	}
}
