package org.tdd.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class GameBoard {
	
	private static final int GAME_BOARD_SIZE = 3;
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	private char[][] board = new char[GAME_BOARD_SIZE][GAME_BOARD_SIZE];
	private char currentPlayer;
	
	public void placeMoveOnTheBoard(int row, int column) {
		currentPlayer = identifyNextPlayer();
		board[row][column] = currentPlayer;
	}

	public char identifyPlayerAt(int row, int column) {
		return board[row][column];
	}
	
	public char identifyNextPlayer() {
		return currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
	}
	
}
