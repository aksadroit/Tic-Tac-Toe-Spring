package org.tdd.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tdd.tictactoe.exception.InvalidUserInputException;
import org.tdd.tictactoe.exception.PositionAlreadyOccupiedException;
import org.tdd.tictactoe.exception.PositionOutOfBoundException;

@SpringBootTest
public class TicTacToeGameExecutorTest {

	private static final String INPUT_ONE = "1";
	private static final String INPUT_SPACE = " ";
	
	private static final String INPUT_ZERO_ZERO = "0,0";
	private static final String INPUT_ONE_ONE = "1,1";
	private static final String INPUT_ZERO_ONE = "0,1";
	private static final String INPUT_TWO_ONE = "2,1";
	private static final String INPUT_ZERO_TWO = "0,2";
	private static final String INVALID_INPUT = "1,";
	private static final String GAME_WINNER = "Winner is Player_X";
	
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
	
	@Test
	public void shouldDeclarePlayerAsWinnerBasedUponUserInputsFromCommandLine() throws InvalidUserInputException, PositionOutOfBoundException, PositionAlreadyOccupiedException {
		StringBuilder userInputBuilder = new StringBuilder(INPUT_ZERO_ZERO).append("\n").append(INPUT_ONE_ONE).append("\n")
				.append(INPUT_ZERO_ONE).append("\n").append(INPUT_TWO_ONE).append("\n").append(INPUT_ZERO_TWO);
		System.setIn(new ByteArrayInputStream(userInputBuilder.toString().getBytes()));

		assertEquals(GAME_WINNER, gameExecutor.runGame());
	}
}
