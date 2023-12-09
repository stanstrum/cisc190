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

import com.stanstrum.project03.FlightInfo.Airport;
import com.stanstrum.project03.FlightInfo.Leg;

import com.stanstrum.project03.Queriers.ArrivalLegAirportQuerier;
import com.stanstrum.project03.Queriers.DepartureLegAirportQuerier;
import com.stanstrum.project03.Queriers.LegSpeedQuerier;
import com.stanstrum.project03.Queriers.LegsQuerier;

import com.stanstrum.project03.Utils.Resources;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Project03 {
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

		// Initialize our queriers.  These objects have
		// specific implementations that take care of
		// printing the prompt, scanning input, verifying,
		// and handling errors.  This allows this method
		// to be a lot simpler.
		LegsQuerier legsQuerier = new LegsQuerier(scanner);
		DepartureLegAirportQuerier departureAirportQuerier = new DepartureLegAirportQuerier(scanner);
		ArrivalLegAirportQuerier arrivalAirportQuerier = new ArrivalLegAirportQuerier(scanner);
		LegSpeedQuerier speedQuerier = new LegSpeedQuerier(scanner);

		// The amount of legs in this trip.
		int legCount = legsQuerier.query();

		// Create a list of Legs with a precomputed capacity.
		List<Leg> legs = new ArrayList<>(legCount);

		// The last airport we departed from to autofill this field.
		// This allows us to not ask the user for duplicate information.
		Airport departureAirport = null;

		// For each leg ...
		for (int i = 0; i < legCount; i++) {
			int legNum = i + 1;

			// Update the leg number for the queriers
			// so that they show the correct value in the
			// prompt.  This could be fixed with a slightly
			// different interface, but the implementation
			// would be very messy, and we would need
			// to supply legNum to the query functions anyway.
			departureAirportQuerier.setLegNum(legNum);
			arrivalAirportQuerier.setLegNum(legNum);
			speedQuerier.setLegNum(legNum);

			// If we haven't got a departure airport yet, ask for one.
			if (departureAirport == null) {
				// Get the first departure airport.
				departureAirport = departureAirportQuerier.query();
			}

			// Get the arrival airport.
			Airport arrivalAirport = arrivalAirportQuerier.query();

			// Get the speed in knots.
			double speed = speedQuerier.query();

			// Add the leg to the List, since the ArrayList
			// is not autofilled to the capacity.
			legs.add(new Leg(departureAirport, arrivalAirport, speed));

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
