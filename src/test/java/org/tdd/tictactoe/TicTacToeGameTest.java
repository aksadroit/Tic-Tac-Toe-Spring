package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
		Mockito.doNothing().when(board).placeMoveOnTheBoard(POS_1, POS_1);
		game.play(POS_1, POS_1);
		Mockito.when(board.identifyPlayerAt(POS_1, POS_1)).thenReturn(PLAYER_X);
		assertEquals(PLAYER_X, game.identifyPlayerAt(POS_1, POS_1));
	}
	
	@Test
	public void alternativelySwitchBetweenPlayers() {
		Mockito.doNothing().when(board).placeMoveOnTheBoard(POS_1, POS_1);
		game.play(POS_1, POS_1);
		
		Mockito.doNothing().when(board).placeMoveOnTheBoard(POS_0, POS_1);
		Mockito.when(board.identifyPlayerAt(POS_0, POS_1)).thenReturn(PLAYER_O);
		game.play(POS_0, POS_1);
		assertEquals(PLAYER_O, game.identifyPlayerAt(POS_0, POS_1));
	}
	
	

}
