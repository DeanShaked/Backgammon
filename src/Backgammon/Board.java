package Backgammon;


/**
 * This class holds all the variables of the board.
 *
 * @param  slots_arr  an array of 27 slots (one for each space on the board)
 * @param  Dice the dices of the game

 */

public class Board {
	
	static Slot[] slots_arr = new Slot[27];
	static Dice dice = new Dice();
	
	
	public static Dice getDice() {
		return dice;
	}
	
	
	public static Slot[] getSlots() {
		return slots_arr;
	}
	
	/**
	 * throwDice sets a new random dice
	 *
	 * @return  returns the number of moves given to the player

	 */
	
	public static int throwDice() {
		dice.randomDice();
		Dice.setDice1Playable(false);
		Dice.setDice2Playable(false);
		if (Dice.getDice1()==Dice.getDice2()) {
			return 4;
		} else {
			return 2;
		}
	}
	
	/**
	 * newGame resets the board variables to start a new game
	 *

	 */
	
	public static void newGame() {
		for(int i=0;i<27;i++) {
			slots_arr[i] = new Slot();
			slots_arr[i].setSlot(0, false);
		}
		slots_arr[0].setSlot(2, false);
		slots_arr[5].setSlot(5, true);
		slots_arr[7].setSlot(3, true);
		slots_arr[11].setSlot(5, false);
		slots_arr[12].setSlot(5, true);
		slots_arr[16].setSlot(3, false);
		slots_arr[18].setSlot(5, false);
		slots_arr[23].setSlot(2, true); 
		
	/*	slots_arr[2].setSlot(5, true);
		slots_arr[3].setSlot(5, true);
		slots_arr[4].setSlot(5, true);
		slots_arr[7].setSlot(0, true);
		slots_arr[11].setSlot(0, false);
		slots_arr[12].setSlot(0, true);
		slots_arr[16].setSlot(0, false);
		slots_arr[19].setSlot(5, false);
		slots_arr[20].setSlot(5, false);
		slots_arr[21].setSlot(5, false);*/
		
	} 
			
			


	public static void setSlots(Slot[] pop) {
		// TODO Auto-generated method stub
		 System.arraycopy(pop, 0, slots_arr,  
                 0, 27);
	}
	
	

}
