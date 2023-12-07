package com.stanstrum.project03;

import java.util.HashMap;
import java.util.Iterator;

import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

import org.json.JSONObject;

/**
 * A helper class that takes care of reading
 * resource files from src/main/resource.  This is
 * useful because it separates different types of
 * code used in this project; viz. Java, JSON, HTML.
 *
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Resources {
	/**
	 * A map of {@link Airport} instances hashed by their IATA code.
	 */
	private static HashMap<String, Airport> airports = null;

	/**
	 * The HTML header template.
	 */
	private static String htmlHead = null;

	/**
	 * The HTML list item template.
	 */
	private static String htmlListItem = null;

	/**
	 * The HTML footer template.
	 */
	private static String htmlFoot = null;

	/**
	 * The primary method for loading all dependent
	 * resources.  Ensure that this method is called
	 * before any resource-dependant operations are
	 * performed.
	 *
	 * @throws Exception if loading any resource fails.
	 */
	public static void load() throws Exception {
		// Dispatch the loaders separately.
		Resources.loadAirports();
		Resources.loadHtml();
	}

	/**
	 * Gets an {@link Airport} by IATA code from a local database
	 * stored in and loaded from resources.
	 *
	 * @param code The IATA code of the {@link Airport}.
	 *
	 * @return The {@link Airport} corresponding to the `code` parameter.
	 * May return `null` if no airport was found.
	 */
	public static Airport getAirport(String code) {
		return Resources.airports.get(code.toLowerCase());
	}

	/**
	 * @return {@link Resources#htmlHead}
	 */
	public static String getHtmlHead() {
		return htmlHead;
	}

	/**
	 * @return {@link Resources#htmlListItem}
	 */
	public static String getHtmlListItem() {
		return htmlListItem;
	}

	/**
	 * @return {@link Resources#htmlFoot}
	 */
	public static String getHtmlFoot() {
		return htmlFoot;
	}

	/**
	 * The method which loads airport data into {@link Resources#airports}.
	 *
	 * @throws Exception if loading the airport data fails.
	 */
	private static void loadAirports() throws Exception {
		// Load resources/airport.json into a JSONObject.
		String json_text = IOUtils.resourceToString("/airports.json", StandardCharsets.UTF_8);
		JSONObject json = new JSONObject(json_text);

		// Create the key iterator.
		Iterator keys = json.keys();

		// Instantiate the hashmap so that we can fill it with airports.
		Resources.airports = new HashMap<>();

		while (keys.hasNext()) {
			// Get the airport data.
			String key = (String)keys.next();
			JSONObject airportData = json.getJSONObject(key);

			// Get the data fields.
			String name = airportData.getString("name");
			double latitude = airportData.getDouble("latitude");
			double longitude = airportData.getDouble("longitude");

			// Create an Airport from the gathered data.
			Airport airport = new Airport(latitude, longitude);
			airport.setIdentifier(key);
			airport.setName(name);

			// Add the Airport to the map.
			Resources.airports.put(key.toLowerCase(), airport);
		}

		// Now we have a usable Airport map.
	}

	/**
	 * The method that loads the HTML source text into
	 * <ul>
	 *   <li>{@link Resources#htmlHead}</li>
	 *   <li> {@link Resources#htmlListItem}</li>
	 *   <li> {@link Resources#htmlFoot}</li>
	 * </u>
	 *
	 * @throws Exception if loading the HTML source text fails.
	 */
	private static void loadHtml() throws Exception {
		// Read the files to strings.
		String head = IOUtils.resourceToString("/html_head.html", StandardCharsets.UTF_8);
		String listItem = IOUtils.resourceToString("/html_list_item.html", StandardCharsets.UTF_8);
		String foot = IOUtils.resourceToString("/html_foot.html", StandardCharsets.UTF_8);

		// Save them statically.
		Resources.htmlHead = head;
		Resources.htmlListItem = listItem;
		Resources.htmlFoot = foot;
	}
}
