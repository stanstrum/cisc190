package com.stanstrum.project03;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class PrettyPrint {
	/**
	 * Reverse a string using a basic swap algorithm.
	 *
	 * @param str The string to reverse.
	 * @return The string, reversed.
	 */
	public static String reverseString(String str) {
		final int lastIdx = str.length() - 1;
		final int halfway = str.length() / 2;

		char[] charArr = str.toCharArray();

		for (int i = 0; i < halfway; i++) {
			int srcIdx = i;
			int destIdx = lastIdx - i;

			char tmp = charArr[srcIdx];
			charArr[srcIdx] = charArr[destIdx];
			charArr[destIdx] = tmp;
		}

		return new String(charArr);
	}

	/**
	 * Pretty print a large number with commas; e.g.:
	 * 1000000 {@literal ->} 1,000,000.
	 *
	 * @param num The number to format prettily as a String.
	 * @return The prettified number as a String.
	 */
	public static String prettyPrintNumber(int num) {
		if (num == 0) {
			return "0";
		}

		StringBuilder backwards = new StringBuilder();

		boolean isNegative = num < 0;

		if (isNegative) {
			num *= -1;
		}

		for (int power = 0; num > 0; power++) {
			// Only add commas after every third digit, not
			// including the first.
			if (power != 0 && power % 3 == 0) {
				backwards.append(',');
			}

			// Get the last digit of num.
			int digit = num % 10;

			// Append it to end of builder.
			backwards.append(digit);

			// Pop last digit by dividing num by 10.
			num /= 10;
		}

		// Since we are constructing this string from back to front,
		// we add the minus sign at the end.
		if (isNegative) {
			backwards.append('-');
		}

		// Collect the StringBuilder into a String.
		String backwardsString = backwards.toString();

		// Now reverse the characters.
		return reverseString(backwardsString);
	}

	// Self-explanatory.
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
	public static final int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;

	/**
	 * Pretty prints an amount of time into a compact,
	 * human-readable form.
	 *
	 * @param seconds The amount of time in seconds.
	 *
	 * @return The time in a human-readable format.
	 */
	public static String prettyPrintTime(int seconds) {
		StringBuilder builder = new StringBuilder();

		// Get rounded-down amount of days by dividing by seconds.
		int days = seconds / SECONDS_PER_DAY;

		// Remove the amount of time we just printed.
		seconds %= SECONDS_PER_DAY;

		// Rinse & repeat
		int hours = seconds / SECONDS_PER_HOUR;
		seconds %= SECONDS_PER_HOUR;

		int minutes = seconds / SECONDS_PER_MINUTE;
		seconds %= SECONDS_PER_MINUTE;

		if (days != 0) {
			// Prints "5d", for example.
			builder.append(days);
			builder.append("d");
		}

		// Repeat for hours.
		if (hours != 0) {
			builder.append(hours);
			builder.append("h");
		}

		// Again for minutes.
		if (minutes != 0) {
			builder.append(minutes);
			builder.append("m");
		}

		// Seconds shows up if no time was printed or if the seconds are nonzero.
		if (seconds > 0) {
			builder.append(seconds);
			builder.append("s");
		}

		// Collect the builder.
		return builder.toString();
	}
}
