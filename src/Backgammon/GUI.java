package Backgammon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * This class holds all required for the board GUI.
	 * 
	 * @param slots   27 playing slots
	 * @param buttons 27 buttons relating to each slot
	 * @param players all the black/white players
	 * @param dices   2 playing dice
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JLabel[] slots = new JLabel[27];
	static JButton[] buttons = new JButton[27];
	private static JLabel[][] players = new JLabel[27][24];
	private static JLabel[] dices = new JLabel[2];
	private static int source = -1;

	/**
	 * This method (GUI) creates the board
	 * 
	 */

	public GUI() {
		initLayout();
		initFrame();
		Board.newGame();
		setBoard(Board.getSlots());
	}

	/**
	 * This method (initLayout) creates and positions all the image elements on the
	 * panel
	 * 
	 * @param slots   27 playing slots
	 * @param buttons 27 buttons relating to each slot
	 * @param players all the black/white players
	 * @param dices   2 playing dice
	 * 
	 */

	void initLayout() {
		panel = new JPanel();
		panel.setLayout(null);
		add(panel);

		JLabel background_label = new JLabel(ImageIcons.Background);
		background_label.setIcon(ImageIcons.Background);
		background_label.setBounds(0, 0, 800, 800);

		dices[0] = new JLabel();
		dices[0].setIcon(ImageIcons.diceIcon(1));
		dices[1] = new JLabel();
		dices[1].setIcon(ImageIcons.diceIcon(2));
		for (int i = 0; i < 2; i++) {
			panel.add(dices[i]);
			dices[i].setBounds(Coordinates.dice_location[i][0], Coordinates.dice_location[i][1],
					Coordinates.dice_location[i][2], Coordinates.dice_location[i][3]);
		}

		for (int i = 0; i < 24; i++) { // set the board colors
			buttons[i] = createButton(i);
			if (i < 12 && i % 2 == 0) {
				slots[i] = createLabel(i, ImageIcons.black_opposite);
			} else if (i < 12 && i % 2 != 0) {
				slots[i] = createLabel(i, ImageIcons.red_opposite);
			} else if (i > 11 && i % 2 == 0) {
				slots[i] = createLabel(i, ImageIcons.black_straight);
			} else {
				slots[i] = createLabel(i, ImageIcons.red_straight);
			}
		}
		buttons[24] = createButton(24);
		slots[24] = createLabel(24, ImageIcons.center);
		panel.add(background_label);

		buttons[25] = createButton(25);
		slots[25] = createLabel(25, ImageIcons.side);
		panel.add(background_label);

		buttons[26] = createButton(26);
		slots[26] = createLabel(26, ImageIcons.side);
		panel.add(background_label);

		JLabel[] labels = new JLabel[649];
		int i = 0;
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.white);
		for (int j = 0; j < 12; j++) {
			for (int k = 0; k < 12; k++) {
				labels[i] = new JLabel();
				labels[i].setIcon(ImageIcons.white);
				players[j][k] = new JLabel();
				players[j][k].setBounds(0, 0, 62, 275);
				players[j][k].add(labels[i]);
				labels[i].setBounds(Coordinates.buttom_player_locations[k][0],
						Coordinates.buttom_player_locations[k][1], Coordinates.buttom_player_locations[k][2],
						Coordinates.buttom_player_locations[k][3]);
				i++;
			}
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.white);
		for (int j = 12; j < 24; j++) {
			for (int k = 0; k < 12; k++) {
				labels[i] = new JLabel();
				labels[i].setIcon(ImageIcons.white);
				players[j][k] = new JLabel();
				players[j][k].setBounds(0, 0, 62, 275);
				players[j][k].add(labels[i]);
				labels[i].setBounds(Coordinates.top_player_locations[k][0], Coordinates.top_player_locations[k][1],
						Coordinates.top_player_locations[k][2], Coordinates.top_player_locations[k][3]);
				i++;
			}
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.black);
		for (int j = 0; j < 12; j++) {
			for (int k = 12; k < 24; k++) {
				labels[i] = new JLabel();
				labels[i].setIcon(ImageIcons.black);
				players[j][k] = new JLabel();
				players[j][k].setBounds(0, 0, 62, 275);
				players[j][k].add(labels[i]);
				labels[i].setBounds(Coordinates.buttom_player_locations[k - 12][0],
						Coordinates.buttom_player_locations[k - 12][1], Coordinates.buttom_player_locations[k - 12][2],
						Coordinates.buttom_player_locations[k - 12][3]);
				i++;
			}
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.black);
		for (int j = 12; j < 24; j++) {
			for (int k = 12; k < 24; k++) {
				labels[i] = new JLabel();
				labels[i].setIcon(ImageIcons.black);
				players[j][k] = new JLabel();
				players[j][k].setBounds(0, 0, 62, 275);
				players[j][k].add(labels[i]);
				labels[i].setBounds(Coordinates.top_player_locations[k - 12][0],
						Coordinates.top_player_locations[k - 12][1], Coordinates.top_player_locations[k - 12][2],
						Coordinates.top_player_locations[k - 12][3]);
				i++;
			}
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.white);
		int j = 24;
		for (int k = 0; k < 12; k++) {
			labels[i] = new JLabel();
			labels[i].setIcon(ImageIcons.white);
			players[j][k] = new JLabel();
			players[j][k].setBounds(0, 0, 62, 275);
			players[j][k].add(labels[i]);
			labels[i].setBounds(Coordinates.center_player_locations[k][0], Coordinates.center_player_locations[k][1],
					Coordinates.center_player_locations[k][2], Coordinates.center_player_locations[k][3]);
			i++;
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.black);
		j = 24;
		for (int k = 12; k < 24; k++) {
			labels[i] = new JLabel();
			labels[i].setIcon(ImageIcons.black);
			players[j][k] = new JLabel();
			players[j][k].setBounds(0, 0, 62, 275);
			players[j][k].add(labels[i]);
			labels[i].setBounds(Coordinates.center_player_locations[k - 12][0],
					Coordinates.center_player_locations[k - 12][1], Coordinates.center_player_locations[k - 12][2],
					Coordinates.center_player_locations[k - 12][3]);
			i++;
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.black_top);
		j = 25;
		for (int k = 0; k < 15; k++) {
			labels[i] = new JLabel();
			labels[i].setIcon(ImageIcons.black_top);
			players[j][k] = new JLabel();
			players[j][k].setBounds(0, 0, 62, 275);
			players[j][k].add(labels[i]);
			labels[i].setBounds(Coordinates.side_player_locations[k][0], Coordinates.side_player_locations[k][1],
					Coordinates.side_player_locations[k][2], Coordinates.side_player_locations[k][3]);
			i++;
		}
		labels[i] = new JLabel();
		labels[i].setIcon(ImageIcons.white_top);
		j = 26;
		for (int k = 0; k < 15; k++) {
			labels[i] = new JLabel();
			labels[i].setIcon(ImageIcons.white_top);
			players[j][k] = new JLabel();
			players[j][k].setBounds(0, 0, 62, 275);
			players[j][k].add(labels[i]);
			labels[i].setBounds(Coordinates.side_player_locations[k][0], Coordinates.side_player_locations[k][1],
					Coordinates.side_player_locations[k][2], Coordinates.side_player_locations[k][3]);
			i++;
		}

		JButton newGame = new JButton("New");
		panel.add(newGame);
		newGame.setBounds(805, 295, 75, 40);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Rules.isOngoing()) {
					// initLayout();
					// initFrame();
					Rules.newGame();
					setBoard(Rules.getSlots());
				} else {
					JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null,
							"There is an ongoing game. Do you want to continue?", "Warning", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						// initLayout();
						// initFrame();
						Rules.newGame();
						setBoard(Rules.getSlots());
						Rules.setOngoing(false);
					}
				}
			}
		});

		JButton throwDice = new JButton("Throw");
		panel.add(throwDice);
		throwDice.setBounds(805, 345, 75, 40);
		throwDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Rules.running_turn == false) {
					Dice.resetDice();
					Rules.throwDice();
					setDice(Board.getDice());
					setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
				} else {
					JOptionPane.showMessageDialog(null, "Please finish turn first!");
				}
			}
		});

		JButton undo = new JButton("Undo");
		panel.add(undo);
		undo.setBounds(805, 465, 75, 40);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Rules.isCanload() == true) {
					Rules.loadGame();
					setBoard(Rules.getSlots());
					JOptionPane.showMessageDialog(null, "Undo Successful");
					setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
				} else {
					JOptionPane.showMessageDialog(null, "Unable to undo");
				}
			}
		});

		JButton endTurn = new JButton("End");
		panel.add(endTurn);
		endTurn.setBounds(805, 405, 75, 40);
		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Rules.getTurnCount() != 0) {
					JOptionPane.showMessageDialog(null, "Please play all your moves");
				} else if (Rules.isWhiteTurn() == true) {
					JOptionPane.showMessageDialog(null, "White Turn Ended");
					Rules.setWhiteTurn(!Rules.isWhiteTurn());
					Dice.resetDice();
					Rules.throwDice();
					setDice(Board.getDice());
					setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
					Rules.emptyStacks();
				} else {
					JOptionPane.showMessageDialog(null, "Black Turn Ended");
					Rules.setWhiteTurn(!Rules.isWhiteTurn());
					Dice.resetDice();
					Rules.throwDice();
					setDice(Board.getDice());
					setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
					Rules.emptyStacks();
				}

				// Push board state because a turn was ended
			}
		});
	}

	/**
	 * endTurn method ends the player turn after confirming that he played all of
	 * his moves
	 */

	public static void endTurn() {
		if (Rules.isWhiteTurn() == true) {
			JOptionPane.showMessageDialog(null, "White Turn Ended");
			Rules.setWhiteTurn(!Rules.isWhiteTurn());
			Dice.resetDice();
			Rules.throwDice();
			setDice(Board.getDice());
			setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
			Rules.emptyStacks();
		} else {
			JOptionPane.showMessageDialog(null, "Black Turn Ended");
			Rules.setWhiteTurn(!Rules.isWhiteTurn());
			Dice.resetDice();
			Rules.throwDice();
			setDice(Board.getDice());
			setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
			Rules.emptyStacks();
		}
	}

	/**
	 * setPlayable method gets an array and colors in red the slots that can play.
	 * 
	 * @param arr an array containing information of which slots can be played from.
	 */

	private static void setPlayble(int[] arr) {
		resetBoardColors();
		if (arr[24] > 0) {
			slots[24].setIcon(ImageIcons.center_red);
		} else {
			for (int i = 0; i < 24; i++) {
				if (arr[i] > 0) {
					if (slots[i].getIcon().equals(ImageIcons.black_opposite)) {
						slots[i].setIcon(ImageIcons.black_red_opposite);
					} else if (slots[i].getIcon().equals(ImageIcons.red_opposite)) {
						slots[i].setIcon(ImageIcons.red_red_opposite);
					} else if (slots[i].getIcon().equals(ImageIcons.black_straight)) {
						slots[i].setIcon(ImageIcons.black_red_straight);
					} else {
						slots[i].setIcon(ImageIcons.red_red_straight);
					}
				}
			}
		}
	}

	/**
	 * setPlayOptions method resets all colors in yellow slots that can be played to
	 * according to the data received.
	 * 
	 * @param source       the slots chosen to be played from.
	 * @param dice         the dice numbers
	 * @param num_of_turns how many turns does the player have.
	 */

	private void setPlayOptions(int source, Dice dice, int num_of_turns) { // check which dice can play
		resetBoardColors(); // resets all the board colors to default
		int i = 0;
		int j = 0;
		int dices[] = new int[4];
		if (source == 24) {
			if (!Rules.isWhiteTurn()) {
				source = -1;
			}
		}
		if (num_of_turns == 4) {
			if (Rules.isWhiteTurn()) {
				dices[0] = source - Dice.getDice1() * 4;
				dices[1] = source - Dice.getDice1() * 3;
				dices[2] = source - Dice.getDice1() * 2;
				dices[3] = source - Dice.getDice1();
			} else {
				dices[0] = source + Dice.getDice1() * 4;
				dices[1] = source + Dice.getDice1() * 3;
				dices[2] = source + Dice.getDice1() * 2;
				dices[3] = source + Dice.getDice1();
			}
			if (Dice.isDice1Playable() && Dice.isDice2Playable() && Dice.isDice3Playable() && Dice.isDice4Playable()) { // checks
																														// which
																														// dice
																														// are
																														// playable
				i = 0;
			} else if (Dice.isDice1Playable() && Dice.isDice2Playable() && Dice.isDice3Playable()) {
				i = 1;
			} else if (Dice.isDice1Playable() && Dice.isDice2Playable()) {
				i = 2;
			} else {
				i = 3;
			}
			for (; i < 4; i++) {
				if (dices[i] <= -1 && Rules.isWhiteOut()) {
					dices[i] = 26;
				} else if (dices[i] >= 24 && Rules.isBlackOut()) {
					dices[i] = 25;
				}
				if (slots[dices[i]].getIcon().equals(ImageIcons.black_opposite)) {
					slots[dices[i]].setIcon(ImageIcons.black_yellow_opposite);
				} else if (slots[dices[i]].getIcon().equals(ImageIcons.red_opposite)) {
					slots[dices[i]].setIcon(ImageIcons.red_yellow_opposite);
				} else if (slots[dices[i]].getIcon().equals(ImageIcons.black_straight)) {
					slots[dices[i]].setIcon(ImageIcons.black_yellow_straight);
				} else {
					slots[dices[i]].setIcon(ImageIcons.red_yellow_straight);
				}
			}
		} else if (num_of_turns == 3) {
			if (Dice.isDice1Playable() || Dice.isDice2Playable() || Dice.isDice3Playable()) { // this means that the
																								// dice are identical so
																								// we can check only 1
																								// dice
				if (Rules.isWhiteTurn()) {
					dices[1] = source - Dice.getDice1() * 3;
					dices[2] = source - Dice.getDice1() * 2;
					dices[3] = source - Dice.getDice1();
				} else {
					dices[1] = source + Dice.getDice1() * 3;
					dices[2] = source + Dice.getDice1() * 2;
					dices[3] = source + Dice.getDice1();
				}
				if (Dice.isDice1Playable() && Dice.isDice2Playable() && Dice.isDice3Playable()) {
					i = 1;
				} else if (Dice.isDice1Playable() && Dice.isDice2Playable()) {
					i = 2;
				} else {
					i = 3;
				}
				for (; i < 4; i++) {
					if (dices[i] <= -1 && Rules.isWhiteOut()) {
						dices[i] = 26;
					} else if (dices[i] >= 24 && Rules.isBlackOut()) {
						dices[i] = 25;
					}
					if (slots[dices[i]].getIcon().equals(ImageIcons.black_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.red_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.black_straight)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_straight);
					} else {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_straight);
					}
				}
			}
		} else if (num_of_turns == 2) {
			if (Dice.isDice1Playable() && Dice.isDice2Playable()) { // checks if both dice are plyable
				if (Rules.isWhiteTurn()) {
					dices[0] = source - Dice.getDice1();
					dices[1] = source - Dice.getDice2();
					j = 2;
					if (Dice.isDice3Playable()) {
						dices[2] = source - Dice.getDice1() - Dice.getDice2();
						j = 3;
					}
				} else {
					dices[0] = source + Dice.getDice1();
					dices[1] = source + Dice.getDice2();
					j = 2;
					if (Dice.isDice3Playable()) {
						dices[2] = source + Dice.getDice1() + Dice.getDice2();
						j = 3;
					}
				}
				for (i = 0; i < j; i++) {
					if (dices[i] <= -1 && Rules.isWhiteOut()) {
						dices[i] = 26;
					} else if (dices[i] >= 24 && Rules.isBlackOut()) {
						dices[i] = 25;
					}
					if (slots[dices[i]].getIcon().equals(ImageIcons.black_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.red_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.black_straight)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_straight);
					} else {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_straight);
					}
				}
			} else if (Dice.isDice1Playable() && Dice.isDice3Playable()) { // checks if both dice are plyable
				if (Rules.isWhiteTurn()) {
					dices[0] = source - Dice.getDice1() - Dice.getDice2();
					dices[1] = source - Dice.getDice1();
				} else {
					dices[0] = source + Dice.getDice1() + Dice.getDice2();
					dices[1] = source + Dice.getDice1();
				}
				for (i = 0; i < 2; i++) {
					if (dices[i] == -1 && Rules.isWhiteOut()) {
						dices[i] = 26;
					} else if (dices[i] == 24 && Rules.isBlackOut()) {
						dices[i] = 25;
					}
					if (slots[dices[i]].getIcon().equals(ImageIcons.black_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.red_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.black_straight)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_straight);
					} else {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_straight);
					}
				}
			} else if (Dice.isDice2Playable() && Dice.isDice3Playable()) { // checks if both dice are plyable
				if (Rules.isWhiteTurn()) {
					dices[0] = source - Dice.getDice1() - Dice.getDice2();
					dices[1] = source - Dice.getDice2();
				} else {
					dices[0] = source + Dice.getDice1() + Dice.getDice2();
					dices[1] = source + Dice.getDice2();
				}
				for (i = 0; i < 2; i++) {
					if (dices[i] == -1 && Rules.isWhiteOut()) {
						dices[i] = 26;
					} else if (dices[i] == 24 && Rules.isBlackOut()) {
						dices[i] = 25;
					}
					if (slots[dices[i]].getIcon().equals(ImageIcons.black_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.red_opposite)) {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_opposite);
					} else if (slots[dices[i]].getIcon().equals(ImageIcons.black_straight)) {
						slots[dices[i]].setIcon(ImageIcons.black_yellow_straight);
					} else {
						slots[dices[i]].setIcon(ImageIcons.red_yellow_straight);
					}
				}
			}
			if (Dice.isDice1Playable()) { // checks if only dice 1 is playable
				i = 0;
				if (Rules.isWhiteTurn())
					i = source - Dice.getDice1();
				else
					i = source + Dice.getDice1();
				if (Rules.whiteTurn && source - Dice.getDice1() <= -1 && Rules.isWhiteOut()) {
					i = 26;
				} else if (!Rules.whiteTurn && source + Dice.getDice1() >= 24 && Rules.isBlackOut()) {
					i = 25;
				}
				if (slots[i].getIcon().equals(ImageIcons.black_opposite)) {
					slots[i].setIcon(ImageIcons.black_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.red_opposite)) {
					slots[i].setIcon(ImageIcons.red_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.black_straight)) {
					slots[i].setIcon(ImageIcons.black_yellow_straight);
				} else {
					slots[i].setIcon(ImageIcons.red_yellow_straight);
				}
			}
			if (Dice.isDice2Playable()) { // checks if only dice 2 is playable
				i = 0;
				if (Rules.isWhiteTurn())
					i = source - Dice.getDice2();
				else
					i = source + Dice.getDice2();
				if (source - Dice.getDice2() <= -1 && Rules.isWhiteOut() && Rules.isWhiteTurn()) {
					i = 26;
				} else if (source + Dice.getDice2() >= 24 && Rules.isBlackOut() && !Rules.isWhiteTurn()) {
					i = 25;
				}
				if (slots[i].getIcon().equals(ImageIcons.black_opposite)) {
					slots[i].setIcon(ImageIcons.black_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.red_opposite)) {
					slots[i].setIcon(ImageIcons.red_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.black_straight)) {
					slots[i].setIcon(ImageIcons.black_yellow_straight);
				} else {
					slots[i].setIcon(ImageIcons.red_yellow_straight);
				}
			}
		} else

		{
			if (Dice.isDice1Playable()) { // checks if only dice 1 is playable
				i = 0;
				if (Rules.isWhiteTurn() && source - Dice.getDice1() >= 0)
					i = source - Dice.getDice1();
				else if (!Rules.isWhiteTurn() && source + Dice.getDice1() <= 23)
					i = source + Dice.getDice1();
				if (source - Dice.getDice1() <= -1 && Rules.isWhiteOut()) {
					i = 26;
				} else if (source + Dice.getDice1() >= 24 && Rules.isBlackOut()) {
					i = 25;
				}
				if (slots[i].getIcon().equals(ImageIcons.black_opposite)) {
					slots[i].setIcon(ImageIcons.black_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.red_opposite)) {
					slots[i].setIcon(ImageIcons.red_yellow_opposite);
				} else if (slots[i].getIcon().equals(ImageIcons.black_straight)) {
					slots[i].setIcon(ImageIcons.black_yellow_straight);
				} else {
					slots[i].setIcon(ImageIcons.red_yellow_straight);
				}
			}
			if (Dice.isDice2Playable()) { // checks if only dice 2 is playable
				i = -1;
				if (Rules.isWhiteTurn() && source - Dice.getDice2() >= 0)
					i = source - Dice.getDice2();
				else if (!Rules.isWhiteTurn() && source + Dice.getDice2() <= 23)
					i = source + Dice.getDice2();
				else if ((source - Dice.getDice2() <= -1 || Rules.getMax_pos() <= source) && Rules.isWhiteOut()
						&& Rules.isWhiteTurn()) {
					i = 26;
				} else if ((source + Dice.getDice2() >= 24 || Rules.getMin_pos() >= source) && Rules.isBlackOut()) {
					i = 25;
				}
				if (i != -1) {
					if (slots[i].getIcon().equals(ImageIcons.black_opposite)) {
						slots[i].setIcon(ImageIcons.black_yellow_opposite);
					} else if (slots[i].getIcon().equals(ImageIcons.red_opposite)) {
						slots[i].setIcon(ImageIcons.red_yellow_opposite);
					} else if (slots[i].getIcon().equals(ImageIcons.black_straight)) {
						slots[i].setIcon(ImageIcons.black_yellow_straight);
					} else {
						slots[i].setIcon(ImageIcons.red_yellow_straight);
					}
				}
			}
		}
	}
	
	/**
	 * createLabel method creates a label based on an image and coordinates
	 * 
	 * @param location the location of the Jlabel.
	 * @param image the required image
	 */

	private JLabel createLabel(int location, ImageIcon image) {
		JLabel label = new JLabel(image);
		panel.add(label);
		label.setBounds(Coordinates.board_array[location][0], Coordinates.board_array[location][1],
				Coordinates.board_array[location][2], Coordinates.board_array[location][3]);
		return label;
	}
	
	/**
	 * createLabel method creates a transparent button based on an image and coordinates
	 * 
	 * @param location the location of the JButton.
	 * It also sets the actions that will be taken when clicking on a button. The options are:
	 * Standard slot (do nothing)
	 * Red slot - check where it can play to
	 * Yellow slot - move the player there
	 */

	private JButton createButton(int location) {
		JButton button = new JButton();
		button.setName(Integer.toString(location));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		panel.add(button);
		button.setBounds(Coordinates.board_array[location][0], Coordinates.board_array[location][1],
				Coordinates.board_array[location][2], Coordinates.board_array[location][3]);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(button.getName());
				if ((slots[i].getIcon().equals(ImageIcons.black_red_opposite)
						|| slots[i].getIcon().equals(ImageIcons.black_red_straight)
						|| slots[i].getIcon().equals(ImageIcons.red_red_opposite)
						|| slots[i].getIcon().equals(ImageIcons.red_red_straight)
						|| slots[i].getIcon().equals(ImageIcons.center_red)) && Rules.turnCount != 0) {
					if (i == 24) {
						Rules.checkTopRules(Board.getSlots(), i, Board.getDice());
						setPlayOptions(i, Board.getDice(), Rules.getTurnCount());
						source = i;
					} else {
						Rules.checkRules(Board.getSlots(), i, Board.getDice());
						setPlayOptions(i, Board.getDice(), Rules.getTurnCount());
						source = i;
					}
				} else if (slots[i].getIcon().equals(ImageIcons.black_yellow_opposite)
						|| slots[i].getIcon().equals(ImageIcons.black_yellow_straight)
						|| slots[i].getIcon().equals(ImageIcons.red_yellow_opposite)
						|| slots[i].getIcon().equals(ImageIcons.red_yellow_straight)) {
					slots[source].removeAll();
					slots[source].repaint();
					slots[i].removeAll();
					slots[i].repaint();
					Rules.movePlayer(Board.getSlots(), source, i);
					if (Rules.getTurnCount() != 0) {
						setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
					} else {
						resetBoardColors();
					}

				} else if (Rules.turnCount != 0) {
					setPlayble(Rules.canPlay(Board.getSlots(), Board.getDice()));
				}
			}
		});
		return button;
	}
	
	/**
	 * setBoard method positions all the players in their right positions and colors
	 * 
	 * @param slots_stat the current condition of all board slots.
	 */

	public static void setBoard(Slot[] slots_stat) {
		int control = 0;
		int i = 0;
		for (i = 0; i <= 26; i++) {
			control = 0;
			if (!slots_stat[i].getWhite() && i != 25) {
				control = 12;
			}
			if (!slots_stat[i].isEmpty()) {
				for (int j = slots_stat[i].getCounter() - 1; j >= 0; j--) {
					// for (int j = 0; j <= slots_stat[i].getCounter() - 1; j++) {
					slots[i].repaint();
					slots[i].add(players[i][j + control]);
				}
			} else {
				slots[i].removeAll();
				slots[i].repaint();
			}
		}
	}
	
	/**
	 * resetBoardColors method resets all board slots colors
	 * 
	 */

	public static void resetBoardColors() { // resets all the board colors to default (no reds and yellows)
		for (int i = 0; i < 24; i++) {
			if (i < 12 && i % 2 == 0) {
				slots[i].setIcon(ImageIcons.black_opposite);
			} else if (i < 12 && i % 2 != 0) {
				slots[i].setIcon(ImageIcons.red_opposite);
			} else if (i > 11 && i % 2 == 0) {
				slots[i].setIcon(ImageIcons.black_straight);
			} else {
				slots[i].setIcon(ImageIcons.red_straight);
			}
		}
		slots[24].setIcon(ImageIcons.center);
		slots[25].setIcon(ImageIcons.side);
		slots[26].setIcon(ImageIcons.side);
	}

	public static void setDice(Dice dice) { // Sets the graphic dice according to the random number of the dice
		dices[0].setIcon(ImageIcons.diceIcon(Dice.getDice1()));
		dices[1].setIcon(ImageIcons.diceIcon(Dice.getDice2()));
		if (Rules.isWhiteTurn()) {
			for (int i = 0; i < 2; i++) {
				dices[i].setBounds(Coordinates.dice_location[i][0], Coordinates.dice_location[i][1],
						Coordinates.dice_location[i][2], Coordinates.dice_location[i][3]);
			}
		} else {
			for (int i = 0; i < 2; i++) {
				dices[i].setBounds(Coordinates.dice_location[i + 2][0], Coordinates.dice_location[i + 2][1],
						Coordinates.dice_location[i + 2][2], Coordinates.dice_location[i + 2][3]);
			}
		}
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 830);
		setVisible(true);
		setResizable(false);
	}
}

