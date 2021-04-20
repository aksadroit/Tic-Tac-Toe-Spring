package org.tdd.tictactoe.exception;

public class PositionAlreadyOccupiedException extends Exception {

	private static final long serialVersionUID = 6985479768430146669L;
	private final String errorMessage;
	
	public PositionAlreadyOccupiedException(String message) {
		super(message);
		this.errorMessage = message;
	}
	
	@Override
	public String toString() {
		return errorMessage;
	}

}
