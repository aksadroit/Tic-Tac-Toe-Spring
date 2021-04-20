package org.tdd.tictactoe;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.tdd.tictactoe.model.Position;

@Component
public class GameBoard {
	
	private static final int GAME_BOARD_SIZE = 3;
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
		Predicate<Position> positionRangePredicate =  pos -> pos.getRow() >= 0 && pos.getRow() <= 2 && pos.getColumn() >= 0 && pos.getColumn() <= 2;
		return positionRangePredicate.test(validPosition);
	}
	
}
