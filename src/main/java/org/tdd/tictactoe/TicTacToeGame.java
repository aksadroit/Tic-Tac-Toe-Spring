package org.tdd.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class TicTacToeGame {

	private char[][] gameBoard = new char[3][3];
	
	public void placeMoveOnTheBoard(int row, int column, char player) {
		gameBoard[row][column] = player;
	}

	public char identifyPlayerAt(int row, int column) {
		char player;
		player = gameBoard[row][column];
		return player;
	}

}
