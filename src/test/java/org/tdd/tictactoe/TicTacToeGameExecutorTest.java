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

	@Autowired
	TicTacToeGameExecutor gameExecutor;
	
	@Test
	public void shouldReturnTrueIfUserInputAreInvalid() {
		String[] userInputs = {"1", " "};
		
		assertTrue(gameExecutor.isUserInputInvalid(userInputs));
	}
	
	@Test
	public void shouldThrowExceptionIfUserInputAreInvalid() throws InvalidUserInputException  {
		StringBuilder userInputBuilder = new StringBuilder("0,0").append("\n").append("1,");
		System.setIn(new ByteArrayInputStream(userInputBuilder.toString().getBytes()));
		
		assertThrows(InvalidUserInputException.class, () -> gameExecutor.runGame());
	}
}
