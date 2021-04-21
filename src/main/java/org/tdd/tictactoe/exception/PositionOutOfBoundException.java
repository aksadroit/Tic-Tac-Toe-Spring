package org.tdd.tictactoe.exception;

public class PositionOutOfBoundException extends Exception {

	private static final long serialVersionUID = -2005240612427390436L;
	private final String errorMessage;
	
	public PositionOutOfBoundException(String message) {
		super(message);
		this.errorMessage = message;
	}

}
