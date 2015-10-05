package lol.hi;

import static org.junit.Assert.*;

import org.gradle.TicTacToe;
import org.junit.Test;

public class TicTacToeTest {
	
	private TicTacToe ttt = new TicTacToe();
	
	@Test
	public void testBoardInitiallyEmpty() {
		assertEquals(getNumberOfFieldsOfType(ttt.EMPTY), ttt.getBoard().length * ttt.getBoard()[0].length);
	}
	
	@Test
	public void testStartAITurn() {
		ttt.setPlayersTurnNext(false);
		for(int i=0; i < ttt.getBoard().length; i++) {
			int aiFieldsBefore = getNumberOfFieldsOfType(ttt.AI);
			int emptyFieldsBefore = getNumberOfFieldsOfType(ttt.EMPTY);
			ttt.startAITurn();
			assertEquals(aiFieldsBefore+1, getNumberOfFieldsOfType(ttt.AI));
			assertEquals(emptyFieldsBefore-1, getNumberOfFieldsOfType(ttt.EMPTY));
		}
	}
	
	@Test
	public void testIsATie() {
		char[][] board = {
				{ttt.AI, ttt.PLAYER, ttt.PLAYER},
				{ttt.AI, ttt.PLAYER, ttt.AI},
				{ttt.PLAYER, ttt.AI, ttt.AI}
		};
		ttt.setBoard(board);
		assertTrue(ttt.isATie());
	}
	
	@Test
	public void testVictorDecidedRowVictory() {
		char[][] board = {
				{ttt.EMPTY, ttt.PLAYER, ttt.PLAYER},
				{ttt.AI, ttt.AI, ttt.AI},
				{ttt.PLAYER, ttt.PLAYER, ttt.AI}
		};
		ttt.setBoard(board);
		assertTrue(ttt.victorDecided());
	}
	
	@Test
	public void testVictorDecidedColumnVictory() {
		char[][] board = {
				{ttt.EMPTY, ttt.AI, ttt.PLAYER},
				{ttt.AI, ttt.AI, ttt.PLAYER},
				{ttt.AI, ttt.PLAYER, ttt.PLAYER}
		};
		ttt.setBoard(board);
		assertTrue(ttt.victorDecided());
	}
	
	@Test
	public void testVictorDecidedDiagonal1Victory() {
		char[][] board = {
				{ttt.AI, ttt.PLAYER, ttt.EMPTY},
				{ttt.PLAYER, ttt.AI, ttt.AI},
				{ttt.PLAYER, ttt.PLAYER, ttt.AI}
		};
		ttt.setBoard(board);
		assertTrue(ttt.victorDecided());
	}
	
	@Test
	public void testVictorDecidedDiagonal2Victory() {
		char[][] board = {
				{ttt.AI, ttt.PLAYER, ttt.PLAYER},
				{ttt.PLAYER, ttt.PLAYER, ttt.AI},
				{ttt.PLAYER, ttt.AI, ttt.AI}
		};
		ttt.setBoard(board);
		assertTrue(ttt.victorDecided());
	}
	
	@Test
	public void testVictorDecidedWithNoVictor() {
		char[][] board = {
				{ttt.EMPTY, ttt.PLAYER, ttt.EMPTY},
				{ttt.EMPTY, ttt.EMPTY, ttt.AI},
				{ttt.PLAYER, ttt.EMPTY, ttt.AI}
		};
		ttt.setBoard(board);
		assertFalse(ttt.victorDecided());
	}
	
	private int getNumberOfFieldsOfType(char type) {
		int emptyFields = 0;
		for(char[] ca : ttt.getBoard())
			for(char c : ca)
				if(c == type)
					emptyFields++;
		return emptyFields;
	}
}
