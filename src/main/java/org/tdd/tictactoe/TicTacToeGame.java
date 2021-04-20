package org.tdd.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tdd.tictactoe.model.Position;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGame {
	
	@Autowired
	private GameBoard board;
	
	public void play(Position position) {
		board.placeMoveOnTheBoard(position);
	}
	
	public char identifyPlayerAt(Position position) {
		return board.identifyPlayerAt(position);
	}

}
