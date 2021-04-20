package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tdd.tictactoe.model.Position;

public class GameBoardTest {

	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	private static final int POS_2 = 2;
	
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
		Position position = new Position(POS_0, POS_2);
		assertTrue(board.isPositionAvailable(position));
	}
	
	@Test
	public void shouldReturnTrueWhenInputPositionValuesAreInValidRangeOfZeroToTwo() {
		Position validPosition = new Position(POS_0, POS_0);
		assertTrue(board.isPositionWithinValidRange(validPosition));
	}
	
	@Test
	public void shouldReturnTrueWhenAnyRowOfTheBoardIsOccupiedBySinglePlayer() {
		Position pos1 = new Position(POS_0, POS_0);
		board.placeMoveOnTheBoard(pos1);
		
		Position pos2 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos2);
		
		Position pos3 = new Position(POS_0, POS_1);
		board.placeMoveOnTheBoard(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		board.placeMoveOnTheBoard(pos4);
		
		Position pos5 = new Position(POS_0, POS_2);
		board.placeMoveOnTheBoard(pos5);
		
		assertTrue(board.isAnyRowOccupiedBySinglePlayer());
	}
	
	@Test
	public void shouldReturnTrueWhenAnyColumnOfTheBoardIsOccupiedBySinglePlayer() {
		Position pos1 = new Position(POS_0, POS_0);
		board.placeMoveOnTheBoard(pos1);
		
		Position pos2 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos2);
		
		Position pos3 = new Position(POS_1, POS_0);
		board.placeMoveOnTheBoard(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		board.placeMoveOnTheBoard(pos4);
		
		Position pos5 = new Position(POS_2, POS_0);
		board.placeMoveOnTheBoard(pos5);
		
		assertTrue(board.isAnyColumnOccupiedBySinglePlayer());
	}
	
	@Test
	public void shouldReturnTrueWhenDiagonalFromTopLeftToBottomRightOfTheBoardIsOccupiedBySinglePlayer() {
		Position pos1 = new Position(POS_0, POS_0);
		board.placeMoveOnTheBoard(pos1);
		
		Position pos2 = new Position(POS_0, POS_1);
		board.placeMoveOnTheBoard(pos2);
		
		Position pos3 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		board.placeMoveOnTheBoard(pos4);
		
		Position pos5 = new Position(POS_2, POS_2);
		board.placeMoveOnTheBoard(pos5);
		
		assertTrue(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer());
	}
	
	@Test
	public void shouldReturnTrueWhenDiagonalFromTopRightToBottomLeftOfTheBoardIsOccupiedBySinglePlayer() {
		Position pos1 = new Position(POS_0, POS_0);
		board.placeMoveOnTheBoard(pos1);
		
		Position pos2 = new Position(POS_2, POS_0);
		board.placeMoveOnTheBoard(pos2);
		
		Position pos3 = new Position(POS_1, POS_0);
		board.placeMoveOnTheBoard(pos3);
		
		Position pos4 = new Position(POS_1, POS_1);
		board.placeMoveOnTheBoard(pos4);
		
		Position pos5 = new Position(POS_1, POS_2);
		board.placeMoveOnTheBoard(pos5);
		
		Position pos6 = new Position(POS_0, POS_2);
		board.placeMoveOnTheBoard(pos6);
		
		assertTrue(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer());
	}

}
