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
    String json_text = IOUtils.resourceToString("/airports.json", StandardCharsets.UTF_8);
    JSONObject json = new JSONObject(json_text);

    Iterator keys = json.keys();

    Resources.airports = new HashMap<>();

    while (keys.hasNext()) {
      String key = (String)keys.next();

      JSONObject airportData = json.getJSONObject(key);

      double latitude = airportData.getDouble("latitude");
      double longitude = airportData.getDouble("longitude");

      Airport airport = new Airport(latitude, longitude);
      airport.setIdentifier(key);

      Resources.airports.put(key.toLowerCase(), airport);
    }
  }
}

