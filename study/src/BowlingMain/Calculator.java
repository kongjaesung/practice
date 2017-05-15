package BowlingMain;

public class Calculator {
	public int thisIndex = 0;
	public int sum = 0;
	
	/*
	 * calculate Score 
	 */
	public void calcScore(Frame [] frame, int [] accumScore, int frameNum) {
		while (thisIndex <= frameNum) {
			// Optimised by jwlee
			int basic = addFirstSecond(frame[thisIndex]);
			int bonus = 0;

			if (frame[thisIndex].isStrike())
				bonus = strikeBonus(frame, thisIndex, frameNum);
			if (frame[thisIndex].isSpare())
				bonus = spareBonus(frame, thisIndex, frameNum);

			if (bonus == -1)
				return;

			sum += basic + bonus;
			accumScore[thisIndex] = sum;
			thisIndex++;
		}
	}
	
	/*
	 * In case "spare", calculate bonus score
	 */
	private int spareBonus(Frame [] frame, int thisIndex, int frameNum) {
		if (thisIndex < 9 && thisIndex + 1 > frameNum)
			return -1;
		
		return frame[thisIndex + 1].first;
	}
	
	/*
	 * In case "strike", calculate bonus score 
	 */
	private int strikeBonus(Frame [] frame, int thisIndex, int frameNum) {
		if (thisIndex < 9 && thisIndex + 1 > frameNum)
			return -1;
		
		if (!frame[thisIndex + 1].isStrike())
			return addFirstSecond(frame[thisIndex + 1]);
		
		if (thisIndex == 9)
			return 10 + frame[thisIndex + 1].second;
		
		if (thisIndex < 8 && thisIndex + 2 > frameNum)
			return -1;

		return 10 + frame[thisIndex + 2].first;
	}
	
	private int addFirstSecond(Frame frame) {
		return frame.first + frame.second;
	}
}
