/* Author: Stan Strum (555-010-9379)
 * Date: 29 Nov 2023
 * Description: Project 3: Flight Itinerary Processor
 */
package com.stanstrum.project03;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	private static void dumpInfoHTML(List<Leg> legs) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm LLLL yyyy");
		String date = dtf.format(LocalDateTime.now());

		double totalDistance = 0d;
		double totalTime = 0d;

		String head = Resources.getHtmlHead()
			.replace("%date", date);

		try (FileWriter outputFile = new FileWriter("itinerary.html")) {
			outputFile.write(head);

			for (int i = 0; i < legs.size(); i++) {
				Leg leg = legs.get(i);

				String legNum = Integer.toString(i + 1);

				Airport departureAirport = leg.getDepartureAirport();
				Airport arrivalAirport = leg.getArrivalAirport();

				String departureIdentifier = departureAirport.getIdentifier();
				String arrivalIdentifier = arrivalAirport.getIdentifier();
				String departureName = departureAirport.getName();
				String arrivalName = arrivalAirport.getName();

				double time = leg.getDistance() / KNOTS_PER_HOUR * (double)MINUTES_PER_HOUR;
				double distance = leg.getDistance();

				String formattedTime = String.format("%.0f", time);
				String formattedDistance = String.format("%.0f", distance);

				String listItem = Resources.getHtmlListItem()
					.replace("%legNum", legNum)
					.replace("%departureIdentifier", departureIdentifier)
					.replace("%arrivalIdentifier", arrivalIdentifier)
					.replace("%departureName", departureName)
					.replace("%arrivalName", arrivalName)
					.replace("%formattedTime", formattedTime)
					.replace("%distance", formattedDistance);

				outputFile.write(listItem);

				totalDistance += distance;
				totalTime += time;
			}

			Leg first = legs.get(0);
			Leg last = legs.get(legs.size() - 1);

			String foot = Resources.getHtmlFoot()
				.replace("%totalTime", String.format("%.0f", totalTime))
				.replace("%start", first.getDepartureAirport().getName())
				.replace("%end", last.getArrivalAirport().getName())
				.replace("%totalDistance", String.format("%.0f", totalDistance));

			outputFile.write(foot);

			outputFile.close();
		} catch (Exception e) {
			System.err.println("Failed to generate itinerary file: " + e);
		}
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

		dumpInfo(legs);
		dumpInfoHTML(legs);

		scanner.close();
	}
}
