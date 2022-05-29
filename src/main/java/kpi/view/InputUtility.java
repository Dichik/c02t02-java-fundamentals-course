package kpi.view;

import kpi.controller.Controller;
import kpi.model.entities.Flat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InputUtility {

	private static final Scanner scanner = new Scanner(System.in);
	private static final Integer DEFAULT_TOTAL_NUMBER_OF_FLATS = 10;
	private static final Logger logger = LogManager.getLogger(InputUtility.class);

	public static List<Flat> enterValues() {
		return enterValues(DEFAULT_TOTAL_NUMBER_OF_FLATS);
	}

	public static List<Flat> enterValues(int numberOfFlats) {
		List<Flat> flats = new ArrayList<>();
		System.out.print(CalculateView.INPUT_FIELDS_FOR_FLAT);
		for (int i = 0; i < numberOfFlats; ++i) {
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

		logger.info("[Values was entered successfully]");
		return flats;
	}

	public static List<Flat> generateValues() {
		return generateValues(DEFAULT_TOTAL_NUMBER_OF_FLATS);
	}

	public static List<Flat> generateValues(int numberOfFlats) {
		List<Flat> flats = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < numberOfFlats; ++i) {
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

		logger.info("[Values was generated successfully]");
		return flats;
	}

}