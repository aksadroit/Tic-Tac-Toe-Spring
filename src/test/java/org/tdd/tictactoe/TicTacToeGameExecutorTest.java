package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicTacToeGameExecutorTest {

	@Autowired
	TicTacToeGameExecutor gameExecutor;
	
	@Test
	public void shouldReturnTrueIfUserInputAreInvalid() {
		String[] userInputs = {"1", " "};
		assertTrue(gameExecutor.isUserInputInvalid(userInputs));
	}
}
