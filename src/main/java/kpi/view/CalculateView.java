package kpi.view;

import kpi.model.entities.Flat;
import kpi.model.entities.UsersChoice;
import kpi.model.exceptions.InvalidUserInputException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class CalculateView {

	public static final Scanner scanner = new Scanner(System.in);
	public static final Logger logger = LogManager.getLogger(CalculateView.class);

	public static final String INCORRECT_INPUT_FORMAT = "Sorry, you entered wrong value," +
		" please try again\n";
	public static final String RESOURCES_PREFIX = "src/main/resources/";

	public static final String INPUT_FIELDS_FOR_FLAT =
		"\nPlease input flats data in the next format:\n" +
			"\nNumber\nSquareFloor\nTotal Rooms\nType\nService Life\n";

	public static final String SUCCESS_MESSAGE = "Value was created successfully\n";
	public static final String SAVED_SUCCESSFULLY = "All values were saved successfully\n";

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
		System.out.println("Please enter Yes / No in case you want to continue...");
		String userInput = scanner.nextLine();
		if (isUserInputValid(userInput)) {
			return userInput.equals("yes");
		} else throw new InvalidUserInputException();
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
		printAvailableOptions();
		String inputString = scanner.nextLine();
		try {
			int input = Integer.parseInt(inputString);
			UsersChoice choice = UsersChoice.getFromIndex(input);
			if(choice == null) {
				throw new InvalidUserInputException();
			}
			return choice;
		} catch (Exception e) {
			System.err.println("Note: you can enter only valid integer. Please try again.");
		}
		return null;
	}

	private void printAvailableOptions() {
		printMessage("Enter [1] to generate new 10 flats");
		printMessage("Enter [2] to enter data for 'n' flats");
		printMessage("Enter [3] to save data");
		printMessage("Enter [4] to print list of flats");
		printMessage("Enter [5] to get flats with 'n' rooms");
		printMessage("Enter [6] to get flats with min square and min no. floors");
		printMessage("Enter [7] to exit with saving data");
	}
	public File getFile() {
		printMessage("Type the name of the file to work with and press enter.");
		printMessage("Please do not enter extension for the file. (Default: .json)");
		printMessage("Example: main_data");
		printMessage("You can find you file in src/main/resources");
		String fileName = scanner.nextLine();
		return new File(RESOURCES_PREFIX + fileName + ".json");
	}

}