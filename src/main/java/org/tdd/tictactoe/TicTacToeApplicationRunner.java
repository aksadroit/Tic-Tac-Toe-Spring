package org.tdd.tictactoe;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tdd.tictactoe.exception.InvalidUserInputException;
import org.tdd.tictactoe.exception.PositionAlreadyOccupiedException;
import org.tdd.tictactoe.exception.PositionOutOfBoundException;

@Component
public class TicTacToeApplicationRunner implements ApplicationRunner {

	private static final Logger LOGGER = Logger.getLogger(TicTacToeApplicationRunner.class.getName());
	
	@Autowired
	TicTacToeGameExecutor gameExecutor;
	
	@Override
	public void run(ApplicationArguments args) throws InvalidUserInputException, PositionOutOfBoundException, PositionAlreadyOccupiedException {
		try {
			
			String result = gameExecutor.runGame();
			
			LOGGER.info(result);
		} catch (InvalidUserInputException | PositionOutOfBoundException | PositionAlreadyOccupiedException excpetion) {
			LOGGER.severe("Error while playing the game...."+excpetion.getMessage());
		}
		
	}

}
