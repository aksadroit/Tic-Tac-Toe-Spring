package org.tdd.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class TicTacToeGame {

	private char[][] gameBoard = new char[3][3];
	
	public void placeMoveOnTheBoard(int i, int j, char c) {
		gameBoard[i][j] = c;
	}

	public char identifyPlayerAt(int i, int j) {
		char player;
		player = gameBoard[i][j];
		return player;
	}

}
