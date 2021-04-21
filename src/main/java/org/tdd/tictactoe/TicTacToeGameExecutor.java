package org.tdd.tictactoe;

import java.util.Scanner;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tdd.tictactoe.exception.InvalidUserInputException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGameExecutor {

	public void runGame() throws InvalidUserInputException {
		Scanner scan = new Scanner(System.in);
		do {
			String[] input = scan.nextLine().split(",");
			if (isUserInputInvalid(input)) {
				scan.close();
				throw new InvalidUserInputException("Invalid inputs Passed..!! Please pass the input in the format 1,1");
			}

		} while (scan.hasNext());
		scan.close();
	} 
	
	public boolean isUserInputInvalid(String[] userInputs) {
		boolean isInvalid = true;
		
		if (userInputs.length == 2 
				&& userInputs[0].matches("[ ]*\\d+[ ]*") 
				&& userInputs[1].matches("[ ]*\\d+[ ]*")) {
			isInvalid = false;
		}
		
		return isInvalid;
	}

}
