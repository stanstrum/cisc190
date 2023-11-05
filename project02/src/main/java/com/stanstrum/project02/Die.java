package com.stanstrum.project02;

import java.util.Arrays;

/**
 * An object that holds {@link Die.Dice} instances.
 * Through this interface, you can roll any amount of
 * die and sum them accordingly.
 *
 * @note This could be done mathematically, however
 * the specifications of the assignment require the
 * usage of a Dice class.
 */
public class Die {
  /**
   * Our die that we use to calculate a roll.
   */
  private final Dice[] die;

  private static final class Dice {
	/**
	 * The amount of sides on this dice.
	 * This is used to compute the value of
	 * a dice roll.
	 */
	private final int sides;

	/**
	 * The current value of the dice roll.
	 *
	 * @note This value will only be zero if
	 * the dice has never been rolled.
	 */
	private int value = 0;

	/**
	 * @return {@link Dice#value}
	 */
	public int getValue() {
	  return this.value;
	}

	/**
	 * Roll the dice.  This method will set {@link Dice#value}
	 * to a value between 1 and {@link Dice#sides}, inclusive.
	 *
	 * @note This does not clear {@link Dice#value} first because
	 * it overwrites it anyway.
	 */
	public void roll() {
	  this.value = (int)(Math.random() * (double)this.sides) + 1;
	}

	public Dice(int sides) {
	  this.sides = sides;
	}
  }

  /**
   * @return The sum of the values of all of the die
   * owned by this object.
   *
   * @note This will return zero if the die have never
   * been rolled.
   */
  public int getValue() {
	int value = 0;

	for (Dice dice : this.die) {
	  value += dice.getValue();
	}

	return value;
  }

  /**
   * Roll the die owned by this object.
   *
   * @note This method does not clear the {@link Dice#value}
   * of our die because they are overwritten anyway.
   */
  public void roll() {
	for (Dice dice : this.die) {
	  dice.roll();
	}
  }

  /**
   * Instantiates a set of {@link Dice}.
   *
   * @param amount Amount of die to use.
   * @param sides Sides to each dice.
   *
   * @note We do not check for correct values.
   * It is assumed the invoker knows what they're
   * doing.
   */
  public Die(int amount, int sides) {
	this.die = new Dice[amount];

	Arrays.fill(this.die, new Dice(sides));
  }
}
