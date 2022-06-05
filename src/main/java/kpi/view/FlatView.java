package kpi.view;

import kpi.model.entities.Flat;
import kpi.model.exceptions.InvalidUserInputException;
import kpi.model.helpers.UsersChoice;

import java.util.List;
import java.util.Scanner;

public class FlatView {
	public static final Scanner scanner = new Scanner(System.in);

	public static final String SAVED_SUCCESSFULLY = "All values were saved successfully\n";
	public static final String CURRENT_LIST_OF_FLATS = "Here is your current list of flats.";
	public static final String EMPTY_LIST = "Your list is empty!";
	public static final String FAILED_TO_LOAD_DATA = "Failed to load data.";
	public static final String PROBLEM_WITH_STORAGE = "Something went wrong with storage.";
	public static final String PROBLEM_WITH_PARSING = "Couldn't parse data. Please try to reload your application.";

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printFlats(List<Flat> flats) {
		if(flats == null || flats.isEmpty()) {
			printMessage(EMPTY_LIST);
		} else flats.forEach(System.out::println);
	}

	public void bye() {
		System.out.println("Thanks! Bye!");
		System.out.println("Your data is successfully saved.");
	}

	public UsersChoice getUsersChoice() throws InvalidUserInputException {
		printMessage("Please choose one of the following operations:");
		printAvailableOptions();
		int input = getIntegerInput();
		UsersChoice choice = UsersChoice.getFromIndex(input);
		if(choice == null) {
			throw new InvalidUserInputException();
		}
		return choice;
	}

	private void printAvailableOptions() {
		printMessage("Enter [1] to generate new 10 flats");
		printMessage("Enter [2] to save data");
		printMessage("Enter [3] to print flats");
		printMessage("Enter [4] to get flats with 'n' rooms");
		printMessage("Enter [5] to get flats with min square and min no. floors");
		printMessage("Enter [6] to exit with saving data");
	}

	public double getMinSquare() {
		printMessage("Enter please min square:");
		while(!scanner.hasNextDouble()) {
			printMessage("You should enter double value. Please try again.");
			scanner.next();
		}
		return scanner.nextDouble();
	}

	public int getMinFloor() {
		printMessage("Enter please min floor:");
		return getIntegerInput();
	}

	public int getNumberOfRooms() {
		printMessage("Enter please number of rooms:");
		return getIntegerInput();
	}

	private int getIntegerInput() {
		while(!scanner.hasNextInt()) {
			printMessage("You should enter int value. Please try again.");
			scanner.next();
		}
		return scanner.nextInt();
	}

}