/* Author: Stan Strum (555-010-9379)
 * Date: 31 Oct 2023
 * Description: Lab 3: Simple Roman Numeral Converter
 */
package com.stanstrum.lab03;

import java.util.Scanner;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab03 {
	/**
	 * The set of Roman numerals to provide conversions for.
	 */
	private static final String[] ROMAN_NUMERALS = {
		"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
	};

	public static void main(String[] args) {
		// Create stdin scanner for reading the number to convert
		Scanner scanner = new Scanner(System.in);

		// Do not print a newline after this message; typically
		// reading a value requires that the user presses the enter
		// key.  Note that this will not display properly in CLI output,
		// however that usage is unsupported here.
		System.out.print("Enter a number within the range of 1 through 10: ");
		int number = scanner.nextInt();

		// Close scanner -- we are done with it.
		scanner.close();

		// We are storing the representations of the numbers 1-10
		// in Roman numerals in the static array.  This allows us
		// to index by number to get the representation.  Consequently,
		// we need to adjust the user input, where I is 1, to match
		// the zero-indexed array, where I is 0.
		int numeral_index = number - 1;

		// Check if `numeral_index` is out of bounds of `ROMAN_NUMERALS`
		if (numeral_index < 0 || numeral_index >= ROMAN_NUMERALS.length) {
			System.out.println("That is an invalid number");

			// Exit due to invalid number
			return;
		}

		// Get the representation from the list
		String numeral = ROMAN_NUMERALS[numeral_index];

		System.out.println(numeral);
	}
}
