package com.stanstrum.project02.Players;

import com.stanstrum.project02.Game;

/**
 * An implementation of {@link Player} that decides
 * whether to continue playing depending on
 * whether doing so will likely cause it to lose.
 */
public final class CPUPlayer extends Player {
	/**
	 * The point at which the CPU player will stop
	 * playing.  This is precomputed at initialization
	 * so it doesn't need to be calculated every round.
	 *
	 * This value is the point at which -- on average --
	 * we would bust.
	 */
	private static final int BACK_OFF_POINT = Game.HIGHEST_SCORE - (Game.DIE * Game.SIDES_PER_DICE + 1) / 2;

	public void play() {
		if (this.points >= CPUPlayer.BACK_OFF_POINT) {
			this.isPlaying = false;
		}
	}

	public void informWon() {
		// No need to do anything here since ConsolePlayer's
		// `informLose` shows the winner's score.
	}

	public void informLost(Player winner) {
		// The assignment details specify:
		// "After the loop has finished, the computer's total is revealed."

		// Check if CPU busted.
		if (this.points > Game.HIGHEST_SCORE) {
			System.out.printf("CPU busted with %d points.\n", this.points);
		} else {
			System.out.printf("CPU lost with %d points.\n", this.points);
		}
	}

	public void informTied() {
		// Don't print info since the ConsolePlayer can
		// infer our score as they had the same one.
	}

	public CPUPlayer() {
		super("CPU");
	}
}
