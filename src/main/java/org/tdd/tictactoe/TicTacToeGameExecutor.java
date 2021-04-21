package org.tdd.tictactoe;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicTacToeGameExecutor {

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
