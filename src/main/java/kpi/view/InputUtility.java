package kpi.view;

import kpi.model.Flat;

import java.util.Random;
import java.util.Scanner;

public class InputUtility {

	private final CalculateView view;
	private final Scanner scanner;
	private final Integer TOTAL_NUMBER_OF_FLATS = 10;

	public InputUtility(CalculateView view) {
		this.view = view;
		scanner = new Scanner(System.in);
	}

	public Flat[] inputValues() {
		view.printMessage(CalculateView.INPUT_DATA);
		int userChoice = scanner.nextInt();
		if (userChoice == CalculateView.ENTER_VALUES_OPTION) {
			return enterValues();
		} else if (userChoice == CalculateView.GENERATE_VALUES_OPTION) {
			return generateValues();
		} else if (userChoice == CalculateView.EXIT_OPTION) {
			return null;
		} else {
			view.printMessage(CalculateView.INCORRECT_INPUT_FORMAT);
			return new Flat[0];
		}
	}

	private Flat[] enterValues() {
		Flat[] flats = new Flat[TOTAL_NUMBER_OF_FLATS];
		System.out.print(CalculateView.INPUT_FIELDS_FOR_FLAT);
		for (int i = 0; i < TOTAL_NUMBER_OF_FLATS; ++i) {
			Flat flat = new Flat();

			Integer number = scanner.nextInt();
			Double square = scanner.nextDouble();
			Integer floor = scanner.nextInt();
			Integer totalRooms = scanner.nextInt();
			String type = scanner.next();
			Integer serviceLife = scanner.nextInt();

			flat.setNumber(number)
				.setSquare(square)
				.setFloor(floor)
				.setTotalRooms(totalRooms)
				.setType(type)
				.setServiceLife(serviceLife);

			flats[i] = flat;
			System.out.println(CalculateView.SUCCESS_MESSAGE);
		}

		System.out.println("[Values was entered successfully]");
		return flats;
	}

	public Flat[] generateValues() {
		Flat[] flats = new Flat[TOTAL_NUMBER_OF_FLATS];
		Random random = new Random();
		for (int i = 0; i < TOTAL_NUMBER_OF_FLATS; ++i) {
			Flat flat = new Flat(
				random.nextInt(100),
				(double) random.nextInt(400) / 10.0,
				random.nextInt(10),
				random.nextInt(5),
				"type" + random.nextInt(5),
				random.nextInt(5)
			);
			flats[i] = flat;
		}

		System.out.println("[Values was generated successfully]");
		return flats;
	}

}