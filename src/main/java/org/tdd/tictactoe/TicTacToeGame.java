package org.tdd.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tdd.tictactoe.exception.PositionAlreadyOccupiedException;
import org.tdd.tictactoe.exception.PositionOutOfBoundException;
import org.tdd.tictactoe.model.Position;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGame {
	
	private static final String POSITION_OCCUPIED_MESSAGE = "Position is already occupied by other player. Please choose a different position.";
	private static final String POSITION_OUT_OF_RANGE_MESSAGE = "Please provide a valid position within a range of 0 to 2";
	
	private static final String GAME_CONTINUE = "Continue..!!";
	private static final String GAME_WINNER = "Winner is Player_";
	
	@Autowired
	private GameBoard board;
	
	public String play(Position position) throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		String result = null;
		if (!board.isPositionWithinValidRange(position)) {
			throw new PositionOutOfBoundException(POSITION_OUT_OF_RANGE_MESSAGE);
		}
		
		if (!board.isPositionAvailable(position)) {
			throw new PositionAlreadyOccupiedException(POSITION_OCCUPIED_MESSAGE);
		}
		
		board.placeMoveOnTheBoard(position);
		
		if (checkPlayerWinningConiditions()) {
			result = GAME_WINNER+board.identifyPlayerAt(position);
		} else if (!board.areAllPositionOnBoardFullyOccupiedByPlayers()) {
			result = GAME_CONTINUE;
		}
		return result;
	}

	private boolean checkPlayerWinningConiditions() {
		return board.isAnyRowOccupiedBySinglePlayer() 
				|| board.isAnyColumnOccupiedBySinglePlayer() 
				|| board.isTopRightToBottomLeftDiagonalOccupiedBySinglePlayer() 
				|| board.isTopLeftToBottomRightDiagonalOccupiedBySinglePlayer();
	}
	
	public char identifyPlayerAt(Position position) {
		return board.identifyPlayerAt(position);
	}

}
