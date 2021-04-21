package org.tdd.tictactoe;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tdd.tictactoe.exception.InvalidUserInputException;
import org.tdd.tictactoe.exception.PositionAlreadyOccupiedException;
import org.tdd.tictactoe.exception.PositionOutOfBoundException;
import org.tdd.tictactoe.model.Position;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGameExecutor {
	
	private static final int USER_INPUT_LENGTH = 2;
	private static final int USER_INPUT_ZERO = 0;
	private static final int USER_INPUT_ONE = 1;
	
	private static final String USER_INPUT_REGEX = "[ ]*\\d+[ ]*";
	private static final String INVALID_USER_INPUT_MESSAGE = "Invalid inputs Passed..!! Please pass the input in the format 1,1";
	private static final String GAME_CONTINUE = "Continue..!!";
	
	@Autowired
	TicTacToeGame game;

	public String runGame() throws InvalidUserInputException, PositionOutOfBoundException, PositionAlreadyOccupiedException {
		String result;
		Scanner scan = new Scanner(System.in);
		do {
			String[] input = scan.nextLine().split(",");
			if (isUserInputInvalid(input)) {
				scan.close();
				throw new InvalidUserInputException(INVALID_USER_INPUT_MESSAGE);
			}
			
			result = game.play(new Position(Integer.parseInt(input[USER_INPUT_ZERO]), Integer.parseInt(input[USER_INPUT_ONE])));

		} while (result.contains(GAME_CONTINUE) && scan.hasNext());
		scan.close();
		return result;
	} 
	
	public boolean isUserInputInvalid(String[] userInputs) {
		boolean isInvalid = true;
		
		if (userInputs.length == USER_INPUT_LENGTH 
				&& userInputs[USER_INPUT_ZERO].matches(USER_INPUT_REGEX) 
				&& userInputs[USER_INPUT_ONE].matches(USER_INPUT_REGEX)) {
			isInvalid = false;
		}
		
		return isInvalid;
	}

}
