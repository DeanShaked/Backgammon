package Backgammon;

/**
 * This class defines players
 */

public class Player {
	boolean white=true;
	
	
	
	public Boolean getWhite() {
		return white;
	}



	public void setWhite(Boolean white) {
		this.white = white;
	}



	@Override
	public String toString() {
		return "Player [white=" + white + "]";
	}
	
}
