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
	
	private static final int POS_0 = 0;
	private static final int POS_1 = 1;
	private static final int POS_2 = 2;
	
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
			if (compareGameBoardContent(board[i][POS_0], board[i][POS_1], board[i][POS_2])) {
				isRowOccupied = true;
				break;
			}
		}
		return isRowOccupied;
	}

	public boolean isAnyColumnOccupiedBySinglePlayer() {
		boolean isColumnOccupied = false;
		for (int i = 0; i < 3; i++) {
			if (compareGameBoardContent(board[POS_0][i], board[POS_1][i], board[POS_2][i])) {
				isColumnOccupied = true;
				break;
			}
		}
		return isColumnOccupied;
	}
	
	public boolean isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer() {
		return compareGameBoardContent(board[POS_0][POS_0], board[POS_1][POS_1], board[POS_2][POS_2]);
	}
	
	public boolean isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer() {
		return compareGameBoardContent(board[POS_0][POS_2], board[POS_1][POS_1], board[POS_2][POS_0]);
	}
	
	private boolean compareGameBoardContent(char move1, char move2, char move3) {
		return (move1 != EMPTY && move1 == move2 && move2 == move3);
	}

	public boolean areAllPositionOnBoardFullyOccupiedByPlayers() {
		boolean isAllPositionOccupied = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == EMPTY) {
					isAllPositionOccupied = false;
					break;
				}
			}
			if (!isAllPositionOccupied) {
				break;
			}
		}
		return isAllPositionOccupied;
	}
	
}
