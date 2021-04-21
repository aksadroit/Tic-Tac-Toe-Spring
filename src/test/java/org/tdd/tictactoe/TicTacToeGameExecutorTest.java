package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tdd.tictactoe.exception.InvalidUserInputException;

@SpringBootTest
public class TicTacToeGameExecutorTest {

	private static final String INPUT_ONE = "1";
	private static final String INPUT_SPACE = " ";
	
	private static final String INPUT_ZERO_ZERO = "0,0";
	private static final String INVALID_INPUT = "1,";
	
	@Autowired
	TicTacToeGameExecutor gameExecutor;
	
	@Test
	public void shouldReturnTrueIfUserInputAreInvalid() {
		String[] userInputs = {INPUT_ONE, INPUT_SPACE};
		
		assertTrue(gameExecutor.isUserInputInvalid(userInputs));
	}
	
	@Test
	public void shouldThrowExceptionIfUserInputAreInvalid() throws InvalidUserInputException  {
		StringBuilder userInputBuilder = new StringBuilder(INPUT_ZERO_ZERO).append("\n").append(INVALID_INPUT);
		System.setIn(new ByteArrayInputStream(userInputBuilder.toString().getBytes()));
		
		assertThrows(InvalidUserInputException.class, () -> gameExecutor.runGame());
	}
}
