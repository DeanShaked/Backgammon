package Backgammon;

/**
 * This class holds the board coordinates for all the GUI components (Slots, Players, Dice)
 */

public class Coordinates {

	static int[][] board_array = {
			//bottom right
			{720, 506, 62, 275}, 
			{658, 506, 62, 275},
			{595, 506, 62, 275},
			{534, 506, 62, 275},
			{472, 506, 62, 275},
			{410, 506, 62, 275},
			//bottom left
			{326, 506, 62, 275},
			{264, 506, 62, 275},
			{202, 506, 62, 275},
			{140, 506, 62, 275},
			{78, 506, 62, 275},
			{14, 506, 62, 275},
			//top left
			{14, 14, 62, 275},
			{78, 14, 62, 275},
			{140, 14, 62, 275},
			{202, 14, 62, 275},
			{264, 14, 62, 275},
			{326, 14, 62, 275},
			//top right
			{410, 14, 62, 275},
			{472, 14, 62, 275},
			{534, 14, 62, 275},
			{595, 14, 62, 275},
			{658, 14, 62, 275},
			{720, 14, 62, 275},
			//middle
			{368, 254, 62, 277},
			//out
			{810, 14, 62, 275},
			{810, 506, 62, 275}
		};

	static int[][] top_player_locations= {
			{0,0,55,55},
			{0,55,55,55},
			{0,110,55,55},
			{0,165,55,55},
			{0,220,55,55},
			{0,22,55,55},
			{0,77,55,55},
			{0,132,55,55},
			{0,187,55,55},
			{0,55,55,55},
			{0,110,55,55},
			{0,165,55,55}
	};
	static int[][] buttom_player_locations= {
			{0,220,55,55},
			{0,165,55,55},
			{0,110,55,55},
			{0,55,55,55},
			{0,0,55,55},
			{0,187,55,55},
			{0,132,55,55},
			{0,77,55,55},
			{0,22,55,55},
			{0,165,55,55},
			{0,110,55,55},
			{0,55,55,55}
	};
	
	static int[][] side_player_locations= {
			{1,1,60,15},
			{1,19,60,15},
			{1,37,60,15},
			{1,55,60,15},
			{1,72,60,15},
			{1,90,60,15},
			{1,108,60,15},
			{1,126,60,15},
			{1,144,60,15},
			{1,162,60,15},
			{1,180,60,15},
			{1,198,60,15},
			{1,216,60,15},
			{1,234,60,15},
			{1,252,60,15}
	};
	
	static int[][] center_player_locations= {
			{0,220,55,55},
			{0,165,55,55},
			{0,110,55,55},
			{0,55,55,55},
			{0,0,55,55},
			{0,187,55,55},
			{0,132,55,55},
			{0,77,55,55},
			{0,22,55,55},
			{0,165,55,55},
			{0,110,55,55},
			{0,55,55,55}
	};
	
	static int[][] dice_location= {
			{150,375,45,45},
			{210,375,45,45},
			{550,375,45,45},
			{610,375,45,45}
	};
	
	
}