/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Project 3: Flight Itinerary Processor
 */
package com.stanstrum.project03;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

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
			System.out.print("How many legs does your itinerary have? ");

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

		Dump.dumpInfo(legs);
		Dump.dumpInfoHTML(legs);

		scanner.close();
	}
}
