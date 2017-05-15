package BowlingMain;

public class Frame {
	public int first;
	public int second;
	
	/*
	 * check strike
	 */
	public boolean isStrike() {
		if (first == 10)
			return true;
		else
			return false;
	}
	
	/*
	 * check spare
	 */
	public boolean isSpare() {
		if (first + second == 10 && first != 10) 
			return true;
		else
			return false;
	}
	
	/*
	 * check open
	 */
	public boolean isOpen() {
		if (first + second < 10)
			return true;
		else
			return false;
	}
}
