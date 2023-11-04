/* Author: Stan Strum (555-010-9379)
 * Date: 1 Nov 2023
 * Description: Project 1: Seconds to Human-Readaable
 */

package com.stanstrum.project01;

import java.util.Scanner;

/**
 * @author Stan Strum <stanleystrum@gmail.com>
 */
public class Project01 {
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
    public static final int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;

    public static void main(String[] args) {
        // Create scanner for reading from stdin
        Scanner scanner = new Scanner(System.in);

        // Present prompt, then read int from console
        System.out.print("Enter a number of seconds: ");
        int seconds = scanner.nextInt();

        System.out.print("You entered ");
        System.out.print(PrettyPrint.pretty_print_number(seconds));
        System.out.print(" seconds, which is ");

        // Get rounded-down amount of days by dividing by
        int days = seconds / SECONDS_PER_DAY;

        // Remove the amount of time we just printed
        seconds %= SECONDS_PER_DAY;

        // Rinse & repeat
        int hours = seconds / SECONDS_PER_HOUR;
        seconds %= SECONDS_PER_HOUR;

        int minutes = seconds / SECONDS_PER_MINUTE;
        seconds %= SECONDS_PER_MINUTE;

        // Marker for if there is any preceding text needing a comma separator.
        boolean did_print_any_time = false;

        if (days != 0) {
            // "day" if only 1, "days" otherwise"
            String pluralized = PrettyPrint.pluralize("day", days);

            // Prints "5 days", for example.
            System.out.printf("%d %s", days, pluralized);

            // Indicate that something has been printed
            did_print_any_time = true;
        }

        // Repeat for hours
        if (hours != 0) {
            if (did_print_any_time) {
                System.out.print(", ");
            }

            String pluralized = PrettyPrint.pluralize("hour", hours);

            System.out.printf("%d %s", hours, pluralized);

            did_print_any_time = true;
        }

        // Again for minutes
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

        // Ending period and newline
        System.out.println(".");
    }
}
