package com.stanstrum.lab07;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rainfall {
	/**
	 * The amount of months in a year.
	 */
	public static final int MONTHS = Month.values().length;

	/**
	 * The list that will be used for storing each month's amount of rainfall.
	 */
	private final List<Double> rainfallByMonth;

	/**
	 * From {@link Month#of} spec:
	 * "The int value follows the ISO-8601 standard,from 1 (January) to 12 (December)."
	 *
	 * In this class, we sometimes need to convert from an array index (zero-based) to
	 * a {@link Month}.  This method takes care of that conversion for convenience.
	 *
	 * @param idx The zero-based index to convert into a {@link Month}
	 * @return A {@link Month} corresponding to the provided index.
	 */
	private static Month monthFromZeroIndex(int idx) {
		return Month.of(idx + 1);
	}

	/**
	 *
	 * From {@link Month#getValue}:
	 * "The values are numbered following the ISO-8601 standard, from 1 (January) to 12 (December)."
	 *
	 * Since the array is zero-indexed, we need to subtract one to select
	 * the correct element in the list.  This method takes care of that
	 * conversion for convenience.
	 *
	 * @param month The {@link Month} object to convert into a zero-based index
	 * @return The zero-based index corresponding to the provided {@link Month}.
	 */
	private static int zeroIndexFromMonth(Month month) {
		return month.getValue() - 1;
	}

	/**
	 * @return The total amount of rainfall stored in this object.
	 */
	public double getTotalRainfall() {
		double sum = 0d;

		for (double rainfall : rainfallByMonth) {
			sum += rainfall;
		}

		return sum;
	}

  /**
	 * @return The average amount of rainfall per month stored in this object.
	 */
	public double getAverageRainfall() {
		return this.getTotalRainfall() / (double)Rainfall.MONTHS;
	}

	/**
	 * @return The {@link Month} that corresponds to the least amount of rainfall stored in this object.
	 */
	public Month getMonthWithLeastRainfall() {
		// Default the lowest we've found to be the first in the list.
		// If any others are lower, this will be overwritten.
		int currentLeastRainfallIdx = 0;

		// Loop over each month by index.
		for (int i = 0; i < Rainfall.MONTHS; i++) {
			double currentLeastRainfall = this.rainfallByMonth.get(currentLeastRainfallIdx);
			double newRainfall = this.rainfallByMonth.get(i);

			// Compare our current lowest amount with the new amount.
			if (newRainfall < currentLeastRainfall) {
				// If the new value is lower, select its index for our lowest.
				currentLeastRainfallIdx = i;
			}
		}

		// Convert the index into a proper Month.
		Month month = Rainfall.monthFromZeroIndex(currentLeastRainfallIdx);

		// Return it.
		return month;
	}

	/**
	 * @return The {@link Month} that corresponds to the most amount of rainfall stored in this object.
	 */
	public Month getMonthWithMostRainfall() {
		// Default the highest we've found to be the first in the list.
		// If any others are higher, this will be overwritten.

		int currentLeastRainfallIdx = 0;

		// Loop over each month by index.
		for (int i = 0; i < Rainfall.MONTHS; i++) {
			double currentLeastRainfall = this.rainfallByMonth.get(currentLeastRainfallIdx);
			double newRainfall = this.rainfallByMonth.get(i);

			// Compare our current highest amount with the new amount.
			if (newRainfall > currentLeastRainfall) {
				// If the new value is higher, select its index for our highest.
				currentLeastRainfallIdx = i;
			}
		}

		// Convert the index into a proper Month.
		Month month = Rainfall.monthFromZeroIndex(currentLeastRainfallIdx);

		// Return it.
		return month;
	}

	/**
	 * Sets the rainfall for a specific month.
	 *
	 * @param month The {@link Month} to set the rainfall of.
	 * @param rainfall The amount of rainfall to set for this month.
	 *
	 * @throws Exception if a negative value is provided -- you cannot have negative rain.
	 */
	public void setRainfallForMonth(Month month, double rainfall) throws Exception {
		// Verify the rainfall is non-negative.
		if (rainfall < 0) {
			throw new Exception("Cannot use negative rainfall");
		}

		// Get the index from the month.
		int idx = Rainfall.zeroIndexFromMonth(month);

		// Set it accordingly.
		this.rainfallByMonth.set(idx, rainfall);
	}

	/**
	 * Gets the rainfall of a specific month.
	 *
	 * @param month The {@link Month} to get the rainfall of.
	 * @return The amount of rainfall for this month.
	 */
	public double getRainfallForMonth(Month month) {
		// Get the index from the month.
		int idx = Rainfall.zeroIndexFromMonth(month);

		// Return the value accordingly.
		return this.rainfallByMonth.get(idx);
	}

	/**
	 * Constructor for {@link Rainfall}.  Initializes the internal
	 * list of rainfall by month to zero values.
	 */
	public Rainfall() {
		// This creates a List of size `MONTHS` initialized to zeroes.
		this.rainfallByMonth = new ArrayList<>(Collections.nCopies(MONTHS, 0d));
	}
}
