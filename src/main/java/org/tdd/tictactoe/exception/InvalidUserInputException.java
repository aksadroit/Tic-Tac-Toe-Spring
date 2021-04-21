package org.tdd.tictactoe.exception;

public class InvalidUserInputException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final String errorMessage;
	
	public InvalidUserInputException(String message) {
		super(message);
		this.errorMessage = message;
	}
	
	@Override
	public String toString() {
		return errorMessage;
	}
}
