package kpi.model.helpers;

import kpi.model.entities.Flat;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputUtility {
	private static final Integer DEFAULT_TOTAL_NUMBER_OF_FLATS = 10;

	public static List<Flat> generateValues() {
		return generateValues(DEFAULT_TOTAL_NUMBER_OF_FLATS);
	}

	public static List<Flat> generateValues(int numberOfFlats) {
		Random random = new Random();

		return IntStream.range(0, numberOfFlats).mapToObj(i -> new Flat(
			random.nextInt(100),
			(double) random.nextInt(400) / 10.0,
			random.nextInt(10),
			random.nextInt(5),
			"type" + random.nextInt(5),
			random.nextInt(5)
		)).collect(Collectors.toList());
	}

}