/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Project 3: Flight Itinerary Processor
 */
package com.stanstrum.project03;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Project03 {
	private static Airport askUserForAirport(Scanner scanner, String prompt) {
		Airport airport = null;

		do {
			System.out.printf(prompt);
			String identifier = scanner.next();

			airport = Resources.getAirport(identifier);

			if (airport == null) {
				System.out.printf("Unknown airport code: %s\n", identifier.toUpperCase());
			}
		} while (airport == null);

		return airport;
	}

	private static final double KNOTS_PER_HOUR = 300d;
	private static final int MINUTES_PER_HOUR = 60;

	private static void dumpInfo(List<Leg> legs) {
		System.out.println("Leg\tFrom\tTo\tDist\tTime (mins)");

		double totalDistance = 0d;
		double totalTimeMins = 0d;
		for (int i = 0; i < legs.size(); i++) {
			Leg leg = legs.get(i);

			Airport departure = leg.getDepartureAirport();
			Airport arrival = leg.getArrivalAirport();
			double distance = leg.getDistance();

			double timeMins = distance / KNOTS_PER_HOUR * (double)MINUTES_PER_HOUR;

			System.out.printf(
				"%d\t%s\t%s\t%.0f\t%.0f\n",
				i + 1,
				departure.getIdentifier(), arrival.getIdentifier(), distance, timeMins
			);

			totalDistance += distance;
			totalTimeMins += timeMins;
		}

		System.out.printf("Total\t\t\t%.0f\t%.0f\n", totalDistance, totalTimeMins);
	}

	public static void main(String[] args) {
		try {
			Resources.load();
		} catch (Exception e) {
			System.err.println("Failed to load resources: " + e);

			return;
		}

		Scanner scanner = new Scanner(System.in);

		int legCount = 0;
		do {
			System.out.print("How many legs does your itineray have? ");

			try {
				// nextInt will crash if an invalid value is provided.
				legCount = Integer.parseInt(scanner.nextLine(), 10);
			} catch (Exception e) {
				System.out.println("Try again ...");
			}
		} while (legCount <= 0);

		List<Leg> legs = new ArrayList<>(legCount);

		Airport departureAirport = null;
		for (int i = 0; i < legCount; i++) {
			if (departureAirport == null) {
				StringBuilder departureAirportBuilder = new StringBuilder();

				departureAirportBuilder.append("Enter the identifier for the departure airport (e.g. SAN) for leg ");
				departureAirportBuilder.append(i + 1);
				departureAirportBuilder.append(": ");

				departureAirport = askUserForAirport(scanner, departureAirportBuilder.toString());
			}

			StringBuilder arrivalAirportBuilder = new StringBuilder();
			arrivalAirportBuilder.append("Enter the identifier for the arrival airport for leg ");
			arrivalAirportBuilder.append(i + 1);
			arrivalAirportBuilder.append(": ");

			Airport arrivalAirport = askUserForAirport(scanner, arrivalAirportBuilder.toString());

			legs.add(new Leg(departureAirport, arrivalAirport));

			departureAirport = arrivalAirport;
		}

		dumpInfo(legs);

		scanner.close();
	}
}
