package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeGameTest {

	@Test
	void playerXShouldBeAbleToMakeMoveInAnyPositionOnTheBoardAndIdentifyTheSame() {
		TicTacToeGame game = new TicTacToeGame();
		game.placeMoveOnTheBoard(1, 1, 'X');
		assertEquals('X', game.identifyPlayerAt(1, 1));
	}

}
