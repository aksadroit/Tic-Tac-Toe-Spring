package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tdd.tictactoe.model.Position;

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
		Position pos1 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos1);
		assertEquals(PLAYER_X, board.identifyPlayerAt(pos1));
	}
	
	@Test
	public void alternativelySwitchBetweenPlayers() {
		Position pos1 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos1);
		assertEquals(PLAYER_X, board.identifyPlayerAt(pos1));
		
		Position pos2 = new Position(POS_0, POS_1);
		board.placeMoveOnTheBoard(pos2);
		assertEquals(PLAYER_O, board.identifyPlayerAt(pos2));
	}
	
	@Test
	public void checkIfPositionIsAvailableToPlay() {
		Position position = new Position(0, 2);
		assertTrue(board.isPositionAvailable(position));
	}
	
	

}
