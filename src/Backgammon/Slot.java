package Backgammon;

/**
 * holds all the relevant information for each slot
 * @param empty the slot is empty/!
 * @param full the slot is full/!
 * @param counter how many players the slots contains
 * @param edible is the player in the slot edible
 */

public class Slot extends Player{
	boolean empty=true;
	boolean full=false;
	int counter=0;
	int source=0;
	boolean edible=true;	
	
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}
	public int getCounter() {
		return counter;
	}
	public void incCounter() {
		this.counter++;
	}
	public void decCounter() {
		this.counter--;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public boolean isEdible() {
		return edible;
	}
	public void setEdible(boolean edible) {
		this.edible = edible;
	}
	
	/**
	 * sets the relevant information according to the slot state
	 * @param players how many players are in the slot
	 * @param white the slot color (black/white)
	 */
	
	public void setSlot (int players, boolean white) {
		this.setCounter(players);
		this.setWhite(white);
		if(players==5) {
			this.setFull(true);
		}
		if(players==0) {
			this.setEmpty(true);
		} else
			this.setEmpty(false);
		if(players>1) {
			this.setEdible(false);
		} else
			this.setEdible(true);
	}
	
	/**
	 * adds a player to the slot and sets the relevant information according to the slot state
	 */
	
	public void addPlayer() {
		this.incCounter();
		if(this.isEmpty()==true) {
			this.setEmpty(false);
		}
		if(this.getCounter()>1) {
			this.setEdible(false);
		}
		if(this.getCounter()>=5) {
			this.setFull(true);
		}
		
	}
	
	/**
	 * removes a player from the slot and sets the relevant information according to the slot state
	 */
	
	public void removePlayer() {
		if(this.isEmpty()==false) {
			this.decCounter();
		}
		if(this.getCounter()<2) {
			this.setEdible(true);
		}
		if(this.getCounter()<5) {
			this.setFull(false);
		}
		if(this.getCounter()==0) {
			this.setEmpty(true);
		}
		
	}
	

}
