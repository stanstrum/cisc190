/* Author: Stan Strum (555-010-9379)
 * Date: 1 Nov 2023
 * Description: Project 2: A Game of Twenty One
 */

package com.stanstrum.project02;

import java.util.Scanner;
import com.stanstrum.project02.Players.*;

/**
 * @author Stan Strum <stanleystrum@gmail.com>
 */
public class Project02 {
	public static void main(String[] args) {
		// Get an stdin scanner for the ConsolePlayer.
		Scanner scanner = new Scanner(System.in);

		// Instantiate the players.
		Player cpuPlayer = new CPUPlayer();
		Player consolePlayer = new ConsolePlayer(scanner);

		// Create the game.
		Game game = new Game(cpuPlayer, consolePlayer);

		// Play.
		game.play();
	}
}
