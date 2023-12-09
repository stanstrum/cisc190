/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Project 3: Flight Itinerary Processor
 */
package com.stanstrum.project03;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import com.stanstrum.project03.Dump.ConsoleDumper;
import com.stanstrum.project03.Dump.HTMLDumper;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Project03 {
	/**
	 * <p>
	 *   A helper method that asks the user for an airport's IATA code.
	 * </p>
	 * <p>
	 *   Note: this method relies on {@link Resources} already having successfully
	 *   loaded resources.
	 * </p>
	 *
	 * @param scanner The scanner to use to read input.
	 * @param prompt The prompt to show the user.
	 *
	 * @return An {@link Airport} that corresponds to the provided code.
	 *
	 * @see {@link Resource#load}.
	 */
	private static Airport askUserForAirport(Scanner scanner, String prompt) {
		Airport airport = null;

		do {
			// Show the prompt to the user.
			System.out.printf(prompt);

			// Get user input.
			String iataCode = scanner.next();

			// Get the airport by code -- will be null if none is found.
			airport = Resources.getAirport(iataCode);

			// If none was found by that code, show an error and repeat.
			if (airport == null) {
				System.out.printf("Unknown airport code: %s\n", iataCode.toUpperCase());
			}
		} while (airport == null);

		// We found an airport; return it.
		return airport;
	}

	/**
	 * The main method of the itinerary program.
	 */
	public static void main(String[] args) {
		// Load resources (airport data, HTML source text).
		try {
			Resources.load();
		} catch (Exception e) {
			System.err.println("Failed to load resources: " + e);

			// Do not continue if we could not load resources.
			// Otherwise, program *will* crash.
			return;
		}

		// Create a scanner to read user input.
		Scanner scanner = new Scanner(System.in);

		// The amount of legs in this trip.
		int legCount = 0;
		do {
			// Prompt the user.
			System.out.print("How many legs does your itinerary have? ");

			// Get the amount of legs until a valid integer is found.
			try {
				// nextInt will crash if an invalid value is provided.
				legCount = Integer.parseInt(scanner.nextLine(), 10);
			} catch (Exception e) {
				System.out.println("Try again ...");
			}
			// Ensure legs is non-negative and nonzero as a trip must have at least one leg.
		} while (legCount <= 0);

		// Create a list of Legs with a precomputed capacity.
		List<Leg> legs = new ArrayList<>(legCount);

		// The last airport we departed from to autofill this field.
		// This allows us to not ask the user for duplicate information.
		Airport departureAirport = null;

		// For each leg ...
		for (int i = 0; i < legCount; i++) {
			// If we haven't got a departure airport yet, ask for one.
			if (departureAirport == null) {
				// Build the prompt for asking for a departure airport.
				StringBuilder departureAirportBuilder = new StringBuilder();

				// This will only happen the first time, so show the (e.g.) here.
				departureAirportBuilder.append("Enter the identifier for the departure airport (e.g. SAN) for leg ");
				departureAirportBuilder.append(i + 1);
				departureAirportBuilder.append(": ");

				// Get our departure airport.  Now, we only have to ask the
				// next arrival airport each time.
				departureAirport = askUserForAirport(scanner, departureAirportBuilder.toString());
			}

			// Get the arrival airport.
			StringBuilder arrivalAirportBuilder = new StringBuilder();
			arrivalAirportBuilder.append("Enter the identifier for the arrival airport for leg ");
			arrivalAirportBuilder.append(i + 1);
			arrivalAirportBuilder.append(": ");

			Airport arrivalAirport = askUserForAirport(scanner, arrivalAirportBuilder.toString());

			// Add the leg to the List, since the ArrayList
			// is not autofilled to the capacity.
			legs.add(new Leg(departureAirport, arrivalAirport));

			// Set our last departure airport to our arrival airport.
			// If we fly from San Diego to Austin, our next leg starts
			// from Austin.
			departureAirport = arrivalAirport;
		}

		// Dump the info to the console.
		ConsoleDumper.dump(legs);

		// Dump the info to an HTML file.
		HTMLDumper.dump(legs);

		// Don't leak.
		scanner.close();
	}
}
