package com.stanstrum.project02;

import java.util.ArrayList;

import com.stanstrum.project02.Players.Player;

/**
 * The object that contains the game logic and
 * informs players when they have won, lost, or tied.
 */
public final class Game {
	/**
	 * The amount of die that this game.
	 * This is used to calculate each round's dice roll.
	 */
	public static final int DIE = 2;

	/**
	 * The amount of sides each dice has.
	 * This is also used to calculate each round's dice roll.
	 */
	public static final int SIDES_PER_DICE = 6;

	/**
	 * The highest point score a player may attain without busting.
	 */
	public static final int HIGHEST_SCORE = 21;

	/**
	 * Whether or not the game is running.
	 */
	private boolean running = false;

	/**
	 * The players currently participating in this game.
	 * This field is immutable because each player's score
	 * needs to be accessed at the end of the game to determine
	 * a winner.
	 */
	private final Player[] players;

	/**
	 * The die that the game uses for calculating scores.
	 */
	private final Die die = new Die(Game.DIE, Game.SIDES_PER_DICE);

	/**
	 * The main method to play the game.
	 * This sets {@link Game#running} to true, and continously
	 * checks it to see if {@link Game#play_round()} ends the game.
	 */
	public void play() {
		this.running = true;

		while (this.running) {
			this.play_round();
		}

		this.informPlayers();
	}

	/**
	 * The core game logic.  This sets {@link Game#running}
	 * to `false` when the game is over
	 */
	public void play_round() {
		boolean anybody_played = false;

		for (Player player : this.players) {
			// Don't play anyone who has stopped playing.
			if (!player.getIsPlaying()) {
				continue;
			}

			// This will run on the first round of every
			// game no matter what.  This is okay because
			// nobody can bust after one roll.
			this.die.roll();
			player.addPoints(this.die.getValue());

			if (player.getPoints() > Game.HIGHEST_SCORE) {
				// Tell them to stop playing.
				player.setIsPlaying(false);

				// Don't ask them to play again.
				continue;
			}

			// Now the player decides if they want to continue.
			player.play();

			// Indicate that this round had a participant.
			anybody_played = true;
		}

		// Only call the winner once _all_ players are finished.
		if (anybody_played) {
			return;
		}

		// End the game
		this.running = false;
	}

	/**
	 * The logic for the end of the game.  We determine
	 * a winner based on highest points (if there is one),
	 * and inform the players accordingly.
	 */
	private void informPlayers() {
		// If there were no players, don't bother
		// informing anyone of win or loss.
		if (this.players.length == 0) {
			return;
		}

		// The index to the winning player.  We are
		// not guaranteed to find one because they all
		// may bust.
		int winnerIdx = -1;

		// Loop through the player indices.
		for (int i = 0; i < this.players.length; i++) {
			// We do this to avoid unnecessary copies of objects.
			int currentWinnerPoints = 0;

			// winnerIdx can be invalid.
			if (winnerIdx != -1) {
				currentWinnerPoints = this.players[winnerIdx].getPoints();
			}

			int currentPlayerPoints = this.players[i].getPoints();

			// Ignore players whom have busted.
			if (currentPlayerPoints > Game.HIGHEST_SCORE) {
				continue;
			}

			// Update `winnerIdx` if this player has a higher, valid score.
			if (currentPlayerPoints > currentWinnerPoints) {
				winnerIdx = i;
			}
		}

		ArrayList<Integer> tiedIndices = new ArrayList<Integer>();

		// Don't worry about tied players if there wasn't a winner.
		if (winnerIdx != -1) {
			int winnerPoints = this.players[winnerIdx].getPoints();

			for (int i = 0; i < this.players.length; i++) {
				// Ignore the previously-found winner; we are
				// looking for someone else with this score.
				if (i == winnerIdx) {
					continue;
				}

				int currentPlayerPoints = this.players[i].getPoints();

				// Test if this player is tied with the winner.
				if (winnerPoints == currentPlayerPoints) {
					// Add it to the array.
					tiedIndices.add(i);
				}
			}

			// If we found players who tied, add the winner as well
			// since they have that same score.
			if (tiedIndices.size() != 0) {
				tiedIndices.add(winnerIdx);
			}
		}

		// Store the winner to inform everybody of,
		// if there is one.
		Player winner = null;

		if (winnerIdx != -1) {
			winner = this.players[winnerIdx];
		}

		// Now inform the winners and losers accordingly.
		for (int i = 0; i < this.players.length; i++) {
			Player player = this.players[i];

			// Check if this player tied.
			if (tiedIndices.contains(i)) {
				player.informTied();

				// This player tied, so skip the rest.
				continue;
			}

			// Check if this player is the winner.
			// If there is no winner, this will always be false
			// since we iterate starting from zero.  `winnerIdx`
			// is `-1` if there isn't one.
			if (i == winnerIdx) {
				player.informWon();

				// This player won, so skip the rest.
				continue;
			}

			// This player neither won nor tied, so they lose.
			player.informLost(winner);
		}
	}

	/**
	 * Instatiate a {@link Game}.
	 *
	 * @param players The {@link Player} instances that will participate.
	 */
	public Game(Player... players) {
		this.players = players;
	}
}
