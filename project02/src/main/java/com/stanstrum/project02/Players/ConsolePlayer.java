package com.stanstrum.project02.Players;

import java.util.Scanner;
import com.stanstrum.project02.Game;

/**
 * An implementation of {@link Player} that decides
 * whether to continue playing based on user input.
 * This also informs the console player by outputting
 * text to the console.
 */
public final class ConsolePlayer extends Player {
  /**
   * The scanner to use for reading input from the user.
   */
  private final Scanner scanner;

  public void play() {
	System.out.printf("You have %d points.\n", this.points);

	// Whether or not we got a choice from the user yet.
	boolean gotChoice = false;

	// Repeat as long as we haven't gotten a valid response.
	// Semantically, do...while is better here but a normal
	// while loop works fine and is more legible.
	while (!gotChoice) {
	  System.out.print("Would you like to roll again? [Y/n] ");

	  // Get a choice from the user.
	  String choice = this.scanner.nextLine();

	  switch (choice.toLowerCase()) {
		case "yes":
		case "y":
		// If empty, choose to continue by default.
		case "":
		  // No need to change `this.isPlaying` as it is set to
		  // `true` by default.	 Only when we are done should we
		  // change this value.

		  // Indicate that we got a choice from the user.
		  gotChoice = true;

		  break;

		case "no":
		case "n":
		  this.isPlaying = false;

		  // Indicate that we got a choice from the user.
		  gotChoice = true;

		  break;

		default:
		  System.out.println("Invalid choice.");

		  // Invalid choice, so don't set `gotChoice`.
	  }
	}
  }

  public void informWon() {
	System.out.printf("You win with %d points!\n", this.points);
  }

  public void informLost(Player winner) {
	if (this.points > Game.HIGHEST_SCORE) {
	  // We busted.

	  System.out.printf("You busted with %d points.	 ", this.points);
	} else {
	  // We lost by not having enough points.

	  System.out.printf("You lose.	");
	}

	// Winner may be null if everyone busts.
	if (winner == null) {
	  System.out.println("Nobody won.");
	} else {
	  System.out.printf("%s won with %d points.\n", winner.getName(), winner.getPoints());
	}
  }

  public void informTied() {
	System.out.printf("You tied with %d points.\n", this.points);
  }

  public ConsolePlayer(Scanner scanner) {
	super("Console Player");

	this.scanner = scanner;
  }
}
