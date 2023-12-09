package com.stanstrum.project03.Dump;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.stanstrum.project03.Airport;
import com.stanstrum.project03.Leg;
import com.stanstrum.project03.PrettyPrint;
import com.stanstrum.project03.Resources;
import com.stanstrum.project03.Utils.Constants;

public class HTMLDumper {
  public static void dump(List<Leg> legs) {
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

				double time = leg.getDistance() / Constants.KNOTS_PER_HOUR * (double)Constants.MINUTES_PER_HOUR;
				double distance = leg.getDistance() * Constants.MI_PER_NMI;

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
