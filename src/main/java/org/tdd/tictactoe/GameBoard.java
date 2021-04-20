package org.tdd.tictactoe;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.tdd.tictactoe.model.Position;

@Component
public class GameBoard {
	
	private static final int GAME_BOARD_SIZE = 3;
	private static final int GAME_BOARD_LOWER_LIMIT = 0;
	private static final int GAME_BOARD_UPPER_LIMIT = 2;
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private static final char EMPTY = '\0';
	
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

	public boolean isPositionAvailable(Position position) {
		Predicate<Position> positionNotOccupiedPredicate =  pos -> board[pos.getRow()][pos.getColumn()] == EMPTY;
		return positionNotOccupiedPredicate.test(position);
	}

	public boolean isPositionWithinValidRange(Position validPosition) {
		Predicate<Position> positionRangePredicate =  pos -> pos.getRow() >= GAME_BOARD_LOWER_LIMIT && pos.getRow() <= GAME_BOARD_UPPER_LIMIT && pos.getColumn() >= GAME_BOARD_LOWER_LIMIT && pos.getColumn() <= GAME_BOARD_UPPER_LIMIT;
		return positionRangePredicate.test(validPosition);
	}

	public boolean isAnyRowOccupiedBySinglePlayer() {
		boolean isRowOccupied = false;
		for (int i = 0; i < 3; i++) {
			if (compareGameBoardContent(board[i][0], board[i][1], board[i][2])) {
				isRowOccupied = true;
				break;
			}
		}
		return isRowOccupied;
	}

	public boolean isAnyColumnOccupiedBySinglePlayer() {
		boolean isColumnOccupied = false;
		for (int i = 0; i < 3; i++) {
			if (compareGameBoardContent(board[0][i], board[1][i], board[2][i])) {
				isColumnOccupied = true;
				break;
			}
		}
		return isColumnOccupied;
	}
	
	public boolean isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer() {
		boolean isDiagonalOccupied = false;
		if (compareGameBoardContent(board[0][0], board[1][1], board[2][2])) {
			isDiagonalOccupied = true;
		}
		return isDiagonalOccupied;
	}
	
	private boolean compareGameBoardContent(char move1, char move2, char move3) {
		return (move1 != EMPTY && move1 == move2 && move2 == move3);
	}
	
}
