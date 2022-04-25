package kpi.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateModel {
	private Flat[] flats;

	public Flat[] getFlats() {
		return flats;
	}

	public void setFlats(Flat[] flats) {
		this.flats = flats;
	}

	public List<Flat> getFlatsWithNRooms(int numberOfRooms) {
		return Arrays.stream(flats)
			.filter(flat -> flat.getTotalRooms() == numberOfRooms)
			.collect(Collectors.toList());
	}

	public List<Flat> getFlatsWithSquare(double minSquare, int minFloor) {
		return Arrays.stream(flats)
			.filter(flat -> flat.getSquare() >= minSquare)
			.filter(flat -> flat.getFloor() >= minFloor)
			.collect(Collectors.toList());
	}

}