package org.tdd.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGame {
	
	@Autowired
	private GameBoard board;
	
	public void play(int row, int column) {
		board.placeMoveOnTheBoard(row, column);
	}
	
	public char identifyPlayerAt(int row, int column) {
		return board.identifyPlayerAt(row, column);
	}

}
