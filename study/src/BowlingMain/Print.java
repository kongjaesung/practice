package BowlingMain;

public class Print {
	
	/*
	 * print header of board
	 */
	public void basicBoard() {
		System.out.println("=====================================================================");
		System.out.println("|     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   10  |");	
	}
	
	/*
	 * print score of board
	 */
	public void scoreBoard(Player [] player, int playerNum, int frameNum) {
		for (int i = 0; i < player.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			sb.append("  " + (i + 1) + "  ");
			sb.append("|");
			
			for ( int j = 0; j < 9; j++) {
				int first = player[i].frame[j].first;
				int second = player[i].frame[j].second;
				
				if (j < frameNum || (j == frameNum && i <= playerNum) ) {
					sb.append(frameScore(first, second) + "|");
				}
				else {
					sb.append("     |");
				}
			}
			
			String first = " ";
			String second = " ";
			String third = " ";
			if (frameNum == 9 && i <= playerNum) {
				if (player[i].frame[9].isOpen()) {
					first = frameScore(player[i].frame[9].first);
					second = frameScore(player[i].frame[9].second);
				}
	
				if (player[i].frame[9].isSpare()) {
					first = frameScore(player[i].frame[9].first);
					second = frameScore(player[i].frame[9].second);
					third = frameScore(player[i].frame[10].first);
				}
						
				if (player[i].frame[9].isStrike()) {
					first = frameScore(player[i].frame[9].first);
					second = frameScore(player[i].frame[10].first);
					third = frameScore(player[i].frame[10].second);
				}
					
				sb.append(" " + first + " " + second + " " + third + " |");
			}
			else 
				sb.append("       |");
			
			System.out.println("---------------------------------------------------------------------");
			System.out.println(sb.toString());
			
			System.out.print("|     |");
			for ( int f = 0; f < 9; f++ ) {
				if ( player[i].accumScore[f] == -1 )
					System.out.print("     |");
				else
					System.out.printf(" %3d |", player[i].accumScore[f]);
			}
			if ( player[i].accumScore[9] == -1 )
				System.out.print("       |");
			else
				System.out.printf("  %3d  |", player[i].accumScore[9]);
		
			System.out.println();
		}
		System.out.println("=====================================================================");
	}
	
	/*
	 * make frame score to string
	 */
	public String frameScore(int first, int second)
	{
		if ( first == 10 )
			return " " + frameScore(first) + "   ";
		
		if ( first + second == 10 )
			return " " + frameScore(first) + " " + "/" + " ";

		return " " + frameScore(first) + " " + frameScore(second) + " ";
	}

	/*
	 * make frame each score to char
	 */
	public String frameScore(int num) {
		if (num == 0)
			return "-";
		else if (num == 10)
			return "X";
		else
			return Integer.toString(num);
	}
}
