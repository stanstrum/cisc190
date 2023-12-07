package com.stanstrum.project03;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.util.Iterator;

public class Resources {
  private static HashMap<String, Airport> airports = null;

  public static void load() throws Exception {
    Resources.loadAirports();
  }

  public static Airport getAirport(String identifier) {
    return Resources.airports.get(identifier.toLowerCase());
  }

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

