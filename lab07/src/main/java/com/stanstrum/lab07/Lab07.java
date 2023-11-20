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
	private static String localizeMonth(Month month) {
		return month.getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	public static void main(String[] args) {
		Rainfall rainfall = new Rainfall();

		try {
			rainfall.setRainfallForMonth(Month.JANUARY, 2.6);
			rainfall.setRainfallForMonth(Month.FEBRUARY, 2.2);
			rainfall.setRainfallForMonth(Month.MARCH, 2.0);
			rainfall.setRainfallForMonth(Month.APRIL, 1.6);
			rainfall.setRainfallForMonth(Month.MAY, 2.0);
			rainfall.setRainfallForMonth(Month.JUNE, 2.6);
			rainfall.setRainfallForMonth(Month.JULY, 2.8);
			rainfall.setRainfallForMonth(Month.AUGUST, 2.8);
			rainfall.setRainfallForMonth(Month.SEPTEMBER, 2.2);
			rainfall.setRainfallForMonth(Month.AUGUST, 3.0);
			rainfall.setRainfallForMonth(Month.OCTOBER, 2.6);
			rainfall.setRainfallForMonth(Month.DECEMBER, 2.6);
		} catch (Exception e) {
			assert false : "Exception thrown when not expected: " + e;
		}

		try {
			rainfall.setRainfallForMonth(Month.JANUARY, -5d);

			assert false : "An exception should have been thrown for " +
							"inserting a negative value to Rainfall";
		} catch (Exception e) {
			// We expect to except here.
		}

		System.out.println(localizeMonth(rainfall.getMonthWithLeastRainfall()));
		System.out.println(localizeMonth(rainfall.getMonthWithMostRainfall()));
		System.out.println(rainfall.getTotalRainfall());
		System.out.println(rainfall.getAverageRainfall());
	}
}
