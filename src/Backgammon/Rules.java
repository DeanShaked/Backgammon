package Backgammon;

import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * This class checks all the rules of the game
 * @param BoardState a "slot[]" type stack containing all board slots
 * @param GameState a "boolean" type stack containing all boolean variables
 * @param GameData a "int" type stack containing all int variables
 * @param ongoing signifies an ongoing match
 * @param running_turn signifies an ongoing turn
 * @param whiteTurn says if the turn is white or black
 * @param turnCount indicates how many moves the player still has
 * @param canload indicates if the game can be loaded
 * @param blackOut indicates if the black player is in "out" state
 * @param whiteOut indicates if the white player is in "out" state
 * @param min_pos/max_pos an assist parameters to out state

 */

public class Rules {

	static Stack<Slot[]> BoardState = new Stack<>(); // stack to keep game variables
	static Stack<Boolean> GameState = new Stack<>(); // stack to keep game variables
	static Stack<Integer> GameData = new Stack<>(); // stack to keep game variables
	static boolean ongoing = false; // game state (ongoing or not)
	static boolean running_turn = false;
	static boolean whiteTurn = true; // who's turn is it
	static int turnCount = 0; // how many turns does the plays has
	static boolean canload = false;
	static boolean blackOut = false; // is the black player in exit state
	static boolean whiteOut = false; // is the black player in exit state
	static int min_pos = 0;
	static int max_pos = 23;

	public static int getMin_pos() {
		return min_pos;
	}

	public static void setMin_pos(int num) {
		min_pos = num;
	}

	public static int getMax_pos() {
		return max_pos;
	}

	public static void setMax_pos(int num) {
		max_pos = num;
	}

	public static boolean isCanload() {
		return canload;
	}

	public static void setCanload(boolean canload) {
		Rules.canload = canload;
	}
	
	   /**
	    * saves all the game valuable information into the stacks
	    */

	public static void saveGame() { // save all crucial information in stacks for undo.
		BoardState.push(Board.getSlots());
		GameData.push(getTurnCount());
		GameData.push(Dice.getDice1());
		GameData.push(Dice.getDice2());
		GameState.push(isWhiteTurn());
		GameState.push(isOngoing());
		GameState.push(Dice.dice1Available);
		GameState.push(Dice.dice2Available);
		GameState.push(Dice.dice3Available);
		GameState.push(Dice.dice4Available);
		GameState.push(Dice.dice1Playable);
		GameState.push(Dice.dice2Playable);
		GameState.push(Dice.dice3Playable);
		GameState.push(Dice.dice4Playable);
		setCanload(true);
	}
	
	   /**
	    * loads all the game valuable information from the stacks
	    */

	public static void loadGame() { // save all crucial information in stacks for load.
		Board.setSlots(BoardState.pop());
		Dice.setDice1(GameData.pop());
		Dice.setDice2(GameData.pop());
		Rules.setTurnCount(GameData.pop());
		Dice.setDice4Playable(GameState.pop());
		Dice.setDice3Playable(GameState.pop());
		Dice.setDice2Playable(GameState.pop());
		Dice.setDice1Playable(GameState.pop());
		Dice.setDice4Available(GameState.pop());
		Dice.setDice3Available(GameState.pop());
		Dice.setDice2Available(GameState.pop());
		Dice.setDice1Available(GameState.pop());
		setOngoing(GameState.pop());
		setWhiteTurn(GameState.pop());
		if (BoardState.empty())
			setCanload(false);
	}
	
	   /**
	    * empty the stacks
	    */

	public static void emptyStacks() { // save all crucial information in stacks for undo.
		BoardState.clear();
		GameData.clear();
		GameState.clear();
		setCanload(false);
	}


	public static boolean isRunning_turn() {
		return running_turn;
	}

	public static void setRunning_turn(boolean running_turn) {
		Rules.running_turn = running_turn;
	}

	public static Slot[] getSlots() {
		return Board.slots_arr;
	}

	public static void newGame() { // set starting board state
		Board.newGame();
	}

	public static boolean isOngoing() {
		return ongoing;
	}

	public static void setOngoing(boolean ongoing) {
		Rules.ongoing = ongoing;
	}

	public static boolean isWhiteTurn() {
		return whiteTurn;
	}

	public static void setWhiteTurn(boolean whiteTurn) {
		Rules.whiteTurn = whiteTurn;
	}

	public static int getTurnCount() {
		return turnCount;
	}

	public static void setTurnCount(int turnCounter) {
		turnCount = turnCounter;
	}

	public static boolean isBlackOut() {
		return blackOut;
	}

	public static void setBlackOut(boolean value) {
		blackOut = value;
	}

	public static boolean isWhiteOut() {
		return whiteOut;
	}

	public static void setWhiteOut(boolean value) {
		whiteOut = value;
	}
	
	   /**
	    * calls a new game
	    */


	public static void callNewGame() {
		if (isOngoing() == false) {
			newGame();
			GUI.setBoard(Board.getSlots());
		}
		// continue the "else"
	}
	
	   /**
	    * throws the dice. If this is the first turn, the game will decide which player is starting.
	    */

	public static void throwDice() {
		turnCount = Board.throwDice();
		Rules.setRunning_turn(true);
		Dice.resetDice();
		Dice.resetDiceAvailability();
		if (!isOngoing()) {
			if (Dice.getDice1() > Dice.getDice2()) { // Dice1 Assigned to White Player
				setOngoing(true);
				setWhiteTurn(true);
				JOptionPane.showMessageDialog(null, "White Player Starting");
			} else if (Dice.getDice1() < Dice.getDice2()) {
				setOngoing(true);
				setWhiteTurn(false);
				JOptionPane.showMessageDialog(null, "Black Player Starting");
			} else {
				throwDice();
			}
		}
	}

	   /**
	    * moves player from the source to the destination and decreases the relevant number of moves
	    * @param slots the slots current condition
	    * @param source where the player is moving from
	    * @param location where the player is moving to

	    * 
	    */
	
	static void movePlayer(Slot[] slots, int source, int dest) { // moves player from one slot to another
		saveGame();
		if (Rules.whiteTurn) {
			if (slots[dest].getWhite() || slots[dest].isEmpty()) {
				slots[source].removePlayer();
				slots[dest].setWhite(isWhiteTurn());
				slots[dest].addPlayer();
			} else {
				slots[source].removePlayer();
				slots[dest].removePlayer();
				slots[24].setWhite(!isWhiteTurn());
				slots[24].addPlayer();
				slots[dest].setWhite(isWhiteTurn());
				slots[dest].addPlayer();
			}
		} else {
			if (!slots[dest].getWhite() || slots[dest].isEmpty()) {
				slots[source].removePlayer();
				slots[dest].setWhite(isWhiteTurn());
				slots[dest].addPlayer();
			} else {
				slots[source].removePlayer();
				slots[dest].removePlayer();
				slots[24].setWhite(!isWhiteTurn());
				slots[24].addPlayer();
				slots[dest].setWhite(isWhiteTurn());
				slots[dest].addPlayer();
			}
		}
		if (isWhiteTurn()) {
			if(dest==26) {
				dest=-1;
			}
			if (source - dest == Dice.getDice1() || (Dice.getDice1() >= getMax_pos() + 1 && dest == -1)) {
				Dice.setDice1Available(false);
				setTurnCount(getTurnCount() - 1);
			} else if (source - dest == Dice.getDice2() || (Dice.getDice2() >= getMax_pos() + 1 && dest == -1)) {
				Dice.setDice2Available(false);
				setTurnCount(getTurnCount() - 1);
			} else if (source - dest == (Dice.getDice1() + Dice.getDice2())) {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				setTurnCount(getTurnCount() - 2);
			} else if (source - dest == Dice.getDice1() * 3) {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				Dice.setDice3Available(false);
				setTurnCount(getTurnCount() - 3);
			} else {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				Dice.setDice3Available(false);
				Dice.setDice4Available(false);
				setTurnCount(getTurnCount() - 4);
			}
		} else {
			if (source == 24) {
				source = -1;
			}
			if (dest - source == Dice.getDice1() || (dest==25 && dest - 1 - source == Dice.getDice1()) || (Dice.getDice1() >= getMin_pos() - 17 && dest == 25)) {
				Dice.setDice1Available(false);
				setTurnCount(getTurnCount() - 1);
			} else if (dest - source == Dice.getDice2()  || (dest==25 && dest - 1 - source == Dice.getDice2())|| (Dice.getDice2() >= getMin_pos() - 17 && dest == 25)) {
				Dice.setDice2Available(false);
				setTurnCount(getTurnCount() - 1);
			} else if (dest - source == (Dice.getDice1() + Dice.getDice2())) {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				setTurnCount(getTurnCount() - 2);
			} else if (dest - source == Dice.getDice1() * 3) {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				Dice.setDice3Available(false);
				setTurnCount(getTurnCount() - 3);
			} else {
				Dice.setDice1Available(false);
				Dice.setDice2Available(false);
				Dice.setDice3Available(false);
				Dice.setDice4Available(false);
				setTurnCount(getTurnCount() - 4);
			}
		}
		if (slots[25].getCounter() == 15 || slots[26].getCounter() == 15) {
			endgame(Board.getSlots());
		}
		GUI.setBoard(Board.getSlots());
	}

	public static void endgame(Slot[] slots) {
		if (slots[25].getCounter() == 15 && slots[26].getCounter()==0) {
			JOptionPane.showMessageDialog(null, "Black Player Won by MARS (2 Points)");
		} else if (slots[25].getCounter() == 15 && slots[26].getCounter()!=0) {
			JOptionPane.showMessageDialog(null, "Black Player Won (1 Point)");
		} else if (slots[26].getCounter() == 15 && slots[25].getCounter()==0) {
			JOptionPane.showMessageDialog(null, "White Player Won by MARS (2 Points)");
		} else {
			JOptionPane.showMessageDialog(null, "White Player Won");
		}
		Rules.setOngoing(false);
		Rules.setRunning_turn(false);
	}

	
	   /**
	    * gets the slots and dices and checks which slots can play
	    * @param slots the slots state
	    * @param dice the current dice state
	    */
	
	public static int[] canPlay(Slot[] slots, Dice dice) { // Returns boolean array with the playable slots colored in
															// red.

		int[] arr = new int[25];
		int dice1 = Dice.getDice1();
		int dice2 = Dice.getDice2();
		int flag = 0;
		boolean whiteFlag = true;
		boolean blackFlag = true;
		for (int i = 0; i < 24; i++) {
			if (i > 5 && slots[i].getWhite() && !slots[i].isEmpty()) {
				whiteFlag = false;
			} else if (i < 18 && !slots[i].getWhite() && !slots[i].isEmpty()) {
				blackFlag = false;

			}
		}
		if (whiteTurn && whiteFlag) {
			setMax_pos(0);
			setWhiteOut(true);
			for (int i = 6; i >=0; i--) {
				if (!slots[i].isEmpty()) {
					if (getMax_pos() < i) {
						setMax_pos(i);
					}
				}
				if (slots[i].getWhite() == true && slots[i].isEmpty() == false && i + 1 == dice1
						&& Dice.isDice1Available()) {
					arr[i]++;
					flag = 1;
					if (getMax_pos() < i) {
						setMax_pos(i);
					}
				} else if (slots[i].getWhite() == true && slots[i].isEmpty() == false && i + 1 == dice2
						&& Dice.isDice2Available()) {
					arr[i]++;
					flag = 1;
					if (getMax_pos() < i) {
						setMax_pos(i);
					}
				} else if (slots[i].getWhite() == true && slots[i].isEmpty() == false && i + 1 < dice1
						&& getMax_pos() <= i && Dice.isDice1Available()) {
					arr[i]++;
					flag = 1;
					if (getMax_pos() < i) {
						setMax_pos(i);
					}
				} else if (slots[i].getWhite() == true && slots[i].isEmpty() == false && i + 1 < dice2
						&& getMax_pos() <= i && Dice.isDice2Available()) {
					arr[i]++;
					flag = 1;
					if (getMax_pos() < i) {
						setMax_pos(i);
					}
				} else if (i - dice1 >= 0 && slots[i].getWhite() && !slots[i].isEmpty()) {
					if ((slots[i - dice1].isEmpty() || slots[i - dice1].getWhite()
							|| (!slots[i - dice1].getWhite() && slots[i - dice1].isEdible()))
							&& (Dice.isDice1Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																													// White
						arr[i]++;
						flag = 1;
					}
				}
				if (i - dice2 >= 0 && slots[i].getWhite() && !slots[i].isEmpty()) {
					if ((slots[i - dice2].isEmpty() || slots[i - dice2].getWhite()
							|| (!slots[i - dice2].getWhite() && slots[i - dice2].isEdible()))
							&& (Dice.isDice2Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice2
																													// White
						arr[i]++;
						flag = 1;
					}
				}

			}
			if (getMax_pos() <= dice1 || getMax_pos() <= dice2) {
				arr[getMax_pos()]++;
				flag = 1;
			}
			if (flag == 0) {
				Rules.noMoves();
				return arr;
			} else {
				return arr;
			}
		} else if (whiteTurn && !whiteFlag) {
			setWhiteOut(false);
		} else if (!whiteTurn && blackFlag) {
			setMin_pos(23);
			setBlackOut(true);
			for (int i = 18, j = 23; i < 24; i++, j--) {
				if (!slots[i].isEmpty()) {
					if (i < getMin_pos()) {
						setMin_pos(i);
					}
				}
				if (slots[i].getWhite() == false && slots[i].isEmpty() == false && j - 17 == dice1
						&& Dice.isDice1Available()) {
					arr[i]++;
					flag = 1;
					if (getMin_pos() > i) {
						setMin_pos(i);
					}
				} else if (slots[i].getWhite() == false && slots[i].isEmpty() == false && j - 17 == dice2
						&& Dice.isDice2Available()) {
					arr[i]++;
					flag = 1;
					if (getMin_pos() > i) {
						setMin_pos(i);
					}
				} else if (slots[i].getWhite() == false && slots[i].isEmpty() == false && j - 17 < dice1
						&& getMin_pos() >= i && Dice.isDice1Available()) {
					arr[i]++;
					flag = 1;
					if (getMin_pos() > i) {
						setMin_pos(i);
					}
				} else if (slots[i].getWhite() == false && slots[i].isEmpty() == false && j - 17 < dice2
						&& getMin_pos() >= i && Dice.isDice2Available()) {
					arr[i]++;
					flag = 1;
					if (getMin_pos() > i) {
						setMin_pos(i);
					}
				} else if (i + dice1 <= 23 && !slots[i].getWhite() && !slots[i].isEmpty()) {
					if ((slots[i + dice1].isEmpty() || !slots[i + dice1].getWhite()
							|| (slots[i + dice1].getWhite() && slots[i + dice1].isEdible()))
							&& (Dice.isDice1Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																													// White
						arr[i]++;
						flag = 1;
					}
				}
				if (i + dice2 <= 23 && !slots[i].getWhite() && !slots[i].isEmpty()) {
					if ((slots[i + dice2].isEmpty() || !slots[i + dice2].getWhite()
							|| (slots[i + dice2].getWhite() && slots[i + dice2].isEdible()))
							&& (Dice.isDice2Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																													// White
						arr[i]++;
						flag = 1;
					}
				}

			}
			if (getMin_pos() <= dice1 || getMin_pos() <= dice2) {
				arr[getMin_pos()]++;
				flag = 1;
			}
			if (flag == 0) {
				Rules.noMoves();
				return arr;
			} else {
				return arr;
			}
		} else {
			setBlackOut(false);
		}
		if (!slots[24].isEmpty() && (slots[24].getWhite() == whiteTurn))

		{
			if (whiteTurn == true && (slots[24].getWhite() && !slots[24].isEmpty())) // check if the player has eaten //
																						// // players
			{
				if ((Board.slots_arr[24 - Dice.getDice1()].isEmpty() || Board.slots_arr[24 - Dice.getDice1()].getWhite()
						|| (!Board.slots_arr[24 - Dice.getDice1()].getWhite()
								&& Board.slots_arr[24 - Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					arr[24]++;
					flag = 1;
				}
				if ((Board.slots_arr[24 - Dice.getDice2()].isEmpty() || Board.slots_arr[24 - Dice.getDice2()].getWhite()
						|| (!Board.slots_arr[24 - Dice.getDice2()].getWhite()
								&& Board.slots_arr[24 - Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice2 White\\
					arr[24]++;
					flag = 1;
				}
				if (flag == 0) {
					Rules.noMoves();
					return arr;
				} else {
					return arr;
				}
			} else if (whiteTurn == false && (!slots[24].getWhite() && !slots[24].isEmpty())) // check if the player has
																								// //
																								// eaten players
			{
				if ((Board.slots_arr[-1 + Dice.getDice1()].isEmpty()
						|| !Board.slots_arr[-1 + Dice.getDice1()].getWhite()
						|| (Board.slots_arr[-1 + Dice.getDice1()].getWhite()
								&& Board.slots_arr[-1 + Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					arr[24]++;
					flag = 1;
				}
				if ((Board.slots_arr[-1 + Dice.getDice2()].isEmpty()
						|| !Board.slots_arr[-1 + Dice.getDice2()].getWhite()
						|| (Board.slots_arr[-1 + Dice.getDice2()].getWhite()
								&& Board.slots_arr[-1 + Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice2 White
					arr[24]++;
					flag = 1;
				}
				if (flag == 0)

				{
					Rules.noMoves();
					return arr;
				} else {
					return arr;
				}
			}
		} else {
			for (int i = 23; i >= 0; i--) {
				if (Dice.getDice1()!=Dice.getDice2()) {
					Dice.setDice3Available(false);
					Dice.setDice4Available(false);
				}
				if (whiteTurn == true && (slots[i].getWhite() && (!slots[i].isEmpty()))) {
					if (i - dice1 >= 0) {
						if ((slots[i - dice1].isEmpty() || slots[i - dice1].getWhite()
								|| (!slots[i - dice1].getWhite() && slots[i - dice1].isEdible()))
								&& (Dice.isDice1Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																														// White
							arr[i]++;
							flag = 1;
						}
					}
					if (i - dice2 >= 0) {
						if ((slots[i - dice2].isEmpty() || slots[i - dice2].getWhite()
								|| (!slots[i - dice2].getWhite() && slots[i - dice2].isEdible()))
								&& (Dice.isDice2Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice2
																														// White
							arr[i]++;
							flag = 1;
						}
					}
				} else if (whiteTurn == false && slots[i].getWhite() == false && (!slots[i].isEmpty())) {
					if (i + dice1 <= 23) {
						if ((slots[i + dice1].isEmpty() || !slots[i + dice1].getWhite()
								|| (slots[i + dice1].getWhite() && slots[i + dice1].isEdible()))
								&& (Dice.isDice1Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																														// White
							arr[i]++;
							flag = 1;
						}
					}
					if (i + dice2 <= 23) {
						if ((slots[i + dice2].isEmpty() || !slots[i + dice2].getWhite()
								|| (slots[i + dice2].getWhite() && slots[i + dice2].isEdible()))
								&& (Dice.isDice2Available() || Dice.isDice3Available() || Dice.isDice4Available())) { // Dice1
																														// White
							arr[i]++;
							flag = 1;
						}
					}
				}
			}
			if (flag == 0) {
				Rules.noMoves();
				return arr;
			} else {
				return arr;
			}
		}
		if (flag == 0) {
			Rules.noMoves();
			return arr;
		} else {
			return arr;
		}
	}

	   /**
	    * sets and error message and ends the turn in case there are no available moves
	    */
	
	private static void noMoves() {

		JOptionPane.showMessageDialog(null, "No Available Moves !");
		GUI.endTurn();
	}
	
	   /**
	    * throws random dice and checks which player is starting
	    */

	public void firstThrow() {

		// Need to change to Do-While
		throwDice();
		Board.getDice();
		Board.getDice();
		if (Dice.getDice1() > Dice.getDice2()) { // Dice1 Assigned to White Player
			setWhiteTurn(true);
		}
		Board.getDice();
		Board.getDice();
		if (Dice.getDice1() < Dice.getDice2()) {
			setWhiteTurn(false);
		}
		Board.getDice();
		Board.getDice();
		if (Dice.getDice1() == Dice.getDice2()) {
			throwDice();
		}
	}

	
	   /**
	    * gets the slots and dices and checks which slots can play in case he is eaten
	    * @param slots the slots state
	    * @param dice the current dice state
	    */


	public static void checkTopRules(Slot[] slots_arr, int location, Dice dice) {
		if (whiteTurn == true) {
			if ((Board.slots_arr[location - Dice.getDice1()].isEmpty()
					|| Board.slots_arr[location - Dice.getDice1()].getWhite()
					|| (!Board.slots_arr[location - Dice.getDice1()].getWhite()
							&& Board.slots_arr[location - Dice.getDice1()].isEdible()))
					&& Dice.isDice1Available()) { // Dice1 White
				Dice.setDice1Playable(true);
			} else {
				Dice.setDice1Playable(false);
			}
			if ((Board.slots_arr[location - Dice.getDice2()].isEmpty()
					|| Board.slots_arr[location - Dice.getDice2()].getWhite()
					|| (!Board.slots_arr[location - Dice.getDice2()].getWhite()
							&& Board.slots_arr[location - Dice.getDice2()].isEdible()))
					&& Dice.isDice2Available()) { // Dice2 White
				Dice.setDice2Playable(true);
			} else {
				Dice.setDice2Playable(false);
			}
		} else {
			if ((Board.slots_arr[-1 + Dice.getDice1()].isEmpty() || !Board.slots_arr[-1 + Dice.getDice1()].getWhite()
					|| (Board.slots_arr[-1 + Dice.getDice1()].getWhite()
							&& Board.slots_arr[-1 + Dice.getDice1()].isEdible()))
					&& Dice.isDice1Available()) { // Dice1 White
				Dice.setDice1Playable(true);
			} else {
				Dice.setDice1Playable(false);
			}
			if ((Board.slots_arr[-1 + Dice.getDice2()].isEmpty() || !Board.slots_arr[-1 + Dice.getDice2()].getWhite()
					|| (Board.slots_arr[-1 + Dice.getDice2()].getWhite()
							&& Board.slots_arr[-1 + Dice.getDice2()].isEdible()))
					&& Dice.isDice2Available()) { // Dice2 White
				Dice.setDice2Playable(true);
			} else {
				Dice.setDice2Playable(false);
			}
		}
		if (!Dice.isDice1Playable() && !Dice.isDice2Playable()) {
			noMoves();
		}
	}
	
	   /**
	    * checks where the player can play to after he chose a slot
	    * @param slots the slots state
	    * @param dice the current dice state
	    * @param location the location the player wants to play from
	    */

	public static void checkRules(Slot[] slots_arr, int location, Dice dice) {

		/*
		 * if (!(Board.slots_arr[location].isEmpty())) {
		 * 
		 * /*************** * Checks for Playable Slots (DOES NOT APPLY TO ENDGAME)
		 *********/
		if (whiteTurn && isWhiteOut()) {
			if (Dice.getDice1() == Dice.getDice2()) {
				Dice.resetDiceAvailability();
			}
			if (Dice.getDice1() > getMax_pos() + 1 && Dice.isDice1Available()) {
				Dice.setDice1Playable(true);
			} else if (location - Dice.getDice1() == -1 && Dice.isDice1Available()) {
				Dice.setDice1Playable(true);
			} else if (location - Dice.getDice1() >= 0) {
				if ((Board.slots_arr[location - Dice.getDice1()].isEmpty()
						|| Board.slots_arr[location - Dice.getDice1()].getWhite()
						|| (!Board.slots_arr[location - Dice.getDice1()].getWhite()
								&& Board.slots_arr[location - Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					Dice.setDice1Playable(true);
				} else {
					Dice.setDice1Playable(false);
				}
			} else {
				Dice.setDice1Playable(false);
			}
			if (Dice.getDice2() > getMax_pos() + 1 && Dice.isDice2Available()) {
				Dice.setDice2Playable(true);
			} else if (location - Dice.getDice2() == -1 && Dice.getDice1() != Dice.getDice2()
					&& Dice.isDice2Available()) {
				Dice.setDice2Playable(true);
			} else if (location - Dice.getDice2() >= 0 && Dice.getDice1() != Dice.getDice2()) {
				if ((Board.slots_arr[location - Dice.getDice2()].isEmpty()
						|| Board.slots_arr[location - Dice.getDice2()].getWhite()
						|| (!Board.slots_arr[location - Dice.getDice2()].getWhite()
								&& Board.slots_arr[location - Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice1 White
					Dice.setDice2Playable(true);
				} else {
					Dice.setDice2Playable(false);
				}
			} else {
				Dice.setDice2Playable(false);
			}
		} else if (!whiteTurn && isBlackOut()) {
			int[] switcher = {23,22,21,20,19,18};
			if (Dice.getDice1() == Dice.getDice2()) {
				Dice.resetDiceAvailability();
			}
			if (location + Dice.getDice1() >= 25 && switcher[Dice.getDice1()-1] <= getMin_pos() && Dice.isDice1Available()) {
				Dice.setDice1Playable(true);
			} else if (location + Dice.getDice1() == 24 && Dice.isDice1Available()) {
				Dice.setDice1Playable(true);
			} else if (location + Dice.getDice1() <= 23) {
				if ((Board.slots_arr[location + Dice.getDice1()].isEmpty()
						|| !Board.slots_arr[location + Dice.getDice1()].getWhite()
						|| (Board.slots_arr[location + Dice.getDice1()].getWhite()
								&& Board.slots_arr[location + Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					Dice.setDice1Playable(true);
				} else {
					Dice.setDice1Playable(false);
				}
			} else {
				Dice.setDice1Playable(false);
			}
			if (location + Dice.getDice2() >= 25 && switcher[Dice.getDice2()-1] <= getMin_pos() && Dice.isDice2Available()) {
				Dice.setDice2Playable(true);
			} else if (location + Dice.getDice2() == 24 && Dice.getDice1() != Dice.getDice2()
					&& Dice.isDice2Available()) {
				Dice.setDice2Playable(true);
			} else if (location + Dice.getDice2() <= 23 && Dice.getDice1() != Dice.getDice2()) {
				if ((Board.slots_arr[location + Dice.getDice2()].isEmpty()
						|| !Board.slots_arr[location + Dice.getDice2()].getWhite()
						|| (Board.slots_arr[location + Dice.getDice2()].getWhite()
								&& Board.slots_arr[location + Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice1 White
					Dice.setDice2Playable(true);
				} else {
					Dice.setDice2Playable(false);
				}
			} else {
				Dice.setDice2Playable(false);
			}
		} else if (Dice.getDice1() == Dice.getDice2()) {
			dice.setDouble_dice(true);
			Dice.resetDiceAvailability();
			if (whiteTurn) {
				if (location - Dice.getDice1() >= 0) {
					if ((Board.slots_arr[location - Dice.getDice1()].isEmpty()
							|| Board.slots_arr[location - Dice.getDice1()].getWhite()
							|| (!Board.slots_arr[location - Dice.getDice1()].getWhite()
									&& Board.slots_arr[location - Dice.getDice1()].isEdible()))
							&& Dice.isDice1Available()) { // Dice1 White
						Dice.setDice1Playable(true);
					} else {
						Dice.setDice1Playable(false);
					}
				} else {
					Dice.setDice1Playable(false);
				}
				if (Dice.isDice1Playable() && Rules.getTurnCount() >= 2) {
					if (location - Dice.getDice1() * 2 >= 0) {
						if (Board.slots_arr[location - Dice.getDice1() * 2].isEmpty()
								|| Board.slots_arr[location - Dice.getDice1() * 2].getWhite()
								|| (!Board.slots_arr[location - Dice.getDice1() * 2].getWhite()
										&& Board.slots_arr[location - Dice.getDice1() * 2].isEdible())) { // Dice1
																											// White
							Dice.setDice2Playable(true);
						} else {
							Dice.setDice2Playable(false);
						}
					} else {
						Dice.setDice2Playable(false);
					}
				} else {
					Dice.setDice2Playable(false);
				}
				if (Dice.isDice2Playable() && Rules.getTurnCount() >= 3) {
					if (location - Dice.getDice1() * 3 >= 0) {
						if (Board.slots_arr[location - Dice.getDice1() * 3].isEmpty()
								|| Board.slots_arr[location - Dice.getDice1() * 3].getWhite()
								|| (!Board.slots_arr[location - Dice.getDice1() * 3].getWhite()
										&& Board.slots_arr[location - Dice.getDice1() * 3].isEdible())) { // Dice1
																											// White
							Dice.setDice3Playable(true);
						} else {
							Dice.setDice3Playable(false);
						}
					} else {
						Dice.setDice3Playable(false);
					}
				} else {
					Dice.setDice3Playable(false);
				}
				if (Dice.isDice3Playable() && Rules.getTurnCount() >= 4) {
					if (location - Dice.getDice1() * 4 >= 0) {
						if (Board.slots_arr[location - Dice.getDice1() * 4].isEmpty()
								|| Board.slots_arr[location - Dice.getDice1() * 4].getWhite()
								|| (!Board.slots_arr[location - Dice.getDice1() * 4].getWhite()
										&& Board.slots_arr[location - Dice.getDice1() * 4].isEdible())) { // Dice1
																											// White
							Dice.setDice4Playable(true);
						} else {
							Dice.setDice4Playable(false);
						}
					} else {
						Dice.setDice4Playable(false);
					}
				} else {
					Dice.setDice4Playable(false);
				}
			} else {
				if (location + Dice.getDice1() <= 23) {
					if ((Board.slots_arr[location + Dice.getDice1()].isEmpty()
							|| !Board.slots_arr[location + Dice.getDice1()].getWhite()
							|| (Board.slots_arr[location + Dice.getDice1()].getWhite()
									&& Board.slots_arr[location + Dice.getDice1()].isEdible()))
							&& Dice.isDice1Available()) { // Dice1 White
						Dice.setDice1Playable(true);
					} else {
						Dice.setDice1Playable(false);
					}
				} else {
					Dice.setDice1Playable(false);
				}
				if (Dice.isDice1Playable() && Rules.getTurnCount() >= 2) {
					if (location + Dice.getDice1() * 2 <= 23) {
						if (Board.slots_arr[location + Dice.getDice1() * 2].isEmpty()
								|| !Board.slots_arr[location + Dice.getDice1() * 2].getWhite()
								|| (Board.slots_arr[location + Dice.getDice1() * 2].getWhite()
										&& Board.slots_arr[location + Dice.getDice1() * 2].isEdible())) { // Dice1
																											// White
							Dice.setDice2Playable(true);
						} else {
							Dice.setDice2Playable(false);
						}
					} else {
						Dice.setDice2Playable(false);
					}
				} else {
					Dice.setDice2Playable(false);
				}
				if (Dice.isDice2Playable() && Rules.getTurnCount() >= 3) {
					if (location + Dice.getDice1() * 3 <= 23) {
						if (Board.slots_arr[location + Dice.getDice1() * 3].isEmpty()
								|| !Board.slots_arr[location + Dice.getDice1() * 3].getWhite()
								|| (Board.slots_arr[location + Dice.getDice1() * 3].getWhite()
										&& Board.slots_arr[location + Dice.getDice1() * 3].isEdible())) { // Dice1
																											// White
							Dice.setDice3Playable(true);
						} else {
							Dice.setDice3Playable(false);
						}
					} else {
						Dice.setDice3Playable(false);
					}
				} else {
					Dice.setDice3Playable(false);
				}
				if (Dice.isDice3Playable() && Rules.getTurnCount() >= 4) {
					if (location + Dice.getDice1() * 4 <= 23) {
						if (Board.slots_arr[location + Dice.getDice1() * 4].isEmpty()
								|| !Board.slots_arr[location + Dice.getDice1() * 4].getWhite()
								|| (Board.slots_arr[location + Dice.getDice1() * 4].getWhite()
										&& Board.slots_arr[location + Dice.getDice1() * 4].isEdible())) { // Dice1
																											// White
							Dice.setDice4Playable(true);
						} else {
							Dice.setDice4Playable(false);
						}
					} else {
						Dice.setDice4Playable(false);
					}
				} else {
					Dice.setDice4Playable(false);
				}
			}
		} else if (whiteTurn == true) {
			if (location - Dice.getDice1() >= 0) {
				if ((Board.slots_arr[location - Dice.getDice1()].isEmpty()
						|| Board.slots_arr[location - Dice.getDice1()].getWhite()
						|| (!Board.slots_arr[location - Dice.getDice1()].getWhite()
								&& Board.slots_arr[location - Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					Dice.setDice1Playable(true);
				} else {
					Dice.setDice1Playable(false);
				}
			} else {
				Dice.setDice1Playable(false);
			}
			if (location - Dice.getDice2() >= 0) {
				if ((Board.slots_arr[location - Dice.getDice2()].isEmpty()
						|| Board.slots_arr[location - Dice.getDice2()].getWhite()
						|| (!Board.slots_arr[location - Dice.getDice2()].getWhite()
								&& Board.slots_arr[location - Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice2 White
					Dice.setDice2Playable(true);
				} else {
					Dice.setDice2Playable(false);
				}
			} else {
				Dice.setDice2Playable(false);
			}
			if ((Dice.isDice1Playable() || Dice.isDice2Playable())
					&& (location - Dice.getDice1() - Dice.getDice2() >= 0) && (getTurnCount() >= 2)) {
				if (Board.slots_arr[location - Dice.getDice1() - Dice.getDice2()].isEmpty()
						|| Board.slots_arr[location - Dice.getDice1() - Dice.getDice2()].getWhite()
						|| (!Board.slots_arr[location - Dice.getDice1() - Dice.getDice2()].getWhite()
								&& Board.slots_arr[location - Dice.getDice1() - Dice.getDice2()].isEdible())) { // Dice2
					// White
					Dice.setDice3Playable(true);
				} else {
					Dice.setDice3Playable(false);
				}
			} else {
				Dice.setDice3Playable(false);
			}
		} else if (whiteTurn == false && Board.slots_arr[location].getWhite() == false
				&& (!Board.slots_arr[location].isEmpty())) {
			if (location + Dice.getDice1() <= 23) {
				if ((Board.slots_arr[location + Dice.getDice1()].isEmpty()
						|| !Board.slots_arr[location + Dice.getDice1()].getWhite()
						|| (Board.slots_arr[location + Dice.getDice1()].getWhite()
								&& Board.slots_arr[location + Dice.getDice1()].isEdible()))
						&& Dice.isDice1Available()) { // Dice1 White
					Dice.setDice1Playable(true);
				} else {
					Dice.setDice1Playable(false);

				}
			}
			if (location + Dice.getDice2() <= 23) {
				if ((Board.slots_arr[location + Dice.getDice2()].isEmpty()
						|| !Board.slots_arr[location + Dice.getDice2()].getWhite()
						|| (Board.slots_arr[location + Dice.getDice2()].getWhite()
								&& Board.slots_arr[location + Dice.getDice2()].isEdible()))
						&& Dice.isDice2Available()) { // Dice2 White
					Dice.setDice2Playable(true);
				} else {
					Dice.setDice2Playable(false);
				}
			}
			if ((Dice.isDice1Playable() || Dice.isDice2Playable())
					&& location + Dice.getDice1() + Dice.getDice2() <= 23) {
				if (Board.slots_arr[location + Dice.getDice1() + Dice.getDice2()].isEmpty()
						|| !Board.slots_arr[location + Dice.getDice1() + Dice.getDice2()].getWhite()
						|| (Board.slots_arr[location + Dice.getDice1() + Dice.getDice2()].getWhite()
								&& Board.slots_arr[location + Dice.getDice1() + Dice.getDice2()].isEdible())) { // Dice2
					// White
					Dice.setDice3Playable(true);
				} else {
					Dice.setDice3Playable(false);
				}
			} else {
				Dice.setDice3Playable(false);
			}
		}
	}
}
