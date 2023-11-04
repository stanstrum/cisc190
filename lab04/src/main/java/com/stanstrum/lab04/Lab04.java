/* Author: Stan Strum (555-010-9379)
 * Date: 1 Nov 2023
 * Description: Lab 4: Create File and Write Primes
 */

package com.stanstrum.lab04;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab04 {
    /**
     * Determines the primeness of a number.
     *
     * @param num The number to be checked.
     * @return The primeness of the number.
     */
    private static boolean is_prime(int num) {
        // You only need to search up to num / 2 because
        // any higher than that would have a divisor less than 2 --
        // a.k.a. the smallest prime number.
        int half_way = num / 2;

        for (int i = 2; i <= half_way; i++) {
            // If num wholly divides into i parts...
            if (num % i == 0) {
                return false;
            }
        }

        // If no numbers from 0..n/2 evenly divide, return true for primeness
        return true;
    }

    /**
     * Hard-coded output variable defined as final to avoid
     * using "magic" values.
     */
    private static final String FILENAME = "output.txt";

    /**
     * Ditto for the max prime count.
     */
    private static final int MAX_PRIME = 1000;

    public static void main(String[] args) {
        FileWriter fw = null;

        try {
            // Create FileWriter.  This invocation method will create
            // the file if it does not already exist, and overwrite an
            // existing file once the first write comes in.
            fw = new FileWriter("output.txt");
        } catch (IOException e) {
            System.out.printf("Failed to open %s: %s\n", FILENAME, e);

            return;
        }

        // At this point, fw cannot be null.
        PrintWriter pw = new PrintWriter(fw, true);

        // Count i from 0 to MAX_PRIME
        for (int i = 1; i < MAX_PRIME; i++) {
            if (is_prime(i)) {
                // Will print the number followed by a newline
                pw.format("%d\n", i);
            }
        }

        // Close & flush PrintWriter.  This ensures that all
        // of the primes are written to the output file.
        pw.close();

        try {
            // Close file as the destructor is not automatically called
            fw.close();
        } catch (IOException e) {
            System.out.printf("Failed to close %s: %s\n", FILENAME, e);
        }
    }
}
