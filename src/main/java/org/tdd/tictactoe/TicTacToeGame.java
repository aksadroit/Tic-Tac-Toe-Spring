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
	
	@Autowired
	private GameBoard board;
	
	public void play(Position position) throws PositionOutOfBoundException, PositionAlreadyOccupiedException {
		if (!board.isPositionWithinValidRange(position)) {
			throw new PositionOutOfBoundException("Please provide a valid position within a range of 0 to 2");
		}
		
		if (!board.isPositionAvailable(position)) {
			throw new PositionAlreadyOccupiedException(POSITION_OCCUPIED_MESSAGE);
		}
		
		board.placeMoveOnTheBoard(position);
	}
	
	public char identifyPlayerAt(Position position) {
		return board.identifyPlayerAt(position);
	}

}
