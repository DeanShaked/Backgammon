package Backgammon;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for all the dice rules
 * 
 * @param dice1 a number reflects to dice1
 * @param dice2 a number reflects to dice2
 * @param dice#Available boolean parameter indicating if the dice is available to be played
 * @param dice#Playable boolean parameter indicating if the dice is possible to be played
 * 
 * 
 */

public class Dice {
	static int dice1=0;
	static int dice2=0;
	static boolean dice1Available;
	static boolean dice2Available;
	static boolean dice3Available;
	static boolean dice4Available;
	static boolean dice1Playable;
	static boolean dice2Playable;
	static boolean dice3Playable;
	static boolean dice4Playable;
	boolean double_dice=false;

	
	public static boolean isDice3Available() {
		return dice3Available;
	}

	public static void setDice3Available(boolean dice3Available) {
		Dice.dice3Available = dice3Available;
	}

	public static boolean isDice4Available() {
		return dice4Available;
	}

	public static void setDice4Available(boolean dice4Available) {
		Dice.dice4Available = dice4Available;
	}

	public static boolean isDice1Available() {
		return dice1Available;
	}

	public static void setDice1Available(boolean dice1Available) {
		Dice.dice1Available = dice1Available;
	}

	public static boolean isDice2Available() {
		return dice2Available;
	}

	public static void setDice2Available(boolean dice2Available) {
		Dice.dice2Available = dice2Available;
	}

	public static int getDice1() {
		return dice1;
	}

	public static int getDice2() {
		return dice2;
	}
	
	public boolean isDouble_dice() {
		return double_dice;
	}

	public void setDouble_dice(boolean double_dice) {
		this.double_dice = double_dice;
	}

	public void randomDice() {
		dice1 = ThreadLocalRandom.current().nextInt(1, 7); // set a random number for dice 1
		dice2 = ThreadLocalRandom.current().nextInt(1, 7); // set a random number for dice 2
		if (dice1 == dice2) {
			Rules.setTurnCount(4); // if the dice is double, the turn count is set to 4
		} else {
			Rules.setTurnCount(2); // if the dice is not double, the turn count is set to 2
		}
	}
	
	public static void resetDice() {
		Dice.setDice1Playable(false);
		Dice.setDice2Playable(false);
		Dice.setDice3Playable(false);
		Dice.setDice4Playable(false);
	}
	
	public static void resetDiceAvailability() {
		Dice.setDice1Available(true);
		Dice.setDice2Available(true);
		Dice.setDice3Available(true);
		Dice.setDice4Available(true);
	}
	
	public static boolean isDice1Playable() {
		return dice1Playable;
	}

	public static void setDice1Playable(boolean dice1Playable) {
		Dice.dice1Playable = dice1Playable;
	}

	public static boolean isDice2Playable() {
		return dice2Playable;
	}

	public static void setDice2Playable(boolean dice2Playable) {
		Dice.dice2Playable = dice2Playable;
	}

	public static boolean isDice3Playable() {
		return dice3Playable;
	}

	public static void setDice3Playable(boolean dice3Playable) {
		Dice.dice3Playable = dice3Playable;
	}

	public static boolean isDice4Playable() {
		return dice4Playable;
	}

	public static void setDice4Playable(boolean dice4Playable) {
		Dice.dice4Playable = dice4Playable;
	}

	public static void setDice1(int dice1) {
		Dice.dice1 = dice1;
	}

	public static void setDice2(int dice2) {
		Dice.dice2 = dice2;
	}
	
}
