/* Author: Stan Strum
 * Date: 6 Nov 2023
 * Description: Proper Case Formatter
 */

package com.stanstrum.lab08;

import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab08 {
	/**
	 * A helper function that capitalizes the first
	 * character in a word and makes the rest lowercase.
	 *
	 * @param part The word to make proper-cased.
	 * @return The word, proper-cased.
	 */
	private static String properCasePart(String part) {
		String out = "";

		// Loop through part's indices.
		for (int i = 0; i < part.length(); i++) {
			// If it's the first character, capitalize
			// Else, make it lowercase.
			if (i == 0) {
				out += Character.toUpperCase(part.charAt(i));
			} else {
				out += Character.toLowerCase(part.charAt(i));
			}
		}

		return out;
	}

	/**
	 * A function that takes in a {@link java.lang.String}
	 * and capitalizes the first letter of each word delimited
	 * by spaces.
	 *
	 * @param source The string to format into proper case.
	 * @return The same string, properly-cased.
	 */
	public static String properCase(String source) {
		// Create stream from source split by space --
		// each part is a word.  This can lead to
		// empty parts if there's a double space
		// but this is no issue.
		return Stream.of(source.split(" "))
			// Map each word with properCasePart,
			// which capitalizes the first character
			// and lowercases the rest.
			.map(Lab08::properCasePart)
			// Collect the mapped parts and join them
			// by the same word delimeter.
			.collect(Collectors.joining(" "));
	}
}
