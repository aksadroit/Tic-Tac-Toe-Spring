package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeGameTest {

	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	private TicTacToeGame game;
	
	@BeforeEach
	void init() {
		game = new TicTacToeGame();
	}
	
	@Test
	void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() {
		game.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, game.identifyPlayerAt(POS_1, POS_1));
	}
	
	@Test
	void alternativelySwitchBetweenPlayers() {
		game.placeMoveOnTheBoard(POS_1, POS_1);
		assertEquals(PLAYER_X, game.identifyPlayerAt(POS_1, POS_1));
		game.placeMoveOnTheBoard(POS_0, POS_1);
		assertEquals(PLAYER_O, game.identifyPlayerAt(POS_0, POS_1));
	}
	
	

}
