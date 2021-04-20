package org.tdd.tictactoe;

import org.springframework.stereotype.Component;
import org.tdd.tictactoe.model.Position;

@Component
public class GameBoard {
	
	private static final int GAME_BOARD_SIZE = 3;
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	
	private char[][] board = new char[GAME_BOARD_SIZE][GAME_BOARD_SIZE];
	private char currentPlayer;
	
	public void placeMoveOnTheBoard(Position position) {
		currentPlayer = identifyNextPlayer();
		board[position.getRow()][position.getColumn()] = currentPlayer;
	}

	public char identifyPlayerAt(Position position) {
		return board[position.getRow()][position.getColumn()];
	}
	
	public char identifyNextPlayer() {
		return currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
	}
	
}
