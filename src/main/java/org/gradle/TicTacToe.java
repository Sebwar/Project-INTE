package org.gradle;

import java.util.Scanner;

public class TicTacToe {
	
	public final char EMPTY = '-', PLAYER = 'X', AI = 'O';
	private char[][] board = {
			{EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY}};
	private boolean playersTurnNext;
	private Scanner scanner = new Scanner(System.in);
	
	public void determineWhoMakesFirstMove() {
		if(Math.random() < .5) {
			playersTurnNext = true;
			System.out.println("You have randomly been selected to make the first move.");
		}
		else {
			playersTurnNext = false;  //Not necessary since default is false. Just for clarity.
			System.out.println("The AI has randomly been selected to make the first move.");
		}
	}
	
	public void startAITurn() {
		int row, col;
		do {
			row = (int)(Math.random() * (2 + 1));
			col = (int)(Math.random() * (2 + 1));
		}
		while(board[row][col] != EMPTY);
		board[row][col] = AI;
		playersTurnNext = true;
	}
	
	public void startPlayerTurn() {
		printBoard();
		int input = getPlayerInput();
		int[] rowAndCol = translateNumIntoRowAndCol(input);
		board[rowAndCol[0]][rowAndCol[1]] = PLAYER;
		playersTurnNext = false;
	}
	
	public boolean isATie() {
		for(int row=0; row < board.length; row++)
			for(int col=0; col < board[0].length; col++)
				if(board[row][col] == EMPTY) return false;
		printBoard();
		System.out.println("The game is a tie.");
		return true;
	}
	
	public boolean victorDecided() {
		//Rows & Columns
		String rowResults = "", columnResults = "";
		for(int i=0; i < board.length; i++) {
			for(int j=0; j < board.length; j++) {
				rowResults += board[i][j];
				columnResults += board[j][i];
			}
			if(analyzeResults(rowResults) || analyzeResults(columnResults)) return true;
			else rowResults = columnResults = "";
		}
		//Diagonals
		String diagonal1Results = "", diagonal2Results = "";
		for(int i=0; i < board.length; i++) {
			diagonal1Results += board[i][i];
			diagonal2Results += board[i][board.length-(i+1)];
		}
		if(analyzeResults(diagonal1Results) || analyzeResults(diagonal2Results)) return true;
		return false;
	}
	
	public void dispose() {scanner.close();}
	
	private boolean analyzeResults(String results) {
		String playerVictory = "", aiVictory = "";
		for(int i=0; i < board.length; i++) {
			playerVictory += PLAYER;
			aiVictory += AI;
		}
		if(results.equals(playerVictory)) {
			printBoard();
			System.out.println("Player has won.");
			return true;
		}
		if(results.equals(aiVictory)) {
			printBoard();
			System.out.println("The AI has won.");
			return true;
		}
		else return false;
	}
	
	private int getPlayerInput() {
		System.out.println("Choose a cell from 1 to 9 (numpad layout):");
		int input = 0;
		String errorMessage;
		do {
			String s = scanner.next();
			errorMessage = "";
			try {
				input = Integer.parseInt(s);
			}
			catch(NumberFormatException e) {
				errorMessage += "Invalid input, only digits are allowed.";
			}
			if(errorMessage.length() == 0 && input < 1 || input > 9)
				errorMessage += "Invalid input, has to be a digit from 1 to 9.";
			if(errorMessage.length() == 0) {
				int[] rowAndCol = translateNumIntoRowAndCol(input);
				if(board[rowAndCol[0]][rowAndCol[1]] != EMPTY)
					errorMessage += "The selected cell is not empty.";
			}
			if(errorMessage.length() > 0)
				System.out.println(errorMessage);
		}
		while(errorMessage.length() > 0);
		return input;
	}
	
	private void printBoard() {
		for(int row=0; row < board.length; row++) {
			String rowString = "";
			for(int col=0; col < board[0].length; col++) {
				rowString += board[row][col] + ", ";
			}
			System.out.println(rowString.substring(0, rowString.length()-2));
		}
	}
	
	private int[] translateNumIntoRowAndCol(int input) {
		int row = board.length - ((int)Math.ceil(input/(board.length*1f) - 1) + 1);
		int col = input % board.length - 1;
		if(col == -1) col = board.length - 1;
		return new int[] {row, col};
	}
	
	//Most getters and setters are never used outside of testing.
	public char[][] getBoard() {
		return board;
	}
	public void setBoard(char[][] board) {
		this.board = board;
	}
	public boolean isPlayersTurnNext() {
		return playersTurnNext;
	}
	public void setPlayersTurnNext(boolean b) {
		playersTurnNext = b;
	}
	
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		ttt.determineWhoMakesFirstMove();
		while(!ttt.victorDecided() && !ttt.isATie())
			if(ttt.isPlayersTurnNext()) ttt.startPlayerTurn();
			else ttt.startAITurn();
		ttt.dispose();
	}
}
