package BowlingMain;

import java.util.Scanner;

public class Game {
	public Player [] player;
	private Scanner a2;
	private Scanner a1;	int []a;
	
	/*
	 * 1) determine player number
	 * 2) input frame score (first point, second point)
	 * 3) calculate score
	 * 4) print scoreboard
	 */
	public void play() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("************ Welcome to < B O W L I N G _ G A M E > ************");
		System.out.print("Input Player Number : ");
		int playerCount = scanner.nextInt();
		
		createPlayer(playerCount);
		
		for ( int frameNum = 0; frameNum < 10; frameNum++) {
			System.out.println("Now is frame " + (frameNum + 1) + ".");
			for ( int playerNum = 0; playerNum < playerCount; playerNum++) {
				System.out.println("-----------------------");
				System.out.println("Player " + (playerNum + 1) + " turn.");
				inputScore(scanner, playerNum, frameNum);
				System.out.println("-----------------------");
				
				player[playerNum].calcScore(frameNum);
				
				Print print = new Print();
				print.basicBoard();
				print.scoreBoard(player, playerNum, frameNum);
			}
			
		}
		for(int i=0; i<playerCount-1; i++)
			for(int j=0; j<playerCount-1; j++){
				if(player[j].accumScore[9] < player[j+1].accumScore[9]){
					int tmp = player[j].accumScore[9];
					player[j].accumScore[9] = player[j+1].accumScore[9];
					player[j+1].accumScore[9] = tmp;
					int abc = a[j];
					a[j] = a[j+1];
					a[j+1] = abc;
				}
			}
		for(int i=0; i<playerCount; i++)
			System.out.println((i+1)+ "등 : "+ "player" +(a[i])+" : " + player[i].accumScore[9] + "점");
					
		scanner.close();
	}

	/*
	 * input score
	 */
	private void inputScore(Scanner scanner, int playerNum, int frameNum) {
		Frame frame = player[playerNum].frame[frameNum];
		int count = 0;	a1 = new Scanner(System.in);
		a2 = new Scanner(System.in);
		do {
			if(count>=1)
				System.out.println("10이 초과되었습니다 다시 입력하시오.");
			System.out.print("first score : ");
			int first = a2.nextInt();
			frame.first = first;
			if(frame.first<=10){
			System.out.println("수정하시겠습니까? ( 예 : y 아니오 : n)");
			String revise = a1.nextLine();
			if(revise.equals("y")){
				System.out.println("숫자를 다시 입력하시오");
				frame.first = 11;
				continue;
			}
			else if(revise.equals("n")) break;
			}
			count++;
		} while (frame.first > 10);	
		
		
			
		if (frame.first != 10) {
			do {
				System.out.print("second score : ");
				int second = scanner.nextInt();
				frame.second = second;
				if(frame.second>10)
					System.out.println("10이 초과되었습니다 다시 입력하시오.");
			} while (frame.second > 10);
		}
	
		
		// Input three score
		if (frameNum == 9){
			if (frame.isSpare()) {
				System.out.print("third score : ");
				int third = scanner.nextInt();
				player[playerNum].frame[10].first = third;
			}
			else if (frame.isStrike()) {
				System.out.print("second score : ");
				int second = scanner.nextInt();
				player[playerNum].frame[10].first = second;
				
				System.out.print("third score : ");
				int third = scanner.nextInt();
				player[playerNum].frame[10].second = third;
			}
		}
	}
	

	/*
	 * create players
	 */
	private void createPlayer(int playerCount) {
		player = new Player [playerCount];
		for ( int i = 0; i < playerCount; i++ ) {
			player[i] = new Player();
		}
		a = new int [playerCount];
		for ( int i = 0; i <playerCount; i++)
			a[i] = (i+1) ;
	}
}
