/* Author: Stan Strum (555-010-9379)
 * Date: 3 Nov 2023
 * Description: Lab 7: Rainfall
 */

package com.stanstrum.lab07;

import java.time.Month;

import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab07 {
	/**
	 * A helper function to convert a month into English-localized
	 * text.
	 *
	 * @param month The month to convert into a {@link String}.
	 * @return The stringified version of the provided {@link Month}.
	 */
	private static String localizeMonth(Month month) {
		return month.getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	/**
	 * The main method of this program.  This method demonstrates
	 * usage of the {@link Rainfall} class.
	 */
	public static void main(String[] args) {
		Rainfall rainfall = new Rainfall();

		try {
			// Demonstrate setting rainfall.
			rainfall.setRainfallForMonth(Month.JANUARY, 2.6);
			rainfall.setRainfallForMonth(Month.FEBRUARY, 2.2);
			rainfall.setRainfallForMonth(Month.MARCH, 2.0);
			rainfall.setRainfallForMonth(Month.APRIL, 1.6);
			rainfall.setRainfallForMonth(Month.MAY, 2.0);
			rainfall.setRainfallForMonth(Month.JUNE, 2.6);
			rainfall.setRainfallForMonth(Month.JULY, 2.8);
			rainfall.setRainfallForMonth(Month.AUGUST, 2.8);
			rainfall.setRainfallForMonth(Month.SEPTEMBER, 2.2);
			rainfall.setRainfallForMonth(Month.OCTOBER, 3.0);
			rainfall.setRainfallForMonth(Month.NOVEMBER, 2.6);
			rainfall.setRainfallForMonth(Month.DECEMBER, 2.6);
		} catch (Exception e) {
			// Exit if the correct usage fails.
			assert false : "Exception thrown when not expected: " + e;
		}

		try {
			rainfall.setRainfallForMonth(Month.JANUARY, -5d);

			// Exit if the incorrect usage succeeds.
			assert false : "An exception should have been thrown for " +
							"inserting a negative value to Rainfall";
		} catch (Exception e) {
			// We expect to throw here.
		}

		// Display the amounts of rain per month to verify heuristics.
		for (Month month : Month.values()) {
			double rainfallForMonth = rainfall.getRainfallForMonth(month);

			System.out.printf("Rainfall for %s: %.2f inch(es)\n", localizeMonth(month), rainfallForMonth);
		}

		// Delimit output to make it clearer.
		System.out.println("------------------------------------");

		// Get heuristics.
		Month monthWithLeastRainfall = rainfall.getMonthWithLeastRainfall();
		Month monthWithMostRainfall = rainfall.getMonthWithMostRainfall();
		double totalRainfall = rainfall.getTotalRainfall();
		double averageRainfall = rainfall.getAverageRainfall();

		// Print them accordingly
		System.out.println("Month with least rainfall: " + localizeMonth(monthWithLeastRainfall));
		System.out.println("Month with most rainfall: " + localizeMonth(monthWithMostRainfall));
		System.out.printf("Total rainfall: %.2f inch(es)\n", totalRainfall);
		System.out.printf("Average rainfall: %.2f inch(es) per month\n", averageRainfall);
	}
}
