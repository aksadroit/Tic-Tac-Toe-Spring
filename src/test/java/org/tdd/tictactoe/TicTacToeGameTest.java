package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tdd.tictactoe.exception.PositionAlreadyOccupiedException;
import org.tdd.tictactoe.exception.PositionOutOfBoundException;
import org.tdd.tictactoe.model.Position;

@SpringBootTest
public class TicTacToeGameTest {

	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	private static final int POS_2 = 2;
	
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	private static final String GAME_CONTINUE = "Continue..!!";
	private static final String GAME_WINNER_PLAYER_X = "Winner is Player_X";
	private static final String GAME_WINNER_PLAYER_O = "Winner is Player_O";
	private static final String GAME_DRAW = "It's a Draw Game";
	
	@MockBean
    private GameBoard board;
	
	@Autowired
	private TicTacToeGame game;
	
	@Test
	public void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() throws PositionAlreadyOccupiedException, PositionOutOfBoundException {
		Position pos1 = new Position(POS_1, POS_1);
		
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		Mockito.when(board.identifyPlayerAt(pos1)).thenReturn(PLAYER_X);
		assertEquals(PLAYER_X, game.identifyPlayerAt(pos1));
	}
	
	@Test
	public void alternativelySwitchBetweenPlayers() throws PositionAlreadyOccupiedException, PositionOutOfBoundException {
		Position pos1 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		
		Position pos2 = new Position(POS_0, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.identifyPlayerAt(pos2)).thenReturn(PLAYER_O);
		game.play(pos2);
		assertEquals(PLAYER_O, game.identifyPlayerAt(pos2));
	}
	
	@Test
	public void shouldThrowExceptionIfPositionIsAlreadyOccupied() throws PositionAlreadyOccupiedException, PositionOutOfBoundException {
		Position pos1 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		
		Position pos2 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(false);
		assertThrows(PositionAlreadyOccupiedException.class, () -> game.play(pos2));
	}
	
	@Test
	public void shouldThrowExceptionIfPositionNotInValidRange() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		
		Position pos2 = new Position(POS_1, 3);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(false);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		assertThrows(PositionOutOfBoundException.class, () -> game.play(pos2));
	}
	
	@Test
	public void shouldReturnContinueIfAnyPositionAvailableForPlayer() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		assertEquals(GAME_CONTINUE, game.play(pos1));	
	}
	
	@Test
	public void declareThePlayerAsWinnerIfThePlayerCompletelyOccupiesAnyRow() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos1);
		
		Position pos2 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos2);
		
		Position pos3 = new Position(POS_0, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos3)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos3)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos3);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos4)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos4)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos4);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos4);
		
		Position pos5 = new Position(POS_0, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos5)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos5)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos5);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(true);
		Mockito.when(board.identifyPlayerAt(pos5)).thenReturn(PLAYER_X);
		
		assertEquals(GAME_WINNER_PLAYER_X, game.play(pos5));

	}
	
	@Test
	public void declareThePlayerAsWinnerIfThePlayerCompletelyOccupiesAnyColumn() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos1);
		
		Position pos2 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos2);
		
		Position pos3 = new Position(POS_1, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos3)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos3)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos3);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos4)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos4)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos4);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos4);
		
		Position pos5 = new Position(POS_2, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos5)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos5)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos5);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(true);
		Mockito.when(board.identifyPlayerAt(pos5)).thenReturn(PLAYER_X);
		
		assertEquals(GAME_WINNER_PLAYER_X, game.play(pos5));

	}
	
	@Test
	public void declareThePlayerAsWinnerIfThePlayerCompletelyOccupiesAnyTopRightToBottonLeftDiagonal() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos1);
		
		Position pos2 = new Position(POS_2, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos2);
		
		Position pos3 = new Position(POS_1, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos3)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos3)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos3);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos3);
		
		Position pos4 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos4)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos4)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos4);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos4);
		
		Position pos5 = new Position(POS_1, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos5)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos5)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos5);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		game.play(pos5);
		
		Position pos6 = new Position(POS_0, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos6)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos6)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos6);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(true);
		Mockito.when(board.identifyPlayerAt(pos6)).thenReturn(PLAYER_O);
		
		assertEquals(GAME_WINNER_PLAYER_O, game.play(pos6));

	}
	
	@Test
	public void declareThePlayerAsWinnerIfThePlayerCompletelyOccupiesAnyTopLeftToBottonRightDiagonal() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos1);
		
		Position pos2 = new Position(POS_0, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos2);
		
		Position pos3 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos3)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos3)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos3);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos3);
		
		Position pos4 = new Position(POS_2, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos4)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos4)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos4);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos4);
		
		Position pos5 = new Position(POS_2, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos5)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos5)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos5);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(true);
		Mockito.when(board.identifyPlayerAt(pos5)).thenReturn(PLAYER_X);
		
		assertEquals(GAME_WINNER_PLAYER_X, game.play(pos5));

	}
	
	@Test
	public void declareTheGameAsDrawIfAllPositionsOnBoardAreOccupied() throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		Position pos1 = new Position(POS_0, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos1)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos1)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos1);
		
		Position pos2 = new Position(POS_0, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos2)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos2)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos2);
		
		Position pos3 = new Position(POS_0, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos3)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos3)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos3);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos3);
		
		Position pos4 = new Position(POS_1, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos4)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos4)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos4);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(false);
		game.play(pos4);
		
		Position pos5 = new Position(POS_1, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos5)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos5)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos5);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		game.play(pos5);
		
		Position pos6 = new Position(POS_1, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos6)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos6)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos6);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		game.play(pos6);
		
		Position pos7 = new Position(POS_2, POS_1);
		Mockito.when(board.isPositionWithinValidRange(pos7)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos7)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos7);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		game.play(pos7);
		
		Position pos8 = new Position(POS_2, POS_0);
		Mockito.when(board.isPositionWithinValidRange(pos8)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos8)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos8);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		game.play(pos8);
		
		Position pos9 = new Position(POS_2, POS_2);
		Mockito.when(board.isPositionWithinValidRange(pos9)).thenReturn(true);
		Mockito.when(board.isPositionAvailable(pos9)).thenReturn(true);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos9);
		Mockito.when(board.isAnyRowOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isAnyColumnOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer()).thenReturn(false);
		Mockito.when(board.areAllPositionOnBoardFullyOccupiedByPlayers()).thenReturn(true);
		game.play(pos9);
		
		assertEquals(GAME_DRAW, game.play(pos9));

	}

}
