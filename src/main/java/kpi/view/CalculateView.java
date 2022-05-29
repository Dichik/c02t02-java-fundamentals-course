package kpi.view;

import kpi.model.entities.Flat;
import kpi.model.entities.UsersChoice;
import kpi.model.exceptions.InvalidUserInputException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class CalculateView {

	public static final Integer ENTER_VALUES_OPTION = 1;
	public static final Integer GENERATE_VALUES_OPTION = 2;
	public static final Integer EXIT_OPTION = 3;
	public static final Scanner scanner = new Scanner(System.in);
	public static final InputUtility inputUtility = new InputUtility();
	public static final Logger logger = LogManager.getLogger(CalculateView.class);

	public static final String INPUT_DATA = "Enter 10 values by\n"
		+ ENTER_VALUES_OPTION + " - enter values\n"
		+ GENERATE_VALUES_OPTION + " - generate values\n"
		+ EXIT_OPTION + " - exit\n";
	public static final String INCORRECT_INPUT_FORMAT = "Sorry, you entered wrong value," +
		" please try again\n";

	public static final String INPUT_FIELDS_FOR_FLAT =
		"\nPlease input flats data in the next format:\n" +
			"\nNumber\nSquareFloor\nTotal Rooms\nType\nService Life\n";

	public static final String SUCCESS_MESSAGE = "Value was created successfully\n";

	// ... functions

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printFlats(List<Flat> flats) {
		for (int i = 1; i <= flats.size(); ++ i) {
			System.out.printf("[%d]", i);
			System.out.println(flats.get(i - 1));
		}
		logger.debug("All flats were printed successfully!");
	}

	public boolean isPerformingAnotherOperation() throws InvalidUserInputException {
		while(true) {
			System.out.println("Please enter Yes[Y] / No[N] in case you want to continue...");
			String userInput = scanner.nextLine();
			if(isUserInputValid(userInput)) {
				return userInput.equals("yes");
			} else throw new InvalidUserInputException();
		}
	}

	public void bye() {
		System.out.println("Thanks! Bye!");
		System.out.println("Your data is successfully saved.");
	}

	private boolean isUserInputValid(String input) {
		input = input.toLowerCase();
		return input.equals("yes") || input.equals("no");
	}

	public UsersChoice getUsersChoice() {
		System.out.println("Please choose one of the following operations:");
		System.out.println(INPUT_DATA);
		String inputString = scanner.nextLine();
		try {
			int input = Integer.parseInt(inputString);
			List<Flat> flats;
			switch (input) {
//				case GENERATE_VALUES_OPTION -> {
//					return
//				}
//				default -> {
//
//				}
			}
		} catch (Exception e) {
			System.err.println("Note: you can enter only integer. Please try again.");
		}
		return null;
	}
}