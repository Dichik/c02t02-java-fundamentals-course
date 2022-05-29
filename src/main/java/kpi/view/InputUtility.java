package kpi.view;

import kpi.model.entities.Flat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InputUtility {

	// rewrite it

	private final Scanner scanner;
	private final Integer TOTAL_NUMBER_OF_FLATS = 10;

	public InputUtility() {
		scanner = new Scanner(System.in);
	}

//	public List<Flat> inputValues() {
//		int userChoice = scanner.nextInt();
//		if (userChoice == CalculateView.ENTER_VALUES_OPTION) {
//			return enterValues();
//		} else if (userChoice == CalculateView.GENERATE_VALUES_OPTION) {
//			return generateValues();
//		} else if (userChoice == CalculateView.EXIT_OPTION) {
//			return null;
//		} else {
//			return new ArrayList<>();
//		}
//	}

	private List<Flat> enterValues() {
		List<Flat> flats = new ArrayList<>();
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

			flats.add(flat);
			System.out.println(CalculateView.SUCCESS_MESSAGE);
		}

		System.out.println("[Values was entered successfully]");
		return flats;
	}

	public List<Flat> generateValues() {
		List<Flat> flats = new ArrayList<>();
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
			flats.add(flat);
		}

		System.out.println("[Values was generated successfully]");
		return flats;
	}

}