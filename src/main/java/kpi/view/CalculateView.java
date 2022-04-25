package kpi.view;

import kpi.model.Flat;

public class CalculateView {

	public static final Integer ENTER_VALUES_OPTION = 1;
	public static final Integer GENERATE_VALUES_OPTION = 2;
	public static final Integer EXIT_OPTION = 3;

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

	private Flat[] getFlatsWithNumberOfRooms(Flat[] flats, int numberOfRooms) {
		int countFlats = 0;
		for (Flat value : flats) {
			if (value.getTotalRooms() == numberOfRooms) {
				countFlats++;
			}
		}
		Flat[] result = new Flat[countFlats];
		int index = 0;
		for (Flat flat : flats) {
			if (flat.getTotalRooms() == numberOfRooms) {
				result[index++] = flat;
			}
		}
		return result;
	}

	private Flat[] getFlatsSatisfyingCondition(Flat[] flats, Double square, Integer floor) {
		int countFlats = 0;
		for (Flat flat : flats) {
			if (flat.getSquare() > square && flat.getFloor() > floor) {
				countFlats++;
			}
		}
		Flat[] result = new Flat[countFlats];
		int index = 0;
		for (Flat flat : flats) {
			if (flat.getSquare() > square && flat.getFloor() > floor) {
				result[index++] = flat;
			}
		}
		return result;
	}


	public void printFlats(Flat[] flats) {
		for (int i = 1; i <= flats.length; ++ i) {
			System.out.printf("[%d]", i);
			System.out.println(flats[i - 1]);
		}
		System.out.println("[all flats were printed successfully]");
	}
}