package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tdd.tictactoe.model.Position;

@SpringBootTest
public class TicTacToeGameTest {

	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	@MockBean
    private GameBoard board;
	
	@Autowired
	private TicTacToeGame game;
	
	@Test
	public void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() {
		Position pos1 = new Position(POS_1, POS_1);
		
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		Mockito.when(board.identifyPlayerAt(pos1)).thenReturn(PLAYER_X);
		assertEquals(PLAYER_X, game.identifyPlayerAt(pos1));
	}
	
	@Test
	public void alternativelySwitchBetweenPlayers() {
		Position pos1 = new Position(POS_1, POS_1);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos1);
		game.play(pos1);
		
		Position pos2 = new Position(POS_0, POS_1);
		Mockito.doNothing().when(board).placeMoveOnTheBoard(pos2);
		Mockito.when(board.identifyPlayerAt(pos2)).thenReturn(PLAYER_O);
		game.play(pos2);
		assertEquals(PLAYER_O, game.identifyPlayerAt(pos2));
	}
	
	

}
