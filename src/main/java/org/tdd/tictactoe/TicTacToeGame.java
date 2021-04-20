package org.tdd.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class TicTacToeGame {

	private static final int GAME_BOARD_SIZE = 3;
	
	private char[][] gameBoard = new char[GAME_BOARD_SIZE][GAME_BOARD_SIZE];
	
	public void placeMoveOnTheBoard(int row, int column, char player) {
		gameBoard[row][column] = player;
	}

	public char identifyPlayerAt(int row, int column) {
		return gameBoard[row][column];
	}

}
