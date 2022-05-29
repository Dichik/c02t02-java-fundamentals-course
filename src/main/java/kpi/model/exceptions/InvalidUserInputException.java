package kpi.model.exceptions;

public class InvalidUserInputException extends Throwable {

	public InvalidUserInputException() {
		super("Invalid user input.");
	}

	public InvalidUserInputException(String message) {
		super(message);
	}

}