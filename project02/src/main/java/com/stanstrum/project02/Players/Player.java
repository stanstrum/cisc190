package com.stanstrum.project02.Players;

import com.stanstrum.project02.Game;

/**
 * The interface with which the {@link Game} class
 * uses to play the game.  Polymorphism here
 * makes it so the console player and the CPU player
 * are used the same way in the game logic.
 */
public abstract class Player {
  /**
   * The amount of points we have.	Each Player
   * keeps track of its own points separate from
   * one another.
   */
  protected int points = 0;

  /**
   * Whether the {@link Player} is still playing.
   * Once all of the players are no longer playing,
   * the {@link Game} class will decide the winner.
   */
  protected boolean isPlaying = true;

  /**
   * The name of this {@link Player}.
   */
  protected String name;

  /**
   * @return {@link Player#points}
   */
  public final int getPoints() {
	return this.points;
  }

  /**
   * @return {@link Player#name}
   */
  public final String getName() {
	return this.name;
  }

  /**
   * @return {@link Player#isPlaying}
   */
  public final boolean getIsPlaying() {
	return this.isPlaying;
  }

  /**
   * @param isPlaying The value to set to {@link Player#isPlaying}.
   */
  public final void setIsPlaying(boolean isPlaying) {
	this.isPlaying = isPlaying;
  }

  /**
   * The interface with which the game informs the player
   * the amount of points gained from a dice roll.
   */
  public final void addPoints(int amount) {
	this.points += amount;
  }

  /**
   * The player's implementation for playing the game.
   * @note This method should set its {@link Player#isPlaying}
   * accordingly after running its internal logic.
   */
  public abstract void play();

  /**
   * What to do once this {@link Player} wins the game.
   * For a console player, we should print an appropriate
   * message.  For a CPU player, this would be a no-op
   * as computers do not need to be congratulated for
   * their work.
   */
  public abstract void informWon();

  /**
   * What to do once this {@link Player} loses the game.
   * For a console player, we should print an appropriate
   * message.  For a CPU player, this would also be a no-op.
   *
   * @param winner The winning {@link Player} for displaying
   * the winner's stats.
   */
  public abstract void informLost(Player winner);

  /**
   * What to do once this {@link Player} ties.
   */
  public abstract void informTied();

  /**
   * Instantiates a {@link Player}.
   *
   * @param name Name of this player.
   */
  public Player(String name) {
	this.name = name;
  }
}
