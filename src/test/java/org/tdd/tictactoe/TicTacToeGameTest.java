package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeGameTest {

	private static final int POS_1 = 1;
	private static final char PLAYER_X = 'X';
	
	@Test
	void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() {
		TicTacToeGame game = new TicTacToeGame();
		game.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, game.identifyPlayerAt(POS_1, POS_1));
	}
	
	@Test
	void alternativelySwitchBetweenPlayers() {
		TicTacToeGame game = new TicTacToeGame();
		game.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, game.identifyPlayerAt(POS_1, POS_1));
		game.placeMoveOnTheBoard(0, POS_1);
		assertEquals('O', game.identifyPlayerAt(0, POS_1));
	}
	
	

}
