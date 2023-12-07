package com.stanstrum.project03;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Dump {
	// Self-explanatory.
  private static final double KNOTS_PER_HOUR = 300d;
	private static final int MINUTES_PER_HOUR = 60;

	/**
	 * Miles per nautical mile.
	 */
  private static double MI_PER_NMI = 1.15077945d;

	/**
	 * A method that dumps to the console a list of Legs that make up the trip.
	 * @param legs
	 */
	public static void dumpInfoConsole(List<Leg> legs) {
		System.out.println("Leg\tFrom\tTo\tDist\tTime (mins)");

		double totalDistance = 0d;
		double totalTimeMins = 0d;

		// Iterate through the list with an index counter.
		for (int i = 0; i < legs.size(); i++) {
			Leg leg = legs.get(i);

			Airport departure = leg.getDepartureAirport();
			Airport arrival = leg.getArrivalAirport();
			double distance = leg.getDistance() * MI_PER_NMI;

			double timeMins = distance / KNOTS_PER_HOUR * (double)MINUTES_PER_HOUR;

			// Print out the row.  I'm sure there's a nicer
			// utility to format a string with many variables,
			// but if it ain't broke ...
			System.out.printf(
				"%d\t%s\t%s\t%.0f\t%.0f\n",
				// Leg number adjusted from zero-indexed.
				i + 1,
				departure.getIdentifier(), arrival.getIdentifier(),
				distance, timeMins
			);

			// Accumulate totals.
			totalDistance += distance;
			totalTimeMins += timeMins;
		}

		// Print total row.
		System.out.printf("Total\t\t\t%.0f\t%.0f\n", totalDistance, totalTimeMins);
	}

	public static void dumpInfoHTML(List<Leg> legs) {
		// Create date-time formatter for filling out
		// the templates in the HTML resources.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String date = dtf.format(LocalDateTime.now());

		double totalDistance = 0d;
		double totalTime = 0d;

		// Replace special template tags from the resource text.
		// This is not ideal for large amounts of text.
		String head = Resources.getHtmlHead()
			.replace("%date", date);

		// Open the output file for reading with a try resource.
		try (FileWriter outputFile = new FileWriter("itinerary.html")) {
			// Write the head that's been filled out with the formatted date.
			outputFile.write(head);

			// Iterate through legs with an index counter.
			for (int i = 0; i < legs.size(); i++) {
				Leg leg = legs.get(i);

				// Compute some variables to replace the templates in the
				// resource text.
				String legNum = Integer.toString(i + 1);

				Airport departureAirport = leg.getDepartureAirport();
				Airport arrivalAirport = leg.getArrivalAirport();

				String departureIdentifier = departureAirport.getIdentifier();
				String arrivalIdentifier = arrivalAirport.getIdentifier();
				String departureName = departureAirport.getName();
				String arrivalName = arrivalAirport.getName();

				double time = leg.getDistance() / KNOTS_PER_HOUR * (double)MINUTES_PER_HOUR;
				double distance = leg.getDistance() * MI_PER_NMI;

				// Use pretty-printing code from prior labs.
				String formattedTime = PrettyPrint.prettyPrintTime((int)time * PrettyPrint.SECONDS_PER_MINUTE);
				String formattedDistance = PrettyPrint.prettyPrintNumber((int)distance);

				// Replace the template tags.
				String listItem = Resources.getHtmlListItem()
					.replace("%legNum", legNum)
					.replace("%departureIdentifier", departureIdentifier)
					.replace("%arrivalIdentifier", arrivalIdentifier)
					.replace("%departureName", departureName)
					.replace("%arrivalName", arrivalName)
					.replace("%formattedTime", formattedTime)
					.replace("%distance", formattedDistance);

				// Write the filled-out HTML.
				outputFile.write(listItem);

				// Accumulate totals.
				totalDistance += distance;
				totalTime += time;
			}

			// Show the first and last airport of the trip
			// to fill out the body of the last row so it
			// doesn't look empty.
			Leg first = legs.get(0);
			// - 1 because of zero-indexing.
			Leg last = legs.get(legs.size() - 1);

			// Pretty print numbers.
			String formattedTotalTime = PrettyPrint.prettyPrintTime(
				(int)totalTime * PrettyPrint.SECONDS_PER_MINUTE
			);
			String fomrattedTotalDistance = PrettyPrint.prettyPrintNumber(
				(int)totalDistance
			);

			// Replace template tags for the foot section.
			String foot = Resources.getHtmlFoot()
				.replace("%totalTime", formattedTotalTime)
				.replace("%start", first.getDepartureAirport().getName())
				.replace("%end", last.getArrivalAirport().getName())
				.replace("%totalDistance", fomrattedTotalDistance);

			// Write the filled-out foot.
			outputFile.write(foot);

			// Inform the user that the file was written.
			System.out.println("An itinerary.html has been saved in the working directory.");

			// Don't leak.
			outputFile.close();
		} catch (Exception e) {
			// Show an error if something bad happens.
			System.err.println("Failed to generate itinerary file: " + e);
		}
	}
}
