package Backgammon;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains all the images required for the GUI components (Slots, Players, Dice)
 */

public class ImageIcons {

	static ImageIcon Background = new ImageIcon(
			new ImageIcon("Board_1000x1000.png").getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT));
	static ImageIcon side = new ImageIcon(
			new ImageIcon("Side_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon side_yellow = new ImageIcon(
			new ImageIcon("Side_Yellow_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_straight = new ImageIcon(
			new ImageIcon("Black_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_straight = new ImageIcon(
			new ImageIcon("Red_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_opposite = new ImageIcon(
			new ImageIcon("Black_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_opposite = new ImageIcon(
			new ImageIcon("Red_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_yellow_straight = new ImageIcon(
			new ImageIcon("Black_yellow_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_yellow_straight = new ImageIcon(
			new ImageIcon("Red_yellow_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_yellow_opposite = new ImageIcon(
			new ImageIcon("Black_yellow_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_yellow_opposite = new ImageIcon(
			new ImageIcon("Red_yellow_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_red_straight = new ImageIcon(
			new ImageIcon("Black_red_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_red_straight = new ImageIcon(
			new ImageIcon("Red_red_79x349.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon black_red_opposite = new ImageIcon(
			new ImageIcon("Black_red_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon red_red_opposite = new ImageIcon(
			new ImageIcon("Red_red_down.png").getImage().getScaledInstance(62, 275, Image.SCALE_DEFAULT));
	static ImageIcon center = new ImageIcon(
			new ImageIcon("Middle.png").getImage().getScaledInstance(62, 277, Image.SCALE_DEFAULT));
	static ImageIcon center_yellow = new ImageIcon(
			new ImageIcon("Middle_Yellow.png").getImage().getScaledInstance(62, 277, Image.SCALE_DEFAULT));
	static ImageIcon center_red = new ImageIcon(
			new ImageIcon("Middle_Red.png").getImage().getScaledInstance(62, 277, Image.SCALE_DEFAULT));
	static ImageIcon white = new ImageIcon(
			new ImageIcon("White.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
	static ImageIcon white_top = new ImageIcon(
			new ImageIcon("White_top.png").getImage().getScaledInstance(78, 23, Image.SCALE_DEFAULT));
	static ImageIcon black = new ImageIcon(
			new ImageIcon("Black.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
	static ImageIcon black_top = new ImageIcon(
			new ImageIcon("Black_top.png").getImage().getScaledInstance(78, 23, Image.SCALE_DEFAULT));
	static ImageIcon dice1 = new ImageIcon(
			new ImageIcon("Dice_1.png").getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));
	static ImageIcon dice2 = new ImageIcon(
			new ImageIcon("Dice_2.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
	static ImageIcon dice3 = new ImageIcon(
			new ImageIcon("Dice_3.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
	static ImageIcon dice4 = new ImageIcon(
			new ImageIcon("Dice_4.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
	static ImageIcon dice5 = new ImageIcon(
			new ImageIcon("Dice_5.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
	static ImageIcon dice6 = new ImageIcon(
			new ImageIcon("Dice_6.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));

	/**
	 * This methods gets random number and returns the correct dice image
	 * 
	 * @param number a random int number generated by Board.throwDice
	 * @return returns the image of the correct dice
	 * 
	 * 
	 */
	
	public static ImageIcon diceIcon(int number) {
		switch (number) {
		case 1:
			return (dice1);
		case 2:
			return (dice2);
		case 3:
			return (dice3);
		case 4:
			return (dice4);
		case 5:
			return (dice5);
		case 6:
			return (dice6);
		default:
			return(dice1);
		}
	}
}

