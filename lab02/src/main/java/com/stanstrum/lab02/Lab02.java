/* Author: Stan Strum (555-010-9379)
 * Date: 31 Oct 2023
 * Description: Lab 2: "Mileage Reimbursement Calculator" Submission
 * --
 * I prefer to use snake_case for my variable
 * names.  Inform me via a comment if you would
 * rather I use camelCase in my submission.
 */

package com.stanstrum.lab02;

import java.util.Scanner;

/**
 * @author Stan Strum {@literal <stanleystrum@gmail.com>}
 */
public class Lab02 {
    public static final float CENTS_PER_MILE = 0.35f;

    public static void main(String[] args) {
        // Create stdin scanner
        Scanner scanner = new Scanner(System.in);

        // Display program identifier.
        System.out.println("MILEAGE REIMBURSEMENT CALCULATOR");

        // Display the query text.  We do not use `println`
        // because we want the cursor to remain on the line
        // that this text appears on.
        System.out.print("Enter beginning odometer reading > ");

        /**
         * @note Reading a fraction as a float is not ideal.
         * However, in this situation we do not need to worry
         * about a lack of precision since `nextFloat` reads the
         * exact digits into the mantissa (and sets the exponent
         * accordingly).  Afterwards, we use these values for
         * floating-point operations and some inaccuracy is to be
         * expected at that stage anyway.
         *
         * @see java.util.Scanner#nextFloat Refer to `nextFloat`
         * implementation.
         */
        float start_odo = scanner.nextFloat();

        // Same song & dance
        System.out.print("Enter ending odometer reading > ");
        float end_odo = scanner.nextFloat();

        float distance_traveled = end_odo - start_odo;
        float reimbursement = distance_traveled * CENTS_PER_MILE;

        // While we could use string concatenation or a StringBuilder,
        // I chose `printf` as it is easier to understand precisely
        // what this statement will write to the console.  Add'ly,
        // it allows for specifying the precision with which the
        // floats will be formatted with.
        System.out.printf(
            "You traveled %.1f miles. At $%.2f per mile, your reimbursement is $%.2f.\n",
            distance_traveled,
            CENTS_PER_MILE,
            reimbursement
        );

        // Close scanner
        scanner.close();
    }
}
