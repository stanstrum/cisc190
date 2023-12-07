package com.stanstrum.project03;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Dump {
  private static final double KNOTS_PER_HOUR = 300d;
	private static final int MINUTES_PER_HOUR = 60;

  private static double MI_PER_NMI = 1.15077945d;

	public static void dumpInfo(List<Leg> legs) {
		System.out.println("Leg\tFrom\tTo\tDist\tTime (mins)");

		double totalDistance = 0d;
		double totalTimeMins = 0d;
		for (int i = 0; i < legs.size(); i++) {
			Leg leg = legs.get(i);

			Airport departure = leg.getDepartureAirport();
			Airport arrival = leg.getArrivalAirport();
			double distance = leg.getDistance() * MI_PER_NMI;

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

	public static void dumpInfoHTML(List<Leg> legs) {
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
				double distance = leg.getDistance() * MI_PER_NMI;

				String formattedTime = PrettyPrint.prettyPrintTime((int)time * PrettyPrint.SECONDS_PER_MINUTE);
				String formattedDistance = PrettyPrint.prettyPrintNumber((int)distance);

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

			String formattedTotalTime = PrettyPrint.prettyPrintTime((int)totalTime * PrettyPrint.SECONDS_PER_MINUTE);
			String fomrattedTotalDistance = PrettyPrint.prettyPrintNumber((int)totalDistance);

			String foot = Resources.getHtmlFoot()
				.replace("%totalTime", formattedTotalTime)
				.replace("%start", first.getDepartureAirport().getName())
				.replace("%end", last.getArrivalAirport().getName())
				.replace("%totalDistance", fomrattedTotalDistance);

			outputFile.write(foot);

			outputFile.close();
		} catch (Exception e) {
			System.err.println("Failed to generate itinerary file: " + e);
		}
	}
}
