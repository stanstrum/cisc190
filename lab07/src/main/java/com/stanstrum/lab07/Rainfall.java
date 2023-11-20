package com.stanstrum.lab07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.time.Month;

public class Rainfall {
	public static final int MONTHS = Month.values().length;

	private final List<Double> rainfallByMonth;

	public double getTotalRainfall() {
		double sum = 0d;

		for (double rainfall : rainfallByMonth) {
			sum += rainfall;
		}

		return sum;
	}

	public double getAverageRainfall() {
		return this.getTotalRainfall() / 12d;
	}

	public Month getMonthWithLeastRainfall() {
		int currentLeastRainfallIdx = 0;

		for (int i = 0; i < Rainfall.MONTHS; i++) {
			double currentLeastRainfall = this.rainfallByMonth.get(currentLeastRainfallIdx);
			double newRainfall = this.rainfallByMonth.get(i);

			if (newRainfall < currentLeastRainfall) {
				currentLeastRainfallIdx = i;
			}
		}

		return Month.of(currentLeastRainfallIdx);
	}

	public Month getMonthWithMostRainfall() {
		int currentLeastRainfallIdx = 0;

		for (int i = 0; i < Rainfall.MONTHS; i++) {
			double currentLeastRainfall = this.rainfallByMonth.get(currentLeastRainfallIdx);
			double newRainfall = this.rainfallByMonth.get(i);

			if (newRainfall > currentLeastRainfall) {
				currentLeastRainfallIdx = i;
			}
		}

		return Month.of(currentLeastRainfallIdx);
	}

	public void setRainfallForMonth(Month month, double rainfall) throws Exception {
		if (rainfall < 0) {
			throw new Exception("Cannot use negative rainfall");
		}

		this.rainfallByMonth.set(month.ordinal(), rainfall);
	}

	public Rainfall() {
		this.rainfallByMonth = new ArrayList<>(Collections.nCopies(MONTHS, 0d));
	}
}
