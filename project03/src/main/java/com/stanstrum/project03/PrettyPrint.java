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
	public static String reverse_string(String str) {
		final int last_idx = str.length() - 1;
		final int halfway = str.length() / 2;

		char[] char_arr = str.toCharArray();

		for (int i = 0; i < halfway; i++) {
			int src_idx = i;
			int dest_idx = last_idx - i;

			char tmp = char_arr[src_idx];
			char_arr[src_idx] = char_arr[dest_idx];
			char_arr[dest_idx] = tmp;
		}

		return new String(char_arr);
	}

	/**
	 * Pretty print a large number with commas; e.g.:
	 * 1000000 {@literal ->} 1,000,000.
	 *
	 * @param num The number to format prettily as a String.
	 * @return The prettified number as a String.
	 */
	public static String pretty_print_number(int num) {
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
		String backwards_string = backwards.toString();

		// Now reverse the characters.
		return reverse_string(backwards_string);
	}

	public static final int SECONDS_PER_MINUTE = 60;
	public static final int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
	public static final int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;

	public static String prettyPrintTime(int seconds) {
		// Get rounded-down amount of days by dividing by seconds.
		int days = seconds / SECONDS_PER_DAY;

		// Remove the amount of time we just printed.
		seconds %= SECONDS_PER_DAY;

		// Rinse & repeat
		int hours = seconds / SECONDS_PER_HOUR;
		seconds %= SECONDS_PER_HOUR;

		int minutes = seconds / SECONDS_PER_MINUTE;
		seconds %= SECONDS_PER_MINUTE;

		// Marker for if there is any preceding text needing a comma separator.
		boolean did_print_any_time = false;

		if (days != 0) {
			// "day" if only 1, "days" otherwise.
			String pluralized = PrettyPrint.pluralize("day", days);

			// Prints "5 days", for example.
			System.out.printf("%d %s", days, pluralized);

			// Indicate that something has been printed.
			did_print_any_time = true;
		}

		// Repeat for hours.
		if (hours != 0) {
			if (did_print_any_time) {
				System.out.print(", ");
			}

			String pluralized = PrettyPrint.pluralize("hour", hours);

			System.out.printf("%d %s", hours, pluralized);

			did_print_any_time = true;
		}

		// Again for minutes.
		if (minutes != 0) {
			if (did_print_any_time) {
				System.out.print(", ");
			}

			String pluralized = PrettyPrint.pluralize("minute", minutes);

			System.out.printf("%d %s", minutes, pluralized);

			did_print_any_time = true;
		}

		// Seconds are handled differently.  They will show up if
		// they are non-zero OR if no other units were printed.
		// However, whether to print the leading comma delimiter
		// depends on if anything has been printed yet.
		if (did_print_any_time && seconds > 0) {
			System.out.print(", ");
		}

		// Seconds shows up if no time was printed or if the seconds are nonzero.
		if (!did_print_any_time || seconds > 0) {
			String pluralized = PrettyPrint.pluralize("second", seconds);
			System.out.printf("%d %s", seconds, pluralized);
		}

		// Ending period and newline.
		System.out.println(".");
	}
}
