package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	private GameBoard board;
	
	@BeforeEach
	public void init() {
		board = new GameBoard();
	}
	
	@Test
	public void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() {
		board.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, board.identifyPlayerAt(POS_1, POS_1));
	}
	
	@Test
	public void alternativelySwitchBetweenPlayers() {
		board.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, board.identifyPlayerAt(POS_1, POS_1));
		board.placeMoveOnTheBoard(POS_0, POS_1);
		assertEquals(PLAYER_O, board.identifyPlayerAt(POS_0, POS_1));
	}
	
	

}
